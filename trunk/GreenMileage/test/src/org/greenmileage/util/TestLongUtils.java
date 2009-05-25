package org.greenmileage.util;

import junit.framework.TestCase;

/**
 * @see LongUtils
 * @author Connor Garvey
 * @created May 24, 2009 10:55:25 PM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestLongUtils extends TestCase {
  /**
   * @see LongUtils#nullZero(Long)
   */
  public void testNullZeroNegative() {
    assertEquals(new Long(-42), LongUtils.nullZero(-42L));
  }
  
  /**
   * @see LongUtils#nullZero(Long)
   */
  public void testNullZeroNull() {
    assertNull(LongUtils.nullZero(null));
  }
  
  /**
   * @see LongUtils#nullZero(Long)
   */
  public void testNullZeroPositive() {
    assertEquals(new Long(42), LongUtils.nullZero(42L));
  }
  
  /**
   * @see LongUtils#nullZero(Long)
   */
  public void testNullZeroZero() {
    assertNull(LongUtils.nullZero(0L));
  }
}
