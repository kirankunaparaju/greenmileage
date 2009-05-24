/**
 * 
 */
package org.greenmileage.util;

import android.content.ContentValues;

/**
 * Helps work with {@link ContentValues}
 * @author Connor Garvey
 * @created Nov 22, 2008, 10:11:41 PM
 * @version 0.0.1
 * @since 0.0.1
 */
public class ContentValuesUtils {
  /**
   * Replaces the value at the specified key with null if the value is an empty string. If the key
   * doesn't exist, this method will not have an effect.
   * @param values The content values
   * @param key The key at which to perform the replacement
   * @throws ClassCastException Thrown if the value at the key is not a string
   */
  public static void nullEmpty(final ContentValues values, final String key)
      throws ClassCastException {
    if (values == null) {
      throw new IllegalArgumentException("values can't be null");
    }
    final Object o = values.get(key);
    if (o == null) {
      return;
    }
    final String value = (String)o;
    values.put(key, StringUtils.nullEmpty(value));
  }
  
  /**
   * Replaces the value at the specified keys with null if the value is an empty string. If any of
   * the keys don't exist, this method will not have an effect.
   * @param values The content values
   * @param keys The keys at which to perform the replacement
   * @throws ClassCastException Thrown if any of the values at the keys are not strings
   */
  public static void nullEmpty(final ContentValues values, final String... keys)
      throws ClassCastException {
    for (final String key : keys) {
      nullEmpty(values, key);
    }
  }
}
