package sg.edu.nus.iss.ft08.phoenix.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;

import sg.edu.nus.iss.ft08.phoenix.Constants;
import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController;
import sg.edu.nus.iss.ft08.phoenix.model.User;

public class LoginController implements PhoenixBaseController {

  private LoginActivity loginActivity;

  public void onDisplay(LoginActivity loginActivity) {
    this.loginActivity = loginActivity;
  }

  public void login(String userId, String password) {
    loginActivity.showLoading();

    User user = new User();
    user.setUserId(userId);
    user.setPassword(password);

    LoginDelegate delegate = new LoginDelegate(this);

    delegate.execute(user);
  }

  public void logout() {
    removeUserFromSharedPreferences();
    Intent intent = new Intent(MainController.getApp(), LoginActivity.class);
    MainController.displayScreen(intent);
  }

  public void loggedIn(User user) {
    loginActivity.hideLoading();
    if (!user.isAuthenticated()) {
      loginActivity.showLoginFailure();
      return;
    }

    keepUserInSharedPreferences(user);
    ControlFactory.getMainController().startUseCase();
  }

  private void keepUserInSharedPreferences(User user) {
    SharedPreferences preferences = loginActivity.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);

    try {
      preferences
          .edit()
          .putString(Constants.KEY_USER_ID, user.getUserId())
          .putString(Constants.KEY_USER_NAME, user.getFullName())
          .putString(Constants.KEY_USER_INFO, user.toJsonCRUD().toString())
          .commit();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void removeUserFromSharedPreferences() {
    SharedPreferences preferences = loginActivity.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    preferences
        .edit()
        .remove(Constants.KEY_USER_ID)
        .remove(Constants.KEY_USER_NAME)
        .remove(Constants.KEY_USER_INFO)
        .commit();
  }


  @Override
  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), LoginActivity.class);
    MainController.displayScreen(intent);
  }
}
