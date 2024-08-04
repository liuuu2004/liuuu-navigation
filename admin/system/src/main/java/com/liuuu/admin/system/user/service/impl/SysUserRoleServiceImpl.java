package com.liuuu.admin.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.user.mapper.SysUserRoleMapper;
import com.liuuu.admin.system.user.po.SysUserRole;
import com.liuuu.admin.system.user.service.SysUserRoleService;
import com.liuuu.common.core.enums.DelFlag;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户角色
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过角色id获取用户id
     * @param roleId
     * @return
     */
    @Override
    public List<Long> getUserIdsByRoleId(Long roleId) {
        if (roleId == null) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getRoleId, roleId);
        wrapper.select(SysUserRole::getId, SysUserRole::getUserId);
        List<SysUserRole> list = sysUserRoleMapper.selectList(wrapper);
        List<Long> userIds = list.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
        return userIds;
    }

    /**
     * 批量插入角色与用户关系
     * @param roleId
     * @param userIds
     * @return
     */
    @Override
    public void saveBatch(Long roleId, List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }
        List<SysUserRole> list = new ArrayList<>();
        for (Long userId : userIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            list.add(sysUserRole);
        }
        super.saveBatch(list);
    }

    /**
     * 删除
     * @param roleId
     * @param userIds
     */
    @Override
    public void remove(Long roleId, List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getRoleId, roleId);
        wrapper.in(SysUserRole::getUserId, userIds);
        sysUserRoleMapper.delete(wrapper);
    }

    /**
     * 是否关联用户
     * @param roleIds
     * @return
     */
    @Override
    public boolean isAssociateUser(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return false;
        }
        return sysUserRoleMapper.selectAssociateNotDeleteUserCount(roleIds, DelFlag.NOT_DELETED.code) > 0;
    }
}
