package sg.edu.nus.iss.ft08.phoenix.authenticate;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.User;

class LoginDelegate extends AsyncTask<User, Void, User> {

  private static final String TAG = LoginDelegate.class.getName();
  private LoginController controller;
  private RestClient client;

  public LoginDelegate(LoginController loginController) {
    controller = loginController;
    client = new RestClient();
  }

  @Override
  protected User doInBackground(User... params) {
    User user = params[0];

    User result = new User();
    result.setAuthenticated(false);

    try {
      String data = client.post(Urls.forAccount(), user.toJson());
      JSONObject json = new JSONObject(data);
      result = User.fromJson(json);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  protected void onPostExecute(User user) {
    controller.loggedIn(user);
  }
}
