package sg.edu.nus.iss.ft08.phoenix.user;

import android.content.Intent;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController;
import sg.edu.nus.iss.ft08.phoenix.model.User;

public class MyProfileController implements PhoenixBaseController {

  private MyProfileActivity activity;

  @Override
  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), MyProfileActivity.class);
    MainController.displayScreen(intent);
  }


  public void onDisplay(MyProfileActivity activity) {
    this.activity = activity;
  }

  public void update(User record) {
    UpdateProfileDelegate delegate = new UpdateProfileDelegate(this);
    delegate.execute(record);
  }

  public void showSaveSuccess(User user) {
    activity.setCurrent(user);
    activity.showSaveSuccess();
  }
}
