package org.greenmileage.data;

import android.content.ContentValues;
import android.os.Bundle;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.greenmileage.GreenMileageDefinitions.Fillups;
import org.greenmileage.util.BigDecimalUtils;

/**
 * Creates a fillup
 * @author Connor Garvey
 * @created Nov 15, 2008, 10:23:06 AM
 * @version 0.0.3
 * @since 0.0.1
 */
public class Fillup {
  /**
   * The date format used when reading or writing fillups
   */
  public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
  private String automobile;
  private Date date;
  private Long iD;
  private Integer mileage;
  private BigDecimal price;
  private BigDecimal volume;
  
  /**
   * Gets the automobile
   * @return The automobile
   */
  public String getAutomobile() {
    return this.automobile;
  }
  
  /**
   * Gets the date
   * @return The date
   */
  public Date getDate() {
    return this.date;
  }
  
  /**
   * Gets the iD
   * @return The iD
   */
  public Long getID() {
    return this.iD;
  }
  
  /**
   * Gets the mileage
   * @return The mileage
   */
  public Integer getMileage() {
    return this.mileage;
  }
  
  /**
   * Gets the price
   * @return The price
   */
  public BigDecimal getPrice() {
    return this.price;
  }
  
  /**
   * Gets the volume
   * @return The volume
   */
  public BigDecimal getVolume() {
    return this.volume;
  }
  
  /**
   * Writes a fillup to a bundle
   * @param bundle The bundle to which to write
   * @param key The key identifying the values in the bundle. This key will be
   *        used as a prefix for bundle values.
   */
  public void saveToBundle(Bundle bundle, String key) {
    BundleUtils.putString(key + Fillups.AUTOMOBILE, this.getAutomobile(),
        bundle);
    BundleUtils.putLong(key + Fillups.DATE, this.getDate().getTime(), bundle);
    BundleUtils.putInt(key + Fillups.MILEAGE, this.getMileage(), bundle);
    BundleUtils.putObjectToString(key + Fillups.PRICE, this.getPrice(), bundle);
    BundleUtils.putObjectToString(key + Fillups.VOLUME, this.getVolume(),
        bundle);
    BundleUtils.putLong(key + Fillups._ID, this.getID(), bundle);
  }
  
  /**
   * Saves the fillup to content values
   * @param values The content values to which to save
   */
  public void saveToContentValues(ContentValues values) {
    values.put(Fillups.AUTOMOBILE, this.getAutomobile());
    values.put(Fillups.DATE, this.getDate().getTime());
    values.put(Fillups.MILEAGE, this.getMileage());
    values.put(Fillups.PRICE, this.getPrice().toString());
    values.put(Fillups.VOLUME, this.getVolume().toString());
    values.put(Fillups._ID, this.getID());
  }
  
  /**
   * Sets the automobile
   * @param automobile The automobile to set
   */
  public void setAutomobile(String automobile) {
    this.automobile = automobile;
  }
  
  /**
   * Sets the date
   * @param date The date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }
  
  /**
   * Sets the date from a string
   * @param dateString The date to set, formatted using {@link #DATE_FORMAT}
   * @throws ParseException Thrown if the input date could not be parsed
   */
  public void setDateFromString(String dateString) throws ParseException {
    Date date = null;
    if (dateString != null) {
      date = DATE_FORMAT.parse(dateString);
    }
    this.setDate(date);
  }
  
  /**
   * Sets the iD
   * @param id The iD to set
   */
  public void setID(Long id) {
    this.iD = id;
  }
  
  /**
   * Sets the ID from a string
   * @param id The ID to set
   */
  public void setIDFromString(String id) {
    Long longID = null;
    if (id != null) {
      longID = new Long(Long.parseLong(id));
    }
    this.setID(longID);
  }
  
  /**
   * Sets the mileage
   * @param mileage The mileage to set
   */
  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }
  
  /**
   * Sets the mileage from a string
   * @param mileage The mileage to set
   */
  public void setMileageFromString(String mileage) {
    Integer integerMileage = null;
    if (mileage != null) {
      integerMileage = new Integer(Integer.parseInt(mileage));
    }
    this.setMileage(integerMileage);
  }
  
  /**
   * Sets the price
   * @param price The price to set
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  
  /**
   * Sets the price
   * @param priceString The price to set
   */
  public void setPriceFromString(String priceString) {
    this.setPrice(BigDecimalUtils.parseForDecimalLength(priceString, 3));
  }
  
  /**
   * Sets the volume
   * @param volume The volume to set
   */
  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }
  
  /**
   * Sets the volume
   * @param volumeString The volume to set
   */
  public void setVolumeFromString(String volumeString) {
    this.setVolume(BigDecimalUtils.parseForDecimalLength(volumeString, 3));
  }
}
