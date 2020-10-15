package com.bajiaostar.domain;

import com.baomidou.mybatisplus.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import com.ruoyi.common.core.domain.PlusBaseEntity;

/**
 * 后勤详情对象 b_user_detail
 * 
 * @author gu
 * @date 2020-08-14
 */
@TableName("b_user_detail")
public class UserDetail extends PlusBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 积分 */
    @Excel(name = "积分")
    private Integer integral;

    /** 用户工作状态(0离线 1空闲 2工作中) */
    @Excel(name = "用户工作状态(0离线 1空闲 2工作中)")
    private Integer jobStatus;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIntegral(Integer integral) 
    {
        this.integral = integral;
    }

    public Integer getIntegral() 
    {
        return integral;
    }
    public void setJobStatus(Integer jobStatus) 
    {
        this.jobStatus = jobStatus;
    }

    public Integer getJobStatus() 
    {
        return jobStatus;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("version", getVersion())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("integral", getIntegral())
            .append("jobStatus", getJobStatus())
            .append("userId", getUserId())
            .toString();
    }
}
