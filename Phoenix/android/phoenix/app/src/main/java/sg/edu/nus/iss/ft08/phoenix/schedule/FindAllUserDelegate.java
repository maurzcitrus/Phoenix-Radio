package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Handles data transfer between android application and REST API server to Find all program  slots
 * for assigned user.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */


public class FindAllUserDelegate extends AsyncTask<User, Void, List<ProgramSlot>> {

  private SchedulesController controller;
  private RestClient client;

  /**
   * Constructor to get the respective Schedule controller.
   *
   * @param controller
   */

  public FindAllUserDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the User object in to JSON using JSON Serializable.
   *
   * @param params
   * @return JSON object with programSlot attributes.
   */
  @Override
  protected List<ProgramSlot> doInBackground(User... params) {
    User user = params[0];

    List<ProgramSlot> results = new ArrayList<>();

    try {
      String data = client.get(Urls.forSchedulesUser(user.getUserId()));
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        ProgramSlot s = ProgramSlot.fromJson(o);
        results.add(s);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  /**
   * Calls the showUserSchedules Method to display the List of Schedule for assigned user .
   *
   * @param slots
   */


  @Override
  protected void onPostExecute(List<ProgramSlot> slots) {
    controller.showUserSchedules(slots);
  }
}

