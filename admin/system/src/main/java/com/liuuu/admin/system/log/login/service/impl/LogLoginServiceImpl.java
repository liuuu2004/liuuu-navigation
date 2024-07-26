package com.liuuu.admin.system.log.login.service.impl;

import com.liuuu.admin.system.log.login.mapper.LogLoginMapper;
import com.liuuu.admin.system.log.login.po.LogLogin;
import com.liuuu.admin.system.log.login.service.LogLoginService;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogLoginServiceImpl extends BaseServiceImpl<LogLoginMapper, LogLogin> implements LogLoginService {
    @Autowired
    private LogLoginMapper logLoginMapper;
}
