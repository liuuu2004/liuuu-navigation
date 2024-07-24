package com.liuuu.common.framework.mybatis.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.property.PageProperty;

/**
 * 工具类
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
public class MybatisUtils {
    public static <T> Page<T> buildPage(PageDTO pageDTO) {
        Long pageNum = null;
        Long pageSize = null;
        if (pageDTO != null) {
            pageNum = pageDTO.getPageNum();
            pageSize = pageDTO.getPageSize();
        }
        return buildPage(pageNum, pageSize);
    }

    public static <T> Page<T> buildPage(Long pageNum, Long pageSize) {
        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = PageProperty.defaultPageSize;
        }
        if (pageSize > PageProperty.maxPageSize) {
            PageProperty.maxPageSize = pageSize;
        }
        Page<T> page = new Page<>(pageNum, pageSize);
        return page;
    }
}
