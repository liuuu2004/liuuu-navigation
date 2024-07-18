package com.liuuu.common.core.util.string;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class StrUtilsTest {

    @Test
    public void testFormat() {
        String formatted = StrUtils.format("Hello, {}", "World!");
        assertEquals("Hello, World!", formatted);
    }

    @Test
    public void testIsHttp() {
        assertTrue(StrUtils.isHttp("http://example.com"));
        assertTrue(StrUtils.isHttp("https://example.com"));
        assertFalse((StrUtils.isHttp("ftp://example.com")));
    }

    @Test
    public void testIsNullBlank() {
        assertTrue(StrUtils.isNullBlank(null));
        assertTrue(StrUtils.isNullBlank(""));
        assertTrue(StrUtils.isNullBlank(" "));
        assertFalse(StrUtils.isNullBlank("test"));
    }

    @Test
    public void testIsNotNullBlank() {
        assertFalse(StrUtils.isNotNullBlank(null));
        assertFalse(StrUtils.isNotNullBlank(""));
        assertFalse(StrUtils.isNotNullBlank(" "));
        assertTrue(StrUtils.isNotNullBlank("test"));
    }

    @Test
    public void testIsArrayContains() {
        String[] array = {"a", "b", "c"};
        assertTrue(StrUtils.isArrayContains(array, "a"));
        assertFalse(StrUtils.isArrayContains(array, "d"));
        assertFalse(StrUtils.isArrayContains(array, "aa"));
    }

    @Test
    public void testMatches() {
        assertTrue(StrUtils.matches("example.com", Arrays.asList("*.com")));
        assertFalse(StrUtils.matches("example.org", Arrays.asList("*.com")));
    }

}

