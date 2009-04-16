package org.greenmileage.util;

/**
 * Helps work with longs
 * @author Connor Garvey
 * @created Nov 23, 2008, 11:01:13 AM
 * @version 0.0.1
 * @since 0.0.1
 */
public class LongUtils {
  
  /**
   * Converts a zero value to null
   * @param value The value to convert
   * @return The input value or null if the value is null or zero
   */
  public static Long nullZero(Long value) {
    if (value == null) {
      return null;
    }
    if (value.longValue() == 0) {
      return null;
    }
    return value;
  }
}
