package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;

import sg.edu.nus.iss.ft08.phoenix.model.UserRole;

/**
 * Handles data transfer between android application and REST API server to Find list of Presenters
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */


public class PresenterDelegate extends AsyncTask<Void, Void, List<UserRole>> {

  private ScheduleProgramDetailController controller;
  private RestClient client;

  /**
   * Constructor to get the respective Schedule controller.
   *
   * @param controller
   */


  public PresenterDelegate(ScheduleProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the UserRole object in to JSON using JSON Serializable.
   *
   * @param params
   * @return JSON object with UserRole attributes.
   */


  @Override
  protected List<UserRole> doInBackground(Void... params) {

    List<UserRole> results = new ArrayList<>();

    try {
      String data = client.get(Urls.forPresenters());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        UserRole ur = UserRole.fromJson(o);
        results.add(ur);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  /**
   * Calls the ShowPresenters Method to display the List of Presenters from users.
   *
   * @param users
   */

  @Override
  protected void onPostExecute(List<UserRole> users) {
    controller.showPresenters(users);
  }
}
