package com.liuuu.admin.nav.category.mapstruct;

import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.client.nav.vo.NavCategorySiteClientVO;
import com.liuuu.admin.nav.category.dto.NavCategoryAddDTO;
import com.liuuu.admin.nav.category.dto.NavCategoryUpdateDTO;
import com.liuuu.admin.nav.category.po.NavCategory;
import com.liuuu.admin.nav.category.vo.NavCategoryTreeVO;
import com.liuuu.admin.nav.category.vo.NavCategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 导航分类对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/5
 */
@Mapper
public interface NavCategoryConverter {
    NavCategoryConverter INSTANCE = Mappers.getMapper(NavCategoryConverter.class);

    List<NavCategoryVO> convert(List<NavCategory> list);

    List<NavCategoryTreeVO> convertTree(List<NavCategory> list);

    NavCategoryVO convert(NavCategory navCategory);

    NavCategory convert(NavCategoryAddDTO addDTO);

    NavCategory convert(NavCategoryUpdateDTO updateDTO);

    List<NavCategoryClientVO> convertClient(List<NavCategoryTreeVO> list);

    List<NavCategorySiteClientVO> convertSiteClient(List<NavCategoryClientVO> list);
}
