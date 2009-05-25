package org.greenmileage.util;

/**
 * Helps work with {@link String}s
 * @author Connor Garvey
 * @created Nov 22, 2008, 10:19:01 PM
 * @version 0.0.5
 * @since 0.0.1
 */
public class StringUtils {
  /**
   * An empty string
   */
  public static final String EMPTY = "";
  
  /**
   * <p>
   * Converts an empty string to a null
   * </p>
   * 
   * <pre>
   * null = null
   * &quot;&quot; = null
   * &quot; &quot; = &quot; &quot;
   * &quot;a&quot; = &quot;a&quot;
   * </pre>
   * @param s The string to test
   * @return Null if the string was null or empty, otherwise the input string
   */
  public static String nullEmpty(final String s) {
    if (s == null) {
      return null;
    }
    if (StringUtils.EMPTY.equals(s)) {
      return null;
    }
    return s;
  }
}
