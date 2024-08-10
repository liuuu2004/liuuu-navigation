package com.liuuu.common.core.util.html;

import org.junit.Test;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import static org.junit.Assert.assertEquals;

public class HTMLFilterTest {
    @Test
    public void testSanitizeBaseXSS() {
        String unsafeHtml = "<div><script>alert('XSS');</script><p>Hello <a href='http://example.com'>world</a>!</p></div>";
        String expectedSafeHtml = "<div><p>Hello <a href=\"http://example.com\">world</a>!</p></div>";
        assertEquals(expectedSafeHtml, HTMLFilter.sanitize(unsafeHtml));
    }

    @Test
    public void testSanitizeWithImageXSS() {
        String unsafeHtml = "<img src='javascript:alert(1)'><b>Bold Text</b>";
        String expectedSafeHtml = "<b>Bold Text</b>";
        assertEquals(expectedSafeHtml, HTMLFilter.sanitize(unsafeHtml));
    }

    @Test
    public void testSanitizeWithInlineEvent() {
        String unsafeHtml = "<p>Normal text <a href='http://malicious.com' onclick='stealCookies()'>click here</a></p>";
        String expectedSafeHtml = "<p>Normal text <a href=\"http://malicious.com\">click here</a></p>";
        assertEquals(expectedSafeHtml, HTMLFilter.sanitize(unsafeHtml));
    }

    @Test
    public void testSanitizeWithSafeContent() {
        String safeHtml = "<span style=\"color: red;\">Colored text</span>";
        assertEquals(safeHtml, HTMLFilter.sanitize(safeHtml));
    }

    @Test
    public void testSanitizeWithCustomPolicy() {
        String unsafeHtml = "<div><script>alert('XSS');</script><p>Hello <a href='http://example.com'>world</a>!</p></div>";
        PolicyFactory customPolicy = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);
        String expectedSafeHtml = "<div><p>Hello world!</p></div>";
        assertEquals(expectedSafeHtml, HTMLFilter.sanitize(unsafeHtml, customPolicy));
    }
}
