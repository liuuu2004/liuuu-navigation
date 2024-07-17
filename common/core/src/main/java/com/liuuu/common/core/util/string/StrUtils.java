package com.liuuu.common.core.util.string;

import com.liuuu.common.core.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import java.util.Collection;

/**
 * 字符串工具类
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class StrUtils extends StringUtils {
    private final static String LEFT_BRACKET = "{";

    private final static String RIGHT_BRACKET = "}";

    private final static char UNDERLINE = '_';

    /**
     * 转换文本,{}表示占位符
     * @param str  待转换的字符串
     * @param params  参数列表
     * @return
     */
    public static String format(String str, Object... params) {
        if (isBlank(str) || params == null || params.length == 0) {
            return str;
        }
        StringBuilder builder = new StringBuilder();
        int idx = 0;
        while (str.contains(LEFT_BRACKET) && str.contains(RIGHT_BRACKET)) {
            int start = str.indexOf(LEFT_BRACKET);
            int end = str.indexOf(RIGHT_BRACKET);
            if (idx > (params.length - 1)) {
                builder.append(str);
                break;
            }
            if (end < start || idx > (params.length - 1)) {
                builder.append(str.substring(0, start + 1));
                str = str.substring(start + 1);
                continue;
            }
            builder.append(str.substring(0, start));
            builder.append(params[idx]);
            str = str.substring(end + 1);
            idx++;
        }
        builder.append(str);
        return builder.toString();
    }


    /**
     * 判断是否以http(s)://开头
     * @param link  链接
     * @return  结果
     */
    public static boolean isHttp(String link) {
        return StringUtils.startsWithAny(link, CommonConstant.HTTP, CommonConstant.HTTPS);
    }

    /**
     * 判断是否为空,包含字符串
     * @param value  待判断的值
     * @return  结果
     */
    public static boolean isNullBlank(Object value) {
        return value == null
                || (value instanceof String && isBlank(String.valueOf(value)))
                || (value instanceof Collection && CollectionUtils.isEmpty((Collection) value));
    }

    /**
     * 判断是否为不空,包含字符串
     * @param value  待判断的值
     * @return  结果
     */
    public static boolean isNotNullBlank(Object value) {
        return !isNullBlank(value);
    }

    /**
     * 批量替换前缀
     *
     * @param value  待替换的值
     * @param matchList  替换内容列表
     * @return
     */
    public static String replacePre(String value, String[] matchList) {
        String result = value;
        for (String match : matchList) {
            if (value.startsWith(match)) {
                result = value.replaceFirst(match, "");
            }
        }
        return result;
    }

    public static String toCamelCase(String value) {
        return toCamelCase(value, false);
    }

    /**
     * 下划线转驼峰
     *
     * @param value  待转换的值
     * @param isFirstUpperCase  首字母是否要大写
     * @return
     */
    public static String toCamelCase(String value, boolean isFirstUpperCase) {
        if (isBlank(value)) {
            return value;
        }
        StringBuilder builder = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (UNDERLINE == c) {
                upperCase = true;
            } else if (upperCase) {
                builder.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                builder.append(c);
            }
        }
        if (isFirstUpperCase) {
            return toFirstUpperCase(builder.toString());
        }
        return builder.toString();
    }

    /**
     * 将首字母转为大写
     * @param value  待转的值
     * @return
     */
    public static String toFirstUpperCase(String value) {
        if (isBlank(value)) {
            return value;
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
