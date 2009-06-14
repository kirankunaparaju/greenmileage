package org.greenmileage.android;

import android.os.Bundle;
import junit.framework.TestCase;

/**
 * @see NullableBundle
 * @author Connor Garvey
 * @created Jun 14, 2009 10:15:13 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class NullableBundleTest extends TestCase {
  private static final String KEY = "key";
  
  private NullableBundle createBundle() {
    return new NullableBundle(new Bundle());
  }
  
  /**
   * @see NullableBundle#putInt(String, Integer)
   */
  public void testPutIntNull() {
    final NullableBundle bundle = this.createBundle();
    bundle.putInt(KEY, null);
    final int value = 1000;
    assertEquals(bundle.getInt(KEY, value), value);
  }
  
  /**
   * @see NullableBundle#putInt(String, Integer)
   */
  public void testPutIntValue() {
    final NullableBundle bundle = this.createBundle();
    final int value = 1;
    bundle.putInt(KEY, value);
    assertEquals(value, bundle.getInt(KEY));
  }
  
  /**
   * @see NullableBundle#putLong(String, Long)
   */
  public void testPutLongNull() {
    final NullableBundle bundle = this.createBundle();
    bundle.putLong(KEY, null);
    final long value = 1000;
    assertEquals(bundle.getLong(KEY, value), value);
  }
  
  /**
   * @see NullableBundle#putLong(String, Long)
   */
  public void testPutLongValue() {
    final NullableBundle bundle = this.createBundle();
    final long value = 1L;
    bundle.putLong(KEY, value);
    assertEquals(value, bundle.getLong(KEY));
  }
  
  /**
   * @see NullableBundle#putObjectToString(String, Object)
   */
  public void testPutObjectToStringNull() {
    final NullableBundle bundle = this.createBundle();
    bundle.putObjectToString(KEY, null);
    assertNull(bundle.getString(KEY));
  }
  
  /**
   * @see NullableBundle#putObjectToString(String, Object)
   */
  public void testPutObjectToStringValue() {
    final NullableBundle bundle = this.createBundle();
    final String value = "expected";
    bundle.putObjectToString(KEY, value);
    assertEquals(value, bundle.getString(KEY));
  }
  
  /**
   * @see NullableBundle#putString(String, String)
   */
  public void testPutStringNull() {
    final NullableBundle bundle = this.createBundle();
    bundle.putString(KEY, null);
    assertNull(bundle.getString(KEY));
  }
  
  /**
   * @see NullableBundle#putString(String, String)
   */
  public void testPutStringValue() {
    final NullableBundle bundle = this.createBundle();
    final String value = "expected";
    bundle.putString(KEY, value);
    assertEquals(value, bundle.getString(KEY));
  }
}
