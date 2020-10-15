package com.ruoyi.common.core.domain.model;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
public class MobileCodeBody
{
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

    private Integer length;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = (length==null?4:length);
    }
}
