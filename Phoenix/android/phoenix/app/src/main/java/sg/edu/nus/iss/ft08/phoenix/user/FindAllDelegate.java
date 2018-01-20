package sg.edu.nus.iss.ft08.phoenix.user;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Handles data transfer between android application and REST API server to Find Users for the List User Screen.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author MuraliKrishnaB
 */
public class FindAllDelegate extends AsyncTask<User, Void, List<User>> {

    private UsersController controller;
    private RestClient client;

    /**
     * Constructor to get the respective User controller.
     * @param controller
     */
    public FindAllDelegate(UsersController controller){
        this.controller=controller;
        client = new RestClient();
    }

    /**
     * Converts the USER object in to JSON using JSON Serializable.
     *
     * @param params
     * @return JSON object with USER attributes.
     */
    @Override
    protected List<User> doInBackground(User... params) {
        User user = params[0];

        List<User> results = new ArrayList<>();
        try {
            String data = client.get(Urls.forUsers());
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                User u = User.fromJson(o);
                results.add(u);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Calls the ShowUser Method to display the List of USERS.
     *
     * @param users
     */
    @Override
    protected void onPostExecute(List<User> users) {
        controller.showUsers(users);
    }
}
