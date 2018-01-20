package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

public class RadioProgramsViewHolder extends RecyclerView.ViewHolder {

  public final View view;
  public final TextView txtName;
  public final TextView txtDescription;
  public final TextView txtDuration;
  public final TextView txtAssignedCount;
  public RadioProgram radioProgram;

  public RadioProgramsViewHolder(View itemView) {
    super(itemView);

    view = itemView;
    txtName = (TextView) itemView.findViewById(R.id.radio_program_name);
    txtDescription = (TextView) itemView.findViewById(R.id.radio_program_description);
    txtDuration = (TextView) itemView.findViewById(R.id.radio_program_duration);
    txtAssignedCount = (TextView) itemView.findViewById(R.id.assigned_slot_count);
  }
}
