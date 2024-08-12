package com.liuuu.admin.system.user.service;

import com.liuuu.admin.system.user.dto.SysUserAddDTO;
import com.liuuu.admin.system.user.dto.SysUserPageDTO;
import com.liuuu.admin.system.user.dto.SysUserResetPasswordDTO;
import com.liuuu.admin.system.user.dto.SysUserResetUserPasswordDTO;
import com.liuuu.admin.system.user.po.SysUser;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.BaseService;
import com.liuuu.framework.security.domain.LoginUserDetail;

import java.util.List;

/**
 * 系统用户
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 获取登录用户
     * @param username
     * @return
     */
    LoginUserDetail getLoginUserByUsername(String username);

    /**
     * 设置登陆成功后的用户权限
     * @param loginUserDetail
     */
    void setLoginUserPermission(LoginUserDetail loginUserDetail);

    /**
     * 通过用户ids获取用户名和昵称
     * @param userIds
     * @return
     */
    List<SysUserVO> getUsernameAndNickNameByUserIds(List<Long> userIds);

    /**
     * 分页列表
     * @param sysUserPageDTO
     * @return
     */
    PageVO<SysUserVO> pageList(SysUserPageDTO sysUserPageDTO);

    /**
     * 新增用户
     * @param sysUserAddDTO
     */
    void add(SysUserAddDTO sysUserAddDTO);

    /**
     * 重置密码
     * @param sysUserResetPasswordDTO
     */
    void resetPassword(SysUserResetPasswordDTO sysUserResetPasswordDTO);

    /**
     * 过滤不存在的用户ids
     * @param ids
     * @return
     */
    List<Long> filterNotExistUserIds(List<Long> ids);

    /**
     * 更新用户头像
     * @param id
     * @param avatar
     */
    void updateAvatarById(Long id, String avatar);

    /**
     * 重置用户密码
     * @param sysUserResetUserPasswordDTO
     */
    void resetUserPassword(SysUserResetUserPasswordDTO sysUserResetUserPasswordDTO);
}
