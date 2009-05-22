package org.greenmileage.util;

import junit.framework.TestCase;

/**
 * @see BigDecimalUtils
 * @author Connor Garvey
 * @created Apr 19, 2009 7:56:33 PM
 * @version 0.0.1
 * @since 0.0.1
 */
public class TestBigDecimalUtils extends TestCase {
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionMultipleInteger() {
    assertEquals(3, BigDecimalUtils.calculatePrecision("123", 0));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionMultipleIntegerMultipleDecimal() {
    assertEquals(9, BigDecimalUtils.calculatePrecision("6421", 5));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionMultipleIntegerSingleDecimal() {
    assertEquals(6, BigDecimalUtils.calculatePrecision("85251", 1));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionNegativeDigits() {
    try {
      BigDecimalUtils.calculatePrecision("1", -1);
    }
    catch (final IllegalArgumentException ex) {
      return;
    }
    fail("Did not throw IllegalArgumentException");
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionNullNumber() {
    try {
      BigDecimalUtils.calculatePrecision(null, 0);
    }
    catch (final IllegalArgumentException ex) {
      return;
    }
    fail("Did not throw IllegalArgumentException");
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionSingleInteger() {
    assertEquals(1, BigDecimalUtils.calculatePrecision("1", 0));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionSingleIntegerMultipleDecimal() {
    assertEquals(3, BigDecimalUtils.calculatePrecision("1", 2));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  public void testCalculatePrecisionSingleIntegerSingleDecimal() {
    assertEquals(2, BigDecimalUtils.calculatePrecision("1", 1));
  }
}
