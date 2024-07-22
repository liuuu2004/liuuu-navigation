package com.liuuu.common.framework.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
public interface FileService {

    /**
     * 文件上传
     * @param file  待上传的文件
     * @param allowedExtensions  允许上传的文件类型后缀
     * @return  文件全路径
     */
    String upload(MultipartFile file, String[] allowedExtensions);

    /**
     * 文件上传
     * @param file  文件
     * @return  文件全路径
     */
    String upload(MultipartFile file);

    /**
     * 上传图片
     * @param file  文件
     * @return  文件全路径
     */
    String uploadImage(MultipartFile file);

    /**
     * 上传图片
     * @param moduleName  模块名
     * @param file  文件
     * @return  文件全路径
     */
    String uploadPicture(String moduleName, MultipartFile file);
}
