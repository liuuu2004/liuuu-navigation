package com.liuuu.admin.system.api.mapper;

import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.vo.SysApiVO;
import com.liuuu.admin.system.menu.vo.SysMenuAuthApiVO;
import com.liuuu.common.framework.web.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysApiMapper extends BaseMapperPlus<SysApi> {
    /**
     * 通过菜单id列表获取接口
     * @param menuIds
     * @param status
     * @return
     */
    List<SysApiVO> getApiByMenuIds(@Param("menuIds") List<Long> menuIds,
                                   @Param("status") Integer status);

    /**
     * 获取最大排序
     * @param apiCategoryId
     * @return
     */
    Integer selectMaxSortByCategoryId(Long apiCategoryId);

    /**
     * 获取分配的api接口
     * @param menuId
     * @return
     */
    List<SysMenuAuthApiVO> selectAuthApiByMenuId(Long menuId);

}
