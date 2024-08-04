package com.liuuu.admin.system.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuuu.admin.system.dictionary.po.SysDictionaryData;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Mapper
public interface SysDictionaryDataMapper extends BaseMapperPlus<SysDictionaryData> {
    Integer selectMaxSortByDictionaryId(Long dictionaryId);
}
