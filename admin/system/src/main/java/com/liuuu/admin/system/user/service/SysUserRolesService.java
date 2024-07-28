package com.liuuu.admin.system.user.service;

import com.liuuu.admin.system.user.po.SysUserRole;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 系统用户角色
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
public interface SysUserRolesService extends BaseService<SysUserRole> {
    /**
     * 通过角色id获取用户ids
     * @param roleId
     * @return
     */
    List<Long> getUserIdsByRoleId(Long roleId);

    /**
     * 批量插入角色和用户关系
     * @param roleId
     * @param userIds
     */
    void saveBatch(Long roleId, List<Long> userIds);

    /**
     * 删除
     * @param roleId
     * @param userIds
     */
    void remove(Long roleId, List<Long> userIds);

    /**
     * 是否关联用户
     * @param roleIds
     * @return
     */
    boolean isAssociateUser(List<Long> roleIds);
}
