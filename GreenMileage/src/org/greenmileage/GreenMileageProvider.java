/**
 * 
 */
package org.greenmileage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.greenmileage.GreenMileageDefinitions.Fillups;
import org.greenmileage.data.FillupUtils;
import org.greenmileage.data.RecordNotFoundException;
import org.greenmileage.util.SQLUtils;

/**
 * Provides mileage data
 * @author Connor Garvey
 * @created Nov 1, 2008, 4:43:59 PM
 * @version 0.0.3
 * @since 0.0.1
 */
public class GreenMileageProvider extends ContentProvider {
  /**
   * This class helps open, create, and upgrade the database file.
   */
  private static class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper(final Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(final SQLiteDatabase db) {
      create(db);
    }
    
    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
      // TODO: This is not good. Fix it.
      Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion +
          ", which will destroy all old data");
      drop(db);
      this.onCreate(db);
    }
  }
  
  private static final String DATABASE_NAME = "green_mileage.db";
  private static final int DATABASE_VERSION = 1;
  private static final int FILLUP_ID = 2;
  private static final int FILLUP_MOST_RECENT = 3;
  private static final int FILLUP_MOST_RECENT_VALID = 4;
  private static final int FILLUP_STATISTICS_MIN_MAX = 5;
  private static final int FILLUP_STATISTICS_TOTAL_VOLUME = 6;
  private static final String FILLUP_TABLE_NAME = "fillup";
  private static Map<String, String> fillupProjectionMap;
  // Fillup query types
  private static final int FILLUPS = 1;
  private static final String LOG_TAG = "GreenMileageProvider";
  private static final UriMatcher uriMatcher;
  static {
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI(GreenMileageDefinitions.AUTHORITY, "fillups", FILLUPS);
    uriMatcher.addURI(GreenMileageDefinitions.AUTHORITY, "fillups/#", FILLUP_ID);
    uriMatcher.addURI(GreenMileageDefinitions.AUTHORITY, "fillups/mostrecent", FILLUP_MOST_RECENT);
    uriMatcher.addURI(GreenMileageDefinitions.AUTHORITY, "fillups/statistics/minsmaxes",
        FILLUP_STATISTICS_MIN_MAX);
    uriMatcher.addURI(GreenMileageDefinitions.AUTHORITY, "fillups/statistics/totals",
        FILLUP_STATISTICS_TOTAL_VOLUME);
    uriMatcher.addURI(GreenMileageDefinitions.AUTHORITY, "fillups/mostrecentvalid",
        FILLUP_MOST_RECENT_VALID);
    fillupProjectionMap = new HashMap<String, String>();
    fillupProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
    fillupProjectionMap.put(Fillups.DATE, Fillups.DATE);
    fillupProjectionMap.put(Fillups.MILEAGE, Fillups.MILEAGE);
    fillupProjectionMap.put(Fillups.PRICE, Fillups.PRICE);
    fillupProjectionMap.put(Fillups.VOLUME, Fillups.VOLUME);
    fillupProjectionMap.put(Fillups.AUTOMOBILE, Fillups.AUTOMOBILE);
  }
  
  private static void create(final SQLiteDatabase db) {
    db.execSQL( //
        "CREATE TABLE " + FILLUP_TABLE_NAME + " (" + //
            BaseColumns._ID + " INTEGER PRIMARY KEY," + //
            Fillups.DATE + " INTEGER," + //
            Fillups.MILEAGE + " INTEGER," + //
            Fillups.PRICE + " TEXT," + //
            Fillups.VOLUME + " TEXT," + //
            Fillups.AUTOMOBILE + " TEXT" + //
            ");");
  }
  
  private static void drop(final SQLiteDatabase db) {
    db.execSQL("DROP TABLE IF EXISTS " + FILLUP_TABLE_NAME);
  }
  
  // private static final int
  private DatabaseHelper openHelper;
  
  /**
   * If the user's phone experienced a problem and the database became corrupted, cleanup any bad
   * records
   */
  public void cleanupDatabase() {
    final Uri lastUri = Uri.withAppendedPath(Fillups.CONTENT_URI, "mostrecent");
    final Cursor c = this.query(lastUri, Fillups.PROJECTION, null, null, null);
    try {
      if (!c.moveToNext()) {
        return;
      }
      if (!FillupUtils.validateFromCursor(c)) {
        this.delete(lastUri, null, null);
      }
    }
    finally {
      c.close();
    }
  }
  
  /**
   * @see ContentProvider#delete(Uri, String, String[])
   */
  @Override
  public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
    final SQLiteDatabase db = this.openHelper.getWritableDatabase();
    int count = 0;
    final int operation = uriMatcher.match(uri);
    if (operation == FILLUPS) {
      count = db.delete(FILLUP_TABLE_NAME, selection, selectionArgs);
    }
    else if (operation == FILLUP_ID) {
      final String fillupID = uri.getPathSegments().get(1);
      final String where = SQLUtils.buildWhere(BaseColumns._ID + "=" + fillupID, selection);
      count = db.delete(FILLUP_TABLE_NAME, where, selectionArgs);
    }
    else if (operation == FILLUP_MOST_RECENT) {
      long lastID = -1;
      try {
        lastID = this.findLatestID();
      }
      catch (final RecordNotFoundException ex) {
      }
      if (lastID != -1) {
        final String where = SQLUtils.buildWhere(BaseColumns._ID + "=" + lastID, selection);
        count = db.delete(FILLUP_TABLE_NAME, where, selectionArgs);
      }
    }
    else {
      throw new IllegalArgumentException("Unknown URI " + uri);
    }
    this.getContext().getContentResolver().notifyChange(uri, null);
    return count;
  }
  
  /**
   * Finds the average distance per volume for all fillups
   * @return The average distance per volume
   */
  public float findAverageDistancePerVolume() {
    final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
    qb.setTables(FILLUP_TABLE_NAME);
    final Map<String, String> idProjectionMap = new HashMap<String, String>();
    final String maxMileageColumn = "max" + Fillups.MILEAGE;
    final String minMileageColumn = "min" + Fillups.MILEAGE;
    final String averageVolumeColumn = Fillups.VOLUME;
    idProjectionMap.put(maxMileageColumn, "max(" + Fillups.MILEAGE + ")");
    idProjectionMap.put(minMileageColumn, "min(" + Fillups.MILEAGE + ")");
    idProjectionMap.put(averageVolumeColumn, "avg(" + Fillups.VOLUME + ")");
    qb.setProjectionMap(idProjectionMap);
    final String[] idProjection = new String[] { maxMileageColumn, minMileageColumn,
        averageVolumeColumn };
    final String rowLimit = "1";
    final SQLiteDatabase iddb = this.openHelper.getReadableDatabase();
    final Cursor c = qb.query(iddb, idProjection, null, null, null, null, null, rowLimit);
    try {
      if (c.getCount() < 1) {
        return 0f;
      }
      c.moveToNext();
      final int maxMileage = c.getInt(0);
      final int minMileage = c.getInt(1);
      final int mileage = maxMileage - minMileage;
      if (mileage == 0) {
        return 0;
      }
      final float averageVolume = c.getInt(2);
      return mileage / averageVolume;
    }
    finally {
      c.close();
    }
  }
  
  private long findEarliestID() throws RecordNotFoundException {
    final SQLiteQueryBuilder idqb = new SQLiteQueryBuilder();
    idqb.setTables(FILLUP_TABLE_NAME);
    final Map<String, String> idProjectionMap = new HashMap<String, String>();
    idProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
    idqb.setProjectionMap(fillupProjectionMap);
    final String idOrderBy = Fillups.MILEAGE + " ASC";
    final String[] idProjection = new String[] { BaseColumns._ID };
    final String rowLimit = "1";
    final SQLiteDatabase iddb = this.openHelper.getReadableDatabase();
    final Cursor c = idqb.query(iddb, idProjection, null, null, null, null, idOrderBy, rowLimit);
    try {
      if (c.getCount() < 1) {
        throw new RecordNotFoundException();
      }
      c.moveToNext();
      return c.getLong(0);
    }
    finally {
      c.close();
    }
  }
  
  private long findLatestID() throws RecordNotFoundException {
    final SQLiteQueryBuilder idqb = new SQLiteQueryBuilder();
    idqb.setTables(FILLUP_TABLE_NAME);
    final Map<String, String> idProjectionMap = new HashMap<String, String>();
    idProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
    idqb.setProjectionMap(fillupProjectionMap);
    final String idOrderBy = Fillups.MILEAGE + " DESC";
    final String[] idProjection = new String[] { BaseColumns._ID };
    final String rowLimit = "1";
    final SQLiteDatabase iddb = this.openHelper.getReadableDatabase();
    final Cursor c = idqb.query(iddb, idProjection, null, null, null, null, idOrderBy, rowLimit);
    try {
      if (c.getCount() < 1) {
        throw new RecordNotFoundException();
      }
      c.moveToNext();
      return c.getLong(0);
    }
    finally {
      c.close();
    }
  }
  
  /**
   * @see ContentProvider#getType(Uri)
   */
  @Override
  public String getType(final Uri uri) {
    switch (uriMatcher.match(uri)) {
      case FILLUPS:
        return Fillups.CONTENT_TYPE;
      case FILLUP_ID:
        return Fillups.CONTENT_ITEM_TYPE;
      case FILLUP_MOST_RECENT:
        return Fillups.CONTENT_ITEM_TYPE;
      case FILLUP_STATISTICS_MIN_MAX:
        return Fillups.CONTENT_STATISTICS_TYPE;
      default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }
  }
  
  /**
   * @see ContentProvider#insert(Uri, ContentValues)
   */
  @Override
  public Uri insert(final Uri uri, final ContentValues valuesIn) {
    this.cleanupDatabase();
    // Validate the requested URI
    if (uriMatcher.match(uri) != FILLUPS) {
      throw new IllegalArgumentException("Unknown URI " + uri);
    }
    final ContentValues values = FillupUtils.prepareForInsert(valuesIn);
    final SQLiteDatabase db = this.openHelper.getWritableDatabase();
    final long rowId = db.insert(FILLUP_TABLE_NAME, Fillups.DATE, values);
    if (rowId > 0) {
      final Uri fillupUri = ContentUris.withAppendedId(Fillups.CONTENT_URI, rowId);
      this.getContext().getContentResolver().notifyChange(fillupUri, null);
      return fillupUri;
    }
    throw new SQLException("Failed to insert row into " + uri);
  }
  
  /**
   * @see ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    this.openHelper = new DatabaseHelper(this.getContext());
    this.cleanupDatabase();
    return true;
  }
  
  /**
   * @see ContentProvider#query(Uri, String[], String, String[], String)
   */
  @Override
  public Cursor query(final Uri uri, final String[] projection, final String selection,
      final String[] selectionArgs, String sortOrder) {
    final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
    String rowLimit = null;
    Map<String, String> projectionMap = null;
    switch (uriMatcher.match(uri)) {
      case FILLUPS:
        qb.setTables(FILLUP_TABLE_NAME);
        qb.setProjectionMap(fillupProjectionMap);
        break;
      case FILLUP_ID:
        qb.setTables(FILLUP_TABLE_NAME);
        qb.setProjectionMap(fillupProjectionMap);
        qb.appendWhere(BaseColumns._ID + "=" + uri.getPathSegments().get(1));
        break;
      case FILLUP_MOST_RECENT:
        qb.setTables(FILLUP_TABLE_NAME);
        qb.setProjectionMap(fillupProjectionMap);
        long id = -1;
        try {
          id = this.findLatestID();
        }
        catch (final RecordNotFoundException ex) {
        }
        qb.appendWhere(BaseColumns._ID + "=" + Long.toString(id));
        break;
      case FILLUP_MOST_RECENT_VALID:
        qb.setTables(FILLUP_TABLE_NAME);
        qb.setProjectionMap(fillupProjectionMap);
        sortOrder = Fillups.MILEAGE + " DESC";
        qb.appendWhere(Fillups.AUTOMOBILE + " NOT NULL");
        qb.appendWhere(" AND " + Fillups.DATE + " NOT NULL");
        qb.appendWhere(" AND " + Fillups.MILEAGE + " NOT NULL");
        qb.appendWhere(" AND " + Fillups.PRICE + " NOT NULL");
        qb.appendWhere(" AND " + Fillups.VOLUME + " NOT NULL");
        break;
      case FILLUP_STATISTICS_MIN_MAX:
        qb.setTables(FILLUP_TABLE_NAME);
        projectionMap = new HashMap<String, String>();
        final String maxMileageColumn = "maxMileage";
        final String minMileageColumn = "minMileage";
        final String sumVolumeColumn = "sumVolume";
        projectionMap.put(minMileageColumn, "min(" + Fillups.MILEAGE + ")");
        projectionMap.put(maxMileageColumn, "max(" + Fillups.MILEAGE + ")");
        projectionMap.put(sumVolumeColumn, "sum(" + Fillups.VOLUME + ")");
        qb.setProjectionMap(projectionMap);
        rowLimit = "1";
        break;
      case FILLUP_STATISTICS_TOTAL_VOLUME:
        qb.setTables(FILLUP_TABLE_NAME);
        projectionMap = new HashMap<String, String>();
        final String sumVolumeColumn1 = "sumVolume";
        projectionMap.put(sumVolumeColumn1, "sum(" + Fillups.VOLUME + ")");
        qb.setProjectionMap(projectionMap);
        try {
          final long earliestID = this.findEarliestID();
          qb.appendWhere(BaseColumns._ID + " <> " + earliestID);
        }
        catch (final RecordNotFoundException ex) {
          // An earliest record doesn't exist, so don't append the where
        }
        rowLimit = "1";
        break;
      default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }
    final String orderBy = (TextUtils.isEmpty(sortOrder)) ? Fillups.DEFAULT_SORT_ORDER : sortOrder;
    final SQLiteDatabase db = this.openHelper.getReadableDatabase();
    final Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy,
        rowLimit);
    // Tell the cursor what URI to watch, so it knows when its source data
    // changes
    c.setNotificationUri(this.getContext().getContentResolver(), uri);
    return c;
  }
  
  /**
   * @see ContentProvider#update(Uri, ContentValues, String, String[])
   */
  @Override
  public int update(final Uri uri, final ContentValues valuesIn, final String selection,
      final String[] selectionArgs) {
    final ContentValues values = FillupUtils.prepareForUpdate(valuesIn);
    final SQLiteDatabase db = this.openHelper.getWritableDatabase();
    int count = 0;
    switch (uriMatcher.match(uri)) {
      case FILLUPS:
        count = db.update(FILLUP_TABLE_NAME, values, selection, selectionArgs);
        break;
      case FILLUP_ID:
        final String fillupId = uri.getPathSegments().get(1);
        count = db.update(FILLUP_TABLE_NAME, values, BaseColumns._ID + "=" + fillupId +
            (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
        break;
      case FILLUP_MOST_RECENT:
        long lastID = -1;
        try {
          lastID = this.findLatestID();
        }
        catch (final RecordNotFoundException ex) {
          break;
        }
        count = db.update(FILLUP_TABLE_NAME, values, BaseColumns._ID + "=" + lastID +
            (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
        break;
      default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }
    this.getContext().getContentResolver().notifyChange(uri, null);
    return count;
  }
}
