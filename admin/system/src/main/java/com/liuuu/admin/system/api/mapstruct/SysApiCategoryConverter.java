package com.liuuu.admin.system.api.mapstruct;

import com.liuuu.admin.system.api.dto.SysApiCategoryAddDTO;
import com.liuuu.admin.system.api.dto.SysApiCategoryUpdateDTO;
import com.liuuu.admin.system.api.po.SysApiCategory;
import com.liuuu.admin.system.api.vo.SysApiCategoryVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统接口对象转换
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
@Mapper
public interface SysApiCategoryConverter {
    SysApiCategoryConverter INSTANCE = Mappers.getMapper(SysApiCategoryConverter.class);

    SysApiCategoryVO convert(SysApiCategory sysApiCategory);

    List<SysApiCategoryVO> convertList(List<SysApiCategory> list);

    PageVO<SysApiCategoryVO> convert(PageVO<SysApiCategory> pageVO);

    SysApiCategory convert(SysApiCategoryAddDTO sysApiCategoryAddDTO);

    SysApiCategory convert(SysApiCategoryUpdateDTO sysApiCategoryUpdateDTO);
}
