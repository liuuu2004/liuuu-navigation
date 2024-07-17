package com.liuuu.common.core.util.ip;

import com.liuuu.common.core.util.string.StrUtils;
import sun.jvm.hotspot.oops.UnknownOopException;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取IP工具类
 *
 * @Author Liuuu
 * @Date 2024/7/17
 */
public class IPUtils {

    /**
     * 获取客户端IP
     * @param request  请求对象
     * @return  IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIP(ip);
    }

    /**
     * 检查是否为内部IP地址
     * @param ip  IP地址
     * @return  结果
     */
    public static boolean internalIP(String ip) {
        byte[] address = textToNumericFormatV4(ip);
        return internalIP(address) || "127.0.0.1".equals(ip);
    }

    private static boolean internalIP(byte[] address) {
        if (address == null || address.length < 2) {
            return true;
        }
        final byte b0 = address[0];
        final byte b1 = address[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0a;

        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xac;
        final byte SECTION_3 = 0x10;
        final byte SECTION_4 = 0x1f;

        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xc0;
        final byte SECTION_6 = (byte) 0xa8;

        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }

    public static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return null;
        }
        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xff);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xffff) >> 8 & 0xff);
                    bytes[3] = (byte) (l & 0xff);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L)) {
                        return null;
                    }
                    bytes[0] = (byte) (l & 0xff);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L)) {
                        return null;
                    }
                    bytes[1] = (byte) (l >> 16 & 0xff);
                    bytes[2] = (byte) ((l & 0xffff) >> 8 & 0xff);
                    bytes[3] = (byte) (l & 0xff);
                    break;
                case 3:

            }
        }
    }

    /**
     * 检测给定字符串是否为未知,多用户检测HTTP请求
     * @param checkString
     * @return  未知(unknown或空)
     */
    public static boolean isUnknown(String checkString) {
        return StrUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }

    /**
     * 获取主机名
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "Unknown";
    }

    /**
     * 获取IP地址
     * @return
     */
    public static String getHostIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "127.0.0.1";
    }

    /**
     * 从多级反向代理中获得第一个非Unknown IP地址
     * @param ip
     * @return
     */
    public static String getMultistageReverseProxyIP(String ip) {
        if (ip != null && ip.indexOf(",") > 0) {
            final String[] ips = ip.trim().split(",");
            for (String subIP : ips) {
                if (false == isUnknown(subIP)) {
                    ip = subIP;
                    break;
                }
            }
        }
        return ip;
    }
}
