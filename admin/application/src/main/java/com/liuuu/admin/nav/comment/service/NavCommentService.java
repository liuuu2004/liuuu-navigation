package com.liuuu.admin.nav.comment.service;

import com.liuuu.admin.client.nav.dto.ClientNavCommentAddDTO;
import com.liuuu.admin.client.nav.vo.ClientNavCommentTreeVO;
import com.liuuu.admin.nav.comment.dto.NavCommentPageDTO;
import com.liuuu.admin.nav.comment.dto.NavCommentRejectDTO;
import com.liuuu.admin.nav.comment.po.NavComment;
import com.liuuu.admin.nav.comment.vo.NavCommentVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.web.service.BaseService;

import java.util.List;

/**
 * 评论
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
public interface NavCommentService extends BaseService<NavComment> {
    /**
     * 分页列表
     * @param pageDTO
     * @return
     */
    PageVO<NavCommentVO> pageList(NavCommentPageDTO pageDTO);

    /**
     * 通过
     * @param ids
     */
    void pass(Long[] ids);

    /**
     * 驳回
     * @param rejectDTO
     */
    void reject(NavCommentRejectDTO rejectDTO);

    /**
     * 更新置顶
     * @param id
     * @param hasSticky
     */
    void updateSticky(Long id, Integer hasSticky);

    /**
     * 获取评论开启状态
     * @return
     */
    boolean getOpenStatus();

    /**
     * 是否开启不用登陆就能提交评论
     * @return
     */
    boolean getNotLoginOpenStatus();

    /**
     * 树形列表
     * @return
     */
    List<ClientNavCommentTreeVO> treeClient();

    /**
     * 新增
     * @param addDTO
     */
    void add(ClientNavCommentAddDTO addDTO);

}
