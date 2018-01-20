package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;
import sg.edu.nus.iss.ft08.phoenix.model.UserRole;

/**
 * Controls use case flows and states for the Schedule Detail Screen.
 *
 * @author Chandramouli
 */
public class ScheduleProgramDetailController {

  private ScheduleProgramDetailActivity activity;
  private ProgramSlot schedule;

  /**
   * Creates an Intent and moves the control to {@link ScheduleProgramDetailActivity} for
   * Editing the ProgramSlot.
   *
   * @param schedule
   */

  public void startEditUseCase(ProgramSlot schedule) {
    this.schedule = schedule;

    Intent intent = new Intent(MainController.getApp(), ScheduleProgramDetailActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Creates an Intent and moves the control to {@link ScheduleProgramDetailActivity} for
   * Creating the ProgramSlot.
   */

  public void startCreateUseCase() {
    this.schedule = null;
    Intent intent = new Intent(MainController.getApp(), ScheduleProgramDetailActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Initialises the CreateDelegate to Generate a JSON object for new ProgramSlot.
   *
   * @param newRecord The New ProgramSlot attributes
   */

  public void create(ProgramSlot newRecord) {
    CreateDelegate delegate = new CreateDelegate(this);
    delegate.execute(newRecord);
  }

  /**
   * Initialises the {@link UpdateDelegate} to Generate a JSON object for current ProgramSlot.
   *
   * @param record
   */
  public void update(ProgramSlot record) {
    UpdateDelegate delegate = new UpdateDelegate(this);
    delegate.execute(record);
  }

  /**
   * Initialises the {@link DeleteDelegate} to Generate a JSON object for current ProgramSlot.
   *
   * @param record
   */
  public void delete(ProgramSlot record) {
    DeleteDelegate delegate = new DeleteDelegate(this);
    delegate.execute(record);
  }

  /**
   * Initialises the {@link CreateDelegate} to Generate a JSON object for copied  ProgramSlot.
   *
   * @param record
   */

  public void copy(ProgramSlot record) {
    CreateDelegate delegate = new CreateDelegate(this);
    delegate.execute(record);
  }

  /**
   * Set the Current ProgramSlot Object to be displayed.
   * OnDisplay Method also execute PresenterDelegate, ProducerDelegate ,RadioProgramDelegate to
   * get the data from REST API
   *
   * @param activity
   */
  public void onDisplay(ScheduleProgramDetailActivity activity) {
    this.activity = activity;
    activity.setCurrent(schedule);

    PresenterDelegate psd = new PresenterDelegate(this);
    ProducerDelegate prd = new ProducerDelegate(this);
    RadioProgramDelegate rpd = new RadioProgramDelegate(this);

    psd.execute();
    prd.execute();
    rpd.execute();
  }

  /**
   * Toast Success Message for Creating ,Copying and  Updating the ProgramSlot.
   * Toast message for Schedule overlap
   *
   * @param schedule
   */
  public void showSaveSuccess(ProgramSlot schedule) {
    if (schedule != null) {
      this.schedule = schedule;
      activity.setCurrent(schedule);
      activity.showSaveSuccess();
    } else {
      activity.showOverlap();
    }
  }

  /**
   * Toast Success Message for Deleting the ProgramSlot.
   */

  public void showDeleteSuccess() {
    activity.showDeleteSuccess();
  }

  /**
   * Calls the ShowPresenters Method to display the List of Presenters from users.
   */

  public void showPresenters(List<UserRole> users) {

    activity.showPresenters(users);


  }

  /**
   * Calls the ShowPresenters Method to display the List of Presenters from users.
   */
  public void showRadioPrograms(List<RadioProgram> radioPrograms) {
    activity.showRadioPrograms(radioPrograms);
  }

  /**
   * Calls the ShowProducers Method to display the List of Producers from users
   */

  public void showProducers(List<UserRole> users) {
    activity.showProduers(users);
  }

}
