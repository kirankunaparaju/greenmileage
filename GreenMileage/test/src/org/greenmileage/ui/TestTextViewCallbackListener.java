package org.greenmileage.ui;

import android.test.InstrumentationTestCase;
import android.widget.TextView;
import org.greenmileage.test.MockTextView;

/**
 * @see TextViewCallbackListener
 * @author Connor Garvey
 * @created May 26, 2009 8:19:47 PM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestTextViewCallbackListener extends InstrumentationTestCase {
  /**
   * @see TextViewCallbackListener#TextViewCallbackListener(android.widget.TextView)
   */
  public void testConstructorNullView() {
    try {
      new TextViewCallbackListener(null);
    }
    catch (final IllegalArgumentException ex) {
      return;
    }
    fail("Didn't throw IllegalArgumentException");
  }
  
  /**
   * @see TextViewCallbackListener#onResult(String)
   */
  public void testOnResult() {
    final TextView textView = new MockTextView(this.getInstrumentation().getContext());
    final String expected = "expected";
    final TextViewCallbackListener listener = new TextViewCallbackListener(textView);
    listener.onResult(expected);
    assertEquals(expected, textView.getText());
  }
}
