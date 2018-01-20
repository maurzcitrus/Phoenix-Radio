package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.content.Intent;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Controls use case flows and states for the RadioProgram Detail Screen.
 *
 * @author Phyo
 */
public class RadioProgramDetailController {

  private RadioProgramDetailActivity activity;
  private RadioProgram radioProgram;

  /**
   * Creates an Intent and moves the control to {@link RadioProgramDetailActivity} for Editing the RadioProgram.
   * @param radioProgram
   */
  public void startEditUseCase(RadioProgram radioProgram) {
    this.radioProgram = radioProgram;

    Intent intent = new Intent(MainController.getApp(), RadioProgramDetailActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Creates an Intent and moves the control to {@link RadioProgramDetailActivity} for Creating the RadioProgram.
   */
  public void startCreateUseCase() {
    this.radioProgram = null;
    Intent intent = new Intent(MainController.getApp(), RadioProgramDetailActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Initialises the {@link CreateDelegate} to Generate a JSON object for current RadioProgram.
   *
   * @param newRecord
   */
  public void create(RadioProgram newRecord) {
    CreateDelegate delegate = new CreateDelegate(this);
    delegate.execute(newRecord);
  }

  /**
   * Initialises the {@link UpdateDelegate} to Generate a JSON object for current RadioProgram.
   * @param record
   */
  public void update(RadioProgram record) {
    UpdateDelegate delegate = new UpdateDelegate(this);
    delegate.execute(record);
  }

  /**
   * Initialises the {@link DeleteDelegate} to Generate a JSON object for current RadioProgram.
   * @param record
   */
  public void delete(RadioProgram record) {
    DeleteDelegate delegate = new DeleteDelegate(this);
    delegate.execute(record);
  }

  /**
   * Set the Current RadioProgram Object to be displayed.
   * @param activity
   */
  public void onDisplay(RadioProgramDetailActivity activity){
    this.activity = activity;
    activity.setCurrent(radioProgram);
  }

  /**
   * Toast Success Message for Creating and Updating the RadioProgram.
   * @param radioProgram
   */
  public void showSaveSuccess(RadioProgram radioProgram) {
    this.radioProgram = radioProgram;
    activity.setCurrent(radioProgram);
    activity.showSaveSuccess();
  }

  /**
   * Toast Success Message for Deleting the RadioProgram.
   */
  public void showDeleteSuccess(){
    activity.showDeleteSuccess();
  }
}
