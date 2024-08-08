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
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.core.enums.YesNo;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.framework.manager.AsyncManager;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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


}
