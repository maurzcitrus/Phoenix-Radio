package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.os.AsyncTask;

import org.json.JSONException;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;

/**
 * Handles data transfer between android application and REST API server to Delete ProgramSlot.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Chandramouli
 */


public class DeleteDelegate extends AsyncTask<ProgramSlot, Void, Void> {

  private ScheduleProgramDetailController controller;
  private RestClient client;

  /**
   * Starts the Delete Process by initiating the API and the Schedule controller object.
   *
   * @param controller
   */

  public DeleteDelegate(ScheduleProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the ProgramSlot object in to JSON using JSON Serializable.
   * Delete the ProgramSlot with corresponding Id
   *
   * @param params
   */
  @Override
  protected Void doInBackground(ProgramSlot... params) {
    ProgramSlot schedule = params[0];
    try {
      String data = client.delete(Urls.forSchedule(schedule.getId()));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Shows a success Message on Completion of the Delete ProgramSlot Task.
   *
   * @param aVoid
   */
  @Override
  protected void onPostExecute(Void aVoid) {
    controller.showDeleteSuccess();
  }
}


