package com.liuuu.admin.system.param.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuuu.admin.system.param.dto.SysParamAddDTO;
import com.liuuu.admin.system.param.dto.SysParamUpdateDTO;
import com.liuuu.admin.system.param.po.SysParam;
import com.liuuu.admin.system.param.vo.SysParamVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 参数配置对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Mapper
public interface SysParamConverter {
    SysParamConverter INSTANCE = Mappers.getMapper(SysParamConverter.class);

    PageVO<SysParamVO> convert(PageVO<SysParam> pageVO);

    SysParamVO convert(SysParam sysParam);

    SysParam convert(SysParamAddDTO addDTO);

    SysParam convert(SysParamUpdateDTO updateDTO);
}
