package com.liuuu.admin.system.role.service;

import com.liuuu.admin.system.role.dto.*;
import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 系统角色
 *
 * @AUthor Liuuu
 * @Date 2024/8/2
 */
public interface SysRoleService extends BaseService<SysRole> {
    /**
     * 通过用户id获取角色
     * @param userId
     */
    List<SysRoleVO> getRoleByUserId(Long userId);

    /**
     * 通过用户ids获取角色
     * @param userIds
     */
    List<SysRoleVO> getRoleByUserIds(List<Long> userIds);

    /**
     * 新增角色
     */
    void add(SysRoleAddDTO addDTO);

    /**
     * 修改角色
     */
    void update(SysRoleUpdateDTO updateDTO);

    /**
     * 删除角色
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     */
    Integer getMaxSort();

    /**
     * 获取角色信息
     * @param id
     */
    SysRoleVO getRoleById(Long id);

    /**
     * 已分配用户分页
     */
    PageVO<SysUserVO> getUserAuthPage(SysRoleAuthUserPageDTO pageDTO);

    /**
     * 未分配用户分页
     */
    PageVO<SysUserVO> getUserUnAuthPage(SysRoleNotAuthUserPageDTO pageDTO);

    /**
     * 授权用户
     */
    void authUser(SysRoleAuthUserDTO authUserDTO);

    /**
     * 取消授权
     */
    void removeAuthUser(SysRoleAuthUserDeleteDTO deleteDTO);
}
