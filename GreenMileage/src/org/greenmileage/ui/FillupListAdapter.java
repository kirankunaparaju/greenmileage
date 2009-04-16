package org.greenmileage.ui;

import org.greenmileage.data.FillupStatistics;
import org.greenmileage.data.FillupUtils;
import org.greenmileage.util.BigDecimalUtils;
import org.greenmileage.util.IntegerUtils;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import org.greenmileage.R;
import org.greenmileage.data.Fillup;

/**
 * Adapts fillups to a list
 * @author Connor Garvey
 * @created Nov 16, 2008, 5:51:02 PM
 * @version 0.0.3
 * @since 0.0.1
 */
public class FillupListAdapter extends CursorAdapter {
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
      "MM-dd-yyyy");
  private Context context;
  
  /**
   * Creates a fillup list adapter
   * @param cursor The cursor from which to retrieve fillups
   * @param context The context
   */
  public FillupListAdapter(Context context, Cursor cursor) {
    super(context, cursor);
    this.context = context;
  }
  
  /**
   * @see CursorAdapter#bindView(View, Context, android.database.Cursor)
   */
  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    Fillup fillup = FillupUtils.readFromCursor(cursor);
    TextView dateText = (TextView)view.findViewById(R.id.date);
    dateText.setText(DATE_FORMAT.format(fillup.getDate()));
    StringBuilder summary = new StringBuilder().
        append(IntegerUtils.toString(fillup.getMileage())). //
        append(context.getString(R.string.message_distanceAbbreviation)). //
        append(" "). //
        append(context.getString(R.string.message_for)). //
        append(" "). //
        append(BigDecimalUtils.toString(fillup.getVolume())). //
        append(context.getString(R.string.message_volumeAbbreviation)). //
        append(" "). //
        append(context.getString(R.string.message_at)). //
        append(" "). //
        append(context.getString(R.string.message_currencySymbol)). //
        append(BigDecimalUtils.toString(fillup.getPrice())). //
        append(" "). //
        append(context.getString(R.string.message_per)). //
        append(" "). //
        append(context.getString(R.string.message_volumeUnit));
    TextView summaryText = (TextView)view.findViewById(R.id.summary);
    summaryText.setText(summary.toString());
    TextView mileageSummaryText = (TextView)view.findViewById(R.id.
        mileageSummary);
    if ((fillup.getVolume() != null) && (fillup.getMileage() != null)) {
      try {
        cursor.moveToNext();
        if (cursor.isAfterLast()) {
          mileageSummaryText.setVisibility(View.GONE);
        }
        else {
          Fillup previousFillup = FillupUtils.readFromCursor(cursor);
          if ((previousFillup.getVolume() != null) && (previousFillup.
              getMileage() != null)) {
            summary = new StringBuilder(). //
                append(FillupStatistics.distancePerVolumeAsString(
                    previousFillup, fillup)). //
                append(context.getString(R.string.
                    message_distancePerVolumeAbbreviation)). //
                append(" "). //
                append(context.getString(R.string.message_sinceLastFillup));
            mileageSummaryText.setText(summary);
            mileageSummaryText.setVisibility(View.VISIBLE);
          }
          else {
            mileageSummaryText.setVisibility(View.GONE);
          }
        }
      }
      finally {
        cursor.moveToPrevious();
      }
    }
    else {
      mileageSummaryText.setVisibility(View.GONE);
    }
  }
  
  /**
   * @see CursorAdapter#newView(Context, Cursor, android.view.ViewGroup)
   */
  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return View.inflate(this.context, R.layout.fillup_list_item, null);
  }
}
