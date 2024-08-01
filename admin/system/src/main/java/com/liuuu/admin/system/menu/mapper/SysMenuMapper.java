package com.liuuu.admin.system.menu.mapper;

import com.liuuu.admin.system.menu.po.SysMenu;
import com.liuuu.admin.system.menu.vo.SysMenuVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysMenuMapper extends BaseMapperPlus<SysMenu> {
    /**
     * 通过角色ids获取菜单
     * @param roleIds
     * @param menuTypes
     * @param menuStatus
     * @return
     */
    List<SysMenuVO> getMenuByRoleIds(@Param("roleIds") Set<Long> roleIds,
                                     @Param("menuTypes") List<String> menuTypes,
                                     @Param("menuStatus") Integer menuStatus);

    /**
     * 通过用户id获取菜单
     * @param userId
     * @param menuStatus
     * @param roleStatus
     * @param menuHasPermission
     * @param menuTypes
     * @return
     */
    List<SysMenuVO> getMenuByUserId(@Param("userId") Long userId,
                                    @Param("menuStatus") Integer menuStatus,
                                    @Param("roleStatus") Integer roleStatus,
                                    @Param("menuHasPermission") Integer menuHasPermission,
                                    @Param("menuTypes") List<String> menuTypes);

    /**
     * 获取最大排序
     * @param parentId
     * @return
     */
    Integer selectMaxSortByParentId(Long parentId);

}
