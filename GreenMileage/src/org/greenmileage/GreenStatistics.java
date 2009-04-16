package org.greenmileage;

import android.os.Handler;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import org.greenmileage.ui.FillupFormat;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;

/**
 * Displays overall statistics
 * @author Connor Garvey
 * @created Nov 28, 2008, 4:32:33 PM
 * @version 0.0.3
 * @since 0.0.3
 */
public class GreenStatistics extends Activity {
  private ContentObserver observer;
  
  /**
   * Creates green statistics
   */
  public GreenStatistics() {
    Handler handler = new Handler();
    this.observer = new ContentObserver(handler){

      /**
       * @see android.database.ContentObserver#deliverSelfNotifications()
       */
      @Override
      public boolean deliverSelfNotifications() {
        return false;
      }

      /**
       * @see android.database.ContentObserver#onChange(boolean)
       */
      @Override
      public void onChange(boolean selfChange) {
      }
      
    };
  }
  
  private String findTotalMPG() {
    // TODO: This is really ugly.  These are separate queries because the mins and maxes include the
    // earliest fillup.  Sums don't.
    Uri.Builder builder = new Uri.Builder();
    builder.scheme("content");
    builder.authority(GreenMileageDefinitions.AUTHORITY);
    builder.appendPath("fillups");
    builder.appendPath("statistics");
    builder.appendPath("minsmaxes");
    // These lines are included because Google's code is shit.  If you don't
    // call these set methods, the generated Uri will throw
    // NullPointerExceptions
    builder.query(null);
    builder.fragment(null);
    Uri uri = builder.build();
    this.getContentResolver().registerContentObserver(uri, false, this.
        observer);
    Cursor c = this.managedQuery(uri, new String[] {"minMileage", "maxMileage"}, null, null, null);
    if (c.getCount() == 0) {
      // TODO: This should be an error message
      return "";
    }
    c.moveToFirst();
    int minMileage = c.getInt(0);
    int maxMileage = c.getInt(1);
    builder = new Uri.Builder();
    builder.scheme("content");
    builder.authority(GreenMileageDefinitions.AUTHORITY);
    builder.appendPath("fillups");
    builder.appendPath("statistics");
    builder.appendPath("totals");
    // These lines are included because Google's code is shit.  If you don't
    // call these set methods, the generated Uri will throw
    // NullPointerExceptions
    builder.query(null);
    builder.fragment(null);
    uri = builder.build();
    this.getContentResolver().registerContentObserver(uri, false, this.
        observer);
    c = this.managedQuery(uri, new String[] {"sumVolume"}, null, null, null);
    if (c.getCount() == 0) {
      // TODO: This should be an error message
      return "";
    }
    c.moveToFirst();
    double volume = c.getDouble(0);
    int mileage = maxMileage - minMileage;
    if ((volume <= 0) || (mileage <= 0)) {
      return "";
    }
    float totalMPG = (float)(mileage / volume);
    return FillupFormat.formatMPG(totalMPG);
  }
  
  /**
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.green_statistics);
    TextView totalMileage = (TextView)this.findViewById(R.id.distancePerVolume);
    totalMileage.setText(this.findTotalMPG());
  }
}
