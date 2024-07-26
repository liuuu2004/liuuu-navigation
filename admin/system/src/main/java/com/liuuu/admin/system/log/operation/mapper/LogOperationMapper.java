package com.liuuu.admin.system.log.operation.mapper;

import com.liuuu.admin.system.log.operation.dto.LogOperationPageDTO;
import com.liuuu.admin.system.log.operation.po.LogOperation;
import com.liuuu.admin.system.log.operation.vo.LogOperationVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Mapper
public interface LogOperationMapper extends BaseMapperPlus<LogOperation> {
    /**
     * 分页列表
     * @param pageDTO
     * @return
     */
    List<LogOperationVO> pageList(LogOperationPageDTO pageDTO);
}
