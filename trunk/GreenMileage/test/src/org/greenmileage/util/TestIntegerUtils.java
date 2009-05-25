package org.greenmileage.util;

import junit.framework.TestCase;

/**
 * @see IntegerUtils
 * @author Connor Garvey
 * @created May 24, 2009 10:55:25 PM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestIntegerUtils extends TestCase {
  /**
   * @see IntegerUtils#nullZero(Integer)
   */
  public void testNullZeroNegative() {
    assertEquals(new Integer(-42), IntegerUtils.nullZero(-42));
  }
  
  /**
   * @see IntegerUtils#nullZero(Integer)
   */
  public void testNullZeroNull() {
    assertNull(IntegerUtils.nullZero(null));
  }
  
  /**
   * @see IntegerUtils#nullZero(Integer)
   */
  public void testNullZeroPositive() {
    assertEquals(new Integer(42), IntegerUtils.nullZero(42));
  }
  
  /**
   * @see IntegerUtils#nullZero(Integer)
   */
  public void testNullZeroZero() {
    assertNull(IntegerUtils.nullZero(0));
  }
  
  /**
   * @see IntegerUtils#toString(Integer)
   */
  public void testToStringNotNull() {
    assertEquals("67", IntegerUtils.toString(67));
  }
  
  /**
   * @see IntegerUtils#toString(Integer)
   */
  public void testToStringNull() {
    assertEquals(StringUtils.EMPTY, IntegerUtils.toString(null));
  }
}
