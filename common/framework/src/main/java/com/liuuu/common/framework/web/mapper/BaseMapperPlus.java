package com.liuuu.common.framework.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.mybatis.plugin.query.QueryWrapperPlus;
import com.liuuu.common.framework.mybatis.util.MybatisUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在Mybatis Plus的BaseMapper的基础上拓展, 提供更多功能
 *
 * @Author Liuuu
 * @Date 2024/7/23
 */
public interface BaseMapperPlus<T> extends BaseMapper<T> {
    /**
     * 查询分页
     * @param pageDTO
     * @param queryMapper
     * @return
     */
    default PageVO<T> selectPage(PageDTO pageDTO, @Param("ew") Wrapper<T> queryMapper) {
        IPage<T> myBatisPage = MybatisUtils.buildPage(pageDTO);
        selectPage(myBatisPage, queryMapper);
        return new PageVO<>(myBatisPage.getRecords(), myBatisPage.getTotal());
    }

    /**
     * 查询分页
     * @param d  实体类参数对接
     * @param selectColumns  查询返回的列
     * @return
     * @param <D>
     */
    default <D> PageVO<T> selectPage(D d, SFunction<T, ?>... selectColumns) {
        PageDTO pageDTO = d instanceof PageDTO ? (PageDTO) d : null;
        IPage<T> myBatisPage = MybatisUtils.buildPage(pageDTO);
        selectPage(myBatisPage, buildQueryWrapper(d, selectColumns));
        return new PageVO<>(myBatisPage.getRecords(), myBatisPage.getTotal());
    }

    /**
     * 查询列表
     * @param d  实体类参数对象
     * @param selectColumns  查询返回的列
     * @return
     * @param <D>
     */
    default <D> List<T> selectList(D d, SFunction<T, ?>... selectColumns) {
        return selectList(buildQueryWrapper(d, selectColumns));
    }

    /**
     * 查询单条
     * @param d  实体类参数对接
     * @return
     * @param <D>
     */
    default <D> Long selectOne(D d) {
        return selectCount(buildQueryWrapper(d));
    }

    /**
     * 查询总记录数
     * @param d  实体类参数对接
     * @return
     * @param <D>
     */
    default <D> Long selectCount(D d) {
        return selectCount(buildQueryWrapper(d));
    }

    /**
     * 构建Wrapper查询条件
     * @param d  DTO实体参数对象
     * @param selectColumns  查询返回的列
     * @return
     * @param <D>
     */
    default <D> QueryWrapperPlus<T> buildQueryWrapper(D d, SFunction<T, ?>... selectColumns) {
        Class<T> entityClass = (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), BaseMapperPlus.class, 0);
        return new QueryWrapperPlus<T>().buildQueryWrapper(entityClass, d, selectColumns);
    }
}
