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
 * Handles data transfer between android application and REST API server to Update ProgramSlot.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */

public class UpdateDelegate extends AsyncTask<ProgramSlot, Void, ProgramSlot> {

  private ScheduleProgramDetailController controller;
  private RestClient client;

  /**
   * Constructor to initialize the Schedule Controller.
   *
   * @param controller
   */

  public UpdateDelegate(ScheduleProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the ProgramSlot object in to JSON using JSON Serializable.
   * Check for overlap using post method and add it overlap list
   * If Overlap List didn't match with Schedule  List , ProgramSlot gets Updated
   *
   * @param params
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

    if (!isOverlapped(overlap, schedule)) {
      try {
        String data = client.post(Urls.forSchedule(), schedule.toJson());
        JSONObject json = new JSONObject(data);
        result = ProgramSlot.fromJson(json);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  /**
   * Compare the ProgramSlot object with overlap object.
   * If its Same Record , Boolean flag sets to false
   *
   * @param overlap
   * @param schedule
   * @return boolean hasOverlap.
   */

  private boolean isOverlapped(List<ProgramSlot> overlap, ProgramSlot schedule) {
    boolean hasOverlap = false;
    if (overlap.size() == 0) {
      hasOverlap = false;
    } else if (overlap.size() > 1) {
      hasOverlap = true;
    } else if (overlap.size() == 1) {
      boolean isSameRecord = overlap.get(0).getId() == schedule.getId();
      if (isSameRecord)
        hasOverlap = false;
      else
        hasOverlap = true;
    } else {
      hasOverlap = false;
    }

    return hasOverlap;
  }

  /**
   * Shows a success Message on Completion of the Update ProgramSlot Task.
   *
   * @param schedule
   */

  @Override
  protected void onPostExecute(ProgramSlot schedule) {
    controller.showSaveSuccess(schedule);
  }
}
