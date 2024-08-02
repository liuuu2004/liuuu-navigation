package com.liuuu.admin.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.role.dto.*;
import com.liuuu.admin.system.role.mapper.SysRoleMapper;
import com.liuuu.admin.system.role.mapstruct.SysRoleConverter;
import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.role.service.SysRoleMenuService;
import com.liuuu.admin.system.role.service.SysRoleService;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.admin.system.user.dto.SysUserPageDTO;
import com.liuuu.admin.system.user.po.SysUserRole;
import com.liuuu.admin.system.user.service.SysUserRoleService;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统角色
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 通过用户id获取角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleVO> getRoleByUserId(Long userId) {
        return sysRoleMapper.getRoleByUserIds(Arrays.asList(userId), CommonStatus.NORMAL.code);
    }

    /**
     * 通过用户ids获取角色
     * @param userIds
     * @return
     */
    @Override
    public List<SysRoleVO> getRoleByUserIds(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        return sysRoleMapper.getRoleByUserIds(userIds, CommonStatus.NORMAL.code);
    }

    /**
     * 新增角色
     * @param addDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleAddDTO addDTO) {
        // 判断角色是否存在
        if (isExistRoleCode(addDTO.getRoleCode(), null)) {
            throw new ParamException(MessageUtils.message("role.code.exist"));
        }
        // 插入角色信息
        SysRole sysRole = SysRoleConverter.INSTANCE.convert(addDTO);
        sysRoleMapper.insert(sysRole);

        // 插入角色和菜单信息
        sysRoleMenuService.saveBatch(sysRole.getId(), addDTO.getMenuIds());
    }

    /**
     * 更新角色信息
     * @param updateDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleUpdateDTO updateDTO) {
        Long roleId = updateDTO.getId();

        // 判断是否为管理员
        if (isSuperAdminRoleByRoleIds(Arrays.asList(roleId))) {
            throw new ParamException(MessageUtils.message("role.super.admin.not.operation"));
        }

        // 判断角色编码是否存在
        if (!isExistRoleCode(updateDTO.getRoleCode(), roleId)) {
            throw new ParamException(MessageUtils.message("role.code.exist"));
        }

        // 更新角色信息
        SysRole sysRole = SysRoleConverter.INSTANCE.convert(updateDTO);
        sysRoleMapper.updateById(sysRole);

        // 删除角色和菜单关系
        sysRoleMenuService.deleteByRoleId(roleId);

        // 插入新的角色和菜单关系
        sysRoleMenuService.saveBatch(roleId, updateDTO.getMenuIds());
    }

    /**
     * 删除角色
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        List<Long> idList = Arrays.asList(ids);
        // 判断是否为超级管理员
        if (isSuperAdminByRoleIds(idList)) {
            throw new ParamException(MessageUtils.message("role.super.admin.not.operation"));
        }

        // 判断角色是否有关联用户
        if (sysUserRoleService.isAssociateUser(idList)) {
            throw new ParamException(MessageUtils.message("role.is.associate.user"))
        }

        // 删除角色
        sysRoleMapper.deleteBatchIds(idList);

        // 删除角色与用户对应关系
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysUserRole::getRoleId, ids);
        sysUserRoleService.removeByIds(idList);
    }

    /**
     * 获取最大排序
     * @return
     */
    @Override
    public Integer getMaxSort() {
        return sysRoleMapper.selectMaxSort();
    }

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    @Override
    public SysRoleVO getRoleById(Long id) {
        SysRoleVO sysRoleVO = SysRoleConverter.INSTANCE.convert(sysRoleMapper.selectById(id));
        if (sysRoleVO != null) {
            sysRoleVO.setMenuIds(sysRoleMenuService.getMenuIdsByRoleId(id));
        }
        return sysRoleVO;
    }

    /**
     * 已分配用户分页
     * @param pageDTO
     * @return
     */
    @Override
    public PageVO<SysUserVO> getUserAuthPage(SysRoleAuthUserPageDTO pageDTO) {
        // 获取已分配的用户
        List<Long> userIds = sysUserRoleService.getUserIdsByRoleId(pageDTO.getRoleId());

        // 如果获取的用户id为空说明没有关联用户则直接返回
        if (CollectionUtils.isEmpty(userIds)) {
            return new PageVO<>();
        }

        // 查询用户数据
        SysUserPageDTO sysUserPageDTO = SysRoleConverter.INSTANCE.convert(pageDTO);
        sysUserPageDTO.setIds(userIds);
        return sysUserService.pageList(sysUserPageDTO);
    }

    /**
     * 未分配用户分页
     * @param pageDTO
     * @return
     */
    @Override
    public PageVO<SysUserVO> getUserUnAuthPage(SysRoleNotAuthUserPageDTO pageDTO) {
        // 获取已分配的用户
        List<Long> userIds = sysUserRoleService.getUserIdsByRoleId(pageDTO.getRoleId());

        // 查询用户数据
        SysUserPageDTO sysUserPageDTO = SysRoleConverter.INSTANCE.convert(pageDTO);
        sysUserPageDTO.setNotIds(userIds);
        return sysUserService.pageList(sysUserPageDTO);
    }

    /**
     * 授权用户
     * @param authUserDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authUser(SysRoleAuthUserDTO authUserDTO) {
        Long roleId = authUserDTO.getRoleId();

        // 先判断角色是否存在
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getId, roleId);
        if (sysRoleMapper.selectCount(wrapper) == 0) {
            throw new ParamException(MessageUtils.message("role.not.exist"));
        }

        // 过滤不存在的用户id
        List<Long> userIds = sysUserService.filterNotExistUserIds(authUserDTO.getUserIds());
        if (CollectionUtils.isEmpty(userIds)) {
            throw new ParamException(MessageUtils.message("role.auth.user.not.exit"));
        }

        // 插入角色和用户直接的关系
        sysUserRoleService.saveBatch(roleId, userIds);
    }
}
