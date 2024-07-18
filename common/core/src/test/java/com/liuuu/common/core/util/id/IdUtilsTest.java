package com.liuuu.common.core.util.id;

import org.junit.Test;
import static org.junit.Assert.*;
public class IdUtilsTest {

    @Test
    public void testSimpleUUID() {
        String uuid = IdUtils.simpleUUID();
        assertNotNull(uuid);
        assertEquals(uuid.length(), 32);
        assertTrue(uuid.matches("[a-zA-Z0-9]+"));
    }
}
