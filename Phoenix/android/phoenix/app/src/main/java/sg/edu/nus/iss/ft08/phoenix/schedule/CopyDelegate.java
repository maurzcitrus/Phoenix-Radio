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
 * Handles data transfer between android application and REST API server to Copy weekly ProgramSlots.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Karthik
 */

public class CopyDelegate extends AsyncTask<List<String>, Void, Boolean> {

  private SchedulesController controller;
  private RestClient client;

  /**
   * The ScheduleProgram controller object is initialised.
   *
   * @param controller
   */
  public CopyDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the String objects in to JSON using JSON Serializable.
   *
   * @param params Check for any overlap in schedule
   * @return JSON object with ProgramSlot attributes.
   */

  @Override
  protected Boolean doInBackground(List<String>... params) {
    List<String> string = params[0];
    Boolean overlapFlag = true;
    int sourceWeekNumber = Integer.parseInt(string.get(2));
    int targetWeekNumber = Integer.parseInt(string.get(3));
    ProgramSlot example = new ProgramSlot();
    example.setStart(string.get(0));
    example.setEnd(string.get(1));
    List<ProgramSlot> overlap = new ArrayList<>();
    try {
      String data = client.post(Urls.forOverlap(), example.toJson());
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
      overlapFlag = false;
      try {
        client.get(Urls.forCopyWeekSchedules(sourceWeekNumber, targetWeekNumber));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return overlapFlag;
  }

  @Override
  protected void onPostExecute(Boolean overlap) {
    controller.showMessage(overlap);
  }
}