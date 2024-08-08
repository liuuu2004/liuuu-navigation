package com.liuuu.admin.client.nav.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.client.nav.constant.NavClientConstant;
import com.liuuu.admin.client.nav.mapstruct.ClientNavConverter;
import com.liuuu.admin.client.nav.service.ClientNavService;
import com.liuuu.admin.client.nav.util.NavClientCategoryUtils;
import com.liuuu.admin.client.nav.util.NavClientUtils;
import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.client.nav.vo.NavCategorySiteClientVO;
import com.liuuu.admin.client.nav.vo.NavClientListVO;
import com.liuuu.admin.nav.category.mapper.NavCategoryMapper;
import com.liuuu.admin.nav.category.mapstruct.NavCategoryConverter;
import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.admin.nav.site.mapper.NavSiteMapper;
import com.liuuu.admin.nav.site.mapstruct.NavSiteConverter;
import com.liuuu.admin.nav.site.po.NavSite;
import com.liuuu.admin.nav.site.vo.NavSiteClientVO;
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.navigation.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 导航工具类
 *
 * @Author Liuuu
 * @Date 2024/8/6
 */
@Service
public class ClientNavServiceImpl implements ClientNavService {
    @Autowired
    private NavCategoryMapper navCategoryMapper;

    @Autowired
    private NavSiteMapper navSiteMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 分类网络列表
     * @return
     */
    @Override
    public NavClientListVO categorySiteList() {
        List<NavCategoryClientVO> categories = redisService.get(NavClientConstant.CATEGORY_CACHE_PREFIX);
        if (categories == null) {
            // 从数据库获取
            return buildCategoryAndSiteList();
        }
        // 从缓存获取分类网站数据
        List<NavCategorySiteClientVO> siteList = redisService.get(NavClientConstant.SITE_CACHE_PREFIX);
        if (siteList == null) {
            return buildCategoryAndSiteList();
        }
        // 分类数据和分类网站数据都是从缓存获取
        return NavClientListVO.builder().categories(categories).sites(siteList).build();
    }

    /**
     * 分类列表
     * @return
     */
    @Override
    public List<NavCategoryClientVO> categoryList() {
        // 从缓存获取分类数据
        List<NavCategoryClientVO> categories = redisService.get(NavClientConstant.CATEGORY_CACHE_PREFIX);
        if (CollectionUtils.isEmpty(categories)) {
            categories = new ArrayList<>();
            List<NavCategorySiteClientVO> siteList = new ArrayList<>();
        }
        return categories;
    }

    /**
     * 清除缓存
     */
    @Override
    public void removeCache() {
        redisService.delete(NavClientConstant.CATEGORY_CACHE_PREFIX);
        redisService.delete(NavClientConstant.SITE_CACHE_PREFIX);
    }

    /**
     * 站内分类网站搜素列表
     * @param searchContent
     * @return
     */
    @Override
    public NavClientListVO categorySiteSearchList(String searchContent) {
        NavClientListVO clientListVO = new NavClientListVO();
        if (StrUtils.isBlank(searchContent)) {
            return clientListVO;
        }
        // 查询网站数据
        LambdaQueryWrapper<NavSite> siteWrapper = new LambdaQueryWrapper<>();
        siteWrapper.eq(NavSite::getStatus, CommonStatus.NORMAL.code);
        siteWrapper.and(wrapper -> {
            wrapper.or().like(NavSite::getSiteName, searchContent);
            wrapper.or().like(NavSite::getSiteDescription, searchContent);
        });
        siteWrapper.orderByAsc(NavSite::getCategoryId, NavSite::getSort);
        siteWrapper.select(NavSite::getId, NavSite::getCategoryId, NavSite::getSiteName, NavSite::getSitePath,
                NavSite::getSiteDescription, NavSite::getSiteUrl);
        List<NavSite> siteSearchList = navSiteMapper.selectList(siteWrapper);
        if (CollectionUtils.isEmpty(siteSearchList)) {
            return clientListVO;
        }

        // 通过分类ids获取所有上级分类
        Set<Long> categoryIds = siteSearchList.stream().map(NavSite::getCategoryId).collect(Collectors.toSet());
        List<NavCategory> navCategoryList = new ArrayList<>();
        getCategoryParent(categoryIds, navCategoryList);

        // 构建树形结构
        List<NavCategoryClientVO> categories = NavClientCategoryUtils.buildTree(ClientNavConverter.INSTANCE.converterCategory(navCategoryList), 0L);

        // 获取最后一级分类
        List<NavCategoryClientVO> lastLevelCategoryList = new ArrayList<>();
        NavClientCategoryUtils.buildLastLevelCategoryList(categories, lastLevelCategoryList);
        if (CollectionUtils.isEmpty(lastLevelCategoryList)) {
            return clientListVO;
        }

        List<NavSiteClientVO> siteList = NavSiteConverter.INSTANCE.convertClient(siteSearchList);

        // 构建分类下的网站
        List<NavCategorySiteClientVO> categorySiteList = NavCategoryConverter.INSTANCE.convertSiteClient(lastLevelCategoryList);
        categorySiteList.parallelStream().forEach(category -> {
            List<NavSiteClientVO> siteClientList = siteList.stream().filter(
                    site -> category.getId().equals(site.getCategoryId())).collect(Collectors.toList());
            category.setSites(siteClientList);
        });

        // 过滤没有网站的分类网站
        categorySiteList = categorySiteList.stream().filter(item -> !CollectionUtils.isEmpty(item.getSites())).collect(Collectors.toList());

        // 过滤没有网站的分类
        NavClientUtils.filterCategoryNotSite(categories, categorySiteList);

        clientListVO.setCategories(categories);
        clientListVO.setSites(categorySiteList);
        return clientListVO;
    }

