package com.liuuu.admin.client.nav.mapstruct;

import com.liuuu.admin.client.nav.vo.NavCategoryClientVO;
import com.liuuu.admin.nav.category.po.NavCategory;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 导航客户端对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/6
 */
@Mapper
public interface ClientNavConverter {
    ClientNavConverter INSTANCE = Mappers.getMapper(ClientNavConverter.class);

    List<NavCategoryClientVO> converterCategory(List<NavCategory> list);
}
