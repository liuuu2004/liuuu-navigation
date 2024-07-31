package com.liuuu.admin.system.api.mapstruct;

import com.liuuu.admin.system.api.dto.SysApiAddDTO;
import com.liuuu.admin.system.api.dto.SysApiUpdateDTO;
import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.vo.SysApiVO;
import com.liuuu.admin.system.menu.dto.SysMenuUpdateDTO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.framework.security.domain.ApiPermission;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 接口对象转换
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Mapper
public interface SysApiConverter {
    SysApiConverter INSTANCE = Mappers.getMapper(SysApiConverter.class);

    List<ApiPermission> convertList(List<SysApiVO> list);

    PageVO<SysApiVO> convert(PageVO<SysApi> pageVO);

    SysApiVO convert(SysApi sysApi);

    SysApi convert(SysApiAddDTO sysApiAddDTO);

    SysApi convert(SysApiUpdateDTO sysApiUpdateDTO);
}
