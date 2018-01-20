package sg.edu.nus.iss.ft08.phoenix.user;

import android.content.Intent;
import android.widget.Toast;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.model.Role;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Controls use case flows and states for the User Detail Screen.
 *
 * @author MuraliKrishnaB
 */
public class UserDetailController {

    private UserDetailActivity activity;
    private User user;


    /**
     * Creates an Intent and moves the control to {@link UserDetailActivity} for Editing the User.
     *
     * @param user
     */
    public void startEditUseCase(User user) {
        this.user = user;
        Intent intent = new Intent(MainController.getApp(), UserDetailActivity.class);
        MainController.displayScreen(intent);
    }

    /**
     * Creates an Intent and moves the control to {@link UserDetailActivity} for Creating the User.
     */
    public void startCreateUseCase() {
        this.user = null;
        Intent intent = new Intent(MainController.getApp(), UserDetailActivity.class);
        MainController.displayScreen(intent);
    }

    /**
     * Initialises the CreateDelegate to Generate a JSON object for current User.
     * @param newRecord The New User attributes
     */
    public void create(User newRecord) {
        CreateDelegate delegate = new CreateDelegate(this);
        delegate.execute(newRecord);
    }

    /**
     * Initialises the {@link UpdateDelegate} to Generate a JSON object for current User.
     * @param record
     */
    public void update(User record) {
        UpdateDelegate delegate = new UpdateDelegate(this);
        delegate.execute(record);
    }

    /**
     * Initialises the {@link DeleteDelegate} to Generate a JSON object for current User.
     * @param record
     */
    public void delete(User record) {
            DeleteDelegate delegate = new DeleteDelegate(this);
            delegate.execute(record);
    }

    /**
     * Set the Current User Object to be displayed.
     * @param activity
     */
    public void onDisplay(UserDetailActivity activity){
        this.activity = activity;
        activity.setCurrent(user);
    /*    activity.setCurrentRole(role);*/
    }

    /**
     * Toast Success Message for Creating and Updating the User.
     * @param user
     */
    public void showSaveSuccess(User user) {
        this.user= user;
        activity.setCurrent(user);
        activity.showSaveSuccess();
    }

    /**
     * Toast Success Message for Deleting the User.
     */
    public void showDeleteSuccess(Boolean status){
        activity.showDeleteSuccess(status);
    }
}
