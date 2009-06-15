package org.greenmileage.ui;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.HashSet;
import java.util.Set;

/**
 * A text watcher that places a decimal point in a number and ignores characters
 * @author Connor Garvey
 * @created Nov 14, 2008, 10:19:25 AM
 * @version 0.0.2
 * @since 0.0.1
 */
public class DecimalTextWatcher implements TextWatcher {
  private static final char DECIMAL_CHARACTER = '.';
  private static final Set<Character> DIGITS;
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
  private int decimalDigits = 3;
  
  /**
   * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
   */
  @Override
  public void afterTextChanged(final Editable s) {
    if (this.validate(s)) {
      return;
    }
    final StringBuilder digits = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      final char c = s.charAt(i);
      if (DIGITS.contains(c)) {
        digits.append(c);
      }
    }
    this.removePrecedingZeroes(digits);
    if (digits.length() <= this.decimalDigits) {
      for (int i = digits.length(); i <= this.decimalDigits - 1; ++i) {
        digits.insert(0, '0');
      }
      digits.insert(0, DecimalTextWatcher.DECIMAL_CHARACTER);
      s.replace(0, s.length(), digits.toString());
      return;
    }
    final int decimalIndex = digits.length() - this.decimalDigits;
    final StringBuilder decimal = new StringBuilder().append(digits.substring(0, decimalIndex))
        .append(DecimalTextWatcher.DECIMAL_CHARACTER).append(digits.substring(decimalIndex));
    s.replace(0, s.length(), decimal.toString());
  }
  
  /**
   * @see TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void beforeTextChanged(final CharSequence s, final int start, final int count,
      final int after) {
  }
  
  /**
   * The number of digits to keep to the right of the decimal
   * @return the decimalDigits
   */
  public int getDecimalDigits() {
    return this.decimalDigits;
  }
  
  /**
   * @see TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
  }
  
  private void removePrecedingZeroes(final StringBuilder number) {
    for (int i = 0; i < number.length(); ++i) {
      if (number.charAt(i) == '0') {
        number.deleteCharAt(i--);
      }
      else {
        return;
      }
    }
  }
  
  /**
   * The number of digits to keep to the right of the decimal
   * @param decimalDigits the decimalDigits to set
   */
  public void setDecimalDigits(final int decimalDigits) {
    this.decimalDigits = decimalDigits;
  }
  
  private boolean validate(final Editable s) {
    if (s.length() < this.decimalDigits + 1) {
      return false;
    }
    final int dotIndex = s.length() - (this.decimalDigits + 1);
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
}
