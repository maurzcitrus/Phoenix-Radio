package sg.edu.nus.iss.ft08.phoenix.user;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.MainController;

import sg.edu.nus.iss.ft08.phoenix.model.User;

import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController;


/**
 * Controls use case flows and states
 *
 * Implements the Interface PhoenixBaseController
 * @see sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController
 * @author MuraliKrishnaB
 */
public class UsersController implements PhoenixBaseController{
  private UsersActivity activity;

  /**
   * Creates an Intent and moves the control to User Activity.
   */
  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), UsersActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Calls the Delegate function to receice Data from API.
   *
   * @param activity
   */
  public void onDisplay(UsersActivity activity){
    this.activity = activity;

    FindAllDelegate delegate = new FindAllDelegate(this);

    delegate.execute(new User());
  }

  /**
   * Lists the User in the User List Screen.
   * @param users
   */
  public void showUsers(List<User> users){
    activity.showUsers(users);
  }
}
