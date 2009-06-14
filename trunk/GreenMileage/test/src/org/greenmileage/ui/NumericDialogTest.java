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
  public void testBack() {
    final NumericDialog dialog = new NumericDialog(this.getInstrumentation().getContext(),
        StringUtils.EMPTY, new EmptyCallbackListener<String>());
    final ExpectationTextWatcher watcher = new ExpectationTextWatcher();
    dialog.addTextWatcher(watcher);
    dialog.getButtonBack().performClick();
    watcher.validate();
  }
  
  /**
   * @see NumericDialog
   */
  public void testMonkey() {
    final NumericDialog dialog = new NumericDialog(this.getInstrumentation().getContext(),
        StringUtils.EMPTY, new EmptyCallbackListener<String>());
    final ExpectationTextWatcher watcher = new ExpectationTextWatcher("c", "", "5", "51", "51.",
        "51.5");
    dialog.addTextWatcher(watcher);
    dialog.getButtonBack().performClick();
    dialog.getNumberText().append("c");
    dialog.getButtonBack().performClick();
    dialog.getButtonBack().performClick();
    dialog.getButton5().performClick();
    dialog.getNumberText().append("1");
    dialog.getNumberText().append(".");
    dialog.getButton5().performClick();
    watcher.validate();
  }
  
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
    final ExpectationTextWatcher watcher = new ExpectationTextWatcher("0", "01", "012", "0123",
        "01234", "012345", "0123456", "01234567", "012345678", "0123456789");
    dialog.addTextWatcher(watcher);
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
    watcher.validate();
  }
  
  /**
   * @see NumericDialog
   */
  public void testTenDigitsWithBack() {
    final NumericDialog dialog = new NumericDialog(this.getInstrumentation().getContext(),
        StringUtils.EMPTY, new EmptyCallbackListener<String>());
    final ExpectationTextWatcher watcher = new ExpectationTextWatcher("0", "01", "012", "0123",
        "012", "0125", "01256", "012567", "0125678", "01256789");
    dialog.addTextWatcher(watcher);
    dialog.getButton0().performClick();
    dialog.getButton1().performClick();
    dialog.getButton2().performClick();
    dialog.getButton3().performClick();
    dialog.getButtonBack().performClick();
    dialog.getButton5().performClick();
    dialog.getButton6().performClick();
    dialog.getButton7().performClick();
    dialog.getButton8().performClick();
    dialog.getButton9().performClick();
    watcher.validate();
  }
  
  /**
   * @see NumericDialog
   */
  public void testTyping() {
    final NumericDialog dialog = new NumericDialog(this.getInstrumentation().getContext(),
        StringUtils.EMPTY, new EmptyCallbackListener<String>());
    final ExpectationTextWatcher watcher = new ExpectationTextWatcher("c", "c1", "c1.");
    dialog.addTextWatcher(watcher);
    dialog.getNumberText().append("c");
    dialog.getNumberText().append("1");
    dialog.getNumberText().append(".");
    watcher.validate();
  }
}
