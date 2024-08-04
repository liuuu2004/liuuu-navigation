package com.liuuu.admin.system.dictionary.service;

import com.liuuu.admin.system.dictionary.po.SysDictionaryData;
import com.liuuu.common.framework.web.service.BaseService;

/**
 * 字典数据
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
public interface SysDictionaryDataService extends BaseService<SysDictionaryData> {
    /**
     * 获取最大排序
     * @param dictionaryId
     * @return
     */
   Integer getMaxSortByDictionaryId(Long dictionaryId);
}
