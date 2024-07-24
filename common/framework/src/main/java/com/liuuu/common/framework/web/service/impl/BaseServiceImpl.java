package com.liuuu.common.framework.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import com.liuuu.common.framework.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 在Mybatis Plus的ServiceImpl的基础上拓展, 提供更多功能
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
public class BaseServiceImpl<M extends BaseMapperPlus<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    private M baseMapper;

    @Override
    public <D> PageVO <T> page(D d, SFunction<T, ?>... selectColumns) {
        return baseMapper.selectPage(d, selectColumns);
    }
}
