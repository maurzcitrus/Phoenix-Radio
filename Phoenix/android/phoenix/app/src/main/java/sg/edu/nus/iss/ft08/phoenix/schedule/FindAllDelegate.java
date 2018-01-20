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

/**
 * Handles data transfer between android application and REST API server to Find ProgramSlot with respective week.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */

public class FindAllDelegate extends AsyncTask<ProgramSlot, Void, List<ProgramSlot>> {

  private SchedulesController controller;
  private RestClient client;

  /**
   * Constructor to get the respective Schedule controller.
   *
   * @param controller
   */

  public FindAllDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the ProgramSlot object in to JSON using JSON Serializable.
   *
   * @param params
   * @return JSON object with programSlot attributes.
   */

  @Override
  protected List<ProgramSlot> doInBackground(ProgramSlot... params) {
    ProgramSlot schedule = params[0];

    List<ProgramSlot> results = new ArrayList<>();

    try {
      String data = client.get(Urls.forSchedules());
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
   * Calls the ShowSchedules Method to display the List of ProgramSlots.
   *
   * @param slots
   */

  @Override
  protected void onPostExecute(List<ProgramSlot> slots) {
    controller.showSchedules(slots);
  }
}

