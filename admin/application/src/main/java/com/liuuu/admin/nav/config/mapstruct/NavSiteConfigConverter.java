package com.liuuu.admin.nav.config.mapstruct;

import com.liuuu.admin.nav.config.dto.NavSiteConfigUpdateDTO;
import com.liuuu.admin.nav.config.po.NavSiteConfig;
import com.liuuu.admin.nav.config.vo.NavSiteConfigAboutVO;
import com.liuuu.admin.nav.config.vo.NavSiteConfigVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 网站配置对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/10
 */
@Mapper
public interface NavSiteConfigConverter {
    NavSiteConfigConverter INSTANCE = Mappers.getMapper(NavSiteConfigConverter.class);

    PageVO<NavSiteConfigVO> convert(PageVO<NavSiteConfig> pageVO);

    NavSiteConfigVO convert(NavSiteConfig navSiteConfig);

    NavSiteConfig convert(NavSiteConfigUpdateDTO updateDTO);

    NavSiteConfigAboutVO convertAbout(NavSiteConfig navSiteConfig);

}
