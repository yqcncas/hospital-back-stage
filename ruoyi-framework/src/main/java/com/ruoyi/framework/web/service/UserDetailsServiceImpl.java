package com.ruoyi.framework.web.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.sms.ISMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    @Qualifier(value = "sms")
    private ISMSService smsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        JSONObject jsonObject = JSONObject.parseObject(username);
        SysUser user = null;
        if (jsonObject.getString("type").equals("0")){
            //loginName
           user = userService.selectUserByUserName(jsonObject.getString("username"));
        }else if (jsonObject.getString("type").equals("1")){
            //phonenumber获取

           //TODO 手机验证码直接在此处验证
            if (smsService.checkSmsCode(jsonObject.getString("username"), jsonObject.getString("credentials"))){
                log.info("登录用户：{} 验证码错误.", jsonObject.getString("username"));
                throw new UsernameNotFoundException("登录用户：" + jsonObject.getString("username") + " 验证码错误");
            }
            redisCache.deleteObject(jsonObject.getString("username"));

            user = userService.selectUserByMobile(jsonObject.getString("username"));

        }else{
           user = userService.selectUserByUserName(username);
        }

        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", jsonObject.getString("username"));
            throw new UsernameNotFoundException("登录用户：" + jsonObject.getString("username") + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", jsonObject.getString("username"));
            throw new BaseException("对不起，您的账号：" + jsonObject.getString("username") + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", jsonObject.getString("username"));
            throw new BaseException("对不起，您的账号：" + jsonObject.getString("username") + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
