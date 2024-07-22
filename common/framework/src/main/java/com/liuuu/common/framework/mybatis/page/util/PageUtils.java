package com.liuuu.common.framework.mybatis.page.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.mybatis.property.PageProperty;

import java.util.List;

/**
 * 分页工具类
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
public class PageUtils {
    /**
     * 开始分页
     * @param pageDTO
     */
    public static void startPage(PageDTO pageDTO) {
        startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
    }

    /**
     * 开始分页
     * @param pageNum  起始页
     * @param pageSize  分页记录数
     */
    public static void startPage(Long pageNum, Long pageSize) {
        if (pageNum == null) {
            pageNum = 1L;
        }
        if (pageSize == null) {
            pageSize = 1L;
        }
        if (pageSize > PageProperty.maxPageSize) {
            pageSize = PageProperty.maxPageSize;
        }
        PageHelper.startPage(pageNum.intValue(), pageSize.intValue());
    }

    /**
     * 获取分页信息
     * @param list
     * @return
     * @param <T>
     */
    public static <T> PageVO<T> getPage(List<T> list) {
        PageInfo pageInfo = new PageInfo(list);
        return new PageVO(list, pageInfo.getTotal());
    }
}
