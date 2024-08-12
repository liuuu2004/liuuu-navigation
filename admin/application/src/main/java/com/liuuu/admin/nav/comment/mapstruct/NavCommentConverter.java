package com.liuuu.admin.nav.comment.mapstruct;

import com.liuuu.admin.client.nav.dto.ClientNavCommentAddDTO;
import com.liuuu.admin.client.nav.vo.ClientNavCommentTreeVO;
import com.liuuu.admin.nav.comment.po.NavComment;
import com.liuuu.admin.nav.comment.vo.NavCommentVO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 评论对象转换
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
@Mapper
public interface NavCommentConverter {
    NavCommentConverter INSTANCE = Mappers.getMapper(NavCommentConverter.class);

    PageVO<NavCommentVO> convert(PageVO<NavComment> pageVO);

    NavCommentVO convert(NavComment navComment);

    List<ClientNavCommentTreeVO> convertClient(List<NavComment> list);

    NavComment convert(ClientNavCommentAddDTO addDTO);

}
