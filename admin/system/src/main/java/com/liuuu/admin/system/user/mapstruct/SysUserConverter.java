package com.liuuu.admin.system.user.mapstruct;

import com.liuuu.admin.system.user.dto.SySUserUpdateInfoDTO;
import com.liuuu.admin.system.user.dto.SysUserAddDTO;
import com.liuuu.admin.system.user.dto.SysUserResetPasswordDTO;
import com.liuuu.admin.system.user.dto.SysUserUpdateDTO;
import com.liuuu.admin.system.user.po.SysUser;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.framework.security.domain.LoginUserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户对象转换
 *
 * @Author Liuuu
 * @Date 2024/7/28
 */
@Mapper
public interface SysUserConverter{
    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    @Mapping(source = "id", target = "userId")
    LoginUserDetail convertDetail(SysUser sysUser);

    SysUserVO convert(SysUser sysUser);

    List<SysUserVO> convertList(List<SysUser> list);

    PageVO<SysUserVO> convert(PageVO<SysUser> pageVO);

    SysUser convert(SysUserAddDTO sysUserAddDTO);

    SysUser convert(SysUserUpdateDTO sysUserUpdateDTO);

    SysUser convert(SySUserUpdateInfoDTO sySUserUpdateInfoDTO);

    SysUser convert(SysUserResetPasswordDTO sysUserResetPasswordDTO);

}
