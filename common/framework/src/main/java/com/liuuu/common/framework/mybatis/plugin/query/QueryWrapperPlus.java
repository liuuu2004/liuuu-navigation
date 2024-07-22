package com.liuuu.common.framework.mybatis.plugin.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扩展QueryWrapper类, 提供更多功能
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
public class QueryWrapperPlus<T> extends QueryWrapper<T> {
    private static final Logger log = LoggerFactory.getLogger(QueryWrapperPlus.class);

    private static final String ORDER_BY_COLUMN_SPLIT = ",";
}
