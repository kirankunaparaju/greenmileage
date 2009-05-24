package org.greenmileage.util;

import junit.framework.TestCase;

/**
 * @see ArrayUtils
 * @author Connor Garvey
 * @created May 22, 2009 9:56:40 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestArrayUtils extends TestCase {
  /**
   * @see ArrayUtils#isZeroLength(Object[])
   */
  public void testIsZeroLengthNonZeroLength() {
    assertFalse(ArrayUtils.isZeroLength(new Object[1]));
  }
  
  /**
   * @see ArrayUtils#isZeroLength(Object[])
   */
  public void testIsZeroLengthNull() {
    assertTrue(ArrayUtils.isZeroLength(null));
  }
  
  /**
   * @see ArrayUtils#isZeroLength(Object[])
   */
  public void testIsZeroLengthZeroLength() {
    assertTrue(ArrayUtils.isZeroLength(new Object[0]));
  }
}
