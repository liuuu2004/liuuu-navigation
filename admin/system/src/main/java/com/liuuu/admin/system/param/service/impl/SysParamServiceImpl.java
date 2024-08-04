package com.liuuu.admin.system.param.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.param.constant.SysParamConstant;
import com.liuuu.admin.system.param.dto.SysParamAddDTO;
import com.liuuu.admin.system.param.dto.SysParamUpdateDTO;
import com.liuuu.admin.system.param.mapper.SysParamMapper;
import com.liuuu.admin.system.param.mapstruct.SysParamConverter;
import com.liuuu.admin.system.param.po.SysParam;
import com.liuuu.admin.system.param.service.SysParamService;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import com.liuuu.navigation.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 参数配置
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParamMapper, SysParam> implements SysParamService {
    @Autowired
    private SysParamMapper sysParamMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void save(SysParamAddDTO addDTO) {
        SysParam sysParam = SysParamConverter.INSTANCE.convert(addDTO);
        sysParamMapper.insert(sysParam);
        removeCache(addDTO.getParamKey());
    }

    @Override
    public void updateById(SysParamUpdateDTO updateDTO) {
        SysParam oldSysParam = sysParamMapper.selectById(updateDTO.getId());
        if (oldSysParam == null) {
            return;
        }
        SysParam sysParam = SysParamConverter.INSTANCE.convert(updateDTO);
        sysParamMapper.updateById(sysParam);
        removeCache(oldSysParam.getParamKey());
    }

    @Override
    public void removeByIds(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        sysParamMapper.deleteBatchIds(Arrays.asList(ids));
        LambdaQueryWrapper<SysParam> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysParam::getId, ids);
        wrapper.select(SysParam::getParamKey);
        List<SysParam> sysParams = sysParamMapper.selectList(wrapper);
        sysParams.forEach(param -> {
            removeCache(param.getParamKey());
        });
    }

    @Override
    public Integer getMaxSort() {
        return sysParamMapper.selectMaxSort();
    }

    /**
     * 获取参数值
     * @param paramKey
     * @return
     */
    @Override
    public String getParamValueByKey(String paramKey) {
        if (StrUtils.isBlank(paramKey)) {
            return paramKey;
        }
        String value = redisService.get(SysParamConstant.CACHE_PREFIX + paramKey);
        if (value != null) {
            return value;
        }
        // 从数据库获取
        LambdaQueryWrapper<SysParam> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysParam::getParamKey, paramKey);
        SysParam sysParam = sysParamMapper.selectOne(wrapper);
        value = sysParam == null ? null : sysParam.getParamValue();

        // 存入缓存
        redisService.set(SysParamConstant.CACHE_PREFIX + paramKey, value, 3, TimeUnit.DAYS);
        return value;
    }

    @Override
    public void removeCache(String paramKey) {
        redisService.delete(SysParamConstant.CACHE_PREFIX + paramKey);
    }
}
