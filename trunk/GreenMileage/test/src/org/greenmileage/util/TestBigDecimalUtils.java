package org.greenmileage.util;

import java.math.BigDecimal;
import java.math.MathContext;
import junit.framework.TestCase;

/**
 * @see BigDecimalUtils
 * @author Connor Garvey
 * @created Apr 19, 2009 7:56:33 PM
 * @version 0.0.5
 * @since 0.0.5
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
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthEmpty() {
    assertNull(BigDecimalUtils.parseForDecimalLength(StringUtils.EMPTY, 0));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthNegativeDecimals() {
    try {
      BigDecimalUtils.calculatePrecision(null, -1);
    }
    catch (final IllegalArgumentException ex) {
      return;
    }
    fail("Did not throw IllegalArgumentException");
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthNull() {
    assertNull(BigDecimalUtils.parseForDecimalLength(null, 0));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthZeroDecimals() {
    assertEquals(new BigDecimal("1", new MathContext(1)), BigDecimalUtils.parseForDecimalLength(
        "1", 0));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthZeroDecimals1() {
    assertEquals(new BigDecimal("1", new MathContext(1)), BigDecimalUtils.parseForDecimalLength(
        "1.116", 0));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthZeroInteger() {
    assertEquals(new BigDecimal(".1", new MathContext(1)), BigDecimalUtils.parseForDecimalLength(
        ".1", 1));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthZeroInteger1() {
    assertEquals(new BigDecimal(".2", new MathContext(1)), BigDecimalUtils.parseForDecimalLength(
        ".22234", 1));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthZeroInteger2() {
    assertEquals(new BigDecimal(".256", new MathContext(3)), BigDecimalUtils.parseForDecimalLength(
        ".256", 3));
  }
  
  /**
   * @see BigDecimalUtils#parseForDecimalLength(String, int)
   */
  public void testParseForDecimalLengthZeroInteger3() {
    assertEquals(new BigDecimal(".256", new MathContext(3)), BigDecimalUtils.parseForDecimalLength(
        ".25615615", 3));
  }
  
  /**
   * @see BigDecimalUtils#toString(BigDecimal)
   */
  public void testToStringNotNull() {
    assertEquals("1", BigDecimalUtils.toString(new BigDecimal("1", new MathContext(1))));
  }
  
  /**
   * @see BigDecimalUtils#toString(BigDecimal)
   */
  public void testToStringNull() {
    assertEquals(StringUtils.EMPTY, BigDecimalUtils.toString(null));
  }
}
