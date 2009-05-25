package org.greenmileage.util;

import junit.framework.TestCase;

/**
 * @see ObjectUtils
 * @author Connor Garvey
 * @created May 24, 2009 11:15:59 PM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestObjectUtils extends TestCase {
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullMultipleNotNull() {
    assertTrue(ObjectUtils.allAreNotNull(new Object(), new Object()));
  }
  
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullMultipleNull() {
    assertFalse(ObjectUtils.allAreNotNull((Object)null, (Object)null));
  }
  
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullNotNullNull() {
    assertFalse(ObjectUtils.allAreNotNull(new Object(), (Object)null));
  }
  
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullNull() {
    try {
      ObjectUtils.allAreNotNull((Object[])null);
    }
    catch (final IllegalArgumentException ex) {
      return;
    }
    fail("Did not throw IllegalArgumentException");
  }
  
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullNullNotNull() {
    assertFalse(ObjectUtils.allAreNotNull((Object)null, new Object()));
  }
  
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullOneNotNull() {
    assertTrue(ObjectUtils.allAreNotNull(new Object()));
  }
  
  /**
   * @see ObjectUtils#allAreNotNull(Object...)
   */
  public void testAllAreNotNullOneNull() {
    assertFalse(ObjectUtils.allAreNotNull((Object)null));
  }
}
