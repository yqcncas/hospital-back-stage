package com.bajiaostar.service;

import com.bajiaostar.domain.UserDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 后勤详情 服务层
 *
 * @author gu
 * @date 2020-08-14
 */
public interface IUserDetailService extends IService<UserDetail>
{

    int insertUser(SysUser user);
}