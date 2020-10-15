package com.bajiaostar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bajiaostar.mapper.UserDetailMapper;
import com.bajiaostar.domain.UserDetail;
import com.bajiaostar.service.IUserDetailService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后勤详情 服务层实现
 *
 * @author gu
 * @date 2020-08-14
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements IUserDetailService
{
    @Autowired
    private ISysUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUser user)
    {
        //新增用户数据
        int rows =  userService.insertUser(user);
        //新增详情数据
        UserDetail userDetail=new UserDetail();
//        userDetail.setCreateBy(user.getCreateBy());
//        userDetail.setCreateTime(DateUtils.getNowDate());
        userDetail.setUserId(user.getUserId());
        this.saveOrUpdate(userDetail);
        return rows;
    }

}