    /**
     * 通过分类ids获取所有上级分类
     * @param categoryIds
     * @param categoryList
     */
    private void getCategoryParent(Set<Long> categoryIds, List<NavCategory> categoryList) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return;
        }
        // 获取分类
        LambdaQueryWrapper<NavCategory> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.in(NavCategory::getId, categoryIds);
        categoryWrapper.eq(NavCategory::getStatus, CommonStatus.NORMAL.code);
        categoryWrapper.orderByAsc(NavCategory::getSort);
        List<NavCategory> childrenList = navCategoryMapper.selectList(categoryWrapper);
        if (CollectionUtils.isEmpty(childrenList)) {
            return;
        }
        categoryList.addAll(childrenList);
        categoryIds = childrenList.stream().filter(category ->
                category.getParentId() != null && category.getParentId() > 0).map(NavCategory::getParentId).collect(Collectors.toSet());
        // 递归获取父分类
        getCategoryParent(categoryIds, categoryList);
    }

    /**
     * 构建分类和网站数据
     * @return
     */
    private NavClientListVO buildCategoryAndSiteList() {
        // 获取分类列表并构建树形结构
        LambdaQueryWrapper<NavCategory> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.eq(NavCategory::getStatus, CommonStatus.NORMAL.code);
        categoryWrapper.orderByAsc(NavCategory::getSort);

        // 构建树形结构
        List<NavCategoryClientVO> categories = NavClientCategoryUtils.buildTree(
                ClientNavConverter.INSTANCE.converterCategory(navCategoryMapper.selectList(categoryWrapper)),
                0L);

        // 后去最后一级分类
        List<NavCategoryClientVO> lastLevelCategoryList = new ArrayList<>();
        NavClientCategoryUtils.buildLastLevelCategoryList(categories, lastLevelCategoryList);

        // 最后一级为空则更新缓存为空
        if (CollectionUtils.isEmpty(lastLevelCategoryList)) {
            setCategoryCache(new ArrayList<>());
            setSiteCache(new ArrayList<>());
            return NavClientListVO.builder().categories(new ArrayList<>()).sites(new ArrayList<>()).build();
        }

        // 获取最后一级分类所有id
        Set<Long> categoryIds = lastLevelCategoryList.parallelStream().map(c -> c.getId()).collect(Collectors.toSet());

        // 获取网站数据
        LambdaQueryWrapper<NavSite> siteWrapper = new LambdaQueryWrapper<>();
        siteWrapper.in(NavSite::getCategoryId, categories);
        siteWrapper.eq(NavSite::getStatus, CommonStatus.NORMAL.code);
        siteWrapper.orderByAsc(NavSite::getCategoryId, NavSite::getSort);
        siteWrapper.select(NavSite::getId, NavSite::getCategoryId, NavSite::getSiteName, NavSite::getSitePath,
                NavSite::getSiteDescription, NavSite::getSiteUrl);
        List<NavSiteClientVO> siteList = NavSiteConverter.INSTANCE.convertClient(navSiteMapper.selectList(siteWrapper));

        // 构建分类下的网站
        List<NavCategorySiteClientVO> categorySiteList = NavCategoryConverter.INSTANCE.convertSiteClient(lastLevelCategoryList);
        categorySiteList.parallelStream().forEach(category -> {
            List<NavSiteClientVO> siteClientList = siteList.stream().filter(
                    site -> category.getId().equals(site.getCategoryId())
            ).collect(Collectors.toList());
        });

        // 过滤没有网站的分类网站
        categorySiteList = categorySiteList.stream().filter(item ->
                !CollectionUtils.isEmpty(item.getSites())).collect(Collectors.toList());

        // 过滤没有网站的分类
        NavClientUtils.filterCategoryNotSite(categories, categorySiteList);

        // 存入缓存
        setCategoryCache(categories);
        setSiteCache(categorySiteList);

        NavClientListVO clientListVO = NavClientListVO.builder().categories(categories).sites(categorySiteList).build();
        return clientListVO;
    }

    /**
     * 设置分类缓存
     * @param list
     */
    private void setCategoryCache(List<NavCategoryClientVO> list) {
        redisService.set(NavClientConstant.CATEGORY_CACHE_PREFIX, list, 7, TimeUnit.DAYS);
    }

    /**
     * 设置网站缓存
     * @param list
     */
    private void setSiteCache(List<NavCategorySiteClientVO> list) {
        redisService.set(NavClientConstant.SITE_CACHE_PREFIX, list, 7, TimeUnit.DAYS);
    }

}
