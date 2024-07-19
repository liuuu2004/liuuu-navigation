package com.liuuu.common.framework.file.util;

/**
 * 媒体类型工具类
 *
 * @Author Liuuu
 * @Data 2024/7/19
 */
public class MimeTypeUtils {
    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";

    public static final String[] IMAGE_EXTENSION = {
            "bmp", "gif", "jpg", "jpeg", "png", "ico", "svg"
    };

    public static final String[] FLASH_EXTENSION = {
            "swf", "flv"
    };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            "bmp", "gif", "jpg", "jpeg", "png",
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            "rar", "zip", "gz", "bz2",
            "pdf"
    };

    public static final String[] HTML_EXTENSION = {
            "html", "htm"
    };

    public static String getExtension(String fileContentType) {
        switch (fileContentType) {
            case IMAGE_PNG:
                return "png";
            case IMAGE_JPG:
                return "jpg";
            case IMAGE_BMP:
                return "bmp";
            case IMAGE_GIF:
                return "gif";
            case IMAGE_JPEG:
                return "jpeg";
            default:
                return "";
        }
    }
}
