package com.liuuu.admin.nav.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.liuuu.admin.client.nav.dto.ClientNavCommentAddDTO;
import com.liuuu.admin.client.nav.vo.ClientNavCommentTreeVO;
import com.liuuu.admin.nav.comment.constant.NavCommentConstant;
import com.liuuu.admin.nav.comment.dto.NavCommentPageDTO;
import com.liuuu.admin.nav.comment.dto.NavCommentRejectDTO;
import com.liuuu.admin.nav.comment.enums.NavCommentStatus;
import com.liuuu.admin.nav.comment.mapper.NavCommentMapper;
import com.liuuu.admin.nav.comment.mapstruct.NavCommentConverter;
import com.liuuu.admin.nav.comment.po.NavComment;
import com.liuuu.admin.nav.comment.service.NavCommentService;
import com.liuuu.admin.nav.comment.vo.NavCommentVO;
import com.liuuu.admin.system.param.service.SysParamService;
import com.liuuu.admin.system.user.mapper.SysUserRoleMapper;
import com.liuuu.admin.system.user.service.SysUserService;
import com.liuuu.admin.system.user.vo.SysUserVO;
import com.liuuu.common.core.constant.HttpStatus;
import com.liuuu.common.core.exception.BaseException;
import com.liuuu.common.core.exception.ParamException;
import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.impl.BaseServiceImpl;
import com.liuuu.framework.security.constant.SecurityConstant;
import com.liuuu.framework.security.domain.LoginUserDetail;
import com.liuuu.framework.security.util.SecurityUtils;
import com.mysql.cj.xdevapi.Client;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.liuuu.admin.client.nav.util.NavClientCategoryUtils.buildTree;

