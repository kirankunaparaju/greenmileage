package org.greenmileage.ui;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.LinkedList;
import java.util.Queue;
import junit.framework.Assert;

/**
 * A text watcher that checks changes against expectations and throws an exception if the
 * expectations aren't met
 * @author Connor Garvey
 * @created Jun 13, 2009 10:15:57 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class ExpectationTextWatcher implements TextWatcher {
  private final Queue<String> expectations;
  
  public ExpectationTextWatcher(final String... expectations) {
    this.expectations = new LinkedList<String>();
    for (final String expectation : expectations) {
      this.expectations.offer(expectation);
    }
  }
  
  /**
   * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
   */
  @Override
  public void afterTextChanged(final Editable s) {
    final String next = this.expectations.poll();
    if (next == null) {
      Assert.fail("Did not expect a text change, but received '" + s.toString() + '\'');
    }
    Assert.assertEquals(next, s.toString());
  }
  
  /**
   * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void beforeTextChanged(final CharSequence s, final int start, final int count,
      final int after) {
  }
  
  /**
   * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
  }
  
  /**
   * Ensures that all expectations were met
   */
  public void validate() {
    if (!this.expectations.isEmpty()) {
      Assert.fail("Expected text changes that didn't happen: " + this.expectations.toString());
    }
  }
}
