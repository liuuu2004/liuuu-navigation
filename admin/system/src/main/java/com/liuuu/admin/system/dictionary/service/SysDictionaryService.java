package com.liuuu.admin.system.dictionary.service;

import com.liuuu.admin.system.dictionary.po.SysDictionary;
import com.liuuu.admin.system.dictionary.vo.DictionaryInfoVO;
import com.liuuu.admin.system.dictionary.vo.SysDictionaryVO;
import com.liuuu.common.framework.web.service.BaseService;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * 字典
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
public interface SysDictionaryService extends BaseService<SysDictionary> {
    /**
     * 列表排序
     * @return
     */
    List<SysDictionaryVO> listSort();

    /**
     * 依照ids删除
     * @param ids
     */
    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     * @return
     */
    Integer getMaxSort();

    /**
     * 获取所有字典详细信息
     * @return
     */
    List<DictionaryInfoVO> getAllDictionaryInfo();

}
