package com.liuuu.common.core.util.message;

import com.liuuu.common.core.util.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源
 *
 * @Author Liuuu
 * @Date 2024/7/18
 */
public class MessageUtils {
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
