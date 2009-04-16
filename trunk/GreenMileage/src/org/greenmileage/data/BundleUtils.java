package org.greenmileage.data;

import android.os.Bundle;

/**
 * Helps work with {@link Bundle}s
 * @author Connor Garvey
 * @created Nov 16, 2008, 9:34:37 AM
 * @version 0.0.1
 * @since 0.0.1
 */
public class BundleUtils {
  
  /**
   * Puts an int in the bundle, handling nulls
   * @param key The key for the value
   * @param value The value to add
   * @param bundle The bundle to which to add the value
   */
  public static void putInt(String key, Integer value, Bundle bundle) {
    if (value == null) {
      return;
    }
    bundle.putInt(key, value.intValue());
  }
  
  /**
   * Puts a the  in the bundle, handling nulls
   * @param key The key for the value
   * @param value The value to add
   * @param bundle The bundle to which to add the value
   */
  public static void putObjectToString(String key, Object value, Bundle
      bundle) {
    if (value == null) {
      return;
    }
    bundle.putString(key, value.toString());
  }
  
  /**
   * Puts a long in the bundle, handling nulls
   * @param key The key for the value
   * @param value The value to add
   * @param bundle The bundle to which to add the value
   */
  public static void putLong(String key, Long value, Bundle bundle) {
    if (value == null) {
      return;
    }
    bundle.putLong(key, value.longValue());
  }
  
  /**
   * Puts a string in the bundle, handling nulls
   * @param key The key for the value
   * @param value The value to add
   * @param bundle The bundle to which to add the value
   */
  public static void putString(String key, String value, Bundle bundle) {
    if (value == null) {
      return;
    }
    bundle.putString(key, value);
  }
}
