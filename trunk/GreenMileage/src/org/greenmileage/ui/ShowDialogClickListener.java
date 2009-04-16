package org.greenmileage.ui;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Handles click events by showing a dialog
 * @author Connor Garvey
 * @created Jan 18, 2009, 11:34:02 AM
 * @version 0.0.4
 * @since 0.0.4
 */
public class ShowDialogClickListener implements OnClickListener {
  private Activity activity;
  private int dialogID;
  
  /**
   * Creates a show dialog click listener
   * @param activity The activity from which the dialog will be created
   * @param dialogID The ID of the dialog in the activity
   */
  public ShowDialogClickListener(Activity activity, int dialogID) {
    this.activity = activity;
    this.dialogID = dialogID;
  }

  /**
   * @see android.view.View.OnClickListener#onClick(android.view.View)
   */
  @Override
  public void onClick(View v) {
    this.activity.showDialog(this.dialogID);
  }
}
