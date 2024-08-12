package com.liuuu.admin.nav.comment.controller;

import com.liuuu.admin.nav.comment.dto.NavCommentPageDTO;
import com.liuuu.admin.nav.comment.service.NavCommentService;
import com.liuuu.admin.nav.comment.vo.NavCommentVO;
import com.liuuu.common.core.enums.YesNo;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.log.annotation.Log;
import com.liuuu.common.log.enums.BusinessType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 评论
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/nav/comment")
public class NavCommentController {
    @Autowired
    private NavCommentService navCommentService;

    @ApiOperation("分页列表")
    @GetMapping("/page")
    @PreAuthorize("@auth.hasUrl()")
    public ResponseResult<PageVO<NavCommentVO>> page(NavCommentPageDTO pageDTO) {
        PageVO<NavCommentVO> pageVO = navCommentService.pageList(pageDTO);
        return ResponseResult.success(pageVO);
    }

    @ApiOperation("通过")
    @PutMapping("/pass/{ids}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "评论-通过", businessType = BusinessType.UPDATE)
    public ResponseResult pass(@PathVariable Long[] ids) {
        navCommentService.pass(ids);
        return ResponseResult.success();
    }

    @ApiOperation("置顶")
    @PutMapping("/sticky/{id}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "评论-置顶", businessType = BusinessType.UPDATE)
    public ResponseResult sticky(@PathVariable Long id) {
        navCommentService.updateSticky(id, YesNo.YES.code);
        return ResponseResult.success();
    }

    @ApiOperation("取消置顶")
    @PutMapping("/cancelSticky/{id}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "评论-取消置顶", businessType = BusinessType.UPDATE)
    public ResponseResult cancelSticky(@PathVariable Long id) {
        navCommentService.updateSticky(id, YesNo.NO.code);
        return ResponseResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@auth.hasUrl()")
    @Log(moduleName = "评论", businessType = BusinessType.DELETE)
    public ResponseResult delete(@PathVariable Long[] ids) {
        navCommentService.removeByIds(Arrays.asList(ids));
        return ResponseResult.success();
    }

}
