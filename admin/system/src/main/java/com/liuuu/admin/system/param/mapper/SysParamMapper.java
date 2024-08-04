package com.liuuu.admin.system.param.mapper;

import com.liuuu.admin.system.param.po.SysParam;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数配置
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Mapper
public interface SysParamMapper extends BaseMapperPlus<SysParam> {
    /**
     * 获取最大排序
     * @return
     */
    Integer selectMaxSort();
}
