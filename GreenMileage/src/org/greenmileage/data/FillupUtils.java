/**
 * 
 */
package org.greenmileage.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import java.text.ParseException;
import java.util.Date;
import org.greenmileage.GreenMileageDefinitions.Fillups;
import org.greenmileage.android.NullableBundle;
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
  public static ContentValues prepareForInsert(final ContentValues valuesIn) {
    final ContentValues values = (valuesIn == null) ? new ContentValues() : new ContentValues(
        valuesIn);
    if (!values.containsKey(Fillups.DATE)) {
      values.put(Fillups.DATE, Long.valueOf(System.currentTimeMillis()));
    }
    ContentValuesUtils.nullEmpty(values, Fillups.AUTOMOBILE, Fillups.MILEAGE, Fillups.PRICE,
        Fillups.VOLUME);
    return values;
  }
  
  /**
   * Prepare a cursor for fillup updating
   * @param valuesIn The fillup values
   * @return The values
   */
  public static ContentValues prepareForUpdate(final ContentValues valuesIn) {
    return prepareForInsert(valuesIn);
  }
  
  /**
   * Reads a fillup from a bundle
   * @param bundle The bundle from which to read
   * @param key The key identifying the values in the bundle. This key will be used as a prefix for
   *        bundle values.
   * @return The fillup
   * @throws ParseException Thrown if the date could not be parsed
   */
  public static Fillup readFromBundle(final Bundle bundle, final String key) throws ParseException {
    final Fillup fillup = new Fillup();
    fillup.setDate(new Date(bundle.getLong(key + Fillups.DATE)));
    fillup.setMileage(IntegerUtils.nullZero(bundle.getInt(key + Fillups.MILEAGE)));
    fillup.setPriceFromString(bundle.getString(key + Fillups.PRICE));
    fillup.setVolumeFromString(bundle.getString(key + Fillups.VOLUME));
    fillup.setAutomobile(bundle.getString(key + Fillups.AUTOMOBILE));
    fillup.setID(LongUtils.nullZero(bundle.getLong(key + BaseColumns._ID)));
    return fillup;
  }
  
  /**
   * Reads a fillup from a cursor
   * @param cursor the cursor from which to read
   * @return the fillup
   */
  public static Fillup readFromCursor(final Cursor cursor) {
    final Fillup fillup = new Fillup();
    fillup.setMileageFromString(cursor.getString(Fillups.MILEAGE_COLUMN_ID));
    fillup.setDate(new Date(cursor.getLong(Fillups.DATE_COLUMN_ID)));
    fillup.setPriceFromString(cursor.getString(Fillups.PRICE_COLUMN_ID));
    fillup.setVolumeFromString(cursor.getString(Fillups.VOLUME_COLUMN_ID));
    fillup.setAutomobile(cursor.getString(Fillups.AUTOMOBILE_COLUMN_ID));
    fillup.setID(cursor.getLong(Fillups.ID_COLUMN_ID));
    return fillup;
  }
  
  /**
   * Writes a fillup to a bundle
   * @param fillup the fillup to write
   * @param bundleIn The bundle to which to write
   * @param key The key identifying the values in the bundle. This key will be used as a prefix for
   *        bundle values.
   */
  public static void saveToBundle(final Fillup fillup, final Bundle bundleIn, final String key) {
    final NullableBundle bundle = new NullableBundle(bundleIn);
    bundle.putString(key + Fillups.AUTOMOBILE, fillup.getAutomobile());
    bundle.putLong(key + Fillups.DATE, fillup.getDate().getTime());
    bundle.putInt(key + Fillups.MILEAGE, fillup.getMileage());
    bundle.putObjectToString(key + Fillups.PRICE, fillup.getPrice());
    bundle.putObjectToString(key + Fillups.VOLUME, fillup.getVolume());
    bundle.putLong(key + BaseColumns._ID, fillup.getID());
  }
  
  /**
   * Saves the fillup to content values
   * @param fillup the fillup to save
   * @param values the content values to which to save
   */
  public static void saveToContentValues(final Fillup fillup, final ContentValues values) {
    values.put(Fillups.AUTOMOBILE, fillup.getAutomobile());
    values.put(Fillups.DATE, fillup.getDate().getTime());
    values.put(Fillups.MILEAGE, fillup.getMileage());
    values.put(Fillups.PRICE, fillup.getPrice().toString());
    values.put(Fillups.VOLUME, fillup.getVolume().toString());
    values.put(BaseColumns._ID, fillup.getID());
  }
  
  /**
   * Validates a fillup from a cursor
   * @param cursor The cursor to validate
   * @return True if the fillup at the cursor is valid, false otherwise
   */
  public static boolean validateFromCursor(final Cursor cursor) {
    Fillup fillup = null;
    try {
      fillup = readFromCursor(cursor);
    }
    catch (final Exception ex) {
      return false;
    }
    if (ObjectUtils.anyIsNull(fillup.getAutomobile(), fillup.getDate(), fillup.getID(), fillup
        .getMileage(), fillup.getPrice(), fillup.getVolume())) {
      return false;
    }
    // TODO: Do more validation
    return true;
  }
}
