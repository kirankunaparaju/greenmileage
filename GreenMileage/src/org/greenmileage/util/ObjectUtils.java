package org.greenmileage.util;

/**
 * Helps work with objects
 * @author Connor Garvey
 * @created Nov 22, 2008, 12:47:34 PM
 * @version 0.0.5
 * @since 0.0.1
 */
public class ObjectUtils {
  /**
   * Determines whether all values are not null
   * @param values The values to check
   * @return True if all values are not null, false otherwise
   */
  public static boolean allAreNotNull(final Object... values) {
    return !anyIsNull(values);
  }
  
  /**
   * Determines whether any value is null
   * @param values The values to check
   * @return True if any value is null, false otherwise
   */
  public static boolean anyIsNull(final Object... values) {
    if (values == null) {
      throw new IllegalArgumentException("values can't be null");
    }
    for (final Object o : values) {
      if (o == null) {
        return true;
      }
    }
    return false;
  }
}
