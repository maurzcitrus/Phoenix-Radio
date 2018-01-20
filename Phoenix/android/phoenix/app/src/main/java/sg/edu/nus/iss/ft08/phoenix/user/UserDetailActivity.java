package sg.edu.nus.iss.ft08.phoenix.user;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.model.Role;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Handles user interface interactions.
 * Displays the User to Edit and Delete the User from the list.
 *
 * @author MuraliKrishnaB
 */
public class UserDetailActivity extends PhoenixBaseActivity {
  private User current;
  private EditText txtUserID;
  private EditText txtFullName;
  private EditText txtpassword;
  private TextView txtprogramSlots;
  private AppCompatCheckBox chkPresenter;
  private AppCompatCheckBox chkProducer;
  private AppCompatCheckBox chkStationManager;
  private AppCompatCheckBox chkSystemAdmin;

  private Boolean presenterBoolean;
  private Boolean producerBoolean;
  private Boolean adminBoolean;
  private Boolean managerBoolean;


  Role role;
  List<Role> roles = new ArrayList<>();


  /**
   * Handles the current User Object
   *
   * @param obj
   */
  public void setCurrent(User obj) {
    current = obj;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_detail);
    initCommonUi();
    initContentUi();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getUserDetailController().onDisplay(this);
    bindValues();
  }

  /**
   * Initialize the Android Widgets on overide method Oncreate
   */
  private void initContentUi() {
    View v = findViewById(R.id.drawer_layout);
    txtUserID = (EditText) v.findViewById(R.id.user_ID_txt);
    txtFullName = (EditText) v.findViewById(R.id.user_full_name);
    txtpassword = (EditText) v.findViewById(R.id.user_password);
    chkStationManager = (AppCompatCheckBox) v.findViewById(R.id.stationmanager_checkcbox);
    chkSystemAdmin = (AppCompatCheckBox) v.findViewById(R.id.systemadmin_checkbox);
    chkProducer = (AppCompatCheckBox) v.findViewById(R.id.producer_checkbox);
    chkPresenter = (AppCompatCheckBox) v.findViewById(R.id.presenter_checkbox);
    txtprogramSlots = (TextView) findViewById(R.id.programSlot_user);

    txtprogramSlots.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ControlFactory.getSchedulesController().startUseCase(current);
      }
    });
  }

  /**
   * Load the User Values Back for the Detailed User Screen.
   */
  private void bindValues() {
    if (current != null) {
      txtUserID.setText(current.getUserId());
      txtFullName.setText(current.getFullName());
      txtpassword.setText(current.getPassword());
      txtprogramSlots.setText(Integer.toString(current.getAssignedProgramSlot()));

      for (Role role : current.getRoles()) {

        if (role.getRole_id().equals("admin")) {
          AppCompatCheckBox adminreloadChk = (AppCompatCheckBox) findViewById(R.id.systemadmin_checkbox);
          adminreloadChk.setChecked(true);
        }
        if (role.getRole_id().equals("presenter")) {
          AppCompatCheckBox presenterreloadChk = (AppCompatCheckBox) findViewById(R.id.presenter_checkbox);
          presenterreloadChk.setChecked(true);
        }
        if (role.getRole_id().equals("producer")) {
          AppCompatCheckBox producerreloadChk = (AppCompatCheckBox) findViewById(R.id.producer_checkbox);
          producerreloadChk.setChecked(true);
        }
        if (role.getRole_id().equals("manager")) {
          AppCompatCheckBox managerreloadChk = (AppCompatCheckBox) findViewById(R.id.stationmanager_checkcbox);
          managerreloadChk.setChecked(true);
        }
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.user_detail, menu);
    return true;
  }

  /**
   * Displays the Delete and Save Button on the option Menu
   *
   * @param item
   * @return selected Item.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_delete_user) {
      delete();
      return true;
    }

    if (id == R.id.action_save_user) {
      setRoles();
      save();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * Initiate the Checkbox to select the Role of the User.
   */
  private void setRoles() {
    if (chkStationManager.isChecked()) {
      role = new Role();
      managerBoolean = true;
      role.setRole_id((String) chkStationManager.getText());
      roles.add(role);
    } else {

    }
    if (chkSystemAdmin.isChecked()) {
      role = new Role();
      adminBoolean = true;
      role.setRole_id((String) chkSystemAdmin.getText());
      roles.add(role);
    } else {

    }
    if (chkPresenter.isChecked()) {
      role = new Role();
      presenterBoolean = true;
      role.setRole_id((String) chkPresenter.getText());
      roles.add(role);
    } else {

    }
    if (chkProducer.isChecked()) {
      role = new Role();
      producerBoolean = true;
      role.setRole_id((String) chkProducer.getText());
      roles.add(role);
    } else {

    }
  }

  /**
   * Checks for an existing User and set the values to the User object.
   * Based on the condition the create or update delegate is called to create or update the User.
   */
  private void save() {
    if (current == null) {
      User newRecord = new User();
      newRecord.setUserId(txtUserID.getText().toString());
      newRecord.setFullName(txtFullName.getText().toString());
      newRecord.setPassword(txtpassword.getText().toString());
      newRecord.setRoles(roles);

      boolean cancel = false;
      View focusView = null;

      //check if USERID text box is empty
      if (TextUtils.isEmpty(newRecord.getUserId())) {
        txtUserID.setError(getString(R.string.UserIDString));
        focusView = txtUserID;
        cancel = true;
      }

      //check if Password text box is empty
      if (TextUtils.isEmpty(newRecord.getPassword())) {
        txtpassword.setError(getString(R.string.UserPassword));
        focusView = txtpassword;
        cancel = true;
      }

      if (cancel) {
        focusView.requestFocus();
      } else {
        ControlFactory.getUserDetailController().create(newRecord);
      }

    } else {
      current.setUserId(txtUserID.getText().toString());
      current.setFullName(txtFullName.getText().toString());
      current.setPassword(txtpassword.getText().toString());
      current.setRoles(roles);

      View focusView = null;
      boolean cancel = false;

      //check if USERID text box is empty
      if (TextUtils.isEmpty(current.getUserId())) {
        txtUserID.setError(getString(R.string.UserIDString));
        focusView = txtUserID;
        cancel = true;
      }

      //check if Password text box is empty
      if (TextUtils.isEmpty(current.getPassword())) {
        txtpassword.setError(getString(R.string.UserPassword));
        focusView = txtpassword;
        cancel = true;
      }

      if (cancel) {
        focusView.requestFocus();
      } else {
        ControlFactory.getUserDetailController().update(current);
      }
    }
  }

  /**
   * Calls the Delete Function which initializes the Delete Delegate of User and deletes the current User.
   */
  private void delete() {
    ControlFactory.getUserDetailController().delete(current);
  }

  /**
   * Displays a Toast Message on successful Save operation of the User.
   */
  public void showSaveSuccess() {
    Toast.makeText(this, "Save successful.", Toast.LENGTH_SHORT).show();
  }

  /**
   * Displays a Toast Message on successful Delete operation of the User
   *
   * @param status
   */
  public void showDeleteSuccess(Boolean status) {
    if (status) {
      ControlFactory.getUsersController().startUseCase();
      Toast.makeText(this, "Delete successful.", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "User Assigned, Cannot Delete", Toast.LENGTH_SHORT).show();
    }
  }


}
