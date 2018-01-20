package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Handles user interface interactions.
 * Displays the RadioProgram to Edit and Delete the RadioProgram from the list and
 *
 * @author Phyo
 */
public class RadioProgramDetailActivity extends PhoenixBaseActivity {

  private RadioProgram current;

  private EditText txtName;
  private EditText txtDesc;
  private EditText txtDuration;
  private TextView txtAssignedCount;

  public void setCurrent(RadioProgram obj){
    current = obj;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_radio_program_detail);
    initCommonUi();
    initContentUi();

  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getRadioProgramDetailController().onDisplay(this);
    bindValues();
  }

  /**
   * Initialize the Android Widgets on overide method Oncreate
   */
  private void initContentUi() {
    View v = findViewById(R.id.drawer_layout);
    txtName = (EditText) v.findViewById(R.id.radio_program_name);
    txtDesc = (EditText) v.findViewById(R.id.radio_program_description);
    txtDuration = (EditText) v.findViewById(R.id.radio_program_duration);
    txtAssignedCount = (TextView) v.findViewById(R.id.assigned_slot_count);

    txtAssignedCount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ControlFactory.getSchedulesController().startUseCase(current);
      }
    });
  }

  /**
   * Load the RadioProgram Values Back for the Detailed RadioProgram Screen.
   */
  private void bindValues() {
    if (current!=null) {
      txtName.setText(current.getName());
      txtDesc.setText(current.getDescription());
      txtDuration.setText(Integer.toString(current.getDuration()));
      txtAssignedCount.setText(Integer.toString(current.getAssignedCount()));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.radio_program_detail, menu);
    return true;
  }

  /**
   * Displays the Delete and Save Button on the option Menu
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
    if (id == R.id.action_delete) {
      delete();
      return true;
    }

    if (id == R.id.action_save) {
      save();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * Calls the Delete Function which initializes the Delete Delegate of RadioProgram and deletes the current RadioProgram.
   */
  private void delete() {
    ControlFactory.getRadioProgramDetailController().delete(current);
  }

  /**
   * Checks for an existing RadioProgram and set the values to the RadioProgram object.
   * Based on the condition the create or update delegate is called to create or update the RadioProgram.
   */
  private void save() {
    if(current == null){
      RadioProgram newRecord = new RadioProgram();
      newRecord.setName(txtName.getText().toString());
      newRecord.setDescription(txtDesc.getText().toString());
      newRecord.setDuration(Integer.parseInt(txtDuration.getText().toString()));
      newRecord.setUpdatedBy(currentUser.getUserId());

      ControlFactory.getRadioProgramDetailController().create(newRecord);
    } else {
      current.setName(txtName.getText().toString());
      current.setDescription(txtDesc.getText().toString());
      current.setDuration(Integer.parseInt(txtDuration.getText().toString()));
      current.setUpdatedBy(currentUser.getUserId());

      ControlFactory.getRadioProgramDetailController().update(current);
    }
  }

  /**
   * Displays a Toast Message on successful Save operation of the RadioProgram.
   */
  public void showSaveSuccess() {
    Toast.makeText(this, "Save successful.", Toast.LENGTH_SHORT).show();
  }

  /**
   * Displays a Toast Message on successful Delete operation of the RadioProgram.
   */
  public void showDeleteSuccess() {
    ControlFactory.getRadioProgramsController().startUseCase();
    Toast.makeText(this, "Delete successful.", Toast.LENGTH_SHORT).show();
  }
}
