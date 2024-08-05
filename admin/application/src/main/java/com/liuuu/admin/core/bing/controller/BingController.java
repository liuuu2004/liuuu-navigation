package com.liuuu.admin.core.bing.controller;

import com.liuuu.admin.core.bing.util.BingUtils;
import com.liuuu.admin.core.bing.vo.BingImageVO;
import com.liuuu.common.core.web.response.ResponseResult;
import com.liuuu.common.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微软Bing图片下载
 *
 * @AUthor Liuuu
 * @Date 2024/8/4
 */
@RestController
@RequestMapping("/bing")
@Api(tags = "微软Bing")
public class BingController extends BaseController {
    @ApiOperation("获取Bing壁纸")
    @GetMapping("/getBingImage")
    public ResponseResult<BingImageVO> getBingImage() {
        String bingOneImage = BingUtils.getBingOneImage();
        BingImageVO bingImageVO = new BingImageVO(bingOneImage);
        return ResponseResult.success(bingImageVO);
    }
}
