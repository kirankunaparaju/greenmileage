package org.greenmileage;

import android.app.Activity;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import org.greenmileage.data.FillupStatistics;
import org.greenmileage.data.util.FillupStatisticsUtils;

/**
 * Displays overall statistics
 * @author Connor Garvey
 * @created Nov 28, 2008, 4:32:33 PM
 * @version 0.0.3
 * @since 0.0.3
 */
public class GreenStatistics extends Activity {
  private final ContentObserver observer;
  private TextView totalDistance;
  private TextView totalMileage;
  
  /**
   * Creates green statistics
   */
  public GreenStatistics() {
    final Handler handler = new Handler();
    this.observer = new ContentObserver(handler) {
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
      public void onChange(final boolean selfChange) {
      }
    };
  }
  
  private void findComponents() {
    this.totalDistance = this.findTextView(R.id.distance);
    this.totalMileage = this.findTextView(R.id.distancePerVolume);
  }
  
  private TextView findTextView(final int id) {
    return (TextView)this.findViewById(id);
  }
  
  /**
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.green_statistics);
    this.findComponents();
    final FillupStatistics statistics = FillupStatisticsUtils.findStatistics(this, this.observer);
    this.totalMileage.setText(statistics.getDistancePerVolume());
    this.totalDistance.setText(statistics.getDistance());
  }
}
