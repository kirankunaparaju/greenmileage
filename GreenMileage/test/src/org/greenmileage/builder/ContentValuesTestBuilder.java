package org.greenmileage.builder;

import android.content.ContentValues;
import org.greenmileage.util.StringUtils;

/**
 * Builds {@link ContentValues} for use in testing
 * @author Connor Garvey
 * @created May 24, 2009 12:24:02 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class ContentValuesTestBuilder {
  /**
   * Creates a content values with string keys 1 to 10 inclusive with string values that are all
   * empty
   * @return the content values
   */
  public static ContentValues createEmptyWithKeys1To10() {
    final ContentValues values = new ContentValues();
    for (int i = 1; i < 11; ++i) {
      final String number = Integer.toString(i);
      values.put(number, StringUtils.EMPTY);
    }
    return values;
  }
  
  /**
   * Creates a content values with string keys 1 to 10 inclusive with string values v1, v2, v3, etc
   * @return the content values
   */
  public static ContentValues createWithKeys1To10() {
    final ContentValues values = new ContentValues();
    for (int i = 1; i < 11; ++i) {
      final String number = Integer.toString(i);
      values.put(number, "v" + number);
    }
    return values;
  }
}
