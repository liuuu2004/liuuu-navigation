package com.liuuu.admin.system.dictionary.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.page.vo.PageVO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典分页
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("字典分页")
public class SysDictionaryPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典名称")
    @Query(QueryWay.LIKE)
    private String dictionaryName;

    @ApiModelProperty("字典编码")
    @Query(QueryWay.LIKE)
    private String dictionaryCode;

    @ApiModelProperty("状态")
    private Integer status;
}
