package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;


/**
 * Handles data transfer between android application and REST API server to Create ProgramSlot.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */
public class CreateDelegate extends AsyncTask<ProgramSlot, Void, ProgramSlot> {

  private ScheduleProgramDetailController controller;
  private RestClient client;

  /**
   * The ScheduleProgram controller object is initialised.
   *
   * @param controller
   */
  public CreateDelegate(ScheduleProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the ProgramSlot object in to JSON using JSON Serializable.
   *
   * @param params Check for any overlap in schedule
   * @return JSON object with ProgramSlot attributes.
   */

  @Override
  protected ProgramSlot doInBackground(ProgramSlot... params) {
    ProgramSlot schedule = params[0];
    ProgramSlot result = null;
    List<ProgramSlot> overlap = new ArrayList<>();
    try {
      String data = client.post(Urls.forOverlap(), schedule.toJson());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        ProgramSlot s = ProgramSlot.fromJson(o);
        overlap.add(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (overlap.size() == 0) {
      try {
        String data = client.put(Urls.forSchedule(), schedule.toJson());
        JSONObject json = new JSONObject(data);
        result = ProgramSlot.fromJson(json);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  /**
   * Shows a success Message on Completion of the Create Schedule Task.
   *
   * @param schedule
   */
  @Override
  protected void onPostExecute(ProgramSlot schedule) {
    controller.showSaveSuccess(schedule);
  }
}
