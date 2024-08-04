package com.liuuu.admin.system.param.service;

import com.liuuu.admin.system.param.dto.SysParamAddDTO;
import com.liuuu.admin.system.param.dto.SysParamUpdateDTO;
import com.liuuu.admin.system.param.po.SysParam;
import com.liuuu.common.framework.web.service.BaseService;

/**
 * 参数配置
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
public interface SysParamService extends BaseService<SysParam> {
    void save(SysParamAddDTO addDTO);

    void updateById(SysParamUpdateDTO updateDTO);

    void removeByIds(Long[] ids);

    Integer getMaxSort();

    String getParamValueByKey(String paramKey);

    void removeCache(String paramKey);
}
