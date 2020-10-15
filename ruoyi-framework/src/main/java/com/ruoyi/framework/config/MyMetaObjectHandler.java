package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("start insert fill ....");
        this.setFieldValByName("createBy", SecurityUtils.getUsername(),metaObject);
        this.setFieldValByName("createTime", DateUtils.getNowDate(),metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUsername(),metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("delFlag", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("start update fill ....");
        this.setFieldValByName("updateBy", SecurityUtils.getUsername(),metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);
    }
}
