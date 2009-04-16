/**
 * 
 */
package org.greenmileage.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import java.text.ParseException;
import java.util.Date;
import org.greenmileage.GreenMileageDefinitions.Fillups;
import org.greenmileage.util.ContentValuesUtils;
import org.greenmileage.util.IntegerUtils;
import org.greenmileage.util.LongUtils;
import org.greenmileage.util.ObjectUtils;

/**
 * Helps work with {@link Fillup}s
 * @author Connor Garvey
 * @created Nov 22, 2008, 12:33:53 PM
 * @version 0.0.3
 * @since 0.0.1
 */
public class FillupUtils {
  
  /**
   * Prepare a cursor for fillup insertion
   * @param valuesIn The fillup values
   * @return The values
   */
  public static ContentValues prepareForInsert(ContentValues valuesIn) {
    ContentValues values = (valuesIn == null) ? new ContentValues() : new
        ContentValues(valuesIn);
    if (!values.containsKey(Fillups.DATE)) {
      values.put(Fillups.DATE, Long.valueOf(System.currentTimeMillis()));
    }
    ContentValuesUtils.nullEmpty(values, Fillups.AUTOMOBILE, Fillups.MILEAGE,
        Fillups.PRICE, Fillups.VOLUME);
    return values;
  }
  
  /**
   * Prepare a cursor for fillup updating
   * @param valuesIn The fillup values
   * @return The values
   */
  public static ContentValues prepareForUpdate(ContentValues valuesIn) {
    return prepareForInsert(valuesIn);
  }

  /**
   * Reads a fillup from a bundle
   * @param bundle The bundle from which to read
   * @param key The key identifying the values in the bundle. This key will be
   *        used as a prefix for bundle values.
   * @return The fillup
   * @throws ParseException Thrown if the date could not be parsed
   */
  public static Fillup readFromBundle(Bundle bundle, String key)
      throws ParseException {
    final Fillup fillup = new Fillup();
    fillup.setDate(new Date(bundle.getLong(key + Fillups.DATE)));
    fillup.setMileage(IntegerUtils.nullZero(bundle.getInt(key + Fillups.
        MILEAGE)));
    fillup.setPriceFromString(bundle.getString(key + Fillups.PRICE));
    fillup.setVolumeFromString(bundle.getString(key + Fillups.VOLUME));
    fillup.setAutomobile(bundle.getString(key + Fillups.AUTOMOBILE));
    fillup.setID(LongUtils.nullZero(bundle.getLong(key + Fillups._ID)));
    return fillup;
  }

  /**
   * Reads a fillup from a cursor
   * @param cursor The cursor from which to read
   * @return The fillup
   */
  public static Fillup readFromCursor(Cursor cursor) {
    Fillup fillup = new Fillup();
    fillup.setMileageFromString(cursor.getString(Fillups.MILEAGE_COLUMN_ID));
    fillup.setDate(new Date(cursor.getLong(Fillups.DATE_COLUMN_ID)));
    fillup.setPriceFromString(cursor.getString(Fillups.PRICE_COLUMN_ID));
    fillup.setVolumeFromString(cursor.getString(Fillups.VOLUME_COLUMN_ID));
    fillup.setAutomobile(cursor.getString(Fillups.AUTOMOBILE_COLUMN_ID));
    fillup.setID(cursor.getLong(Fillups.ID_COLUMN_ID));
    return fillup;
  }
  
  /**
   * Validates a fillup from a cursor
   * @param cursor The cursor to validate
   * @return True if the fillup at the cursor is valid, false otherwise
   */
  public static boolean validateFromCursor(Cursor cursor) {
    Fillup fillup = null;
    try {
      fillup = readFromCursor(cursor);
    }
    catch (Exception ex) {
      return false;
    }
    if (ObjectUtils.anyIsNull(fillup.getAutomobile(), fillup.getDate(),
        fillup.getID(), fillup.getMileage(), fillup.getPrice(), fillup.
        getVolume())) {
      return false;
    }
    // TODO: Do more validation
    return true;
  }
}
