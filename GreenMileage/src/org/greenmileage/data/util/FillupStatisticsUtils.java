package org.greenmileage.data.util;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import org.greenmileage.GreenMileageDefinitions;
import org.greenmileage.data.FillupStatistics;
import org.greenmileage.ui.FillupFormat;

/**
 * Finds information about all fillups in persistent storage
 * @author Connor Garvey
 * @created Jul 18, 2009 11:22:57 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class FillupStatisticsUtils {
  private static SimpleStatistics findData(final Activity activity, final ContentObserver observer) {
    final SimpleStatistics statistics = new SimpleStatistics();
    {
      final Uri.Builder builder = new Uri.Builder();
      builder.scheme("content");
      builder.authority(GreenMileageDefinitions.AUTHORITY);
      builder.appendPath("fillups");
      builder.appendPath("statistics");
      builder.appendPath("minsmaxes");
      // These lines are included because Google's code is buggy. If you don't call these set
      // methods,
      // the generated Uri will throw NullPointerExceptions
      builder.query(null);
      builder.fragment(null);
      final Uri uri = builder.build();
      activity.getContentResolver().registerContentObserver(uri, false, observer);
      final Cursor c = activity.managedQuery(uri, new String[] {"minMileage", "maxMileage"}, null,
          null, null);
      if (c.getCount() == 0) {
        // TODO: This should be an error message
        return null;
      }
      c.moveToFirst();
      statistics.minMileage = c.getInt(0);
      statistics.maxMileage = c.getInt(1);
      try {
        c.close();
      }
      catch (final Exception ex) {
        // Ignore
      }
    }
    {
      final Uri.Builder builder = new Uri.Builder();
      builder.scheme("content");
      builder.authority(GreenMileageDefinitions.AUTHORITY);
      builder.appendPath("fillups");
      builder.appendPath("statistics");
      builder.appendPath("totals");
      // These lines are included because Google's code is buggy. If you don't call these set
      // methods,
      // the generated Uri will throw NullPointerExceptions
      builder.query(null);
      builder.fragment(null);
      final Uri uri = builder.build();
      activity.getContentResolver().registerContentObserver(uri, false, observer);
      final Cursor c = activity.managedQuery(uri, new String[] {"sumVolume"}, null, null, null);
      if (c.getCount() == 0) {
        // TODO: This should be an error message
        return null;
      }
      c.moveToFirst();
      statistics.volume = c.getDouble(0);
      try {
        c.close();
      }
      catch (final Exception ex) {
        // Ignore
      }
    }
    return statistics;
  }
  
  /**
   * Finds the total distance per volume for all distance recorded
   * @param activity the activity making the request
   * @param observer a result observer
   * @return the distance per volume
   */
  public static FillupStatistics findStatistics(final Activity activity,
      final ContentObserver observer) {
    final SimpleStatistics statistics = findData(activity, observer);
    if (statistics == null) {
      return FillupStatisticsBuilder.blank();
    }
    final int mileage = statistics.maxMileage - statistics.minMileage;
    if ((statistics.volume <= 0) || (mileage <= 0)) {
      return FillupStatisticsBuilder.blank();
    }
    final FillupStatistics result = new FillupStatistics();
    {
      final float totalDPV = (float)(mileage / statistics.volume);
      result.setDistancePerVolume(FillupFormat.formatDPV(totalDPV));
    }
    result.setDistance(FillupFormat.formatDistance(mileage));
    return result;
  }
  
  private static class SimpleStatistics {
    public int maxMileage;
    public int minMileage;
    public double volume;
  }
}
