package com.liuuu.admin.system.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.dictionary.mapper.SysDictionaryDataMapper;
import com.liuuu.admin.system.dictionary.mapper.SysDictionaryMapper;
import com.liuuu.admin.system.dictionary.mapstruct.SysDictionaryConverter;
import com.liuuu.admin.system.dictionary.po.SysDictionary;
import com.liuuu.admin.system.dictionary.po.SysDictionaryData;
import com.liuuu.admin.system.dictionary.service.SysDictionaryService;
import com.liuuu.admin.system.dictionary.vo.DictionaryInfoVO;
import com.liuuu.admin.system.dictionary.vo.SysDictionaryVO;
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 字典
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Service
public class SysDictionaryServiceImpl extends BaseServiceImpl<SysDictionaryMapper, SysDictionary> implements SysDictionaryService {
    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Autowired
    private SysDictionaryDataMapper sysDictionaryDataMapper;

    /**
     * 列表排序
     * @return
     */
    @Override
    public List<SysDictionaryVO> listSort() {
        LambdaQueryWrapper<SysDictionary> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SysDictionary::getSort, SysDictionary::getId);
        return SysDictionaryConverter.INSTANCE.convertList(sysDictionaryMapper.selectList(wrapper));
    }

    /**
     * 删除
     * @param ids
     */
    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        LambdaQueryWrapper<SysDictionaryData> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysDictionaryData::getDictionaryId, ids);
        if (sysDictionaryDataMapper.selectCount(wrapper) > 0) {
            throw new ParamException(MessageUtils.message("dictionary.exist.data"));
        }
        sysDictionaryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 获取最大排序
     * @return
     */
    @Override
    public Integer getMaxSort() {
        return sysDictionaryMapper.selectMaxSort();
    }

    /**
     * 获取所有字典详细信息
     * @return
     */
    @Override
    public List<DictionaryInfoVO> getAllDictionaryInfo() {
        return sysDictionaryMapper.getAllDictionaryInfo(CommonStatus.NORMAL.code);
    }
}
