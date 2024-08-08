package com.liuuu.admin.nav.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.client.nav.service.ClientNavService;
import com.liuuu.admin.nav.category.dto.NavCategoryUpdateDTO;
import com.liuuu.admin.nav.category.mapper.NavCategoryMapper;
import com.liuuu.admin.nav.category.mapstruct.NavCategoryConverter;
import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.admin.nav.category.service.NavCategoryService;
import com.liuuu.admin.nav.category.vo.NavCategoryTreeVO;
import com.liuuu.admin.nav.site.mapper.NavSiteMapper;
import com.liuuu.admin.nav.site.po.NavSite;
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.core.enums.YesNo;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.framework.manager.AsyncManager;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 导航分类
 *
 * @AUthor Liuuu
 * @Date 2024/8/6
 */
@Service
public class NavCategoryServiceImpl extends BaseServiceImpl<NavCategoryMapper, NavCategory> implements NavCategoryService {
    @Autowired
    private NavCategoryMapper navCategoryMapper;

    @Autowired
    private NavSiteMapper navSiteMapper;

    @Autowired
    private ClientNavService clientNavService;

    @Override
    public Integer getMaxSortByParentId(Long parentId) {
        return navCategoryMapper.selectMaxSortByParentId(parentId);
    }

    @Override
    public List<NavCategoryTreeVO> tree() {
        LambdaQueryWrapper<NavCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NavCategory::getStatus, YesNo.YES.code);
        wrapper.orderByAsc(NavCategory::getSort);
        List<NavCategory> list = navCategoryMapper.selectList(wrapper);
        List<NavCategoryTreeVO> categoryVOList = NavCategoryConverter.INSTANCE.convertTree(list);

        return buildTree(categoryVOList, 0L);
    }

    @Override
    public List<NavCategoryTreeVO> treeByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<NavCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(NavCategory::getId, ids);
        wrapper.eq(NavCategory::getStatus, CommonStatus.NORMAL.code);
        List<NavCategory> list = navCategoryMapper.selectList(wrapper);
        List<NavCategoryTreeVO> categoryTreeVOS = NavCategoryConverter.INSTANCE.convertTree(list);
        return buildTree(categoryTreeVOS, 0L);
    }

    @Override
    public void updateById(NavCategoryUpdateDTO updateDTO) {
        if (updateDTO.getId().equals(updateDTO.getParentId())) {
            throw new ParamException(MessageUtils.message("nav.category.id.parent.id"));
        }
        NavCategory navCategory = NavCategoryConverter.INSTANCE.convert(updateDTO);
        navCategoryMapper.updateById(navCategory);
        AsyncManager.me().execute(() -> clientNavService.removeCache());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        if (id == null) {
            return;
        }
        // 判断是否有下级分类,若有则不能删除
        LambdaQueryWrapper<NavCategory> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.eq(NavCategory::getParentId, id);
        Long cnt = navCategoryMapper.selectCount(categoryWrapper);
        if (cnt > 0) {
            throw new ParamException(MessageUtils.message("nav.category.has.child"));
        }

        // 删除分类数据
        navCategoryMapper.deleteById(id);

        // 删除分类和网站对应关系
        LambdaQueryWrapper<NavSite> siteWrapper = new LambdaQueryWrapper<>();
        siteWrapper.eq(NavSite::getCategoryId, id);
        navSiteMapper.delete(siteWrapper);

        // 清除缓存
        AsyncManager.me().execute(() -> clientNavService.removeCache());
    }

    public List<NavCategoryTreeVO> buildTree(List<NavCategoryTreeVO> allList, Long parentId) {
        List<NavCategoryTreeVO> returnList = new ArrayList<>();
        for (NavCategoryTreeVO category : allList) {
            // 根据传入的某个父节点id遍历该父节点的所有父节点
            if (parentId != null && parentId.equals(category.getParentId())) {
                recursive(allList, category);
                returnList.add(category);
            }
        }
        return returnList;
    }

    private void recursive(List<NavCategoryTreeVO> allList, NavCategoryTreeVO category) {
        List<NavCategoryTreeVO> childList = getChildList(allList, category);
        category.setChildren(childList);
        for (NavCategoryTreeVO tChild : childList) {
            if (hasChild(allList, tChild)) {
                recursive(allList, tChild);
            }
        }
    }

    private boolean hasChild(List<NavCategoryTreeVO> allList, NavCategoryTreeVO category) {
        return getChildList(allList, category).size() > 0;
    }

    private List<NavCategoryTreeVO> getChildList(List<NavCategoryTreeVO> allList, NavCategoryTreeVO category) {
        List<NavCategoryTreeVO> childList = new ArrayList<>();
        Iterator<NavCategoryTreeVO> it = allList.iterator();
        while (it.hasNext()) {
            NavCategoryTreeVO child = it.next();
            if (category.getId().equals(child.getParentId())) {
                childList.add(child);
            }
        }
        return childList;
    }


}
