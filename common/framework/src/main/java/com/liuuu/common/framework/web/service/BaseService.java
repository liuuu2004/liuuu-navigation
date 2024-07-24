package com.liuuu.common.framework.web.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.mybatis.plugin.query.QueryWrapperPlus;

import java.util.List;

/**
 * 在MyBatis Plus的IService的基础上拓展, 提供更多功能
 *
 * @Author Liuuu
 * @Date 2024/7/23
 * @param <T>
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 查询分页
     * @param d  实体类参数对接
     * @param selectColumns  查询返回的列
     * @return
     * @param <D>
     */
    <D> PageVO<T> page(D d, SFunction<T, ?>... selectColumns);

    /**
     * 查询列表
     * @param d  实体类参数对接
     * @param selectedColumns  查询返回的列
     * @return
     * @param <D>
     */
    default <D> List<T> list(D d, SFunction<T, ?>... selectedColumns) {
        return list(buildQueryWrapper(d, selectedColumns));
    }

    /**
     * 查询单条
     * @param d  实体类参数对接
     * @return
     * @param <D>
     */
    default <D> T getOne(D d) {
        return getOne(buildQueryWrapper(d));
    }

    /**
     * 查询单条
     * @param d  实体类参数对接
     * @param throwEx  有多个结果是否抛出异常
     * @return
     * @param <D>
     */
    default <D> T getOne(D d, boolean throwEx) {
        return getOne(buildQueryWrapper(d), throwEx);
    }

    /**
     * 查询总记录数
     * @param d  实体类参数对接
     * @return
     * @param <D>
     */
    default <D> Long count(D d) {
        return count(buildQueryWrapper(d));
    }

    /**
     * 构建Wrapper查询条件
     * @param d  DTO尸体参数对象
     * @param selectColumns  查询返回的列
     * @return  查询构造器
     * @param <D>
     */
    default <D> QueryWrapperPlus<T> buildQueryWrapper(D d, SFunction<T, ?>... selectColumns) {
        return new QueryWrapperPlus<T>().buildQueryWrapper(getEntityClass(), d, selectColumns);
    }


}
