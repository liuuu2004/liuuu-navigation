package com.liuuu.admin.nav.config.mapper;

import com.liuuu.admin.nav.config.po.NavSiteConfig;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网站配置
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Mapper
public interface NavSiteConfigMapper extends BaseMapperPlus<NavSiteConfig> {
    /**
     * 更新访问量
     * @param id
     */
    void updateVisitCount(Long id);
}
