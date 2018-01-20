package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Handles data transfer between android application and REST API server to Update RadioProgram.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Phyo
 */
public class UpdateDelegate extends AsyncTask<RadioProgram, Void, RadioProgram> {

  private RadioProgramDetailController controller;
  private RestClient client;

  /**
   * Constructor to initialize the RadioProgram Controller.
   * @param controller
   */
  public UpdateDelegate(RadioProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the USER object in to JSON using JSON Serializable.
   *
   * @param params
   * @return JSON data of RadioProgarm to be updated.
   */
  @Override
  protected RadioProgram doInBackground(RadioProgram... params) {
    RadioProgram radioProgram = params[0];
    RadioProgram result = null;
    try {
      String data = client.post(Urls.forRadioProgram(), radioProgram.toJson());
      JSONObject json = new JSONObject(data);
      result = RadioProgram.fromJson(json);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Shows a success Message on Completion of the Update RadioProgarm Task.
   * @param radioProgram
   */
  @Override
  protected void onPostExecute(RadioProgram radioProgram) {
    controller.showSaveSuccess(radioProgram);
  }
}
