package com.liuuu.admin.system.log.operation.mq;

import com.alibaba.fastjson.JSONObject;
import com.liuuu.admin.system.log.operation.mapstruct.LogOperationConverter;
import com.liuuu.admin.system.log.operation.po.LogOperation;
import com.liuuu.admin.system.log.operation.service.LogOperationService;
import com.liuuu.common.core.util.id.IdWorker;
import com.liuuu.common.log.dto.LogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作日志消息中心
 *
 * @Author Liuuu
 * @Date 2024/7/26
 */
@Component
public class LogOperationMq {
    @Autowired
    private LogOperationService logOperationService;

    public void consumeMqLog(String message) {
        LogDTO logDTO = JSONObject.parseObject(message, LogDTO.class);
        LogOperation logOperation = LogOperationConverter.INSTANCE.converter(logDTO);
        logOperation.setId(IdWorker.nextId());
        logOperationService.save(logOperation);
    }

}
