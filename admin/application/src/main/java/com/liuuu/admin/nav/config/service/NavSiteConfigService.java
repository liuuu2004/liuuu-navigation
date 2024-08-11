package com.liuuu.admin.nav.config.service;

import com.liuuu.admin.nav.config.dto.NavSiteConfigUpdateDTO;
import com.liuuu.admin.nav.config.po.NavSiteConfig;
import com.liuuu.admin.nav.config.vo.NavSiteConfigAboutVO;
import com.liuuu.admin.nav.config.vo.NavSiteConfigVO;
import com.liuuu.common.framework.web.service.BaseService;

/**
 * 网站配置
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
public interface NavSiteConfigService extends BaseService<NavSiteConfig> {
    /**
     * 获取配置
     * @return
     */
    NavSiteConfigVO getConfig();

    /**
     * 更新
     * @param updateDTO
     */
    void updateById(NavSiteConfigUpdateDTO updateDTO);

    /**
     * 关于本站
     * @return
     */
    NavSiteConfigAboutVO about();

}
