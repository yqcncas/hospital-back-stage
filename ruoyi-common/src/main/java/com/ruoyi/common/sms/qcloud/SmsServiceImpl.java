package com.ruoyi.common.sms.qcloud;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.sms.ISMSService;
import com.ruoyi.common.utils.MD5;
import com.ruoyi.common.utils.SSLClient;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rsa.RSAUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service(value = "sms")
public class SmsServiceImpl implements ISMSService {

    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    //接口地址
    @Value("${sms.sms.url}")
    public  String url ;
    //用户ID
    @Value("${sms.sms.userid}")
    public  String userid;
    //用户账号名
    @Value("${sms.sms.account}")
    public  String account;
    //接口密码
    @Value("${sms.sms.password}")
    public  String password;

    @Autowired
    private RedisCache redisUtils;

    String sendTime="";
    //扩展号，没有请留空
    String extno="";



    public String sendSingleMsg(String mobile,String msg) throws Exception {

        return "成功";
    }

    @Override
    public String sendGroupMsg(String[] mobiles, String[] msg) throws Exception {
        //多个手机号用逗号分隔
        String mobile= StringUtils.join(mobiles,",");
        String text = "";
        return sendMsg(mobile, text);

    }

    private String sendMsg(String mobile, String text) throws IOException {
        List list = new ArrayList();
        list.add(new BasicNameValuePair("action","send"));
        list.add(new BasicNameValuePair("userid", userid));
        list.add(new BasicNameValuePair("account", account));
        list.add(new BasicNameValuePair("password", MD5.GetMD5Code(password)));
        list.add(new BasicNameValuePair("mobile", mobile));
        list.add(new BasicNameValuePair("content", text));
        list.add(new BasicNameValuePair("sendTime", sendTime));
        list.add(new BasicNameValuePair("extno", extno));

        CloseableHttpClient httpclient = SSLClient.createSSLClientDefault();
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
        HttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String returnString= EntityUtils.toString(entity);
        System.out.println(returnString);
        JSONObject jsonObject = JSONObject.parseObject(returnString);
        EntityUtils.consume(entity);
        if("Success".equals(jsonObject.get("returnstatus"))){
            log.info("发送短信成功",jsonObject.get("message").toString());
            return jsonObject.get("message").toString();
        }else{
            return "发送失败";
        }
    }


    @Override
    public String sendCode(String mobile, Integer length) throws Exception {

        PrivateKey privateKey = RSAUtil.getPrivateKey(ISMSService.privateKey);
        String phone = RSAUtil.decryptString(privateKey, mobile);
        String randomCode = getRandomCode(length == null ? 4 : 6);
        String text = "【后勤管理】尊敬的客户您好，您当前验证码为"+randomCode;
        if (StringUtils.isNotBlank(phone)&& phone.length()==11) {
            System.out.println("手机号:" + phone + ",短信验证码:" + randomCode);
          //  String result = sendMsg(phone, text);
         //   if (!result.equals("发送失败")) {
                redisUtils.setCacheObject(RedisCache.CHECK_CODE+phone, randomCode,120, TimeUnit.SECONDS);
                return "发送成功";
          //  } else {
          //      return "发送失败";
         //   }
        }else{
            System.out.println("手机号:" + mobile + ",短信验证码:" + randomCode);
            redisUtils.setCacheObject(RedisCache.CHECK_CODE+phone, randomCode,120, TimeUnit.SECONDS);
            return randomCode;
        }
    }

    @Override
    public boolean checkSmsCode(String mobile, String checkCode) {
        //通过缓存获取手机验证码
        String code = redisUtils.getCacheObject(RedisCache.CHECK_CODE+mobile);
        if (StringUtils.isEmpty(code)){
            return true;
        }
        //不相等时返回错误
        if (!code.equals(checkCode)){
            return true;
        }
        redisUtils.deleteObject(RedisCache.CHECK_CODE+mobile);
        return false;
    }

    //获取随机验证码
    @Override
    public String getRandomCode(int length) {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }



}
