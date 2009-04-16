package org.greenmileage.data;

import org.greenmileage.ui.FillupFormat;

/**
 * Calculates statistical information about {@link Fillup}s
 * @author Connor Garvey
 * @created Nov 24, 2008, 12:38:21 PM
 * @version 0.0.3
 * @since 0.0.2
 */
public class FillupStatistics {
  /**
   * Calculates the distance per volume between fillups, assuming that the
   * mileage of the second fillup is greater than the first
   * @param f1 The first fillup
   * @param f2 The second fillup
   * @return The distance per volume
   */
  public static double distancePerVolume(Fillup f1, Fillup f2) {
    int distance = f2.getMileage() - f1.getMileage();
    double dpv = distance / f2.getVolume().doubleValue();
    return dpv;
  }
  
  /**
   * Calculates the distance per volume between fillups, assuming that the
   * mileage of the second fillup is greater than the first
   * @param f1 The first fillup
   * @param f2 The second fillup
   * @return The distance per volume as a two decimal string
   */
  public static String distancePerVolumeAsString(Fillup f1, Fillup f2) {
    return FillupFormat.MPG_FORMAT.format(distancePerVolume(f1, f2));
  }
}
