package org.greenmileage.util;

/**
 * Helps work with arrays
 * @author Connor Garvey
 * @created May 22, 2009 9:59:00 AM
 * @version 0.0.1
 * @since 0.0.1
 */
public class ArrayUtils {
  /**
   * Determines whether an array is null or has a length of zero
   * @param items the array to test
   * @return true if the array is null or has length 0, false otherwise
   */
  public static boolean isZeroLength(final Object[] items) {
    return (items == null) || (items.length == 0);
  }
}
