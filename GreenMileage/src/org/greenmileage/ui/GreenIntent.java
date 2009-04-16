package org.greenmileage.ui;

import org.greenmileage.GreenMileageDefinitions;

/**
 * Defines intents specific to Green Mileage
 * @author Connor Garvey
 * @created Nov 28, 2008, 5:27:45 PM
 * @version 0.0.3
 * @since 0.0.3
 */
public class GreenIntent {
  private static final String INTENT_NAMESPACE = ".intent";
  /**
   * View green statistics
   */
  public static final String ACTION_VIEW_STATISTICS;
  
  static {
    ACTION_VIEW_STATISTICS = GreenMileageDefinitions.URI_ROOT +
        INTENT_NAMESPACE + ".ACTION_VIEW_STATISTICS";
  }
}
