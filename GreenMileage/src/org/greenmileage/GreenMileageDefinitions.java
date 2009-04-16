package org.greenmileage;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Definitions for the database
 * @author Connor Garvey
 * @created Nov 1, 2008, 6:31:33 PM
 * @version 0.0.3
 * @since 0.0.1
 */
public class GreenMileageDefinitions {
  /**
   * The root of Green Mileage URI's
   */
  public static final String URI_ROOT = "org.greenmileage";
  /**
   * The class that will provide data definitions
   */
  public static final String AUTHORITY;
  
  static {
    AUTHORITY = URI_ROOT + ".GreenMileageDefinitions";
  }
  
  /**
   * Mileages table definitions
   * @created Nov 1, 2008, 6:36:13 PM
   * @version 0.0.1
   * @since 0.0.1
   */
  public static final class Fillups implements BaseColumns {
    private Fillups() { }

    /**
     * The content:// style URI for this table
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY +
        "/fillups");

    /**
     * The MIME type of {@link #CONTENT_URI} providing a directory of fill-ups.
     */
    public static final String CONTENT_TYPE =
        "vnd.android.cursor.dir/vnd.greenmileage.fillup";

    /**
     * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
     * fill-up.
     */
    public static final String CONTENT_ITEM_TYPE =
        "vnd.android.cursor.item/vnd.greenmileage.fillup";

    /**
     * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
     * fill-up.
     */
    public static final String CONTENT_STATISTICS_TYPE =
        "vnd.android.cursor.item/vnd.greenmileage.statistics";

    /**
     * The default sort order for this table
     */
    public static final String DEFAULT_SORT_ORDER;
    
    /**
     * The ID of the ID column
     */
    public static final int ID_COLUMN_ID = 0;

    /**
     * The time-stamp when the fill-up was made
     * <P>Type: INTEGER (long from System.currentTimeMillis())</P>
     */
    public static final String DATE = "date";
    
    /**
     * The ID of the date column
     */
    public static final int DATE_COLUMN_ID = 1;

    /**
     * The car's mileage when the fill-up was made
     * <P>Type: INTEGER</P>
     */
    public static final String MILEAGE = "mileage";
    
    /**
     * The ID of the mileage column
     */
    public static final int MILEAGE_COLUMN_ID = 2;

    /**
     * The per-gallon price when the fill-up was made
     * <P>Type: TEXT (string representation of the decimal value)</P>
     */
    public static final String PRICE = "price";
    
    /**
     * The ID of the price column
     */
    public static final int PRICE_COLUMN_ID = 3;
    
    /**
     * The volume of the fillup
     */
    public static final String VOLUME = "volume";
    
    /**
     * The ID of the volume column
     */
    public static final int VOLUME_COLUMN_ID = 4;
    
    /**
     * The automobile for which the mileage is being recorded
     */
    public static final String AUTOMOBILE = "automobile";
    
    /**
     * The ID of the automobile column
     */
    public static final int AUTOMOBILE_COLUMN_ID = 5;
    
    /**
     * A projection that includes all columns
     */
    public static final String[] PROJECTION;
    
    static {
      DEFAULT_SORT_ORDER = MILEAGE + " DESC";
      PROJECTION = new String[] { //
            BaseColumns._ID, // 0
                Fillups.DATE, // 1
                Fillups.MILEAGE, // 2
                Fillups.PRICE, // 3
                Fillups.VOLUME, // 4
                Fillups.AUTOMOBILE // 5
            };
    }
  }
}
