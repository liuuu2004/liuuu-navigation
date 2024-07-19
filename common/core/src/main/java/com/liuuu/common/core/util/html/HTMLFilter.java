//package com.liuuu.common.core.util.html;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.regex.Pattern;
//
///**
// * HTML过滤器,用于去除XSS漏洞隐患
// *
// * @Author Liuuu
// * @Date 2024/7/18
// */
//public class HTMLFilter {
//    /**
//     * regex flag union representing /si modifiers in php
//     */
//    private static final int REGEX_FLAGS_SI = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
//    private static final Pattern P_COMMENTS = Pattern.compile("<!--(.*?)-->", Pattern.DOTALL);
//    private static final Pattern P_COMMENT = Pattern.compile("^!--(.*)--$", REGEX_FLAGS_SI);
//    private static final Pattern P_TAGS = Pattern.compile("<(.*?)>", Pattern.DOTALL);
//    private static final Pattern P_END_TAG = Pattern.compile("^/([a-z0-9]+)", REGEX_FLAGS_SI);
//    private static final Pattern P_START_TAG = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", REGEX_FLAGS_SI);
//    private static final Pattern P_QUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", REGEX_FLAGS_SI);
//    private static final Pattern P_UNQUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", REGEX_FLAGS_SI);
//    private static final Pattern P_PROTOCOL = Pattern.compile("^([^:]+):", REGEX_FLAGS_SI);
//    private static final Pattern P_ENTITY = Pattern.compile("&#(\\d+);?");
//    private static final Pattern P_ENTITY_UNICODE = Pattern.compile("&#x([0-9a-f]+);?");
//    private static final Pattern P_ENCODE = Pattern.compile("%([0-9a-f]{2});?");
//    private static final Pattern P_VALID_ENTITIES = Pattern.compile("&([^&;]*)(?=(;|&|$))");
//    private static final Pattern P_VALID_QUOTES = Pattern.compile("(>|^)([^<]+?)(<|$)", Pattern.DOTALL);
//    private static final Pattern P_END_ARROW = Pattern.compile("^>");
//    private static final Pattern P_BODY_TO_END = Pattern.compile("<([^>]*?)(?=<|$)");
//    private static final Pattern P_XML_CONTENT = Pattern.compile("(^|>)([^<]*?)(?=>)");
//    private static final Pattern P_STRAY_LEFT_ARROW = Pattern.compile("<([^>]*?)(?=<|$)");
//    private static final Pattern P_STRAY_RIGHT_ARROW = Pattern.compile("(^|>)([^<]*?)(?=>)");
//    private static final Pattern P_AMP = Pattern.compile("&");
//    private static final Pattern P_QUOTE = Pattern.compile("\"");
//    private static final Pattern P_LEFT_ARROW = Pattern.compile("<");
//    private static final Pattern P_RIGHT_ARROW = Pattern.compile(">");
//    private static final Pattern P_BOTH_ARROWS = Pattern.compile("<>");
//
//    private static final ConcurrentMap<String, Pattern> P_REMOVE_PAIR_BLANKS = new ConcurrentHashMap<>();
//    private static final ConcurrentMap<String, Pattern> P_REMOVE_SELF_BLANKS = new ConcurrentHashMap<>();
//
//    private final Map<String, List<String>> vAllowed;
//
//    private final Map<String, Integer> vTagCounts = new HashMap<>();
//
//    private final String[] vSelfClosingTags;
//
//    private final String[] vNeedClosingTags;
//
//    private final String[] vDisallowed;
//
//    private final String[] vProtocalAtts;
//
//    private final String vAllowedProtocals;
//}
