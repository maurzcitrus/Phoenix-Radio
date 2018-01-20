package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

public class RadioProgramsRecyclerViewAdapter extends RecyclerView.Adapter<RadioProgramsViewHolder> {

  private final List<RadioProgram> radioPrograms;

  public RadioProgramsRecyclerViewAdapter(List<RadioProgram> radioPrograms) {

    if(radioPrograms!=null) {
      this.radioPrograms = radioPrograms;
    } else {
      this.radioPrograms = new ArrayList<>();
    }
  }

  @Override
  public RadioProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.view_holder_radioprogram, parent, false);
    return new RadioProgramsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final RadioProgramsViewHolder holder, int position) {
    RadioProgram current = radioPrograms.get(position);

    holder.radioProgram = current;

    holder.txtName.setText(current.getName());
    holder.txtDescription.setText(current.getDescription());
    holder.txtDuration.setText(Integer.toString(current.getDuration()));
    holder.txtAssignedCount.setText(Integer.toString(current.getAssignedCount()));

    holder.view.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        ControlFactory.getRadioProgramDetailController().startEditUseCase(holder.radioProgram);
      }
    });
  }

  @Override
  public int getItemCount() {
    return this.radioPrograms.size();
  }
}
