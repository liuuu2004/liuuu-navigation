package com.liuuu.admin.system.log.operation.mapstruct;

import com.liuuu.admin.system.log.operation.po.LogOperation;
import com.liuuu.admin.system.log.operation.vo.LogOperationVO;
import com.liuuu.common.log.dto.LogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 操作日志转换
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Mapper
public interface LogOperationConverter {
    LogOperationConverter INSTANCE = Mappers.getMapper(LogOperationConverter.class);

    LogOperationVO converter(LogOperation logOperation);

    LogOperation converter(LogDTO logDTO);
}
