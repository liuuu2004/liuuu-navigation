package com.liuuu.admin.system.user.service.impl;

import com.liuuu.admin.system.role.service.SysRoleService;
import com.liuuu.admin.system.user.mapper.SysUserMapper;
import com.liuuu.admin.system.user.po.SysUser;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    // TODO SysRoleService
    @Autowired
    private SysRoleService


}
