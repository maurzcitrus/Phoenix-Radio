package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Handles data transfer between android application and REST API server to Create RadioProgram.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Phyo
 */
public class CreateDelegate extends AsyncTask<RadioProgram, Void, RadioProgram> {

  private RadioProgramDetailController controller;
  private RestClient client;

  /**
   * The RadioProgram controller object is initialised.
   *
   * @param controller
   */
  public CreateDelegate(RadioProgramDetailController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the RadioProgram object in to JSON using JSON Serializable API.
   * @param params
   * @return JSON data of RadioProgram
   */
  @Override
  protected RadioProgram doInBackground(RadioProgram... params) {
    RadioProgram radioProgram = params[0];
    RadioProgram result = null;
    try {
      String data = client.put(Urls.forRadioProgram(), radioProgram.toJson());
      JSONObject json = new JSONObject(data);
      result = RadioProgram.fromJson(json);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Shows a success Message on Completion of the Create User Task.
   *
   * @param radioProgram
   */
  @Override
  protected void onPostExecute(RadioProgram radioProgram) {
    controller.showSaveSuccess(radioProgram);
  }
}
