package com.liuuu.common.core.util.html;

import com.liuuu.common.core.util.string.StrUtils;

/**
 * 转义和反转义工具
 *
 * @Author Liuuu
 * @Date 2024/8/9
 */
public class EscapeUtil {
    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";

    private static final char[][] TEXT = new char[64][];

    static {
        for (int i = 0; i < 64; ++i) {
            TEXT[i] = new char[] {(char) i};
        }

        TEXT['\''] = "&#039;".toCharArray();
        // 双引号
        TEXT['"'] = "&#34;".toCharArray();
        // &符
        TEXT['&'] = "&#38;".toCharArray();
        // 小于号
        TEXT['<'] = "&#60;".toCharArray();
        // 大于号
        TEXT['>'] = "&#62;".toCharArray();
    }

    /**
     * 转义文本中的HTML字符为安全的字符
     * @param text  被转义的文版
     * @return  转义后的文本
     */
    public static String escape(String text) {
        return encode(text);
    }

    /**
     * 还原被转义的HTML特殊字符
     * @param content  包含转义符的HTML内容
     * @return  转换后的字符串
     */
    public static String unescape(String content) {
        return decode(content);
    }

    /**
     * 清除所有HTML标签但不删除标签内的内容
     * @param content  文本
     * @return  清楚标签后的文本
     */
    public static String clean(String content) {
        return new HTMLFilter().filter(content);
    }

    /**
     * Escape编码
     * @param text  被编码的文本
     * @return  编码后的字符
     */
    private static String encode(String text) {
        if (StrUtils.isEmpty(text)) {
            return StrUtils.EMPTY;
        }

        final StringBuilder builder = new StringBuilder(text.length() * 6);
        char c;

        for (int i = 0; i < text.length(); ++i) {
            c = text.charAt(i);
            if (c < 256) {
                builder.append("%");
                if (c < 16) {
                    builder.append("0");
                }
                builder.append(Integer.toString(c, 16));
            } else {
                builder.append("%u");
                if (c <= 0xfff) {
                    builder.append("0");
                }
                builder.append(Integer.toString(c, 16));
            }
        }
        return builder.toString();
    }

    /**
     * Escape解码
     * @param content  被转义的内容
     * @return  解码后的字符串
     */
    public static String decode(String content) {
        if (StrUtils.isEmpty(content)) {
            return content;
        }

        StringBuilder builder = new StringBuilder(content.length());
        int lastPos = 0, pos = 0;
        char ch;

        while (lastPos < content.length()) {
            pos = content.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (content.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
                    builder.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
                    builder.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    builder.append(content.substring(lastPos));
                    lastPos = content.length();
                } else {
                    builder.append(content.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return builder.toString();
    }

}
