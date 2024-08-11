package com.liuuu.admin.nav.index.service;

import com.liuuu.admin.nav.index.vo.NavIndexStatisticsVO;

/**
 * 后台首页
 *
 * @Author Liuuu
 * @Date 2024/8/11
 */
public interface NavIndexService {
    /**
     * 获取统计数据
     * @return
     */
    NavIndexStatisticsVO getStatistics();
}
