package org.greenmileage.data.util;

import org.greenmileage.data.FillupStatistics;
import org.greenmileage.util.StringUtils;

/**
 * Creates {@link FillupStatistics}
 * @author Connor Garvey
 * @created Jul 18, 2009 11:30:51 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class FillupStatisticsBuilder {
  /**
   * Creates a blank fillup statistics
   * @return a fillup statistics with empty, non-null, displayable values
   */
  public static FillupStatistics blank() {
    final FillupStatistics result = new FillupStatistics();
    result.setDistance(StringUtils.EMPTY);
    result.setDistancePerVolume(StringUtils.EMPTY);
    return result;
  }
}
