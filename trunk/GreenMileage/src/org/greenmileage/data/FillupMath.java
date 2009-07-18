package org.greenmileage.data;

import org.greenmileage.ui.FillupFormat;

/**
 * Performs calculations using {@link Fillup}s
 * @author Connor Garvey
 * @created Nov 24, 2008, 12:38:21 PM
 * @version 0.0.3
 * @since 0.0.2
 */
public class FillupMath {
  /**
   * Calculates the distance per volume between fillups, assuming that the mileage of the second
   * fillup is greater than the first
   * @param f1 The first fillup
   * @param f2 The second fillup
   * @return The distance per volume
   */
  public static double distancePerVolume(final Fillup f1, final Fillup f2) {
    final int distance = f2.getMileage() - f1.getMileage();
    final double dpv = distance / f2.getVolume().doubleValue();
    return dpv;
  }
  
  /**
   * Calculates the distance per volume between fillups, assuming that the mileage of the second
   * fillup is greater than the first
   * @param f1 The first fillup
   * @param f2 The second fillup
   * @return The distance per volume as a two decimal string
   */
  public static String distancePerVolumeAsString(final Fillup f1, final Fillup f2) {
    return FillupFormat.DPV_FORMAT.format(distancePerVolume(f1, f2));
  }
}
