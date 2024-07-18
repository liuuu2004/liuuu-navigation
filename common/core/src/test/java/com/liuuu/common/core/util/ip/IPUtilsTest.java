package com.liuuu.common.core.util.ip;

import static org.junit.Assert.*;
import org.junit.Test;

public class IPUtilsTest {

    @Test
    public void testInternalIP() {
        assertTrue(IPUtils.internalIP("192.168.1.1"));
        assertFalse(IPUtils.internalIP("223.255.255.255"));
//        assertFalse(IPUtils.internalIP("1.1.1.1.1"));
    }

    @Test
    public void testIsUnknown() {
        assertTrue(IPUtils.isUnknown(null));
        assertTrue(IPUtils.isUnknown(""));
        assertFalse(IPUtils.isUnknown("127.0.0.1"));
//        assertTrue(IPUtils.isUnknown("1.1.1.1.1"));
    }

    @Test
    public void testGetMultistageReverseProxyIP() {
        String ip = "192.168.1.1, unknown, 127.0.0.1";
        String proxyIp = IPUtils.getMultistageReverseProxyIP(ip);
        assertNotNull(proxyIp);
        assertNotEquals("unknown", proxyIp);
    }
}
