package com.wis.template.infrastructure.persistent.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wis.template.business.domain.entity.SysUser;
import com.wis.template.infrastructure.persistent.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<User> {

//    List<SysUser> selectUserList(Page page, SysUser user);
    List<SysUser> getList();
}
