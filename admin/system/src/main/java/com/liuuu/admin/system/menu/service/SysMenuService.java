package com.liuuu.admin.system.menu.service;

import com.liuuu.admin.system.menu.dto.SysMenuAuthApiDTO;
import com.liuuu.admin.system.menu.dto.SysMenuListDTO;
import com.liuuu.admin.system.menu.po.SysMenu;
import com.liuuu.admin.system.menu.vo.RouterVO;
import com.liuuu.admin.system.menu.vo.SysMenuVO;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
public interface SysMenuService extends BaseService<SysMenu> {
    /**
     * 通过角色ids获取菜单
     * @param roleIds
     * @return
     */
    List<SysMenuVO> getMenuByRoleIds(Set<Long> roleIds);

    /**
     * 获取菜单路由
     * @return
     */
    List<RouterVO> getRouters();

    /**
     * 获取菜单列表
     * @param sysMenuListDTO
     * @return
     */
    List<SysMenuVO> getMenuList(SysMenuListDTO sysMenuListDTO);

    /**
     * 菜单树形
     * @return
     */
    List<SysMenuVO> getMenuTree();

    /**
     * 获取最大排序
     * @param parentId
     * @return
     */
    Integer getMaxSortByParentId(Long parentId);

    /**
     * 删除
     * @param id
     */
    void remove(Long id);

    /**
     * 分配api
     * @param authApiDTO
     */
    void authApi(SysMenuAuthApiDTO authApiDTO);

}
