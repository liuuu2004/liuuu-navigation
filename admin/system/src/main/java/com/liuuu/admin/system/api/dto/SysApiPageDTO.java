package com.liuuu.admin.system.api.dto;

import com.liuuu.common.framework.mybatis.page.dto.PageDTO;
import com.liuuu.common.framework.mybatis.plugin.annotation.Query;
import com.liuuu.common.framework.mybatis.plugin.enums.QueryWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 接口分页查询
 *
 * @Author Liuuu
 * @Date 2024/7/31
 */
@Data
@ApiModel("接口分页")
public class SysApiPageDTO extends PageDTO implements Serializable {
    private static final long serialVersionUIDD = 1L;

    @ApiModelProperty("接口名称")
    @Query(QueryWay.LIKE)
    private String apiName;

    @ApiModelProperty("接口地址")
    @Query(QueryWay.LIKE)
    private String apiUrl;

    @ApiModelProperty("接口请求方式")
    private String apiMethod;

    @ApiModelProperty(value = "所属分类", required = true)
    @NotNull(message = "所属分类不能为空")
    private Long apiCategoryId;

    @ApiModelProperty("状态")
    private Integer status;
}
