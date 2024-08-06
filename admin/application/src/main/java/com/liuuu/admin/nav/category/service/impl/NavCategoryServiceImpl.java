package com.liuuu.admin.nav.category.service.impl;

import com.liuuu.admin.nav.category.mapper.NavCategoryMapper;
import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.admin.nav.category.service.NavCategoryService;
import com.liuuu.admin.nav.site.mapper.NavSiteMapper;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导航分类
 *
 * @AUthor Liuuu
 * @Date 2024/8/6
 */
@Service
public class NavCategoryServiceImpl extends BaseServiceImpl<NavCategoryMapper, NavCategory> implements NavCategoryService {
    @Autowired
    private NavCategoryMapper navCategoryMapper;

    @Autowired
    private NavSiteMapper navSiteMapper;

    // TODO
    @Autowired
    private ClientNavService
}
