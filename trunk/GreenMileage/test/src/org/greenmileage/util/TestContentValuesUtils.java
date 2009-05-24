package org.greenmileage.util;

import android.content.ContentValues;
import junit.framework.TestCase;
import org.greenmileage.builder.ContentValuesTestBuilder;

/**
 * @see ContentValuesUtils
 * @author Connor Garvey
 * @created May 24, 2009 12:18:53 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestContentValuesUtils extends TestCase {
  /**
   * @see ContentValuesUtils#nullEmpty(android.content.ContentValues, String)
   */
  public void testNullEmptyContentValuesStringKeyEmpty() {
    final ContentValues values = ContentValuesTestBuilder.createEmptyWithKeys1To10();
    ContentValuesUtils.nullEmpty(values, "1");
    assertNull(values.get("1"));
  }
  
  /**
   * @see ContentValuesUtils#nullEmpty(android.content.ContentValues, String)
   */
  public void testNullEmptyContentValuesStringKeyNotEmpty() {
    final ContentValues values = ContentValuesTestBuilder.createWithKeys1To10();
    ContentValuesUtils.nullEmpty(values, "1");
    assertEquals("v1", values.getAsString("1"));
  }
  
  /**
   * @see ContentValuesUtils#nullEmpty(android.content.ContentValues, String)
   */
  public void testNullEmptyContentValuesStringKeyNotExist() {
    final ContentValues values = new ContentValues();
    ContentValuesUtils.nullEmpty(values, "1");
    assertNull(values.get("1"));
  }
  
  /**
   * @see ContentValuesUtils#nullEmpty(android.content.ContentValues, String)
   */
  public void testNullEmptyContentValuesStringNullContentValues() {
    try {
      ContentValuesUtils.nullEmpty(null, StringUtils.EMPTY);
    }
    catch (final IllegalArgumentException ex) {
      return;
    }
    fail("Did not throw IllegalArgumentException");
  }
}
