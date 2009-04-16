package org.greenmileage.util;

import java.math.MathContext;
import java.math.BigDecimal;

/**
 * Helps work with big decimals
 * @author Connor Garvey
 * @created Nov 22, 2008, 1:09:26 AM
 * @version 0.0.1
 * @since 0.0.1
 */
public class BigDecimalUtils {
  private BigDecimalUtils() {
  }
  
  /**
   * Calculates the precision of a value, including decimal digits
   * @param value The value for which to calculate
   * @param decimals The number of decimal digits to include
   * @return The precision
   */
  public static int calculatePrecision(String value, int decimals) {
    if (value.contains(".")) {
      return value.substring(0, value.indexOf('.')).length() + decimals;
    }
    else {
      return value.length() + decimals;
    }
  }
  
  /**
   * Reads a decimal value from a string, handling nulls and applying a
   * precision based on the number of decimals to include
   * @param value The value to parse
   * @param decimals The number of decimals to include in the parsed value
   * @return The value as a big decimal or null if the input value was null
   */
  public static BigDecimal parseForDecimalLength(String value, int decimals) {
    if (value == null) {
      return null;
    }
    return new BigDecimal(value, new MathContext(calculatePrecision(value,
        decimals)));
  }
  
  /**
   * Gets a string representation of the value
   * @param value The value to represent
   * @return The string representation of the big decimal or an empty string if
   *    the value is null
   */
  public static String toString(BigDecimal value) {
    return value == null ? "" : value.toString();
  }
}
