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

public class FindAllWeekDelegate  extends AsyncTask<Integer, Void, List<ProgramSlot> > {

  private SchedulesController controller;
  private RestClient client;

  public FindAllWeekDelegate(SchedulesController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected List<ProgramSlot> doInBackground(Integer... params) {
    Integer weekNumber = params[0];

    List<ProgramSlot> results = new ArrayList<>();

    try {
      String ul = Urls.forSchedulesWeek(weekNumber);
      String data = client.get(Urls.forSchedulesWeek(weekNumber));
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

  @Override
  protected void onPostExecute(List<ProgramSlot> slots) {
    controller.showSchedules(slots);
  }
}