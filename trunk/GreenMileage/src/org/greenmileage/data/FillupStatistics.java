package org.greenmileage.data;

/**
 * Holds statistics about fillups
 * @author Connor Garvey
 * @created Jul 18, 2009 11:27:01 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class FillupStatistics {
  private String distance;
  private String distancePerVolume;
  
  /**
   * @return the distance
   */
  public String getDistance() {
    return this.distance;
  }
  
  /**
   * @return the distancePerVolume
   */
  public String getDistancePerVolume() {
    return this.distancePerVolume;
  }
  
  /**
   * @param distance the distance to set
   */
  public void setDistance(final String distance) {
    this.distance = distance;
  }
  
  /**
   * @param distancePerVolume the distancePerVolume to set
   */
  public void setDistancePerVolume(final String distancePerVolume) {
    this.distancePerVolume = distancePerVolume;
  }
}
