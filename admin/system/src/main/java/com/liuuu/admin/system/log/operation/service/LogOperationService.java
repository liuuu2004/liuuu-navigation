package com.liuuu.admin.system.log.operation.service;

import com.liuuu.admin.system.log.operation.dto.LogOperationPageDTO;
import com.liuuu.admin.system.log.operation.po.LogOperation;
import com.liuuu.admin.system.log.operation.vo.LogOperationVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.BaseService;

/**
 * 操作日志
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
public interface LogOperationService extends BaseService<LogOperation> {
    /**
     * 分页列表
     */
    PageVO<LogOperationVO> pageList(LogOperationPageDTO logOperationPageDTO);

    /**
     * 获取详情
     */
    LogOperationVO getLogOperationById(Long id);
}
