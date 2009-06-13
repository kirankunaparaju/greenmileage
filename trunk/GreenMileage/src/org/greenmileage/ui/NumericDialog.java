package org.greenmileage.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import org.greenmileage.R;
import org.greenmileage.util.CallbackListener;

/**
 * A dialog allowing numeric input
 * @author Connor Garvey
 * @created Jan 10, 2009, 11:08:51 PM
 * @version 0.0.4
 * @since 0.0.4
 */
public class NumericDialog extends AlertDialog {
  private static final String NUMBER = "number";
  private Button button0;
  private Button button1;
  private Button button2;
  private Button button3;
  private Button button4;
  private Button button5;
  private Button button6;
  private Button button7;
  private Button button8;
  private Button button9;
  private Button buttonBack;
  private Button buttonOK;
  private CallbackListener<String> callbackListener;
  private TextView numberText;
  
  /**
   * Creates a numeric dialog
   * @param context The context
   * @param initialValue The initial value to display
   * @param cancelable True if the dialog should be allowed to be cancelled, false otherwise
   * @param cancelListener Receives the cancel event
   * @param callbackListener Receives the input number
   */
  public NumericDialog(final Context context, final String initialValue, final boolean cancelable,
      final OnCancelListener cancelListener, final CallbackListener<String> callbackListener) {
    super(context, cancelable, cancelListener);
    this.construct(context, initialValue, callbackListener);
  }
  
  /**
   * Creates a numeric dialog
   * @param context The context
   * @param initialValue The initial value to display
   * @param callbackListener Receives the input number
   */
  public NumericDialog(final Context context, final String initialValue,
      final CallbackListener<String> callbackListener) {
    super(context);
    this.construct(context, initialValue, callbackListener);
  }
  
  /**
   * Creates a numeric dialog
   * @param context The context
   * @param initialValue The initial value to display
   * @param theme The theme to apply to the dialog
   * @param callbackListener Receives the input number
   */
  public NumericDialog(final Context context, final String initialValue, final int theme,
      final CallbackListener<String> callbackListener) {
    super(context, theme);
    this.construct(context, initialValue, callbackListener);
  }
  
  /**
   * Updates the text watcher for the number field
   * @param textWatcher The text watcher
   */
  public void addTextWatcher(final TextWatcher textWatcher) {
    this.numberText.addTextChangedListener(textWatcher);
  }
  
