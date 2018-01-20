package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.os.AsyncTask;

import org.json.JSONException;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Handles data transfer between android application and REST API server to Delete RadioProgram.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Phyo
 */
public class DeleteDelegate extends AsyncTask<RadioProgram, Void, Void> {

  private RadioProgramDetailController controller;
  private RestClient client;

  /**
   * Starts the Delete Process by initiating the API and the User controller object.
   * @param controller
   */
  public DeleteDelegate(RadioProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the RadioProgram object in to JSON using JSON Serializable.
   * @param params
   * @return JSON data of RadioProgram
   */
  @Override
  protected Void doInBackground(RadioProgram... params) {
    RadioProgram radioProgram = params[0];
    try {
      String data = client.delete(Urls.forRadioProgram(radioProgram.getId()));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Shows a success Message on Completion of the Delete RadioProgram Task.
   *
   * @param aVoid
   */
  @Override
  protected void onPostExecute(Void aVoid) {
    controller.showDeleteSuccess();
  }
}
