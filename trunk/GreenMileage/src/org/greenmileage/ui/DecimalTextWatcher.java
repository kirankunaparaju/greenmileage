package org.greenmileage.ui;

import java.util.HashSet;
import java.util.Set;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * A text watcher that places a decimal point in a number and ignores characters
 * @author Connor Garvey
 * @created Nov 14, 2008, 10:19:25 AM
 * @version 0.0.2
 * @since 0.0.1
 */
public class DecimalTextWatcher implements TextWatcher {
  private static final Set<Character> DIGITS;
  private char decimalChar = '.';
  private int decimalDigits = 3;
  
  static {
    DIGITS = new HashSet<Character>();
    DIGITS.add('0');
    DIGITS.add('1');
    DIGITS.add('2');
    DIGITS.add('3');
    DIGITS.add('4');
    DIGITS.add('5');
    DIGITS.add('6');
    DIGITS.add('7');
    DIGITS.add('8');
    DIGITS.add('9');
  }
  
  /**
   * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
   */
  @Override
  public void afterTextChanged(Editable s) {
    if (this.validate(s)) {
      return;
    }
    StringBuilder digits = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if (DIGITS.contains(c)) {
        digits.append(c);
      }
    }
    this.removePrecedingZeroes(digits);
    if (digits.length() <= this.decimalDigits) {
      for (int i = digits.length(); i <= this.decimalDigits - 1; ++i) {
        digits.insert(0, '0');
      }
      digits.insert(0, this.decimalChar);
      s.replace(0, s.length(), digits.toString());
      return;
    }
    int decimalIndex = digits.length() - this.decimalDigits;
    StringBuilder decimal = new StringBuilder().
        append(digits.substring(0, decimalIndex)).
        append(this.decimalChar).
        append(digits.substring(decimalIndex));
    s.replace(0, s.length(), decimal.toString());
  }
  
  private void removePrecedingZeroes(StringBuilder number) {
    for (int i = 0; i < number.length(); ++i) {
      if (number.charAt(i) == '0') {
        number.deleteCharAt(i--);
      }
      else {
        return;
      }
    }
  }
  
  private boolean validate(Editable s) {
    if (s.length() < this.decimalDigits + 1) {
      return false;
    }
    int dotIndex = s.length() - (this.decimalDigits + 1);
    for (int i = s.length() - 1; i > -1; --i) {
      if (i == dotIndex) {
        if (s.charAt(i) != '.') {
          return false;
        }
      }
      else {
        if (!DIGITS.contains(s.charAt(i))) {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * @see TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int
      after) {
  }
  
  /**
   * @see TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }
}
