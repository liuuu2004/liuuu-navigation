package com.liuuu.admin.nav.site.mapstruct;

import com.liuuu.admin.nav.site.dto.NavSiteAddDTO;
import com.liuuu.admin.nav.site.dto.NavSiteUpdateDTO;
import com.liuuu.admin.nav.site.dto.NavSiteUpdateSortDTO;
import com.liuuu.admin.nav.site.po.NavSite;
import com.liuuu.admin.nav.site.vo.NavSiteClientVO;
import com.liuuu.admin.nav.site.vo.NavSiteLatestCollectVO;
import com.liuuu.admin.nav.site.vo.NavSiteMatchVO;
import com.liuuu.admin.nav.site.vo.NavSiteVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import lombok.Data;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 导航网站对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Data
public interface NavSiteConverter {
    NavSiteConverter INSTANCE = Mappers.getMapper(NavSiteConverter.class);

    PageVO<NavSiteVO> convert(PageVO<NavSite> pageVO);

    NavSiteVO convert(NavSite navSite);

    NavSite convert(NavSiteAddDTO addDTO);

    NavSite convert(NavSiteUpdateDTO updateDTO);

    NavSiteMatchVO convertMatch(NavSite navSite);

    List<NavSite> convert(List<NavSiteUpdateSortDTO> list);

    List<NavSiteClientVO> convertClient(List<NavSite> list);

    List<NavSiteLatestCollectVO> convertCollect(List<NavSite> list);
}
