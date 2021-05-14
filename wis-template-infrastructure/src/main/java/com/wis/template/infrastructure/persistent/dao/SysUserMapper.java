package com.wis.template.infrastructure.persistent.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wis.template.business.domain.entity.SysUser;
import com.wis.template.infrastructure.persistent.model.pojo.User;

import java.util.List;

public interface SysUserMapper extends BaseMapper<User> {

    List<SysUser> selectUserList(Page page, SysUser user);
}