  /**
   * Performs constructor tasks specific to this class
   * @param context The Android context
   * @param callbackListener The callback listener
   */
  private void construct(final Context context, final String initialValue,
      final CallbackListener<String> callbackListener) {
    if (callbackListener == null) {
      throw new IllegalArgumentException("callbackListener can't be null");
    }
    this.callbackListener = callbackListener;
    final LayoutInflater inflater = (LayoutInflater)context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View view = inflater.inflate(R.layout.number_input_dialog, null);
    this.setView(view);
    this.numberText = (TextView)view.findViewById(R.id.number);
    this.button0 = (Button)view.findViewById(R.id.n0);
    this.button1 = (Button)view.findViewById(R.id.n1);
    this.button2 = (Button)view.findViewById(R.id.n2);
    this.button3 = (Button)view.findViewById(R.id.n3);
    this.button4 = (Button)view.findViewById(R.id.n4);
    this.button5 = (Button)view.findViewById(R.id.n5);
    this.button6 = (Button)view.findViewById(R.id.n6);
    this.button7 = (Button)view.findViewById(R.id.n7);
    this.button8 = (Button)view.findViewById(R.id.n8);
    this.button9 = (Button)view.findViewById(R.id.n9);
    this.buttonBack = (Button)view.findViewById(R.id.back);
    this.buttonOK = (Button)view.findViewById(R.id.ok);
    this.numberText.setText(initialValue);
    this.button0.setOnClickListener(new NumberButtonListener(this.numberText, "0"));
    this.button1.setOnClickListener(new NumberButtonListener(this.numberText, "1"));
    this.button2.setOnClickListener(new NumberButtonListener(this.numberText, "2"));
    this.button3.setOnClickListener(new NumberButtonListener(this.numberText, "3"));
    this.button4.setOnClickListener(new NumberButtonListener(this.numberText, "4"));
    this.button5.setOnClickListener(new NumberButtonListener(this.numberText, "5"));
    this.button6.setOnClickListener(new NumberButtonListener(this.numberText, "6"));
    this.button7.setOnClickListener(new NumberButtonListener(this.numberText, "7"));
    this.button8.setOnClickListener(new NumberButtonListener(this.numberText, "8"));
    this.button9.setOnClickListener(new NumberButtonListener(this.numberText, "9"));
    this.buttonBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        final TextView number = NumericDialog.this.numberText;
        final CharSequence text = number.getText();
        // This is annoyingly ugly, but is from the Android source
        if (!(text instanceof Editable)) {
          number.setText(text, BufferType.EDITABLE);
        }
        final Editable editable = (Editable)number.getText();
        editable.delete(text.length() - 1, text.length());
      }
    });
    this.buttonOK.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        NumericDialog.this.dismiss();
        NumericDialog.this.callbackListener.onResult(NumericDialog.this.numberText.getText()
            .toString());
      }
    });
  }
  
  /**
   * @return the button0
   */
  public Button getButton0() {
    return this.button0;
  }
  
  /**
   * @return the button1
   */
  public Button getButton1() {
    return this.button1;
  }
  
  /**
   * @return the button2
   */
  public Button getButton2() {
    return this.button2;
  }
  
  /**
   * @return the button3
   */
  public Button getButton3() {
    return this.button3;
  }
  
  /**
   * @return the button4
   */
  public Button getButton4() {
    return this.button4;
  }
  
  /**
   * @return the button5
   */
  public Button getButton5() {
    return this.button5;
  }
  
  /**
   * @return the button6
   */
  public Button getButton6() {
    return this.button6;
  }
  
  /**
   * @return the button7
   */
  public Button getButton7() {
    return this.button7;
  }
  
  /**
   * @return the button8
   */
  public Button getButton8() {
    return this.button8;
  }
  
  /**
   * @return the button9
   */
  public Button getButton9() {
    return this.button9;
  }
  
  /**
   * @return the buttonBack
   */
  public Button getButtonBack() {
    return this.buttonBack;
  }
  
  /**
   * @return the buttonOK
   */
  public Button getButtonOK() {
    return this.buttonOK;
  }
  
  /**
   * @return the numberText
   */
  public TextView getNumberText() {
    return this.numberText;
  }
  
  /**
   * @see android.app.Dialog#onRestoreInstanceState(android.os.Bundle)
   */
  @Override
  public void onRestoreInstanceState(final Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    this.numberText.setText(savedInstanceState.getString(NUMBER));
  }
  
  /**
   * @see android.app.Dialog#onSaveInstanceState()
   */
  @Override
  public Bundle onSaveInstanceState() {
    final Bundle state = super.onSaveInstanceState();
    state.putString(NUMBER, this.numberText.getText().toString());
    return state;
  }
  
  /**
   * Sets the text input filter
   * @param filter The filter
   */
  public void setInputFilter(final InputFilter filter) {
    this.numberText.setFilters(new InputFilter[] {filter});
  }
  
  /**
   * Sets the number in the dialog
   * @param value The number
   */
  public void setValue(final String value) {
    this.numberText.setText(value);
  }
  
  private class NumberButtonListener implements View.OnClickListener {
    private final TextView field;
    private final String number;
    
    public NumberButtonListener(final TextView field, final String number) {
      this.field = field;
      this.number = number;
    }
    
    @Override
    public void onClick(final View v) {
      this.field.append(this.number);
    }
  }
}
