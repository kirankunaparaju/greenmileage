package org.greenmileage;

import android.text.method.DigitsKeyListener;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.greenmileage.GreenMileageDefinitions.Fillups;
import org.greenmileage.data.Fillup;
import org.greenmileage.data.FillupUtils;
import org.greenmileage.ui.DecimalTextWatcher;
import org.greenmileage.ui.NumericDialog;
import org.greenmileage.ui.ShowDialogClickListener;
import org.greenmileage.ui.TextViewCallbackListener;

/**
 * Edits a single fillup
 * @author Connor Garvey
 * @created Nov 9, 2008, 7:54:10 PM
 * @version 0.0.1
 * @since 0.0.1
 */
public class FillupEditor extends Activity {
  private static final int DIALOG_DATE_ID = 0;
  private static final int DIALOG_ODOMETER_ID = 1;
  private static final int DIALOG_PRICE_ID = 2;
  private static final int DIALOG_VOLUME_ID = 3;
  private static final String LOG_TAG = "FillupEdit";
  private static final int MENU_DELETE_ID = Menu.FIRST + 2;
  private static final int MENU_DISCARD_ID = Menu.FIRST + 1;
  private static final int MENU_REVERT_ID = Menu.FIRST;
  private static final String ORIGINAL_FILLUP = "originalFillup";
  private static final int STATE_EDIT = 0;
  private static final int STATE_INSERT = 1;
  private Cursor cursor;
  private TextView dateDisplay;
  private final OnDateSetListener dateSetListener = new OnDateSetListener() {
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      FillupEditor.this.fillupYear = year;
      FillupEditor.this.fillupMonth = monthOfYear;
      FillupEditor.this.fillupDay = dayOfMonth;
      FillupEditor.this.updateDateDisplay();
    }
  };
  private int fillupDay;
  private int fillupMonth;
  private final boolean fillupOnly = false;
  private int fillupYear;
  private EditText mileageText;
  private Fillup originalFillup;
  private EditText priceText;
  private Button saveButton;
  private int state;
  private Uri uri;
  private EditText volumeText;
  
  /**
   * Creates a fillup editor
   */
  public FillupEditor() {
  }
  
  /**
   * Take care of canceling work on a fillup. Deletes the fillup if we had created it, otherwise
   * reverts to the original fillup.
   */
  private final void cancelFillup() {
    if (this.cursor != null) {
      if (this.state == STATE_EDIT) {
        this.cursor.close();
        this.cursor = null;
        final ContentValues values = new ContentValues();
        this.originalFillup.saveToContentValues(values);
        this.getContentResolver().update(this.uri, values, null, null);
      }
      else if (this.state == STATE_INSERT) {
        this.deleteFillup();
      }
    }
    this.setResult(RESULT_CANCELED);
    this.finish();
  }
  
  /**
   * Deletes the fillup and clears the entry fields
   */
  private final void deleteFillup() {
    if (this.cursor != null) {
      this.cursor.close();
      this.cursor = null;
      this.getContentResolver().delete(this.uri, null, null);
      this.mileageText.setText("");
      this.volumeText.setText("");
      this.priceText.setText("");
    }
  }
  
  private long getFillupTime() {
    final Calendar c = Calendar.getInstance();
    c.set(this.fillupYear, this.fillupMonth, this.fillupDay, 0, 0, 0);
    return c.getTimeInMillis();
  }
  
  private String getMileage() {
    return this.getTextValue(this.mileageText);
  }
  
  private String getPrice() {
    return this.getTextValue(this.priceText);
  }
  
  private String getTextValue(EditText textField) {
    return textField.getText().toString();
  }
  
  private String getVolume() {
    return this.getTextValue(this.volumeText);
  }
  
  /**
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final Intent intent = this.getIntent();
    String action = intent.getAction();
    if (savedInstanceState != null) {
      try {
        this.originalFillup = FillupUtils.readFromBundle(savedInstanceState, ORIGINAL_FILLUP);
        this.uri = savedInstanceState.getParcelable("uri");
        this.state = savedInstanceState.getInt("state");
        action = Intent.ACTION_EDIT;
      }
      catch (final ParseException ex) {
        Log.e(LOG_TAG, "Could not parse fillup date");
        // TODO: Display an error
      }
    }
    else if (Intent.ACTION_EDIT.equals(action)) {
      this.state = STATE_EDIT;
      this.uri = intent.getData();
    }
    else if (Intent.ACTION_INSERT.equals(action)) {
      this.state = STATE_INSERT;
      this.uri = this.getContentResolver().insert(intent.getData(), null);
      if (this.uri == null) {
        Log.e(LOG_TAG, "Failed to insert new note into " + this.getIntent().getData());
        this.finish();
        return;
      }
      this.setResult(RESULT_OK, (new Intent()).setAction(this.uri.toString()));
    }
    else {
      Log.e(LOG_TAG, "Unknown action.  Exiting.");
      this.finish();
      return;
    }
    this.setContentView(R.layout.fillup_editor);
    this.cursor = this.managedQuery(this.uri, Fillups.PROJECTION, null, null, null);
    {
      final Calendar c = Calendar.getInstance();
      this.fillupYear = c.get(Calendar.YEAR);
      this.fillupMonth = c.get(Calendar.MONTH);
      this.fillupDay = c.get(Calendar.DAY_OF_MONTH);
    }
    this.dateDisplay = (TextView)this.findViewById(R.id.date);
    this.volumeText = (EditText)this.findViewById(R.id.volume);
    this.volumeText.addTextChangedListener(new DecimalTextWatcher());
    this.mileageText = (EditText)this.findViewById(R.id.mileage);
    this.priceText = (EditText)this.findViewById(R.id.price);
    this.priceText.addTextChangedListener(new DecimalTextWatcher());
    this.updateDateDisplay();
    final Button pickDateButton = (Button)this.findViewById(R.id.pickDate);
    pickDateButton.setOnClickListener(new ShowDialogClickListener(this, DIALOG_DATE_ID));
    final ImageButton pickVolumeButton = (ImageButton)this.findViewById(R.id.pickVolume);
    pickVolumeButton.setOnClickListener(new ShowDialogClickListener(this, DIALOG_VOLUME_ID));
    final ImageButton pickMileageButton = (ImageButton)this.findViewById(R.id.pickMileage);
    pickMileageButton.setOnClickListener(new ShowDialogClickListener(this, DIALOG_ODOMETER_ID));
    final ImageButton pickPriceButton = (ImageButton)this.findViewById(R.id.pickPrice);
    pickPriceButton.setOnClickListener(new ShowDialogClickListener(this, DIALOG_PRICE_ID));
    this.saveButton = (Button)this.findViewById(R.id.save);
    this.saveButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FillupEditor.this.finish();
      }
    });
  }
  
  /**
   * @see android.app.Activity#onCreateDialog(int)
   */
  @Override
  protected Dialog onCreateDialog(int id) {
    if (id == DIALOG_DATE_ID) {
      return new DatePickerDialog(this, this.dateSetListener, this.fillupYear, this.fillupMonth,
          this.fillupDay);
    }
    else if (id == DIALOG_VOLUME_ID) {
      NumericDialog dialog =
          new NumericDialog(this, this.getVolume(), new TextViewCallbackListener(this.volumeText));
      dialog.addTextWatcher(new DecimalTextWatcher());
      dialog.setTitle(this.getString(R.string.title_volumeUnits));
      return dialog;
    }
    else if (id == DIALOG_ODOMETER_ID) {
      NumericDialog dialog =
          new NumericDialog(this, this.getMileage(), new TextViewCallbackListener(this.
              mileageText));
      dialog.setInputFilter(new DigitsKeyListener());
      dialog.setTitle(this.getString(R.string.title_odometer));
      return dialog;
    }
    else if (id == DIALOG_PRICE_ID) {
      NumericDialog dialog =
          new NumericDialog(this, this.getPrice(), new TextViewCallbackListener(this.priceText));
      dialog.addTextWatcher(new DecimalTextWatcher());
      dialog.setTitle(this.getString(R.string.title_pricePerVolumeUnit));
      return dialog;
    }
    return null;
  }
  
  /**
   * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    // Build the menus that are shown when editing.
    if (this.state == STATE_EDIT) {
      menu.add(0, MENU_REVERT_ID, 0, R.string.menu_revert).setShortcut('0', 'r').setIcon(
          android.R.drawable.ic_menu_revert);
      if (!this.fillupOnly) {
        menu.add(0, MENU_DELETE_ID, 0, R.string.menu_delete).setShortcut('1', 'd').setIcon(
            android.R.drawable.ic_menu_delete);
      }
      // Build the menus that are shown when inserting.
    }
    else {
      menu.add(0, MENU_DISCARD_ID, 0, R.string.menu_discard).setShortcut('0', 'd').setIcon(
          android.R.drawable.ic_menu_delete);
    }
    // If we are working on a full note, then append to the
    // menu items for any other activities that can do stuff with it
    // as well. This does a query on the system for any activities that
    // implement the ALTERNATIVE_ACTION for our data, adding a menu item
    // for each one that is found.
    if (!this.fillupOnly) {
      final Intent intent = new Intent(null, this.getIntent().getData());
      intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
      menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, new ComponentName(this,
          FillupEditor.class), null, intent, 0, null);
    }
    return true;
  }
  
  /**
   * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle all of the possible menu actions.
    switch (item.getItemId()) {
      case MENU_DELETE_ID:
        this.deleteFillup();
        this.finish();
        break;
      case MENU_DISCARD_ID:
        this.cancelFillup();
        break;
      case MENU_REVERT_ID:
        this.cancelFillup();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
  
  /**
   * @see android.app.Activity#onPause()
   */
  @Override
  protected void onPause() {
    super.onPause();
    if (this.cursor != null) {
      if (this.isFinishing() && !this.valid()) {
        this.setResult(RESULT_CANCELED);
        this.deleteFillup();
        return;
      }
      final ContentValues values = new ContentValues();
      values.put(Fillups.DATE, this.getFillupTime());
      values.put(Fillups.AUTOMOBILE, "Default");
      values.put(Fillups.MILEAGE, this.getMileage());
      values.put(Fillups.PRICE, this.getPrice());
      values.put(Fillups.VOLUME, this.getVolume());
      this.getContentResolver().update(this.uri, values, null, null);
    }
  }
  
  /**
   * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog)
   */
  @Override
  protected void onPrepareDialog(int id, Dialog dialogIn) {
    super.onPrepareDialog(id, dialogIn);
    if (id == DIALOG_VOLUME_ID) {
      NumericDialog dialog = (NumericDialog)dialogIn;
      dialog.setValue(this.getVolume());
    }
    else if (id == DIALOG_ODOMETER_ID) {
      NumericDialog dialog = (NumericDialog)dialogIn;
      dialog.setValue(this.getMileage());
    }
    else if (id == DIALOG_PRICE_ID) {
      NumericDialog dialog = (NumericDialog)dialogIn;
      dialog.setValue(this.getPrice());
    }
  }
  
  /**
   * @see android.app.Activity#onResume()
   */
  @Override
  protected void onResume() {
    super.onResume();
    if (this.cursor != null) {
      this.cursor.moveToFirst();
      if (this.state == STATE_EDIT) {
        this.setTitle(this.getText(R.string.title_editFillup));
      }
      else if (this.state == STATE_INSERT) {
        this.setTitle(this.getText(R.string.title_addFillup));
      }
      final String id = this.cursor.getString(Fillups.ID_COLUMN_ID);
      final String mileage = this.cursor.getString(Fillups.MILEAGE_COLUMN_ID);
      final Date date = new Date(this.cursor.getLong(Fillups.DATE_COLUMN_ID));
      final String price = this.cursor.getString(Fillups.PRICE_COLUMN_ID);
      final String volume = this.cursor.getString(Fillups.VOLUME_COLUMN_ID);
      final String automobile = this.cursor.getString(Fillups.AUTOMOBILE_COLUMN_ID);
      final Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      this.fillupYear = calendar.get(Calendar.YEAR);
      this.fillupMonth = calendar.get(Calendar.MONTH);
      this.fillupDay = calendar.get(Calendar.DAY_OF_MONTH);
      this.mileageText.setText(mileage);
      this.updateDateDisplay();
      this.priceText.setText(price);
      this.volumeText.setText(volume);
      if (this.originalFillup == null) {
        this.originalFillup = new Fillup();
        this.originalFillup.setMileageFromString(mileage);
        this.originalFillup.setDate(date);
        this.originalFillup.setPriceFromString(price);
        this.originalFillup.setVolumeFromString(volume);
        this.originalFillup.setAutomobile(automobile);
        this.originalFillup.setIDFromString(id);
      }
    }
    else {
      this.setTitle(this.getText(R.string.error_title_resume));
      this.mileageText.setText(this.getText(R.string.error_message_resume));
    }
  }
  
  /**
   * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
   */
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    this.originalFillup.setIDFromString(uri.getPathSegments().get(1));
    this.originalFillup.saveToBundle(outState, ORIGINAL_FILLUP);
    outState.putParcelable("uri", this.uri);
    outState.putInt("state", this.state);
  }
  
  private void updateDateDisplay() {
    this.dateDisplay.setText( //
        new StringBuilder(). //
            append(this.fillupMonth + 1). //
            append("-"). //
            append(this.fillupDay). //
            append("-"). //
            append(this.fillupYear).toString());
  }
  
  private boolean valid() {
    if (this.getVolume().length() == 0) {
      return false;
    }
    if (this.getMileage().length() == 0) {
      return false;
    }
    if (this.getPrice().length() == 0) {
      return false;
    }
    return true;
  }
}
