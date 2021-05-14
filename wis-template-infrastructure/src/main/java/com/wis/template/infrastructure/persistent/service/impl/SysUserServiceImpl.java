package com.wis.template.infrastructure.persistent.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wis.template.business.domain.entity.SysUser;
import com.wis.template.infrastructure.persistent.dao.SysUserMapper;
import com.wis.template.infrastructure.persistent.model.pojo.User;
import com.wis.template.infrastructure.persistent.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, User> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

//    @Override
//    public List<SysUser> selectUserList(Page page, SysUser user) {
//        return sysUserMapper.selectUserList(page, user);
//    }

    @Override
    public List<SysUser> getList() {
        return sysUserMapper.getList();
    }
}
