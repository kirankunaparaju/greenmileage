package org.greenmileage.ui;

import android.widget.TextView;
import org.greenmileage.util.CallbackListener;

/**
 * Receives a string callback and applies it as the text of a text view
 * @author Connor Garvey
 * @created Jan 18, 2009, 4:26:23 PM
 * @version 0.0.4
 * @since 0.0.4
 */
public class TextViewCallbackListener implements CallbackListener<String> {
  private TextView textView;
  
  /**
   * Creates a text view callback listener
   * @param textView The text view to receive the response
   */
  public TextViewCallbackListener(TextView textView) {
    this.textView = textView;
  }
  /**
   * @see org.greenmileage.util.CallbackListener#onResult(java.lang.Object)
   */
  @Override
  public void onResult(String result) {
    this.textView.setText(result);
  }
}
