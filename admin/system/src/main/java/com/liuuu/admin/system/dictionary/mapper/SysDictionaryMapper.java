package com.liuuu.admin.system.dictionary.mapper;

import com.liuuu.admin.system.dictionary.po.SysDictionary;
import com.liuuu.admin.system.dictionary.vo.DictionaryInfoVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Mapper
public interface SysDictionaryMapper extends BaseMapperPlus<SysDictionary> {
    Integer selectMaxSort();

    List<DictionaryInfoVO> getAllDictionaryInfo(Integer status);
}
