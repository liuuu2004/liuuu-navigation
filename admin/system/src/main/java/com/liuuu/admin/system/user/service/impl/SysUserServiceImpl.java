package com.liuuu.admin.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuuu.admin.system.api.mapstruct.SysApiConverter;
import com.liuuu.admin.system.api.service.SysApiService;
import com.liuuu.admin.system.api.vo.SysApiVO;
import com.liuuu.admin.system.menu.service.SysMenuService;
import com.liuuu.admin.system.menu.vo.SysMenuVO;
import com.liuuu.admin.system.role.service.SysRoleService;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.admin.system.user.dto.SysUserAddDTO;
import com.liuuu.admin.system.user.dto.SysUserPageDTO;
import com.liuuu.admin.system.user.dto.SysUserResetPasswordDTO;
import com.liuuu.admin.system.user.dto.SysUserResetUserPasswordDTO;
import com.liuuu.admin.system.user.mapper.SysUserMapper;
import com.liuuu.admin.system.user.mapstruct.SysUserConverter;
import com.liuuu.admin.system.user.po.SysUser;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.core.enums.DelFlag;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import com.liuuu.framework.security.constant.SecurityConstant;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.service.TokenService;
import com.liuuu.framework.security.util.SecurityUtils;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysApiService sysApiService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取登录用户
     * @param username
     * @return
     */
    @Override
    public LoginUserDetail getLoginUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser == null) {
            log.info("用户: {} 不存在", username);
            return null;
        }
        LoginUserDetail loginUserDetail = SysUserConverter.INSTANCE.convertDetail(sysUser);
        return loginUserDetail;
    }

    /**
     * 设置登录成功后的用户权限
     * @param loginUserDetail
     */
    @Override
    public void setLoginUserPermission(LoginUserDetail loginUserDetail) {
        // 获取角色
        List<SysRoleVO> roles = sysRoleService.getRoleByUserId(loginUserDetail.getUserId());
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }

        // 获取角色id和编码
        Set<Long> roleIds = new HashSet<>();
        Set<String> roleCodes = new HashSet<>();
        for (SysRoleVO role : roles) {
            roleIds.add(role.getId());
            roleCodes.add(role.getRoleCode());
            if (SecurityConstant.SUPER_ADMIN_ROLE_CODE.equals(role.getRoleCode())) {
                loginUserDetail.setHasSuperAdmin(true);
                Set<String> permissionCodes = new HashSet<>();
                permissionCodes.add(SecurityConstant.ALL_PERMISSION_CODE);
                loginUserDetail.setPermissionCodes(permissionCodes);
            }
        }
        loginUserDetail.setRoleCodes(roleCodes);

        // 若为超级管理员则不用获取权限标识和接口地址
        if (loginUserDetail.isHasSuperAdmin()) {
            return;
        }

        // 获取权限标识和接口地址
        List<SysMenuVO> menus = sysMenuService.getMenuByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(menus)) {
            return;
        }

        // 设置权限标识
        Set<String> permissionCodes = menus.stream()
                .filter(m -> StrUtils.isNotBlank(m.getPermissionCode()))
                .map(m -> m.getPermissionCode())
                .collect(Collectors.toSet());
        loginUserDetail.setPermissionCodes(permissionCodes);

        // 设置接口地址
        List<Long> menuIds = menus.stream().map(m -> m.getId()).collect(Collectors.toList());
        List<SysApiVO> apis = sysApiService.getApiByMenuIds(menuIds);
        loginUserDetail.setApiPermissions(SysApiConverter.INSTANCE.convertList(apis));

    }

    /**
     * 通过用户ids获取用户名和昵称
     * @param userIds
     * @return
     */
    @Override
    public List<SysUserVO> getUsernameAndNickNameByUserIds(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysUser::getId, new HashSet<>(userIds));
        wrapper.select(SysUser::getId, SysUser::getUsername, SysUser::getNickName);
        List<SysUser> list = sysUserMapper.selectList(wrapper);
        return SysUserConverter.INSTANCE.convertList(list);
    }

    /**
     * 分页列表
     * @param pageDTO
     * @return
     */
    @Override
    public PageVO<SysUserVO> pageList(SysUserPageDTO pageDTO) {
        pageDTO.setOrderColumn("gmtCreate,id");
        PageVO<SysUserVO> page = SysUserConverter.INSTANCE.convert(sysUserMapper.selectPage(pageDTO));
        List<SysUserVO> userList = page.getList();
        if (CollectionUtils.isEmpty(userList)) {
            return page;
        }

        // 获取用户角色
        List<Long> userIds = userList.stream().map(u -> u.getId()).collect(Collectors.toList());
        List<SysRoleVO> roles = sysRoleService.getRoleByUserIds(userIds);
        if (CollectionUtils.isEmpty(roles)) {
            return page;
        }

        // 设置用户角色名称
        for (SysUserVO user : userList) {
            for (SysRoleVO role : roles) {
                if (user.getId().equals(role.getUserId())) {
                    if (user.getRoleNames() == null) {
                        user.setRoleNames(new HashSet<>());
                    }
                    user.getRoleNames().add(role.getRoleName());
                }
            }
        }
        return page;
    }

    /**
     * 添加用户
     * @param addDTO
     */
    @Override
    public void add(SysUserAddDTO addDTO) {
        // 判断用户名是否已经存在
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, addDTO.getUsername());
        Long count = sysUserMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ParamException(MessageUtils.message("user.username.exist"));
        }
        SysUser sysUser = SysUserConverter.INSTANCE.convert(addDTO);
        sysUser.setPassword(SecurityUtils.encryptPassword(addDTO.getPassword()));
        sysUserMapper.insert(sysUser);
    }

    /**
     * 重置密码
     * @param sysUserResetPasswordDTO
     */
    @Override
    public void resetPassword(SysUserResetPasswordDTO sysUserResetPasswordDTO) {
        SysUser sysUser = SysUserConverter.INSTANCE.convert(sysUserResetPasswordDTO);
        sysUser.setPassword(sysUserResetPasswordDTO.getPassword());
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 过滤不存在的用户ids
     * @param ids
     * @return
     */
    @Override
    public List<Long> filterNotExistUserIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysUser::getId, ids);
        wrapper.eq(SysUser::getStatus, DelFlag.NOT_DELETED.code);
        wrapper.select(SysUser::getId);
        List<SysUser> users = sysUserMapper.selectList(wrapper);
        return users.stream().map(SysUser::getId).collect(Collectors.toList());
    }

    /**
     * 更新用户头像
     * @param id
     * @param avatar
     */
    @Override
    public void updateAvatarById(Long id, String avatar) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setAvatar(avatar);
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 重置用户密码
     * @param sysUserResetUserPasswordDTO
     */
    @Override
    public void resetUserPassword(SysUserResetUserPasswordDTO sysUserResetUserPasswordDTO) {
        LoginUserDetail loginUser = SecurityUtils.getLoginUser();

        // 是否需要输入旧密码
        if (StrUtils.isNotBlank(loginUser.getPassword()) && StrUtils.isBlank(sysUserResetUserPasswordDTO.getOldPassword())) {
            throw new ParamException(MessageUtils.message("reset.user.password.old.not.null"));
        }

        if (StrUtils.isNotBlank(sysUserResetUserPasswordDTO.getOldPassword())) {
            if (!SecurityUtils.matchesPassword(sysUserResetUserPasswordDTO.getOldPassword(), loginUser.getPassword())) {
                throw new ParamException(MessageUtils.message("reset.user.password.old.not.matches"));
            }
            if (SecurityUtils.matchesPassword(sysUserResetUserPasswordDTO.getNewPassword(), loginUser.getPassword())) {
                throw new ParamException(MessageUtils.message("reset.user.password.old.equal.new"));
            }
        }

        // 修改密码
        SysUser sysUser = new SysUser();
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUserResetUserPasswordDTO.getNewPassword()));
        sysUser.setId(loginUser.getUserId());
        sysUserMapper.updateById(sysUser);

        tokenService.setNeedRefreshPermission(loginUser.getUserId());
    }
}
