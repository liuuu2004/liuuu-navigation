package com.liuuu.admin.client.nav.service;

import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.client.nav.vo.NavClientListVO;

import java.util.List;

/**
 * 导航客户端
 *
 * @Author Liuuu
 * @Date 2024/8/6
 */
public interface ClientNavService {
    /**
     * 分类网站列表
     * @return
     */
    NavClientListVO categorySiteList();

    /**
     * 分类列表
     * @return
     */
    List<NavCategoryClientVO> categoryList();

    /**
     * 清除缓存
     */
    void removeCache();

    /**
     * 站内分类网站搜索列表
     * @param searchContent
     * @return
     */
    NavClientListVO categorySiteSearchList(String searchContent);

}
