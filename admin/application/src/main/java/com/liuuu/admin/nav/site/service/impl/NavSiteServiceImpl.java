package com.liuuu.admin.nav.site.service.impl;


import com.liuuu.admin.nav.site.mapper.NavSiteMapper;
import com.liuuu.admin.nav.site.po.NavSite;
import com.liuuu.admin.nav.site.service.NavSiteService;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导航网站
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Service
public class NavSiteServiceImpl extends BaseServiceImpl<NavSiteMapper, NavSite> implements NavSiteService {
    private final Logger logger = LoggerFactory.getLogger(NavSiteServiceImpl.class);

    @Autowired
    private NavSiteMapper navSiteMapper;

    // TODO
}
