package org.greenmileage.ui;

import android.text.InputFilter;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
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
  private CallbackListener<String> callbackListener;
  private TextView number;
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
  
  /**
   * Creates a numeric dialog
   * @param context The context
   * @param initialValue The initial value to display
   * @param callbackListener Receives the input number
   */
  public NumericDialog(Context context, String initialValue, CallbackListener<String>
      callbackListener) {
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
  public NumericDialog(Context context, String initialValue, int theme, CallbackListener<String>
      callbackListener) {
    super(context, theme);
    this.construct(context, initialValue, callbackListener);
  }
  
  /**
   * Creates a numeric dialog
   * @param context The context
   * @param initialValue The initial value to display
   * @param cancelable True if the dialog should be allowed to be cancelled, false otherwise
   * @param cancelListener Receives the cancel event
   * @param callbackListener Receives the input number
   */
  public NumericDialog(Context context, String initialValue, boolean cancelable,
      OnCancelListener cancelListener, CallbackListener<String> callbackListener) {
    super(context, cancelable, cancelListener);
    this.construct(context, initialValue, callbackListener);
  }
  
  /**
   * Updates the text watcher for the number field
   * @param textWatcher The text watcher
   */
  public void addTextWatcher(TextWatcher textWatcher) {
    this.number.addTextChangedListener(textWatcher);
  }
  
  /**
   * Performs constructor tasks specific to this class
   * @param context The Android context
   * @param callbackListener The callback listener
   */
  private void construct(Context context, String initialValue, CallbackListener<String>
      callbackListener) {
    if (callbackListener == null) {
      throw new IllegalArgumentException("callbackListener can't be null");
    }
    this.callbackListener = callbackListener;
    LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.
        LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.number_input_dialog, null);
    this.setView(view);
    this.number = (TextView)view.findViewById(R.id.number);
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
    this.number.setText(initialValue);
    this.button0.setOnClickListener(new NumberButtonListener(this.number, "0"));
    this.button1.setOnClickListener(new NumberButtonListener(this.number, "1"));
    this.button2.setOnClickListener(new NumberButtonListener(this.number, "2"));
    this.button3.setOnClickListener(new NumberButtonListener(this.number, "3"));
    this.button4.setOnClickListener(new NumberButtonListener(this.number, "4"));
    this.button5.setOnClickListener(new NumberButtonListener(this.number, "5"));
    this.button6.setOnClickListener(new NumberButtonListener(this.number, "6"));
    this.button7.setOnClickListener(new NumberButtonListener(this.number, "7"));
    this.button8.setOnClickListener(new NumberButtonListener(this.number, "8"));
    this.button9.setOnClickListener(new NumberButtonListener(this.number, "9"));
    this.buttonBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        TextView number = NumericDialog.this.number;
        CharSequence text = number.getText();
        // This is annoyingly ugly, but is from the Android source
        if (!(text instanceof Editable)) {
          number.setText(text, BufferType.EDITABLE);
        }
        Editable editable = (Editable)number.getText();
        editable.delete(text.length() - 1, text.length());
      }
    });
    this.buttonOK.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NumericDialog.this.dismiss();
        NumericDialog.this.callbackListener.onResult(NumericDialog.this.number.getText().
            toString());
      }
    });
  }
  
  /**
   * @see android.app.Dialog#onSaveInstanceState()
   */
  @Override
  public Bundle onSaveInstanceState() {
      Bundle state = super.onSaveInstanceState();
      state.putString(NUMBER, this.number.getText().toString());
      return state;
  }
  
  /**
   * @see android.app.Dialog#onRestoreInstanceState(android.os.Bundle)
   */
  @Override
  public void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
      this.number.setText(savedInstanceState.getString(NUMBER));
  }
  
  /**
   * Sets the text input filter
   * @param filter The filter
   */
  public void setInputFilter(InputFilter filter) {
    this.number.setFilters(new InputFilter[] {filter});
  }
  
  /**
   * Sets the number in the dialog
   * @param value The number
   */
  public void setValue(String value) {
    this.number.setText(value);
  }
  
  private class NumberButtonListener implements View.OnClickListener {
    private TextView field;
    private String number;
    
    public NumberButtonListener(TextView field, String number) {
      this.field = field;
      this.number = number;
    }
    
    @Override
    public void onClick(View v) {
      this.field.append(this.number);
    }
  }
}
