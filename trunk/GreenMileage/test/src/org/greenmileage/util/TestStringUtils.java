package org.greenmileage.util;

import junit.framework.TestCase;

/**
 * @see StringUtils
 * @author Connor Garvey
 * @created May 25, 2009 8:43:18 PM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestStringUtils extends TestCase {
  /**
   * @see StringUtils#nullEmpty(String)
   */
  public void testNullEmptyBlank() {
    assertEquals(" ", StringUtils.nullEmpty(" "));
  }
  
  /**
   * @see StringUtils#nullEmpty(String)
   */
  public void testNullEmptyEmpty() {
    assertNull(StringUtils.nullEmpty(StringUtils.EMPTY));
  }
  
  /**
   * @see StringUtils#nullEmpty(String)
   */
  public void testNullEmptyNonEmpty() {
    assertEquals("a", StringUtils.nullEmpty("a"));
  }
  
  /**
   * @see StringUtils#nullEmpty(String)
   */
  public void testNullEmptyNull() {
    assertNull(StringUtils.nullEmpty(null));
  }
}
