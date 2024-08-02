package com.liuuu.admin.system.role.service;

import com.liuuu.admin.system.role.po.SysRoleMenu;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 角色菜单
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {
    /**
     * 批量插入
     * @param roleId
     * @param menuIds
     */
    void saveBatch(Long roleId, List<Long> menuIds);

    /**
     * 通过角色id获取菜单ids
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 删除角色和菜单关系
     * @param roleId
     */
    void deleteByRoleId(Long roleId);
}
