package org.greenmileage.ui;

import android.test.InstrumentationTestCase;
import android.text.Editable;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import org.greenmileage.test.MockTextView;
import org.greenmileage.util.StringUtils;

/**
 * @see DecimalTextWatcher
 * @author Connor Garvey
 * @created Jun 12, 2009 10:41:49 PM
 * @version 0.0.5
 * @since 0.0.5
 */
public class TestDecimalTextWatcher extends InstrumentationTestCase {
  private DecimalTextWatcher createDecimalTextWatcher() {
    final DecimalTextWatcher watcher = new DecimalTextWatcher();
    watcher.setDecimalDigits(3);
    return watcher;
  }
  
  /**
   * @see DecimalTextWatcher#afterTextChanged(Editable)
   */
  public void testAfterTextChangedAllInvalid() {
    final TextView textView = new MockTextView(this.getInstrumentation().getContext());
    textView.setText("ABcde", BufferType.EDITABLE);
    final Editable editable = textView.getEditableText();
    this.createDecimalTextWatcher().afterTextChanged(editable);
    final CharSequence actual = textView.getText();
    assertEquals(".000", actual.toString());
  }
  
  /**
   * @see DecimalTextWatcher#afterTextChanged(Editable)
   */
  public void testAfterTextChangedEmpty() {
    final TextView textView = new MockTextView(this.getInstrumentation().getContext());
    textView.setText(StringUtils.EMPTY, BufferType.EDITABLE);
    final Editable editable = textView.getEditableText();
    this.createDecimalTextWatcher().afterTextChanged(editable);
    final CharSequence actual = textView.getText();
    assertEquals(".000", actual.toString());
  }
  
  /**
   * @see DecimalTextWatcher#afterTextChanged(Editable)
   */
  public void testAfterTextChangedPartialInvalid() {
    final TextView textView = new MockTextView(this.getInstrumentation().getContext());
    textView.setText("1.abc", BufferType.EDITABLE);
    final Editable editable = textView.getEditableText();
    this.createDecimalTextWatcher().afterTextChanged(editable);
    final CharSequence actual = textView.getText();
    assertEquals(".001", actual.toString());
  }
  
  /**
   * @see DecimalTextWatcher#afterTextChanged(Editable)
   */
  public void testAfterTextChangedValid() {
    final TextView textView = new MockTextView(this.getInstrumentation().getContext());
    textView.setText("112.588", BufferType.EDITABLE);
    final Editable editable = textView.getEditableText();
    this.createDecimalTextWatcher().afterTextChanged(editable);
    final CharSequence actual = textView.getText();
    assertEquals("112.588", actual.toString());
  }
  
  /**
   * @see DecimalTextWatcher#afterTextChanged(Editable)
   */
  public void testAfterTextChangedVeryInvalid() {
    final TextView textView = new MockTextView(this.getInstrumentation().getContext());
    textView.setText("aa2xa.1.abc", BufferType.EDITABLE);
    final Editable editable = textView.getEditableText();
    this.createDecimalTextWatcher().afterTextChanged(editable);
    final CharSequence actual = textView.getText();
    assertEquals(".021", actual.toString());
  }
}
