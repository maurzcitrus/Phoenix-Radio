package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;

/**
 * Handles individual record list item user interface and its corresponding events
 *
 * @author Chandramouli
 */
public class SchedulesViewHolder extends RecyclerView.ViewHolder {

  public final View view;
  public final TextView txtPresenter;
  public final TextView txtProgName;
  public final TextView txtStartTime;
  public final TextView txtEndTime;

  public ProgramSlot schedule;

  /**
   * Initialise the ProgramName ,Presenter Name , Start Time , End Time  Text Field.
   *
   * @param itemView
   */

  public SchedulesViewHolder(View itemView) {
    super(itemView);

    view = itemView;
    txtProgName = (TextView) itemView.findViewById(R.id.program);
    txtPresenter = (TextView) itemView.findViewById(R.id.presenter);
    txtStartTime = (TextView) itemView.findViewById(R.id.starttime);
    txtEndTime = (TextView) itemView.findViewById(R.id.endtime);

  }

}
