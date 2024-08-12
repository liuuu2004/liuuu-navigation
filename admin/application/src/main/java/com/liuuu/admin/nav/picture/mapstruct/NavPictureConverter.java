package com.liuuu.admin.nav.picture.mapstruct;

import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 导航图片对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Mapper
public interface NavPictureConverter {
    NavPictureConverter INSTANCE = Mappers.getMapper(NavPictureConverter.class);
}
