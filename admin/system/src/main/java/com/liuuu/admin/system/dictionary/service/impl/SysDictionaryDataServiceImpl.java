package com.liuuu.admin.system.dictionary.service.impl;

import com.liuuu.admin.system.dictionary.mapper.SysDictionaryDataMapper;
import com.liuuu.admin.system.dictionary.po.SysDictionaryData;
import com.liuuu.admin.system.dictionary.service.SysDictionaryDataService;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典数据
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Service
public class SysDictionaryDataServiceImpl extends BaseServiceImpl<SysDictionaryDataMapper, SysDictionaryData> implements SysDictionaryDataService {
    @Autowired
    private SysDictionaryDataMapper sysDictionaryDataMapper;

    /**
     * 获取最大排序
     * @param dictionaryId
     * @return
     */
    @Override
    public Integer getMaxSortByDictionaryId(Long dictionaryId) {
        return sysDictionaryDataMapper.selectMaxSortByDictionaryId(dictionaryId);
    }
}
