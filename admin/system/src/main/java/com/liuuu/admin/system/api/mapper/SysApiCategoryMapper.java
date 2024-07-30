package com.liuuu.admin.system.api.mapper;

import com.liuuu.admin.system.api.po.SysApiCategory;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统接口分类
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Mapper
public interface SysApiCategoryMapper extends BaseMapperPlus<SysApiCategory> {
    /**
     * 获取最大排序
     * @return
     */
    Integer selectMaxSort();
}
