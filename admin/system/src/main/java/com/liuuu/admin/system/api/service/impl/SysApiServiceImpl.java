package com.liuuu.admin.system.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.api.mapper.SysApiMapper;
import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.po.SysApiMenu;
import com.liuuu.admin.system.api.service.SysApiMenuService;
import com.liuuu.admin.system.api.service.SysApiService;
import com.liuuu.admin.system.api.vo.SysApiVO;
import com.liuuu.admin.system.menu.vo.SysMenuAuthApiVO;
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.framework.mybatis.plugin.query.LambdaQueryWrapperPlus;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 接口
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Service
public class SysApiServiceImpl extends BaseServiceImpl<SysApiMapper, SysApi> implements SysApiService {
    @Autowired
    private SysApiMapper sysApiMapper;

    @Autowired
    private SysApiMenuService sysApiMenuService;

    /**
     * 通过菜单id列表获取接口
     */
    @Override
    public List<SysApiVO> getApiByMenuIds(List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return new ArrayList<SysApiVO>();
        }
        return sysApiMapper.getApiByMenuIds(menuIds, CommonStatus.NORMAL.code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        sysApiMapper.deleteBatchIds(Arrays.asList(ids));
        LambdaQueryWrapper<SysApiMenu> amWrapper = new LambdaQueryWrapperPlus<SysApiMenu>();
        amWrapper.in(SysApiMenu::getApiId, ids);
        sysApiMenuService.remove(amWrapper);
    }

    @Override
    public Integer getMaxSortByCategoryId(Long apiCategoryId) {
        return sysApiMapper.selectMaxSortByCategoryId(apiCategoryId);
    }

    /**
     * 分配api
     * @param menuId
     * @param apiIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authApi(Long menuId, List<Long> apiIds) {
        if (menuId == null) {
            return;
        }
        LambdaQueryWrapper<SysApiMenu> apiMenuWrapper = new LambdaQueryWrapper<>();
        apiMenuWrapper.eq(SysApiMenu::getMenuId, menuId);
        sysApiMenuService.remove(apiMenuWrapper);

        if (CollectionUtils.isEmpty(apiIds)) {
            return;
        }

        List<SysApiMenu> apiMenus = new ArrayList<>();
        for (Long apiId : apiIds) {
            SysApiMenu sysApiMenu = new SysApiMenu();
            sysApiMenu.setMenuId(menuId);
            sysApiMenu.setApiId(apiId);
            apiMenus.add(sysApiMenu);
        }
        sysApiMenuService.saveBatch(apiMenus);
    }

    @Override
    public List<SysMenuAuthApiVO> getAuthApiByMenuId(Long menuId) {
        return sysApiMapper.selectAuthApiByMenuId(menuId);
    }
}
