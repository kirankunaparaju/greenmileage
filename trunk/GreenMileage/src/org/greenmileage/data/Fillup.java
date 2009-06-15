package org.greenmileage.data;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.greenmileage.util.BigDecimalUtils;

/**
 * Holds information about a fillup
 * @author Connor Garvey
 * @created Nov 15, 2008, 10:23:06 AM
 * @version 0.0.5
 * @since 0.0.1
 */
public class Fillup {
  private static final String DATE_FORMAT_STRING = "yyyyMMdd";
  
  /**
   * Gets a new date format used when reading or writing fillups
   * @return a new date formatter
   */
  public static DateFormat createDateFormat() {
    return new SimpleDateFormat(DATE_FORMAT_STRING);
  }
  
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
   * Sets the automobile
   * @param automobile The automobile to set
   */
  public void setAutomobile(final String automobile) {
    this.automobile = automobile;
  }
  
  /**
   * Sets the date
   * @param date The date to set
   */
  public void setDate(final Date date) {
    this.date = date;
  }
  
  /**
   * Sets the date from a string
   * @param dateString The date to set, formatted using {@link #createDateFormat()}
   * @throws ParseException Thrown if the input date could not be parsed
   */
  public void setDateFromString(final String dateString) throws ParseException {
    Date date = null;
    if (dateString != null) {
      date = createDateFormat().parse(dateString);
    }
    this.setDate(date);
  }
  
  /**
   * Sets the iD
   * @param id The iD to set
   */
  public void setID(final Long id) {
    this.iD = id;
  }
  
  /**
   * Sets the ID from a string
   * @param id The ID to set
   */
  public void setIDFromString(final String id) {
    Long longID = null;
    if (id != null) {
      longID = Long.valueOf(Long.parseLong(id));
    }
    this.setID(longID);
  }
  
  /**
   * Sets the mileage
   * @param mileage The mileage to set
   */
  public void setMileage(final Integer mileage) {
    this.mileage = mileage;
  }
  
  /**
   * Sets the mileage from a string
   * @param mileage The mileage to set
   */
  public void setMileageFromString(final String mileage) {
    Integer integerMileage = null;
    if (mileage != null) {
      integerMileage = Integer.valueOf(Integer.parseInt(mileage));
    }
    this.setMileage(integerMileage);
  }
  
  /**
   * Sets the price
   * @param price The price to set
   */
  public void setPrice(final BigDecimal price) {
    this.price = price;
  }
  
  /**
   * Sets the price
   * @param priceString The price to set
   */
  public void setPriceFromString(final String priceString) {
    this.setPrice(BigDecimalUtils.parseForDecimalLength(priceString, 3));
  }
  
  /**
   * Sets the volume
   * @param volume The volume to set
   */
  public void setVolume(final BigDecimal volume) {
    this.volume = volume;
  }
  
  /**
   * Sets the volume
   * @param volumeString The volume to set
   */
  public void setVolumeFromString(final String volumeString) {
    this.setVolume(BigDecimalUtils.parseForDecimalLength(volumeString, 3));
  }
}
