package com.liuuu.admin.system.menu.mapstruct;

import com.liuuu.admin.system.menu.dto.SysMenuAddDTO;
import com.liuuu.admin.system.menu.dto.SysMenuUpdateDTO;
import com.liuuu.admin.system.menu.po.SysMenu;
import com.liuuu.admin.system.menu.vo.SysMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单对象转换
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Mapper
public interface SysMenuConverter {
    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);

    SysMenuVO convert(SysMenu menus);

    List<SysMenuVO> convertList(List<SysMenu> menus);

    SysMenu convert(SysMenuAddDTO addDTO);

    SysMenu convert(SysMenuUpdateDTO updateDTO);
}
