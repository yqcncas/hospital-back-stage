package com.ruoyi.common.sms.qcloud;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.sms.ISMSService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯云短信
 *
 * @author gu
 */
@Service(value = "qcloud")
public class QCloudSmsServiceImpl implements ISMSService {

    // 短信应用 SDK AppID
    @Value("${sms.qcloud.appid}")
    private int APP_ID; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    @Value("${sms.qcloud.appkey}")
    private String APP_KEY;
    @Value("${sms.qcloud.smsSign}")
    private String smsSign;
    @Value("${sms.qcloud.templateId}")
    private int templateId; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请

    @Autowired
    private RedisCache redisUtils;

    @Override
    public String sendCode(String mobile, Integer length) throws Exception {
        String randomCode = getRandomCode(length==null||length==0?4:length);
        SmsSingleSenderResult results = null;
        Map<String, Object> resultmap = new HashMap<>();
        SmsSingleSender ssender = new SmsSingleSender(APP_ID, APP_KEY);
        results = ssender.sendWithParam("86", mobile, Integer.valueOf(templateId),new String[]{randomCode} , smsSign,"", "");
        if (results!=null&&results.result != 0) {
            throw  new Exception("短信发送失败，原因:" + results.errMsg);
        }
        System.out.println(randomCode);
        redisUtils.setCacheObject(RedisCache.CHECK_CODE+mobile,randomCode,120, TimeUnit.SECONDS);
        //return results.errMsg;
        return randomCode;
    }

    @Override
    public String sendGroupMsg(String[] mobiles, String[] msg) throws Exception {
        return "失败";
    }

    /**
     *校验手机验证码 验证码错误时返回true
     */
    @Override
    public  boolean checkSmsCode(String mobile, String checkCode){
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
