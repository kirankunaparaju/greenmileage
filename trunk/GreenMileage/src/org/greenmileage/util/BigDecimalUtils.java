package org.greenmileage.util;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Helps work with big decimals
 * @author Connor Garvey
 * @created Nov 22, 2008, 1:09:26 AM
 * @version 0.0.5
 * @since 0.0.1
 */
public class BigDecimalUtils {
  /**
   * Calculates precision by counting the number of integer digits in <code>value</code> and adding
   * the specified number of decimal digits
   * @param value The value for which to calculate
   * @param decimals The number of decimal digits to include
   * @return The precision, the number of integer digits in
   *         <code>value</value> plus the specified number of decimal digits
   */
  public static int calculatePrecision(final String value, final int decimals) {
    if (value == null) {
      throw new IllegalArgumentException("value can't be null");
    }
    if (decimals < 0) {
      throw new IllegalArgumentException("decimals can't be less than 0");
    }
    final int decimalIndex = value.indexOf('.');
    if (decimalIndex != -1) {
      return decimalIndex + decimals;
    }
    else {
      return value.length() + decimals;
    }
  }
  
  /**
   * Reads a decimal value from a string, handling nulls and applying a precision based on the
   * number of decimals to include
   * @param value The value to parse
   * @param decimals The number of decimals to include in the parsed value
   * @return The value as a big decimal or null if the input value was null
   */
  public static BigDecimal parseForDecimalLength(final String value, final int decimals) {
    if (TextUtils.isEmpty(value)) {
      return null;
    }
    return new BigDecimal(value, new MathContext(calculatePrecision(value, decimals)));
  }
  
  /**
   * Gets a string representation of the value
   * @param value The value to represent
   * @return The string representation of the big decimal or an empty string if the value is null
   */
  public static String toString(final BigDecimal value) {
    return value == null ? "" : value.toString();
  }
  
  private BigDecimalUtils() {
  }
}
