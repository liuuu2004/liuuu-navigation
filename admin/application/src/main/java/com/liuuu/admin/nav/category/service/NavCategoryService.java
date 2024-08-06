package com.liuuu.admin.nav.category.service;

import com.liuuu.admin.nav.category.dto.NavCategoryUpdateDTO;
import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.admin.nav.category.vo.NavCategoryTreeVO;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 导航分类
 *
 * @Author Liuuu
 * @Date 2024/8/6
 */
public interface NavCategoryService extends BaseService<NavCategory> {
    /**
     * 获取最大排序
     * @param parentId
     * @return
     */
    Integer getMaxSortByParentId(Long parentId);

    /**
     * 树形
     * @return
     */
    List<NavCategoryTreeVO> tree();

    /**
     * 树形
     * @param ids
     * @return
     */
    List<NavCategoryTreeVO> treeByIds(List<Long> ids);

    /**
     * 更新
     * @param updateDTO
     */
    void updateById(NavCategoryUpdateDTO updateDTO);

    /**
     * 删除
     * @param id
     */
    void remove(Long id);

}
