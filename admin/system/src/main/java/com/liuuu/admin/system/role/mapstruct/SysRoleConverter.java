package com.liuuu.admin.system.role.mapstruct;

import com.liuuu.admin.system.role.dto.SysRoleAddDTO;
import com.liuuu.admin.system.role.dto.SysRoleAuthUserPageDTO;
import com.liuuu.admin.system.role.dto.SysRoleNotAuthUserPageDTO;
import com.liuuu.admin.system.role.dto.SysRoleUpdateDTO;
import com.liuuu.admin.system.role.po.SysRole;
import com.liuuu.admin.system.role.vo.SysRoleVO;
import com.liuuu.admin.system.user.dto.SysUserPageDTO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/2
 */
@Mapper
public interface SysRoleConverter {
    SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);

    SysRoleVO convert(SysRole sysRole);

    PageVO<SysRoleVO> convert(PageVO<SysRole> pageVO);

    SysRole convert(SysRoleAddDTO addDTO);

    SysRole convert(SysRoleUpdateDTO updateDTO);

    SysUserPageDTO convert(SysRoleAuthUserPageDTO pageDTO);

    SysUserPageDTO convert(SysRoleNotAuthUserPageDTO pageDTO);
}
