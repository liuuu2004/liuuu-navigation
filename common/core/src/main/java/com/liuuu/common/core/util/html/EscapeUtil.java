//package com.liuuu.common.core.util.html;
//
///**
// * 转义和反转义工具类
// *
// * @Author Liuuu
// * @Date 2024/7/18
// */
//public class EscapeUtil {
//
//    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";
//
//    private static final char[][] TEXT = new char[64][];
//
//    static {
//        for (int i = 0; i < 64; i++) {
//            TEXT[i] = new char[]{(char) i};
//        }
//
//        TEXT['\''] = "&#039;".toCharArray();
//        TEXT['"'] = "&#34;".toCharArray();
//        TEXT['&'] = "&#38;".toCharArray();
//        TEXT['<'] = "&#60;".toCharArray();
//        TEXT['>'] = "&#62;".toCharArray();
//    }
//
//    public static String escape(String text) {
//        return encode(text);
//    }
//
//    public static String unescape(String content) {
//        return decode(content);
//    }
//
//    public static String clean(String content) {
//        return new HTMLFilter
//    }
//}
