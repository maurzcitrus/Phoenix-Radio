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
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Handles data transfer between android application and REST API server to Find all program  slots
 * for assigned radio program.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */


public class FindAllRadioProgramDelegate extends AsyncTask<RadioProgram, Void, List<ProgramSlot>> {

  private SchedulesController controller;
  private RestClient client;

  /**
   * Constructor to get the respective Schedule controller.
   *
   * @param controller
   */
  public FindAllRadioProgramDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the RadioProgram object in to JSON using JSON Serializable.
   *
   * @param params
   * @return JSON object with programSlot attributes.
   */
  @Override
  protected List<ProgramSlot> doInBackground(RadioProgram... params) {
    RadioProgram radioProgram = params[0];

    List<ProgramSlot> results = new ArrayList<>();

    try {
      String data = client.get(Urls.forSchedulesRadioProgram(radioProgram.getId()));
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
   * Calls the showRadioProgramSchedules Method to display the List of scheduled RadioPrograms .
   *
   * @param slots
   */

  @Override
  protected void onPostExecute(List<ProgramSlot> slots) {
    controller.showRadioProgramSchedules(slots);
  }
}


