package org.greenmileage.util;

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
   * conditions are null or empty, the result will be null. If any condition is null or empty, the
   * result will not contain those conditions.
   * @param conditions the list of conditions
   * @return the where clause
   */
  public static String buildWhere(final String... conditions) {
    if ((conditions == null) || (conditions.length == 0)) {
      return null;
    }
    else if (conditions.length == 1) {
    }
    return null;
  }
}
