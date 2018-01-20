package sg.edu.nus.iss.ft08.phoenix.user;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.ft08.phoenix.Urls;
import sg.edu.nus.iss.ft08.phoenix.http.RestClient;
import sg.edu.nus.iss.ft08.phoenix.model.User;


/**
 * Handles data transfer between android application and REST API server to Delete User.
 * Extends Android AsyncTask to handle simultaneous request in background.
 *
 * @author MuraliKrishnaB
 */
public class DeleteDelegate extends AsyncTask<User, Void, Boolean> {
    private UserDetailController controller;
    private RestClient client;

    /**
     * Starts the Delete Process by initiating the API and the User controller object.
     *
     * @param controller
     */
    public DeleteDelegate(UserDetailController controller){
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
    protected Boolean doInBackground(User... params) {
        User user = params[0];
        User assignedUser;
        boolean result = false;
        try {
            String data = client.get(Urls.forassignedUser(user.getUserId()));
            JSONObject json = new JSONObject(data);
            assignedUser = User.fromJson(json);
            if(assignedUser != null){
                result = false;
            }else {
                client.delete(Urls.forUser(user.getUserId()));
                result = true;
            }
        } catch (JSONException e) {
            try {
                client.delete(Urls.forUser(user.getUserId()));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    /**
     * Shows a success Message on Completion of the Delete User Task.
     * @param status
     */
    @Override
    protected void onPostExecute(Boolean status) {
       controller.showDeleteSuccess(status);
    }
}
