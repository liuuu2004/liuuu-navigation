package com.liuuu.admin.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.role.mapper.SysRoleMenuMapper;
import com.liuuu.admin.system.role.po.SysRoleMenu;
import com.liuuu.admin.system.role.service.SysRoleMenuService;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色菜单
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 批量插入
     * @param roleId
     * @param menuIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatch(Long roleId, List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        List<SysRoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            roleMenus.add(sysRoleMenu);
        }
        super.saveBatch(roleMenus);
    }

    /**
     * 通过角色id获取菜单ids
     * @param roleId
     * @return
     */
    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        if (roleId == null) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, roleId);
        wrapper.select(SysRoleMenu::getMenuId);
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(wrapper);
        Set<Long> menuIds = roleMenus.stream().map(rm -> rm.getMenuId()).collect(Collectors.toSet());
        return new ArrayList<>(menuIds);
    }

    /**
     * 删除角色和菜单关系
     * @param roleId
     */
    @Override
    public void deleteByRoleId(Long roleId) {
        if (roleId == null) {
            return;
        }
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, roleId);
        sysRoleMenuMapper.delete(wrapper);
    }
}
