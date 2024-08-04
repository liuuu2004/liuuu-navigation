package com.liuuu.admin.system.dictionary.mapstruct;

import com.liuuu.admin.system.dictionary.dto.SysDictionaryDataAddDTO;
import com.liuuu.admin.system.dictionary.dto.SysDictionaryDataUpdateDTO;
import com.liuuu.admin.system.dictionary.po.SysDictionary;
import com.liuuu.admin.system.dictionary.po.SysDictionaryData;
import com.liuuu.admin.system.dictionary.vo.SysDictionaryDataVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典数据对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Mapper
public interface SysDictionaryDataConverter {
    SysDictionaryDataConverter INSTANCE = Mappers.getMapper(SysDictionaryDataConverter.class);

    SysDictionaryDataVO convert(SysDictionaryData sysDictionaryData);

    PageVO<SysDictionaryDataVO> convert(PageVO<SysDictionaryData> dataPageVO);

    SysDictionaryData convert(SysDictionaryDataAddDTO addDTO);

    SysDictionaryData convert(SysDictionaryDataUpdateDTO updateDTO);
}
