package org.greenmileage.util;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @see SQLUtils
 * @author Connor Garvey
 * @created May 19, 2009 10:09:39 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestSQLUtils {
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereEmpty() {
    assertEquals(null, SQLUtils.buildWhere(new String[0]));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereNull() {
    assertEquals(null, SQLUtils.buildWhere((String[])null));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereNulls() {
    assertEquals(null, SQLUtils.buildWhere((String)null, (String)null));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereNullString() {
    assertEquals("a='1'", SQLUtils.buildWhere((String)null, "a='1'"));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereNullStringNull() {
    assertEquals("a='1'", SQLUtils.buildWhere((String)null, "a='1'", null));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereOne() {
    assertEquals("a='1'", SQLUtils.buildWhere("a='1'"));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereOneNull() {
    assertEquals(null, SQLUtils.buildWhere((String)null));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereStringNull() {
    assertEquals("a='1'", SQLUtils.buildWhere("a='1'", (String)null));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereStringNullString() {
    assertEquals("a='1' AND b='2'", SQLUtils.buildWhere("a='1'", null, "b='2'"));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereThree() {
    assertEquals("a='1' AND b='2' AND c='3'", SQLUtils.buildWhere("a='1'", "b='2'", "c='3'"));
  }
  
  /**
   * @see SQLUtils#buildWhere(String...)
   */
  @Test
  public void testBuildWhereTwo() {
    assertEquals("a='1' AND b='2'", SQLUtils.buildWhere("a='1'", "b='2'"));
  }
}
