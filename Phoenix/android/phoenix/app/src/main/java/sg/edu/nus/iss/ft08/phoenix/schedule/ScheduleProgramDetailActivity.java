package sg.edu.nus.iss.ft08.phoenix.schedule;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;
import sg.edu.nus.iss.ft08.phoenix.model.UserRole;

/**
 * Handles user interface interactions.
 * Displays the User to Create ,Edit , Copy and Delete the ProgramSlot from the list.
 *
 * @author Chandramouli
 */


public class ScheduleProgramDetailActivity extends PhoenixBaseActivity implements
    View.OnClickListener {

  private ProgramSlot current;
  public EditText txtStartTime;
  public EditText txtEndTime;
  public EditText txtStartDate;
  public EditText txtEndDate;
  public boolean copy;

  int mYear;
  int mMonth;
  int mDay;

  int mHour;
  int mMinute;

  private UserRole selectedPresenter;
  private UserRole selectedProducer;
  private RadioProgram selectedProgram;

  private List<UserRole> presenters;
  private List<UserRole> producers;
  private List<RadioProgram> radioPrograms;

  Spinner radioProgramSpinner, presenterSpinner, producerSpinner;
  ArrayAdapter<String> adapter;

  Calendar selectedDate = Calendar.getInstance();
  Calendar selectedTime = Calendar.getInstance();

  private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
  private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

  /**
   * Handles the current ProgramSlot Object
   *
   * @param obj
   */

  public void setCurrent(ProgramSlot obj) {
    current = obj;
  }


  /**
   * Initialize the Common User Interface and Contents and OnClickListeners
   *
   * @param savedInstanceState
   */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_schedule_program_detail);
    initCommonUi();
    initContentUi();
    txtStartTime.setOnClickListener(this);
    txtEndTime.setOnClickListener(this);
    txtStartDate.setOnClickListener(this);
    txtEndDate.setOnClickListener(this);
  }

  /**
   * Enable  Listening for  OnClickListeners in view and display android calendar
   *
   * @param v
   */

  @Override
  public void onClick(View v) {
    if (v == txtStartDate) {

      // Get Current Date
      final Calendar c = Calendar.getInstance();
      mYear = c.get(Calendar.YEAR);
      mMonth = c.get(Calendar.MONTH);
      mDay = c.get(Calendar.DAY_OF_MONTH);

      DatePickerDialog datePickerDialog = new DatePickerDialog(this,
          new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

              Calendar calendar = Calendar.getInstance();
              calendar.set(year, monthOfYear, dayOfMonth);
              selectedDate = calendar;
              txtStartDate.setText(dateFormatter.format(calendar.getTime()));
            }
          }, mYear, mMonth, mDay);
      datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
      datePickerDialog.show();
    }
    if (v == txtEndDate) {

      // Get Current Date
      final Calendar c = Calendar.getInstance();
      mYear = c.get(Calendar.YEAR);
      mMonth = c.get(Calendar.MONTH);
      mDay = c.get(Calendar.DAY_OF_MONTH);

      DatePickerDialog datePickerDialog = new DatePickerDialog(this,
          new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

              Calendar calendar = Calendar.getInstance();
              calendar.set(year, monthOfYear, dayOfMonth);
              selectedDate = calendar;
              txtEndDate.setText(dateFormatter.format(calendar.getTime()));
            }
          }, mYear, mMonth, mDay);
      datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
      datePickerDialog.show();
    }
    if (v == txtEndTime) {

      // Get Current Time
      final Calendar c = Calendar.getInstance();
      mHour = c.get(Calendar.HOUR_OF_DAY);
      mMinute = c.get(Calendar.MINUTE);

      // Launch Time Picker Dialog
      TimePickerDialog timePickerDialog = new TimePickerDialog(this,
          new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
              Calendar calendar = Calendar.getInstance();
              calendar.set(Calendar.HOUR, hourOfDay);
              calendar.set(Calendar.MINUTE, minute);
              selectedTime = calendar;
              txtEndTime.setText(timeFormatter.format(calendar.getTime()));
            }
          }, mHour, mMinute, false);
      timePickerDialog.show();
    }
    if (v == txtStartTime) {

      // Get Current Time
      final Calendar c = Calendar.getInstance();
      mHour = c.get(Calendar.HOUR_OF_DAY);
      mMinute = c.get(Calendar.MINUTE);

      // Launch Time Picker Dialog
      TimePickerDialog timePickerDialog = new TimePickerDialog(this,
          new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
              Calendar calendar = Calendar.getInstance();
              calendar.set(Calendar.HOUR, hourOfDay);
              calendar.set(Calendar.MINUTE, minute);
              selectedTime = calendar;
              txtStartTime.setText(timeFormatter.format(calendar.getTime()));
            }
          }, mHour, mMinute, false);
      timePickerDialog.show();
    }
  }


  /**
   * Calls the Display Method in the User Controller.
   *
   * @param savedInstanceState
   */
  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getScheduleProgramDetailController().onDisplay(this);
    bindValues();
  }

  /**
   * Initialize the Android Widgets on override method OnCreate
   */

  private void initContentUi() {
    View v = findViewById(R.id.drawer_layout);

    txtStartDate = (EditText) v.findViewById(R.id.txt_startdate);
    txtEndDate = (EditText) v.findViewById(R.id.txt_enddate);
    txtStartTime = (EditText) v.findViewById(R.id.txt_starttime);
    txtEndTime = (EditText) v.findViewById(R.id.txt_endtime);
  }

  /**
   * Load the ProgramSlot Values Back for the Detailed ProgramSlot Screen.
   */

  private void bindValues() {
    if (current != null) {


      txtStartDate.setText(current.getStartDate());
      txtStartTime.setText(current.getStartTime());

      txtEndDate.setText(current.getEndDate());
      txtEndTime.setText(current.getEndTime());
    }
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.schedule_program_detail, menu);
    if(!currentUser.isAdmin() && !currentUser.isManager()) {
      menu.findItem(R.id.action_save).setVisible(false);
      menu.findItem(R.id.action_delete).setVisible(false);
      menu.findItem(R.id.action_copy).setVisible(false);
    }
    return true;
  }

  /**
   * Displays the Copy,  Delete and Save Button on the option Menu
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
    if (id == R.id.action_delete) {
      delete();
      return true;
    }

    if (id == R.id.action_save) {
      save();
      return true;
    }
    if (id == R.id.action_copy) {
      copy();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * Initialize the Content again and setting the date and time fields to Empty for allowing
   * user to copy programSlot
   *
   * @return copy
   */
  public boolean copy() {
    View v = findViewById(R.id.drawer_layout);
    initCommonUi();
    initContentUi();
    txtStartTime.setOnClickListener(this);
    txtEndTime.setOnClickListener(this);
    txtStartDate.setOnClickListener(this);
    txtEndDate.setOnClickListener(this);
    txtStartDate = ((EditText) findViewById(R.id.txt_startdate));
    txtStartDate.getText().clear();
    txtStartTime = ((EditText) findViewById(R.id.txt_starttime));
    txtStartTime.getText().clear();
    txtEndDate = ((EditText) findViewById(R.id.txt_enddate));
    txtEndDate.getText().clear();
    txtEndTime = ((EditText) findViewById(R.id.txt_endtime));
    txtEndTime.getText().clear();
    copy = true;
    return copy;

  }

  /**
   * Checks for an existing ProgramSlot and set the values to the ProgramSlot object.
   * Based on the ProgramSlot object state, the create or update delegate is called to
   * create/Copy (or) update the ProgramSlot.
   */
  private void save() {
    if (current == null) {
      ProgramSlot newRecord = new ProgramSlot();

      newRecord.setProgramId(selectedProgram.getId());
      newRecord.setPresenterId(selectedPresenter.getUserId());
      newRecord.setProducerId(selectedProducer.getUserId());

      newRecord.setStartDate(txtStartDate.getText().toString());
      newRecord.setStartTime(txtStartTime.getText().toString());

      newRecord.setEndDate(txtEndDate.getText().toString());
      newRecord.setEndTime(txtEndTime.getText().toString());

      newRecord.setUpdatedBy(currentUser.getUserId());

      if (newRecord.getStartDate().equals(newRecord.getEndDate()))

      {
        ControlFactory.getScheduleProgramDetailController().create(newRecord);
      } else {

        Toast toast = Toast.makeText(this, " Please select same Start Date and End Date ",
            Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }


    } else if (copy) {
      ProgramSlot newRecord1 = new ProgramSlot();
      newRecord1.setProgramId(selectedProgram.getId());
      newRecord1.setPresenterId(selectedPresenter.getUserId());
      newRecord1.setProducerId(selectedProducer.getUserId());

      newRecord1.setStartDate(txtStartDate.getText().toString());
      newRecord1.setStartTime(txtStartTime.getText().toString());

      newRecord1.setEndDate(txtEndDate.getText().toString());
      newRecord1.setEndTime(txtEndTime.getText().toString());

      newRecord1.setUpdatedBy(currentUser.getUserId());

      if (newRecord1.getStartDate().equals(newRecord1.getEndDate()))

      {
        ControlFactory.getScheduleProgramDetailController().copy(newRecord1);
      } else {

        Toast toast = Toast.makeText(this, " Please select same Start Date and End Date ",
            Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }

    } else {
      current.setProgramId(selectedProgram.getId());
      current.setPresenterId(selectedPresenter.getUserId());
      current.setProducerId(selectedProducer.getUserId());

      current.setStartDate(txtStartDate.getText().toString());
      current.setStartTime(txtStartTime.getText().toString());

      current.setEndDate(txtEndDate.getText().toString());
      current.setEndTime(txtEndTime.getText().toString());

      current.setUpdatedBy(currentUser.getUserId());

      if (current.getStartDate().equals(current.getEndDate()))

      {
        ControlFactory.getScheduleProgramDetailController().update(current);
      } else {

        Toast toast = Toast.makeText(this, " Please select same Start Date and End Date ",
            Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }

    }
  }

  /**
   * Calls the Delete Function which initializes the Delete Delegate of ProgramSlot and deletes the
   * current ProgramSlot.
   */
  private void delete() {
    ControlFactory.getScheduleProgramDetailController().delete(current);
  }

  /**
   * Displays a Toast Message on successful Save operation of the ProgramSlot
   */
  public void showSaveSuccess() {
    ControlFactory.getSchedulesController().startUseCase();
    Toast.makeText(this, "Save successful.", Toast.LENGTH_SHORT).show();
  }

  /**
   * Displays a Toast Message on successful Delete operation of the ProgramSlot
   */
  public void showDeleteSuccess() {
    ControlFactory.getSchedulesController().startUseCase();
    Toast.makeText(this, "Delete successful.", Toast.LENGTH_SHORT).show();
  }

  /**
   * Displays a Toast Message on Overlapping with existing ProgramSlot
   */

  public void showOverlap() {
    Toast.makeText(this, "Schedule Overlap. Please change Start and End time",
        Toast.LENGTH_SHORT).show();
  }

  /**
   * Calls the showPresenters to get the presenter from users
   * Initialize the Presenter Spinner
   * setting selected value to presenter spinner
   */
  public void showPresenters(List<UserRole> users) {
    this.presenters = users;
    View v = findViewById(R.id.drawer_layout);
    presenterSpinner = (Spinner) v.findViewById(R.id.presenter_spinner);

    SpinnerAdapter adapter = new ArrayAdapter<UserRole>(this,
        R.layout.support_simple_spinner_dropdown_item, this.presenters);
    presenterSpinner.setAdapter(adapter);

    presenterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPresenter = presenters.get(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
      }
    });


    int selectedIndex = getPresenterIndex();
    presenterSpinner.setSelection(selectedIndex);

  }

  /**
   * Calls the showRadioPrograms to get the RadioPrograms from radioPrograms list
   * initialize the RadioProgram Spinner
   * setting selected value to RadioProgram spinner
   */
  public void showRadioPrograms(final List<RadioProgram> radioPrograms) {
    this.radioPrograms = radioPrograms;
    View v = findViewById(R.id.drawer_layout);
    radioProgramSpinner = (Spinner) v.findViewById(R.id.radio_program_spinner);

    SpinnerAdapter adapter = new ArrayAdapter<RadioProgram>(this,
        R.layout.support_simple_spinner_dropdown_item,
        this.radioPrograms);

    radioProgramSpinner.setAdapter(adapter);

    radioProgramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedProgram = radioPrograms.get(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
      }
    });
    int selectedIndex = getRadioProgramIndex();
    radioProgramSpinner.setSelection(selectedIndex);
  }

  /**
   * Calls the showProduers to get the producers from users
   * Initialize the Producer Spinner
   * setting selected value to Producer spinner
   */

  public void showProduers(List<UserRole> users) {
    this.producers = users;
    View v = findViewById(R.id.drawer_layout);
    producerSpinner = (Spinner) v.findViewById(R.id.producer_spinner);

    SpinnerAdapter adapter = new ArrayAdapter<UserRole>(this,
        R.layout.support_simple_spinner_dropdown_item, this.producers);
    producerSpinner.setAdapter(adapter);

    producerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedProducer = producers.get(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
      }
    });

    int selectedIndex = getProducerIndex();
    producerSpinner.setSelection(selectedIndex);

  }

  private int getPresenterIndex() {
    if (current == null) {
      return -1;

    }

    if (presenters == null || presenters.isEmpty()) {
      return -1;
    }

    UserRole temp = new UserRole();
    temp.setUserId(current.getPresenterId());

    return presenters.indexOf(temp);
  }

  private int getProducerIndex() {
    if (current == null) {
      return -1;

    }

    if (producers == null || producers.isEmpty()) {
      return -1;
    }

    UserRole temp = new UserRole();
    temp.setUserId(current.getProducerId());

    return producers.indexOf(temp);
  }

  private int getRadioProgramIndex() {
    if (current == null) {
      return -1;

    }

    if (radioPrograms == null || radioPrograms.isEmpty()) {
      return -1;
    }

    RadioProgram temp = new RadioProgram();
    temp.setId(current.getProgramId());


    return radioPrograms.indexOf(temp);
  }
}