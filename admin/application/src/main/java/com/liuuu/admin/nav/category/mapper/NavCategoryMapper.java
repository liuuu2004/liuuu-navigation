package com.liuuu.admin.nav.category.mapper;

import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 导航分类
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Mapper
public interface NavCategoryMapper extends BaseMapperPlus<NavCategory> {
    /**
     * 获取最大排序
     * @param parentId
     * @return
     */
    Integer selectMaxSortByParentId(Long parentId);
}
