package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;

/**
 * Inherits the Android Base RecyclerVIew Class and handles record list user interface
 *
 * @author Chandramouli
 * @see android.support.v7.widget.RecyclerView.Adapter
 */

public class SchedulesRecyclerViewAdapter extends RecyclerView.Adapter<SchedulesViewHolder> {

  private List<ProgramSlot> schedules;

  /**
   * Constructor to Initialise the ProgramSlot entity.
   *
   * @param schedules
   */
  public SchedulesRecyclerViewAdapter(List<ProgramSlot> schedules) {

    if (schedules != null) {
      this.schedules = schedules;
    } else {
      this.schedules = new ArrayList<>();
    }
  }

  public void setSchedules(List<ProgramSlot> slots) {
    this.schedules = slots;
  }

  public List<ProgramSlot> getSchedules() {
    return this.schedules;
  }


  @Override
  public SchedulesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.view_holder_schedule, parent, false);
    return new SchedulesViewHolder(view);
  }

  /**
   * Set the ProgramSlot attributes to be displayed on the Screen.
   *
   * @param holder
   * @param position
   */

  @Override
  public void onBindViewHolder(final SchedulesViewHolder holder, int position) {
    ProgramSlot current = schedules.get(position);

    holder.schedule = current;
    holder.txtProgName.setText(current.getProgramName());
    holder.txtPresenter.setText(current.getPresenterName());

    holder.txtStartTime.setText(current.getStartTime());
    holder.txtEndTime.setText(current.getEndTime());

    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ControlFactory.getScheduleProgramDetailController().startEditUseCase(holder.schedule);
      }
    });
  }

  /**
   * Number of rows that needs to be populated on the screen.
   *
   * @return Number of rows to be created.
   */

  @Override
  public int getItemCount() {
    return this.schedules.size();
  }
}

