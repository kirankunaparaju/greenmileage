package org.greenmileage.util;

/**
 * Helps work with integers
 * @author Connor Garvey
 * @created Nov 22, 2008, 12:54:19 AM
 * @version 0.0.1
 * @since 0.0.1
 */
public class IntegerUtils {
  private IntegerUtils() {
  }
  
  /**
   * Converts a zero value to null
   * @param value The value to convert
   * @return The input value or null if the value is null or zero
   */
  public static Integer nullZero(Integer value) {
    if (value == null) {
      return null;
    }
    if (value.intValue() == 0) {
      return null;
    }
    return value;
  }
  
  /**
   * Gets a string representation of an integer
   * @param integer The integer to convert to a string
   * @return The integer as a string or an empty string if the integer is null
   */
  public static String toString(Integer integer) {
    return integer == null ? "" : integer.toString();
  }
}
