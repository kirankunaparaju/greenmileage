package org.greenmileage.util;

/**
 * Helps work with {@link String}s
 * @author Connor Garvey
 * @created Nov 22, 2008, 10:19:01 PM
 * @version 0.0.1
 * @since 0.0.1
 */
public class StringUtils {
  
  /**
   * <p>Converts an empty string to a null</p>
   * <pre>
   * null = null
   * "" = null
   * " " = " "
   * "a" = "a"
   * </pre>
   * @param s The string to test
   * @return Null if the string was null or empty, otherwise the input string
   */
  public static String nullEmpty(String s) {
    if (s == null) {
      return null;
    }
    if ("".equals(s)) {
      return null;
    }
    return s;
  }
}
