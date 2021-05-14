package com.wis.mom.template.facade;

import com.wis.mom.template.application.SysUserApplication;
import com.wis.template.business.domain.entity.SysUser;
import com.wis.template.infrastructure.general.common.bean.RspBase;
import com.wis.template.infrastructure.persistent.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 权限用户信息
 *
 * @author wzengheng
 * @since 2021/05/12
 */
@RestController
@RequestMapping("/wis/user")
public class SysUserContorller {


    @Autowired
    SysUserService sysUserApplication1;

    @Autowired
    SysUserApplication sysUserApplication;


    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    @ResponseBody
    public RspBase list(SysUser user)
    {
//        return RspBase.success(sysUserApplication.getList(user));
        return RspBase.success(sysUserApplication1.selectUserList(null, user));
    }


}