/**
 * 评论
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Service
public class NavCommentServiceImpl extends BaseServiceImpl<NavCommentMapper, NavComment> implements NavCommentService {
    @Autowired
    private NavCommentMapper navCommentMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysParamService sysParamService;

    /**
     * 分页列表
     * @param pageDTO
     * @return
     */
    @Override
    public PageVO<NavCommentVO> pageList(NavCommentPageDTO pageDTO) {
        PageVO<NavCommentVO> pageVO = NavCommentConverter.INSTANCE.convert(super.page(pageDTO));
        List<NavCommentVO> list = pageVO.getList();
        if (CollectionUtils.isEmpty(list)) {
            return pageVO;
        }

        // 获取对应的评论人信息
        List<Long> userIds = list.parallelStream().filter(c -> c.getCreateUserId() != null)
                .map(c -> c.getCreateUserId()).collect(Collectors.toList());
        List<SysUserVO> userList = sysUserService.getUsernameAndNickNameByUserIds(userIds);

        // 构造用户信息
        list.forEach(comment -> {
            SysUserVO user = userList.parallelStream().filter(u -> u.getId().equals(comment.getCreateUserId()))
                    .findFirst().orElse(null);
            if (user != null) {
                comment.setNickName(user.getNickName());
                comment.setUsername(user.getUsername());
            }
        });
        return pageVO;
    }

    /**
     * 通过
     * @param ids
     */
    @Override
    public void pass(Long[] ids) {
        if (ids.length == 0) {
            return;
        }
        NavComment navComment = new NavComment();
        navComment.setStatus(NavCommentStatus.PASS.code);
        LambdaQueryWrapper<NavComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(NavComment::getId, ids);
        wrapper.ne(NavComment::getStatus, NavCommentStatus.PASS.code);
        navCommentMapper.update(navComment, wrapper);
    }

    /**
     * 驳回
     * @param rejectDTO
     */
    @Override
    public void reject(NavCommentRejectDTO rejectDTO) {
        if (CollectionUtils.isEmpty(rejectDTO.getIds())) {
            return;
        }
        NavComment navComment = new NavComment();
        navComment.setStatus(NavCommentStatus.REJECT.code);
        navComment.setRemark(rejectDTO.getRemark());
        LambdaQueryWrapper<NavComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(NavComment::getId, rejectDTO.getIds());
        wrapper.eq(NavComment::getStatus, NavCommentStatus.REJECT.code);
        navCommentMapper.update(navComment, wrapper);
    }

    /**
     * 更新置顶
     * @param id
     * @param hasSticky
     */
    @Override
    public void updateSticky(Long id, Integer hasSticky) {
        NavComment navComment = new NavComment();
        navComment.setHasSticky(hasSticky);
        LambdaQueryWrapper<NavComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NavComment::getId, id);
        wrapper.ne(NavComment::getHasSticky, hasSticky);
        navCommentMapper.update(navComment, wrapper);
    }

    /**
     * 获取评论开启状态
     * @return
     */
    @Override
    public boolean getOpenStatus() {
        String status = sysParamService.getParamValueByKey(NavCommentConstant.NAV_COMMENT_OPEN);
        if (StrUtils.isNotBlank(status) && Boolean.TRUE.equals(Boolean.valueOf(status))) {
            return true;
        }
        return false;
    }

    /**
     * 是否开启不用登录就能提交评论
     * @return
     */
    @Override
    public boolean getNotLoginOpenStatus() {
        String status = sysParamService.getParamValueByKey(NavCommentConstant.NAV_COMMENT_NOT_LOGIN_OPEN);
        if (StrUtils.isNotBlank(status) && Boolean.TRUE.equals(Boolean.valueOf(status))) {
            return true;
        }
        return false;
    }

    /**
     * 树形列表
     * @return
     */
    @Override
    public List<ClientNavCommentTreeVO> treeClient() {
        // 如果未开启,不查询数据
        if (!getOpenStatus()) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<NavComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NavComment::getStatus, NavCommentStatus.PASS.code);
        wrapper.orderByAsc(NavComment::getHasSticky).orderByDesc(NavComment::getGmtCreate);
        List<ClientNavCommentTreeVO> list = NavCommentConverter.INSTANCE.convertClient(navCommentMapper.selectList(wrapper));

        // 获取对应的评论人信息
        List<Long> userIds = list.parallelStream().filter(c -> c.getCreateUserId() != null)
                .map(c -> c.getCreateUserId()).collect(Collectors.toList());
        Map<Long, SysUserVO> userMap = sysUserService.getUsernameAndNickNameByUserIds(userIds)
                .stream().collect(Collectors.toMap(SysUserVO::getId, u -> u));

        // 获取超级管理员用户,用于判断是否是站长
        List<Long> superUserIds = sysUserRoleMapper.selectUserIdByRoleCode(SecurityConstant.SUPER_ADMIN_ROLE_CODE);

        // 构造用户信息
        list.forEach(comment -> {
            SysUserVO user = userMap.get(comment.getCreateUserId());
            if (user != null) {
                comment.setNickName(user.getNickName());
            }
            comment.setWebMaster((superUserIds.stream().filter(superUserId -> superUserId.equals(comment.getCreateUserId()))
                    .findFirst().orElse(null)) != null);
        });
        return buildTree(list, 0L);
    }

    @Override
    public void add(ClientNavCommentAddDTO addDTO) {
        NavComment navComment = NavCommentConverter.INSTANCE.convert(addDTO);
        try {
            LoginUserDetail loginUser = SecurityUtils.getLoginUser();
            navComment.setCreateUserId(loginUser.getUserId());
            navComment.setNickName("");
            navComment.setEmail("");
            // 若为超级管理员则默认通过,否则审核中
            Integer status = loginUser.getRoleCodes().contains(SecurityConstant.SUPER_ADMIN_ROLE_CODE)
                    ? NavCommentStatus.PASS.code : NavCommentStatus.AUDIT.code;
            navComment.setStatus(status);
        } catch (Exception e) {
            // 是否开启了不用登陆就能提交评论
            boolean notLoginOpenStatus = getNotLoginOpenStatus();
            if (!notLoginOpenStatus) {
                throw new BaseException(HttpStatus.UNAUTHORIZED, MessageUtils.message("security.not.login"));
            }
            // 校验参数
            if (StrUtils.isBlank(addDTO.getEmail())) {
                throw new ParamException(MessageUtils.message("nav.comment.email.not.null"));
            }
            if (StrUtils.isBlank(addDTO.getNickName())) {
                throw new ParamException(MessageUtils.message("nav.comment.nick.name.not.null"));
            }
        }
        navCommentMapper.insert(navComment);
    }

    /**
     * 根据父节点的id获取所有子节点
     * @param allList
     * @param parentId
     * @return
     */
    public List<ClientNavCommentTreeVO> buildTree(List<ClientNavCommentTreeVO> allList, Long parentId) {
        List<ClientNavCommentTreeVO> returnList = new ArrayList<>();
        for (ClientNavCommentTreeVO comment : allList) {
            if (parentId != null && parentId.equals(comment.getParentId())) {
                recursive(allList, comment);
                returnList.add(comment);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * @param allList
     * @param comment
     */
    private void recursive(List<ClientNavCommentTreeVO> allList, ClientNavCommentTreeVO comment) {
        // 得到子节点列表
        List<ClientNavCommentTreeVO> childList = getChildList(allList, comment);
        comment.setChildren(childList);
        for (ClientNavCommentTreeVO tChild : childList) {
            if (hasChild(allList, tChild)) {
                recursive(allList, tChild);
            }
        }
    }

    private boolean hasChild(List<ClientNavCommentTreeVO> allList, ClientNavCommentTreeVO comment) {
        return getChildList(allList, comment).size() > 0;
    }

    private List<ClientNavCommentTreeVO> getChildList(List<ClientNavCommentTreeVO> allList, ClientNavCommentTreeVO comment) {
        List<ClientNavCommentTreeVO> childList = new ArrayList<>();
        Iterator<ClientNavCommentTreeVO> it = allList.iterator();
        while (it.hasNext()) {
            ClientNavCommentTreeVO child = it.next();
            if (comment.getId().equals(child.getParentId())) {
                childList.add(child);
            }
        }
        return childList;
    }

}
