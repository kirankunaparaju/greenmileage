package org.greenmileage.util;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @see ArrayUtils
 * @author Connor Garvey
 * @created May 22, 2009 9:56:40 AM
 * @version 0.0.1
 * @since 0.0.1
 */
public class TestArrayUtils {
  /**
   * @see ArrayUtils#isZeroLength(Object[])
   */
  @Test
  public void testIsZeroLengthNonZeroLength() {
    assertFalse(ArrayUtils.isZeroLength(new Object[1]));
  }
  
  /**
   * @see ArrayUtils#isZeroLength(Object[])
   */
  @Test
  public void testIsZeroLengthNull() {
    assertTrue(ArrayUtils.isZeroLength(null));
  }
  
  /**
   * @see ArrayUtils#isZeroLength(Object[])
   */
  @Test
  public void testIsZeroLengthZeroLength() {
    assertTrue(ArrayUtils.isZeroLength(new Object[0]));
  }
}
