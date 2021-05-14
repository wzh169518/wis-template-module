package com.wis.template.infrastructure.persistent.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wis.template.business.domain.entity.SysUser;
import com.wis.template.infrastructure.persistent.model.pojo.User;

import java.util.List;

/**
 * 权限用户 业务层
 *
 * @author wzengheng
 * @since 2021/05/12
 */
public interface SysUserService extends IService<User> {
//    public List<SysUser> selectUserList(Page page, SysUser user);
    public List<SysUser> getList();

}
