package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Handles data transfer between android application and REST API server to Find RadioProgram for the RadioProgram List Screen.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author Phyo
 */
public class FindAllDelegate extends AsyncTask<RadioProgram, Void, List<RadioProgram>> {

  private RadioProgramsController controller;
  private RestClient client;

  /**
   * Constructor to get the respective RadioProgram controller.
   * @param controller
   */
  public FindAllDelegate(RadioProgramsController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  /**
   * Converts the USER object in to JSON using JSON Serializable.
   * @param params
   * @return JSON object with RadioProgram attributes.
   */
  @Override
  protected List<RadioProgram> doInBackground(RadioProgram... params) {
    RadioProgram radioProgram = params[0];

    List<RadioProgram> results = new ArrayList<>();

    try {
      String data = client.get(Urls.forRadioPrograms());
      JSONArray array = new JSONArray(data);
      for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);
        RadioProgram r = RadioProgram.fromJson(o);
        results.add(r);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  /**
   * Calls the ShowUser Method to display the List of RadioProgram.
   *
   * @param radioPrograms
   */
  @Override
  protected void onPostExecute(List<RadioProgram> radioPrograms) {
    controller.showRadioPrograms(radioPrograms);
  }
}
