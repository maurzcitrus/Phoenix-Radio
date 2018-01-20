package sg.edu.nus.iss.ft08.phoenix.user;

import android.os.AsyncTask;
import android.speech.tts.Voice;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.User;


/**
 * Handles data transfer between android application and REST API server to Update User.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author MuraliKrishnaB
 */
public class UpdateDelegate extends AsyncTask<User, Void, User> {

    private UserDetailController controller;
    private RestClient client;

    /**
     * Constructor to initialize the User Controller.
     * @param controller
     */
    public UpdateDelegate(UserDetailController controller) {
        this.controller = controller;
        client = new RestClient();
    }

    /**
     * Converts the USER object in to JSON using JSON Serializable.
     *
     * @param params
     * @return JSON object with USER attributes.
     */
    @Override
    protected User doInBackground(User... params) {
        User user= params[0];
        User result = null;
        try {
            String data = client.post(Urls.forUser(), user.toJsonCRUD());
            JSONObject json = new JSONObject(data);
            result = user.fromJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Shows a success Message on Completion of the Update User Task.
     * @param user
     */
    @Override
    protected void onPostExecute(User user) {
        controller.showSaveSuccess(user);
    }
}
