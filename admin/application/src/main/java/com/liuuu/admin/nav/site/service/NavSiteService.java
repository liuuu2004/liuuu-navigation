package com.liuuu.admin.nav.site.service;

import com.liuuu.admin.client.search.vo.NavClientSiteSearchVO;
import com.liuuu.admin.nav.site.dto.NavSiteAddDTO;
import com.liuuu.admin.nav.site.dto.NavSitePageDTO;
import com.liuuu.admin.nav.site.dto.NavSiteUpdateDTO;
import com.liuuu.admin.nav.site.po.NavSite;
import com.liuuu.admin.nav.site.vo.NavSiteVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 导航网站
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
public interface NavSiteService extends BaseService<NavSite> {
    /**
     * 分页列表
     * @param pageDTO
     * @return
     */
    PageVO<NavSiteVO> pageList(NavSitePageDTO pageDTO);

    /**
     * 新增
     * @param addDTO
     */
    void save(NavSiteAddDTO addDTO);

    /**
     * 修改
     * @param updateDTO
     */
    void updateById(NavSiteUpdateDTO updateDTO);

    /**
     * 删除
     * @param ids
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     * @param categoryId
     * @return
     */
    Integer getMaxSortByCategoryId(Long categoryId);

    /**
     * 更新点击量
     * @param id
     */
    void updateClickCountById(Long id);

    /**
     * 站内网站搜索
     * @param searchContent
     * @return
     */
    List<NavClientSiteSearchVO> siteSearch(String searchContent);

}
