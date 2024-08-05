package com.liuuu.admin.nav.site.mapper;

import com.liuuu.admin.client.search.vo.NavClientSiteSearchVO;
import com.liuuu.admin.nav.site.dto.NavSiteListUserImportDTO;
import com.liuuu.admin.nav.site.po.NavSite;
import com.liuuu.admin.nav.site.vo.NavSiteVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 导航网站
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Mapper
public interface NavSiteMapper extends BaseMapperPlus<NavSite> {
    /**
     * 获取最大排序
     * @param categoryId
     * @return
     */
    Integer selectMaxSortByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 更新点击量
     * @param id
     */
    void updateClickCountById(@Param("id") Long id);

    /**
     * 查询列表
     * @param userImportDTO
     * @return
     */
    List<NavSiteVO> selectListByUserImport(NavSiteListUserImportDTO userImportDTO);

    /**
     * 站内网站搜索
     * @param searchContent
     * @param status
     * @return
     */
    List<NavClientSiteSearchVO> selectSiteSearchList(@Param("searchContent") String searchContent,
                                                     @Param("status") Integer status);

    /**
     * 获取点击量
     * @return
     */
    Long selectClickCount();

}
