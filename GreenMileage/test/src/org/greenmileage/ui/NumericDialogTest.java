package org.greenmileage.ui;

import android.test.InstrumentationTestCase;
import org.greenmileage.util.EmptyCallbackListener;
import org.greenmileage.util.StringUtils;

/**
 * @see NumericDialog
 * @author Connor Garvey
 * @created Jun 13, 2009 10:12:38 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class NumericDialogTest extends InstrumentationTestCase {
  /**
   * @see NumericDialog
   */
  public void testOneDigit() {
    final NumericDialog dialog = new NumericDialog(this.getInstrumentation().getContext(),
        StringUtils.EMPTY, new EmptyCallbackListener<String>());
    dialog.addTextWatcher(new ExpectationTextWatcher("0"));
    dialog.getButton0().performClick();
  }
  
  /**
   * @see NumericDialog
   */
  public void testTenDigits() {
    final NumericDialog dialog = new NumericDialog(this.getInstrumentation().getContext(),
        StringUtils.EMPTY, new EmptyCallbackListener<String>());
    dialog.addTextWatcher(new ExpectationTextWatcher("0", "01", "012", "0123", "01234", "012345",
        "0123456", "01234567", "012345678", "0123456789"));
    dialog.getButton0().performClick();
    dialog.getButton1().performClick();
    dialog.getButton2().performClick();
    dialog.getButton3().performClick();
    dialog.getButton4().performClick();
    dialog.getButton5().performClick();
    dialog.getButton6().performClick();
    dialog.getButton7().performClick();
    dialog.getButton8().performClick();
    dialog.getButton9().performClick();
  }
}
