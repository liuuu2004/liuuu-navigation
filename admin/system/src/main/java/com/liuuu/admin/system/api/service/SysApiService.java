package com.liuuu.admin.system.api.service;

import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.vo.SysApiVO;
import com.liuuu.admin.system.menu.vo.SysMenuAuthApiVO;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 接口
 *
 * @Author Liuuu
 * @Date 2024/7/30
 */
public interface SysApiService extends BaseService<SysApi> {
    /**
     * 通过菜单id列表获取接口
     * @param menuIds
     * @return
     */
    List<SysApiVO> getApiByMenuIds(List<Long> menuIds);

    void removeByIds(Long[] ids);

    /**
     * 获取最大排序
     * @param apiCategoryId
     * @return
     */
    Integer getMaxSortByCategoryId(Long apiCategoryId);

    /**
     * 分配api
     * @param menuId
     * @param apiIds
     */
    void authApi(Long menuId, List<Long> apiIds);

    /**
     * 获取分配的api接口
     * @param menuId
     * @return
     */
    List<SysMenuAuthApiVO> getAuthApiByMenuId(Long menuId);

}
