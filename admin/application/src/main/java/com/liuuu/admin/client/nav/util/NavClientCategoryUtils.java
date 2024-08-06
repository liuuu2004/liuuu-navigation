package com.liuuu.admin.client.nav.util;

import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.client.nav.vo.NavCategorySiteClientVO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 客户端分类工具类
 *
 * @Author Liuuu
 * @Date 2024/8/6
 */
public class NavClientCategoryUtils {
    /**
     * 构建最后一级分类
     * @param categoryList  分类数据
     * @param lastLevelCategoryList  最后一集数据
     */
    public static void buildLastLevelCategoryList(List<NavCategoryClientVO> categoryList,
                                                  List<NavCategoryClientVO> lastLevelCategoryList) {
        categoryList.stream().forEach(category -> {
            if (CollectionUtils.isEmpty(category.getChildren())) {
                lastLevelCategoryList.add(category);
            } else {
                buildLastLevelCategoryList(category.getChildren(), lastLevelCategoryList);
            }
        });
    }

    /**
     * 根据父节点的id获取所有子节点
     * @param allList  所有数据
     * @param parentId  传入的父节点id
     * @return
     */
    public static List<NavCategoryClientVO> buildTree(List<NavCategoryClientVO> allList, Long parentId) {
        List<NavCategoryClientVO> returnList = new ArrayList<>();
        for (NavCategoryClientVO category : allList) {
            if (parentId != null && parentId.equals(category.getParentId())) {
                recursive(allList, category);
                returnList.add(category);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * @param allList
     * @param category  当前分类
     */
    private static void recursive(List<NavCategoryClientVO> allList, NavCategoryClientVO category) {
        List<NavCategoryClientVO> childList = getChildList(allList, category);
        category.setChildren(childList);
        for (NavCategoryClientVO tChild : childList) {
            if (hasChild(allList, tChild)) {
                recursive(allList, tChild);
            }
        }
    }

    private static boolean hasChild(List<NavCategoryClientVO> allList, NavCategoryClientVO category) {
        return getChildList(allList, category).size() > 0;
    }

    private static List<NavCategoryClientVO> getChildList(List<NavCategoryClientVO> allList,
                                                              NavCategoryClientVO category) {
        List<NavCategoryClientVO> childList = new ArrayList<>();
        Iterator<NavCategoryClientVO> it = allList.iterator();
        while (it.hasNext()) {
            NavCategoryClientVO child = it.next();
            if (category.getId().equals(child.getParentId())) {
                childList.add(child);
            }
        }
        return childList;
    }
}
