package org.greenmileage.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @see BigDecimalUtils
 * @author Connor Garvey
 * @created Apr 19, 2009 7:56:33 PM
 * @version 0.0.1
 * @since 0.0.1
 */
public class TestBigDecimalUtils {
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test
  public void testCalculatePrecisionMultipleInteger() {
    assertEquals(3, BigDecimalUtils.calculatePrecision("123", 0));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test
  public void testCalculatePrecisionMultipleIntegerMultipleDecimal() {
    assertEquals(9, BigDecimalUtils.calculatePrecision("6421", 5));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test
  public void testCalculatePrecisionMultipleIntegerSingleDecimal() {
    assertEquals(6, BigDecimalUtils.calculatePrecision("85251", 1));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCalculatePrecisionNegativeDigits() {
    BigDecimalUtils.calculatePrecision("1", -1);
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCalculatePrecisionNullNumber() {
    BigDecimalUtils.calculatePrecision(null, 0);
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test
  public void testCalculatePrecisionSingleInteger() {
    assertEquals(1, BigDecimalUtils.calculatePrecision("1", 0));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test
  public void testCalculatePrecisionSingleIntegerMultipleDecimal() {
    assertEquals(3, BigDecimalUtils.calculatePrecision("1", 2));
  }
  
  /**
   * @see BigDecimalUtils#calculatePrecision(String, int)
   */
  @Test
  public void testCalculatePrecisionSingleIntegerSingleDecimal() {
    assertEquals(2, BigDecimalUtils.calculatePrecision("1", 1));
  }
}
