package com.liuuu.admin.system.dictionary.mapstruct;

import com.liuuu.admin.system.dictionary.dto.SysDictionaryAddDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryUpdateDTO;
import com.liuuu.admin.system.dictionary.po.SysDictionary;
import com.liuuu.admin.system.dictionary.vo.SysDictionaryVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Mapper
public interface SysDictionaryConverter {
    SysDictionaryConverter INSTANCE = Mappers.getMapper(SysDictionaryConverter.class);

    SysDictionaryVO convert(SysDictionary sysDictionary);

    List<SysDictionaryVO> convertList(List<SysDictionary> list);

    PageVO<SysDictionaryVO> convert(PageVO<SysDictionary> pageVO);

    SysDictionary convert(SysDictionaryAddDTO addDTO);

    SysDictionary convert(SysDictionaryUpdateDTO updateDTO);
}
