package com.liuuu.admin.system.dictionary.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 字典数据分页
 *
 * @Author Liuuu
 * @Date 2024/8/4
 */
@Data
@ApiModel("字典数据分页")
public class SysDictionaryDataPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典标签")
    @Query(QueryWay.LIKE)
    private String dictionaryLabel;

    @ApiModelProperty(value = "所属字典", required = true)
    @NotNull(message = "所属字典不能为空")
    private Long dictionaryId;

    @ApiModelProperty("状态")
    private Integer status;
}
