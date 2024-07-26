package com.liuuu.admin.system.log.login.mapstruct;

import com.liuuu.admin.system.log.login.dto.LogLoginAddDTO;
import com.liuuu.admin.system.log.login.po.LogLogin;
import com.liuuu.admin.system.log.login.vo.LogLoginVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 登录日志对象转换
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Mapper
public interface LogLoginConverter {
    LogLoginConverter INSTANCE = Mappers.getMapper(LogLoginConverter.class);

    LogLoginVO converter(LogLogin logLogin);

    PageVO<LogLoginVO> converter(PageVO<LogLogin> pageVO);

    LogLogin converter(LogLoginAddDTO addDTO);

}
