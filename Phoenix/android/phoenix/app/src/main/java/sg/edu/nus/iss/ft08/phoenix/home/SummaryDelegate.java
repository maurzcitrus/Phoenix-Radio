package sg.edu.nus.iss.ft08.phoenix.home;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.Summary;

public class SummaryDelegate extends AsyncTask<Void, Void, Summary> {

  private MainController controller;
  private RestClient client;

  public SummaryDelegate(MainController controller) {
    this.controller = controller;
    client = new RestClient();
  }

  @Override
  protected Summary doInBackground(Void... params) {
    Summary summary = null;
    try{
      String data = client.get(Urls.forSummary());
      JSONObject obj = new JSONObject(data);
      summary = Summary.fromJson(obj);
    } catch (JSONException e){
      e.printStackTrace();
    }
    return summary;
  }

  @Override
  protected void onPostExecute(Summary summary) {
    controller.showDashboardItems(summary);
  }
}
