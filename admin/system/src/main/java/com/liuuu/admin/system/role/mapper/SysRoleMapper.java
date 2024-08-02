package com.liuuu.admin.system.role.mapper;

import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Mapper
public interface SysRoleMapper extends BaseMapperPlus<SysRole> {
    /**
     * 通过角色id获取角色
     * @param userId
     * @param status
     * @return
     */
    List<SysRoleVO> getRoleByUserIds(@Param("userIds") List<Long> userId, @Param("status") Integer status);

    /**
     * 获取最大排序
     * @return
     */
    Integer selectMaxSort();
}
