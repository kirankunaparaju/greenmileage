package org.greenmileage.util;

/**
 * A listener that receives callbacks
 * @author Connor Garvey
 * @created Jan 10, 2009, 11:13:33 PM
 * @version 0.0.4
 * @param <T> The result of the callback
 * @since 0.0.4
 */
public interface CallbackListener<T> {
  
  /**
   * Indicates that the function has finished and is returning the result
   * @param result The result
   */
  public void onResult(T result);
}
