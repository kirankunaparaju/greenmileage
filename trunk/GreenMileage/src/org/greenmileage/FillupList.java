package org.greenmileage;

import org.greenmileage.ui.GreenIntent;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import org.greenmileage.GreenMileageDefinitions.Fillups;
import org.greenmileage.ui.FillupListAdapter;

/**
 * The main class
 * @author Connor Garvey
 * @created Nov 1, 2008, 3:59:52 PM
 * @version 0.0.3
 * @since 0.0.1
 */
public class FillupList extends ListActivity {
  /**
   * The delete fillup menu item
   */
  public static final int MENU_ITEM_DELETE = Menu.FIRST + 2;
  /**
   * The edit fillup menu item
   */
  public static final int MENU_ITEM_EDIT = Menu.FIRST + 1;
  /**
   * The insert fillup menu item
   */
  public static final int MENU_ITEM_INSERT = Menu.FIRST;
  /**
   * The view statistics menu item
   */
  public static final int MENU_ITEM_VIEW_STATISTICS = Menu.FIRST + 3;
  private static final String LOG_TAG = "NotesList";
  
  /**
   * Set the title for the activity, use the configured layout, set up the data
   * provider and populate the fillup list
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);
    Intent intent = this.getIntent();
    if (intent.getData() == null) {
      intent.setData(Fillups.CONTENT_URI);
    }
    Cursor cursor = this.managedQuery(this.getIntent().getData(), Fillups.
        PROJECTION, null, null, Fillups.DEFAULT_SORT_ORDER);
    this.setContentView(R.layout.fillup_list);
    this.setListAdapter(new FillupListAdapter(this, cursor));
    this.getListView().setOnCreateContextMenuListener(this);
  }
  
  /**
   * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    menu.add(0, MENU_ITEM_INSERT, 0, R.string.menu_addFillup). //
        setShortcut('3', 'a'). //
        setIcon(android.R.drawable.ic_menu_add);
    menu.add(0, MENU_ITEM_VIEW_STATISTICS, 0, R.string.menu_viewStatistics). //
      setShortcut('4', 's'). //
      setIcon(android.R.drawable.ic_menu_info_details);
    Intent intent = new Intent(null, this.getIntent().getData());
    intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
    menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, new ComponentName(
        this, FillupList.class), null, intent, 0, null);
    return true;
  }
  
  /**
   * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
   */
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
    final boolean haveItems = getListAdapter().getCount() > 0;
    if (haveItems) {
      Uri uri = ContentUris.withAppendedId(this.getIntent().getData(), this.
          getSelectedItemId());
      Intent[] specifics = new Intent[1];
      specifics[0] = new Intent(Intent.ACTION_EDIT, uri);
      MenuItem[] items = new MenuItem[1];
      Intent intent = new Intent(null, uri);
      intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
      menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0, null, specifics,
          intent, 0, items);
      if (items[0] != null) {
        items[0].setShortcut('1', 'e');
        items[0].setIcon(android.R.drawable.ic_menu_edit);
      }
    }
    else {
      menu.removeGroup(Menu.CATEGORY_ALTERNATIVE);
    }
    return true;
  }
  
  /**
   * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case MENU_ITEM_INSERT:
        // Launch activity to insert a new item
        this.startActivity(new Intent(Intent.ACTION_INSERT, this.getIntent().
            getData()));
        return true;
      case MENU_ITEM_VIEW_STATISTICS:
        // Launch activity to view statistics
        this.startActivity(new Intent(GreenIntent.ACTION_VIEW_STATISTICS,
            this.getIntent().getData()));
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  /**
   * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
   * android.view.View, android.view.ContextMenu.ContextMenuInfo)
   */
  @Override
  public void onCreateContextMenu(ContextMenu menu, View view,
      ContextMenuInfo menuInfo) {
    AdapterView.AdapterContextMenuInfo info;
    try {
      info = (AdapterView.AdapterContextMenuInfo) menuInfo;
    }
    catch (ClassCastException e) {
      Log.e(LOG_TAG, "bad menuInfo", e);
      return;
    }
    Cursor cursor = (Cursor) getListAdapter().getItem(info.position);
    if (cursor == null) {
      return;
    }
    menu.setHeaderTitle(view.getContext().getString(R.string.
        message_fillupAtMile) + " " + cursor.getString(Fillups.
        MILEAGE_COLUMN_ID));
    menu.add(0, MENU_ITEM_EDIT, 0, R.string.menu_editFillup);
    menu.add(0, MENU_ITEM_DELETE, 1, R.string.menu_deleteFillup);
  }
  
  /**
   * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
   */
  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info;
    try {
      info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    }
    catch (ClassCastException e) {
      Log.e(LOG_TAG, "bad menuInfo", e);
      return false;
    }
    Uri fillupUri = ContentUris.withAppendedId(getIntent().getData(), info.id);
    switch (item.getItemId()) {
      case MENU_ITEM_EDIT: {
        this.startActivity(new Intent(Intent.ACTION_EDIT, fillupUri));
        return true;
      }
      case MENU_ITEM_DELETE: {
        this.getContentResolver().delete(fillupUri, null, null);
        return true;
      }
    }
    return false;
  }
  
  /**
   * @see android.app.ListActivity#onListItemClick(android.widget.ListView,
   * android.view.View, int, long)
   */
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
    String action = getIntent().getAction();
    if (Intent.ACTION_PICK.equals(action)
        || Intent.ACTION_GET_CONTENT.equals(action)) {
      setResult(RESULT_OK, new Intent().setData(uri));
    }
    else {
      this.startActivity(new Intent(Intent.ACTION_EDIT, uri));
    }
  }
}