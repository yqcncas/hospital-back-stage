package com.bajiaostar.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;
import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 后勤详情对象 b_user_detail
 * 
 * @author gu
 * @date 2020-08-14
 */
public class UserDetailVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 版本号 */
    private Long version;

    /** 备注 */
    private String remark;

    /** 逻辑删标识(0 未删 1 已删) */
    private Integer delFlag;

    /** 积分 */
    private Integer integral;

    /** 用户工作状态(0离线 1空闲 2工作中) */
    private Integer jobStatus;

    /** 用户id */
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCreateBy(String createBy) 
    {
        this.createBy = createBy;
    }

    public String getCreateBy() 
    {
        return createBy;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateBy(String updateBy) 
    {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() 
    {
        return updateBy;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setRemark(String remark) 
    {
        this.remark = remark;
    }

    public String getRemark() 
    {
        return remark;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
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
