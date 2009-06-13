package org.greenmileage.util;

/**
 * A callback listener that doesn't do anything
 * @author Connor Garvey
 * @created Jun 13, 2009 11:06:42 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class EmptyCallbackListener<T> implements CallbackListener<T> {
  /**
   * @see CallbackListener#onResult(Object)
   */
  @Override
  public void onResult(final T result) {
  }
}
