package com.liuuu.common.framework.file.service.impl;

import com.liuuu.common.core.util.message.MessageUtils;
import com.liuuu.common.core.util.string.StrUtils;
import com.liuuu.common.framework.file.exception.FileException;
import com.liuuu.common.framework.file.property.FileUploadProperty;
import com.liuuu.common.framework.file.service.FileService;
import com.liuuu.common.framework.file.util.FileUploadUtils;
import com.liuuu.common.framework.file.util.MimeTypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件服务
 *
 * @Author Liuuu
 * @Date 2024/7/22
 */
@Service
public class FileServiceImpl implements FileService {

    private final static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileUploadProperty fileUploadProperty;

    @Override
    public String upload(MultipartFile file) {
        return upload(fileUploadProperty.getBasePath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
    }

    @Override
    public String upload(MultipartFile file, String[] allowedExtensions) {
        return upload(fileUploadProperty.getBasePath(), file, allowedExtensions);
    }

    @Override
    public String uploadImage(MultipartFile file) {
        return upload(fileUploadProperty.getBasePath(), file, MimeTypeUtils.IMAGE_EXTENSION);
    }

    @Override
    public String uploadPicture(String moduleName, MultipartFile file) {
        return upload(fileUploadProperty.getBasePath() + "/" + moduleName, file, MimeTypeUtils.IMAGE_EXTENSION);
    }

    /**
     * 文件上传
     * @param baseDirectory  基本目录
     * @param file  文件
     * @param allowedExtensions  允许上传的文件类型
     * @return  文件全路径
     */
    private String upload(String baseDirectory, MultipartFile file, String[] allowedExtensions) {
        // 校验文件名长度是否合法
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > fileUploadProperty.getNameMaxLength()) {
            String message = StrUtils.format(MessageUtils.message("file.upload.file.name.max.length"),
                    fileUploadProperty.getNameMaxLength());
            throw new FileException(message);
        }

        // 校验文件大小是否合法
        long fileSize = file.getSize();
        if (fileSize != -1 && fileSize > fileUploadProperty.getFileMaxSize()) {
            throw new FileException(MessageUtils.message("file.upload.file.size.max"));
        }

        // 校验文件类型是否合法
        String extension = FileUploadUtils.getExtension(file);
        boolean isAllowedExtension = FileUploadUtils.isAllowedExtension(extension, allowedExtensions);
        if (!isAllowedExtension) {
            throw new FileException(MessageUtils.message("file.upload.file.type.not.allow"));
        }
        // 获取文件上传路径
        String filePathName = FileUploadUtils.getUploadFilePath(extension);
        File absoluteFile = null;
        try {
            absoluteFile = FileUploadUtils.getAbsoluteFile(baseDirectory, filePathName);
            file.transferTo(absoluteFile);
        } catch (IOException e) {
            log.error("文件上传失败: {}", e.getMessage());
            throw new FileException(MessageUtils.message("file.upload.fail"));
        }
        String fileMapPath = getFileMapPath(baseDirectory, filePathName);
        return fileMapPath;
    }

    private String getFileMapPath(String baseDirectory, String filePathName) {
        // 文件完整路径
        String fileFullPath = baseDirectory + filePathName;

        // 去除基本路径
        if (StrUtils.startsWith(fileFullPath, fileUploadProperty.getBasePath())) {
            fileFullPath = fileFullPath.replaceFirst(fileUploadProperty.getBasePath(), "");
        }

        // 文件域名地址不为空则拼接域名和路径
        if (StrUtils.isNotBlank(fileUploadProperty.getDomain())) {
            return fileUploadProperty.getDomain() + fileFullPath;
        }

        // 拼接项目映射地址
        return fileUploadProperty.getMapPrefix() + fileFullPath;
    }
}
