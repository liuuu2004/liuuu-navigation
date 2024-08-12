package com.liuuu.admin.nav.picture.service;

import com.liuuu.admin.nav.picture.po.NavPicture;
import com.liuuu.common.framework.web.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导航图片
 *
 * @Author Liuuu
 * @Date 2024/8/12
 */
public interface NavPictureService extends BaseService<NavPicture> {
    /**
     * 获取图片上传路径
     * @param modulePath  模块路径
     * @param file  文件
     * @return
     */
    NavPicture getUploadFilePath(String modulePath, MultipartFile file);
}
