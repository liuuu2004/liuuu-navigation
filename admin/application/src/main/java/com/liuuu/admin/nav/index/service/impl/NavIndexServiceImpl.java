package com.liuuu.admin.nav.index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.nav.category.mapper.NavCategoryMapper;
import com.liuuu.admin.nav.index.service.NavIndexService;
import com.liuuu.admin.nav.index.vo.NavIndexStatisticsVO;
import com.liuuu.admin.nav.site.mapper.NavSiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台首页
 *
 * @Author Liuuu
 * @Date 2024/8/11
 */
@Service
public class NavIndexServiceImpl implements NavIndexService {
    @Autowired
    private NavCategoryMapper navCategoryMapper;

    @Autowired
    private NavSiteMapper navSiteMapper;

    /**
     * 获取统计数据
     * @return
     */
    @Override
    public NavIndexStatisticsVO getStatistics() {
        NavIndexStatisticsVO navIndexStaticsVO = new NavIndexStatisticsVO();

        navIndexStaticsVO.setCategoryCount(navCategoryMapper.selectCount(new LambdaQueryWrapper<>()));

        navIndexStaticsVO.setSiteCount(navSiteMapper.selectCount(new LambdaQueryWrapper<>()));

        navIndexStaticsVO.setSiteClickCount(navSiteMapper.selectClickCount());

        return navIndexStaticsVO;
    }
}
