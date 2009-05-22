package org.greenmileage.util;

import android.text.TextUtils;

/**
 * Helps work with SQL
 * @author Connor Garvey
 * @created May 19, 2009 9:57:19 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class SQLUtils {
  /**
   * Builds a where clause from conditions. If the list of conditions is null or empty or if all
   * conditions are null or empty, the result will be null. The result will not contain any
   * condition that is null or empty.
   * @param conditions the list of conditions
   * @return the where clause
   */
  public static String buildWhere(final String... conditions) {
    if (ArrayUtils.isZeroLength(conditions)) {
      return null;
    }
    else if (conditions.length == 1) {
      final String condition = conditions[0];
      if (TextUtils.isEmpty(condition)) {
        return null;
      }
      return condition;
    }
    return null;
  }
}
