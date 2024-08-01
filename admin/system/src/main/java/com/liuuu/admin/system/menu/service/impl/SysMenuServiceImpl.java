package com.liuuu.admin.system.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuuu.admin.system.api.mapper.SysApiMenuMapper;
import com.liuuu.admin.system.api.po.SysApi;
import com.liuuu.admin.system.api.po.SysApiMenu;
import com.liuuu.admin.system.api.service.SysApiService;
import com.liuuu.admin.system.menu.constant.MenuConstant;
import com.liuuu.admin.system.menu.dto.SysMenuAuthApiDTO;
import com.liuuu.admin.system.menu.dto.SysMenuListDTO;
import com.liuuu.admin.system.menu.enums.MenuShowStatus;
import com.liuuu.admin.system.menu.enums.MenuStatus;
import com.liuuu.admin.system.menu.enums.MenuType;
import com.liuuu.admin.system.menu.mapper.SysMenuMapper;
import com.liuuu.admin.system.menu.mapstruct.SysMenuConverter;
import com.liuuu.admin.system.menu.po.SysMenu;
import com.liuuu.admin.system.menu.service.SysMenuService;
import com.liuuu.admin.system.menu.vo.MetaVO;
import com.liuuu.admin.system.menu.vo.RouterVO;
import com.liuuu.admin.system.menu.vo.SysMenuVO;
import com.liuuu.common.core.enums.CommonStatus;
import com.liuuu.common.core.enums.YesNo;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.exception.ServiceException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统菜单
 *
 * @Author Liuuu
 * @Date 2024/7/31
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysApiService sysApiService;

    @Autowired
    private SysApiMenuMapper sysApiMenuMapper;

    /**
     * 通过角色ids获取菜单
     * @param roleIds
     * @return
     */
    @Override
    public List<SysMenuVO> getMenuByRoleIds(Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        List<String> menuTypes = Arrays.asList(MenuType.MENU.code, MenuType.BUTTON.code);
        return sysMenuMapper.getMenuByRoleIds(roleIds, menuTypes, MenuStatus.NORMAL.code);
    }

    /**
     * 获取菜单路由
     * @return
     */
    @Override
    public List<RouterVO> getRouters() {
        LoginUserDetail loginUserDetail = SecurityUtils.getLoginUser();

        List<String> menuTypes = Arrays.asList(MenuType.DIRECTORY.code, MenuType.MENU.code);

        List<SysMenuVO> menuVOS = new ArrayList<>();

        if (loginUserDetail.isHasSuperAdmin()) {
            LambdaQueryWrapper<SysMenu> menuWrapper = new LambdaQueryWrapper<>();
            menuWrapper.eq(SysMenu::getStatus, CommonStatus.NORMAL.code);
            menuWrapper.in(SysMenu::getMenuType, menuTypes);
            menuWrapper.orderByAsc(SysMenu::getParentId, SysMenu::getSort);
            List<SysMenu> menus = sysMenuMapper.selectList(menuWrapper);
            menuVOS = SysMenuConverter.INSTANCE.convertList(menus);
        } else {
            menuVOS = sysMenuMapper.getMenuByUserId(loginUserDetail.getUserId(), CommonStatus.NORMAL.code,
                    CommonStatus.NORMAL.code, , YesNo.NO.code, menuTypes);
        }
        if (CollectionUtils.isEmpty(menuVOS)) {
            return new ArrayList<>();
        }
        List<SysMenuVO> treeMenus = buildMenuTree(menuVOS, 0L);
        return buildRouters(treeMenus, null);
    }

    /**
     * 获取菜单列表
     */
    @Override
    public List<SysMenuVO> getMenuList(SysMenuListDTO sysMenuListDTO) {
        sysMenuListDTO.setOrderColumn("sort,parentId");
        List<SysMenu> menus = sysMenuMapper.selectList(sysMenuListDTO);
        return SysMenuConverter.INSTANCE.convertList(menus);
    }

    /**
     * 菜单树形
     */
    @Override
    public List<SysMenuVO> getMenuTree() {
        List<SysMenuVO> menuVOList = getMenuList(new SysMenuListDTO());
        return buildMenuTree(menuVOList, 0L);
    }

    /**
     * 获取最大顺序
     */
    @Override
    public Integer getMaxSortByParentId(Long parentId) {
        return sysMenuMapper.selectMaxSortByParentId(parentId);
    }

    /**
     * 删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        if (id == null) {
            return;
        }
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        Long count = sysMenuMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ServiceException(MessageUtils.message("menu.has.child"));
        }
        sysMenuMapper.deleteById(id);
        LambdaQueryWrapper<SysApiMenu> apiWrapper = new LambdaQueryWrapper<>();
        apiWrapper.eq(SysApiMenu::getMenuId, id);
        sysApiMenuMapper.delete(apiWrapper);
    }

    /**
     * 分配api
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void authApi(SysMenuAuthApiDTO authApiDTO) {
        // 判断菜单是否存在
        LambdaQueryWrapper<SysMenu> menuWrapper = new LambdaQueryWrapper<>();
        menuWrapper.eq(SysMenu::getId, authApiDTO.getMenuId());
        if (sysMenuMapper.selectCount(menuWrapper) == 0) {
            throw new ParamException(MessageUtils.message("menu.not.exist"));
        }

        // 过滤不存在的api
        if (!CollectionUtils.isEmpty(authApiDTO.getApiIds())) {
            LambdaQueryWrapper<SysApi> apiWrapper = new LambdaQueryWrapper<>();
            apiWrapper.in(SysApi::getId, authApiDTO.getApiIds());
            apiWrapper.select(SysApi::getId);
            List<Long> apiIds = sysApiService.list(apiWrapper).stream().map(api -> api.getId()).collect(Collectors.toList());
            authApiDTO.setApiIds(apiIds);
        }
        // 分配api
        sysApiService.authApi(authApiDTO.getMenuId(), authApiDTO.getApiIds());
    }

    /**
     * 根据父节点的id获取所有子节点
     */
    public List<SysMenuVO> buildMenuTree(List<SysMenuVO> allList, Long parentId) {
        List<SysMenuVO> list = new ArrayList<>();
        for (SysMenuVO menu : allList) {
            // 根据传入的某个父节点id,遍历该父节点的所有子节点
            if (parentId != null && parentId.equals(menu.getParentId())) {
                // 递归菜单
                recursiveMenu(allList, menu);
                list.add(menu);
            }
        }
        return list;
    }

    /**
     * 递归菜单列表
     */
    private void recursiveMenu(List<SysMenuVO> allList, SysMenuVO menu) {
        // 得到子节点列表
        List<SysMenuVO> childList = getChildList(allList, menu);
        menu.setChildren(childList);
        for (SysMenuVO child : childList) {
            if (hasChild(allList, child)) {
                recursiveMenu(allList, child);
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVO> allList, SysMenuVO menu) {
        return getChildList(allList, menu).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuVO> getChildList(List<SysMenuVO> allList, SysMenuVO menu) {
        List<SysMenuVO> childList = new ArrayList<>();
        Iterator<SysMenuVO> it = allList.iterator();
        while (it.hasNext()) {
            SysMenuVO child = it.next();
            if (menu.getId().equals(child.getParentId())) {
                childList.add(child);
            }
        }
        return childList;
    }

    /**
     * 构建前端路由所需要的菜单
     */
    private List<RouterVO> buildRouters(List<SysMenuVO> menus, SysMenuVO parentMenu) {
        List<RouterVO> routerVOS = new LinkedList<RouterVO>();
        for (SysMenuVO menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden(MenuShowStatus.HIDDEN.code.equals(menu.getShowStatus()));
            router.setName(getRouterName(menu, parentMenu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getRouteParam());
            router.setMeta(new MetaVO(menu.getMenuName(), menu.getMenuIcon(), YesNo.NO.code.equals(menu.getHasCache()), menu.getRouteUrl()));
            List<SysMenuVO> cMenus = menu.getChildren();
            if (!CollectionUtils.isEmpty(cMenus) && MenuType.DIRECTORY.code.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildRouters(cMenus, menu));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVO> childrenList = new ArrayList<RouterVO>();
                RouterVO children = new RouterVO();
                children.setPath(menu.getRouteUrl());
                children.setComponent(menu.getComponentPath());
                children.setName(StrUtils.capitalize(menu.getRouteUrl()));
                children.setMeta(new MetaVO(menu.getMenuName(), menu.getMenuIcon(), YesNo.NO.code.equals(menu.getHasCache()), menu.getRouteUrl()));
                children.setQuery(menu.getRouteParam());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (isRootMenu(menu.getParentId()) && isInnerLink(menu)) {
                router.setMeta(new MetaVO(menu.getMenuName(), menu.getMenuIcon()));
                router.setPath("/");
                List<RouterVO> childrenList = new ArrayList<RouterVO>();
                RouterVO children = new RouterVO();
                String routerPath = innerLinkReplaceEach(menu.getRouteUrl());
                children.setPath(routerPath);
                children.setComponent(MenuConstant.INNER_LINK);
                children.setName(StrUtils.capitalize(routerPath));
                children.setMeta(new MetaVO(menu.getMenuName(), menu.getMenuIcon(), menu.getRouteUrl()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routerVOS.add(router);
        }
        return routerVOS;
    }

    /**
     * 获取路由名称
     */
    public String getRouterName(SysMenuVO menu, SysMenuVO parentMenu) {
        String routerName = (parentMenu != null ? StrUtils.capitalize(parentMenu.getRouteUrl()) : "")
                + StrUtils.capitalize(menu.getRouteUrl());
        // 若并非外链且是一级目录
        if (isMenuFrame(menu)) {
            routerName = StrUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     */
    public boolean isMenuFrame(SysMenuVO sysMenuVO) {
        return isRootMenu(sysMenuVO.getParentId()) && MenuType.MENU.code.equals(sysMenuVO.getMenuType())
                && sysMenuVO.getHasFrame().equals(YesNo.NO.code);
    }



}
