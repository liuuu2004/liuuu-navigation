package com.liuuu.admin.system.user.mapper;

import com.liuuu.admin.system.user.po.SysUserRole;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户角色
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRole> {
    /**
     * 获取关联未删除的用户数量
     * @param roleIds
     * @param delFlag
     * @return
     */
    Long selectAssociateNotDeleteUserCount(@Param("roleIds") List<Long> roleIds,
                                           @Param("delFlag") Integer delFlag);

    /**
     * 通过角色编码获取用户ids
     * @param roleCode
     * @return
     */
    List<Long> selectUserIdByRoleCode(@Param("roleCode") String roleCode);
}
