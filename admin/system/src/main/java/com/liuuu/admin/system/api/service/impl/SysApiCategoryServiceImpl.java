package com.liuuu.admin.system.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.api.mapper.SysApiCategoryMapper;
import com.liuuu.admin.system.api.mapper.SysApiMapper;
import com.liuuu.admin.system.api.mapstruct.SysApiCategoryConverter;
import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.po.SysApiCategory;
import com.liuuu.admin.system.api.service.SysApiCategoryService;
import com.liuuu.admin.system.api.vo.SysApiCategoryVO;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 系统接口分类
 *
 * @Author Liuuu
 * @Date 2024/7/31
 */
@Service
public class SysApiCategoryServiceImpl extends BaseServiceImpl<SysApiCategoryMapper, SysApiCategory> implements SysApiCategoryService{
    @Autowired
    private SysApiCategoryMapper sysApiCategoryMapper;

    @Autowired
    private SysApiMapper sysApiMapper;

    /**
     * 列表排序
     * @return
     */
    @Override
    public List<SysApiCategoryVO> listSort() {
        LambdaQueryWrapper<SysApiCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SysApiCategory::getSort, SysApiCategory::getId);
        return SysApiCategoryConverter.INSTANCE.convertList(sysApiCategoryMapper.selectList(wrapper));
    }

    @Override
    public void removeByIds(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        LambdaQueryWrapper<SysApi> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysApi::getApiCategoryId, ids);
        if (sysApiMapper.selectCount(wrapper) > 0) {
            throw new ParamException(MessageUtils.message("api.category.allocated"));
        }
        sysApiCategoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public Integer getMaxSort() {
        return sysApiCategoryMapper.selectMaxSort();
    }
}
