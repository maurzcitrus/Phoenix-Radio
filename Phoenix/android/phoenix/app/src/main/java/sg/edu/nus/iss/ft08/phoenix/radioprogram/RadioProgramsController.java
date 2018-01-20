package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

public class RadioProgramsController implements PhoenixBaseController {
  private RadioProgramsActivity activity;

  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), RadioProgramsActivity.class);
    MainController.displayScreen(intent);
  }

  public void onDisplay(RadioProgramsActivity activity) {
    this.activity = activity;

    FindAllDelegate delegate =  new FindAllDelegate(this);

    delegate.execute(new RadioProgram());
  }

  public void showRadioPrograms(List<RadioProgram> radioPrograms){
    activity.showRadioPrograms(radioPrograms);
  }

  public void showRadioProgram(RadioProgram radioProgram){
    System.out.println(radioProgram.getName());
    System.out.println(radioProgram.getDescription());
  }


}
