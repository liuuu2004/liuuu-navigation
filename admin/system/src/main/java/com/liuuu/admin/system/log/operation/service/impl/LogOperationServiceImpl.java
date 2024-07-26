package com.liuuu.admin.system.log.operation.service.impl;

import com.liuuu.admin.system.log.operation.dto.LogOperationPageDTO;
import com.liuuu.admin.system.log.operation.mapper.LogOperationMapper;
import com.liuuu.admin.system.log.operation.mapstruct.LogOperationConverter;
import com.liuuu.admin.system.log.operation.po.LogOperation;
import com.liuuu.admin.system.log.operation.service.LogOperationService;
import com.liuuu.admin.system.log.operation.vo.LogOperationVO;
import com.liuuu.common.framework.mybatis.page.util.PageUtils;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 操作日志
 *
 * @Auhtor Liuuu
 * @Date 2024/7/26
 */
@Service
public class LogOperationServiceImpl extends BaseServiceImpl<LogOperationMapper, LogOperation> implements LogOperationService {
    @Autowired
    private LogOperationMapper logOperationMapper;

    @Autowired
    private SysUSerService sysUSerService;

    /**
     * 分页列表
     * @param logOperationPageDTO
     * @return
     */
    @Override
    public PageVO<LogOperationVO> pageList(LogOperationPageDTO logOperationPageDTO) {
        PageUtils.startPage(logOperationPageDTO);
        List<LogOperationVO> list = logOperationMapper.pageList(logOperationPageDTO);
        PageVO<LogOperationVO> pageVO = PageUtils.getPage(list);
        return pageVO;
    }

    @Override
    public LogOperationVO getLogOperationById(Long id) {
        LogOperation logOperation = logOperationMapper.selectById(id);
        if (logOperation == null) {
            return null;
        }
        LogOperationVO logOperationVO = LogOperationConverter.INSTANCE.converter(logOperation);
        List<SysUserVO> userList = sysUSerService.getUsernameAndNiskNameByUserIds(Arrays.asList(logOperationVO.getUserId()));
        if (!CollectionUtils.isEmpty(userList)) {
            SysUserVO sysUserVO = userList.get(0);
            logOperationVO.setUsername(sysUserVO.getUsername());
            logOperationVO.setNickName(sysUserVO.getNickName());
        }
        return logOperationVO;
    }
}
