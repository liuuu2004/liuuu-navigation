package com.liuuu.admin.client.nav.util;

import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.client.nav.vo.NavCategorySiteClientVO;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 导航客户端工具类
 *
 * @Author Liuuu
 * @Date 2024/8/6
 */
public class NavClientUtils {
    /**
     * 过滤没有网站的分类
     * @param categoryList
     * @param siteList
     */
    public static void filterCategoryNotSite(List<NavCategoryClientVO> categoryList,
                                             List<NavCategorySiteClientVO> siteList) {
        if (CollectionUtils.isEmpty(categoryList) || CollectionUtils.isEmpty(siteList)) {
            return;
        }
        int depth = 0;
        for (NavCategoryClientVO category : categoryList) {
            int childDepth = getCategoryDepth(category, 0);
            depth = childDepth > depth ? childDepth : depth;
        }
        for (int i = 1; i <= depth; ++i) {
            filterNoSite(categoryList, siteList);
        }
    }

    /**
     * 获取分类深度
     * @param category
     * @param depth
     * @return
     */
    private static int getCategoryDepth(NavCategoryClientVO category, int depth) {
        if (CollectionUtils.isEmpty(category.getChildren())) {
            ++depth;
        }
        int deepChildren = depth;
        for (NavCategoryClientVO children : category.getChildren()) {
            int child = getCategoryDepth(category, depth + 1);
            deepChildren = child > deepChildren ? child : deepChildren;
        }
        return deepChildren;
    }

    private static void filterNoSite(List<NavCategoryClientVO> categoryList,
                                     List<NavCategorySiteClientVO> siteList) {
        for (int t = 0; t < categoryList.size(); ++t) {
            NavCategoryClientVO category = categoryList.get(t);
            if (CollectionUtils.isEmpty(category.getChildren())) {
                NavCategorySiteClientVO site = siteList.parallelStream().filter(s ->
                        category.getId().equals(s.getId())).findFirst().orElse(null);
                if (site != null) {
                    categoryList.remove(t--);
                }
            } else {
                filterNoSite(category.getChildren(), siteList);
            }
        }
    }

}
