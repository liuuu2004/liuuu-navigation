package com.liuuu.admin.system.api.service;

import com.liuuu.admin.system.api.vo.SysApiCategoryVO;

import java.util.List;

/**
 * 系统接口分类
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
public interface SysApiCategoryService {
    List<SysApiCategoryVO> listSort();

    void removeByIds(Long[] ids);

    Integer getMaxSort();
}
