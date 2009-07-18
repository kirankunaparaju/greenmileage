package org.greenmileage.ui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.greenmileage.data.Fillup;

/**
 * Formats information related to {@link Fillup}s
 * @author Connor Garvey
 * @created Dec 22, 2008, 10:14:57 PM
 * @version 0.0.3
 * @since 0.0.3
 */
public class FillupFormat {
  /**
   * . Formats distance measurements
   */
  public static final NumberFormat DISTANCE_FORMAT = new DecimalFormat("##0.00");
  /**
   * . Formats DPV measurements
   */
  public static final NumberFormat DPV_FORMAT = new DecimalFormat("##0.00");
  
  /**
   * Formats an integer containing a distance measurement
   * @param distance The vehicle's distance
   * @return The distance as a string
   */
  public static final String formatDistance(final int distance) {
    return Integer.toString(distance);
  }
  
  /**
   * Formats a float containing a MPG measurement
   * @param dpv The vehicle's distance per volume
   * @return The DPV as a string
   */
  public static final String formatDPV(final float dpv) {
    return DPV_FORMAT.format(dpv);
  }
}
