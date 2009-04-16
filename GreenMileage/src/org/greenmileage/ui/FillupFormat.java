package org.greenmileage.ui;

import org.greenmileage.data.Fillup;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Formats information related to {@link Fillup}s
 * @author Connor Garvey
 * @created Dec 22, 2008, 10:14:57 PM
 * @version 0.0.3
 * @since 0.0.3
 */
public class FillupFormat {

  /**.
   * Formats MPG measurements
   */
  public static final NumberFormat MPG_FORMAT = new DecimalFormat("##0.00");
  
  /**
   * Formats a float containing a MPG measurement
   * @param mpg The vehicle's MPG
   * @return The MPG as a string
   */
  public static final String formatMPG(float mpg) {
    return MPG_FORMAT.format(mpg);
  }
}
