/**
 * 
 */
package org.greenmileage.data;

/**
 * An exception indicating that an expected record was not found
 * @author Connor Garvey
 * @created Nov 22, 2008, 12:09:16 PM
 * @version 0.0.1
 * @since 0.0.1
 */
public class RecordNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a record not found exception
   */
  public RecordNotFoundException() {
  }
  
  /**
   * Creates a record not found exception
   * @param detailMessage A message describing the exception
   */
  public RecordNotFoundException(String detailMessage) {
    super(detailMessage);
  }
  
  /**
   * Creates a record not found exception
   * @param throwable The exception that caused this exception
   */
  public RecordNotFoundException(Throwable throwable) {
    super(throwable);
  }
  
  /**
   * Creates a record not found exception
   * @param detailMessage A message describing the exception
   * @param throwable The exception that caused this exception
   */
  public RecordNotFoundException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }
}
