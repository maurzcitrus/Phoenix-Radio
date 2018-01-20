package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.content.Intent;

import java.util.Calendar;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.MainController;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Controls use case flows and states
 * <p>
 * Implements the Interface PhoenixBaseController
 *
 * @author Chandramouli
 * @see sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController
 */

public class SchedulesController implements PhoenixBaseController {

  private SchedulesActivity activity;
  private RadioProgramSchedulesActivity radioProgramSchedulesActivity;
  private UserSchedulesActivity userSchedulesActivity;
  private User targetUser;
  private RadioProgram targetRadioProgram;

  /**
   * Creates an Intent and moves the control to Schedules Activity.
   */

  public void startUseCase() {
    Intent intent = new Intent(MainController.getApp(), SchedulesActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Creates an Intent and moves the control to UserSchedules Activity.
   *
   * @param targetUser
   */
  public void startUseCase(User targetUser) {
    this.targetUser = targetUser;

    Intent intent = new Intent(MainController.getApp(), UserSchedulesActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Creates an Intent and moves the control to RadioprogramSchedules Activity.
   *
   * @param targetRadioProgram
   */
  public void startUseCase(RadioProgram targetRadioProgram) {
    this.targetRadioProgram = targetRadioProgram;

    Intent intent = new Intent(MainController.getApp(), RadioProgramSchedulesActivity.class);
    MainController.displayScreen(intent);
  }

  /**
   * Calls the Delegate function to receive Data from API.
   *
   * @param activity
   */
  public void onDisplay(SchedulesActivity activity) {
    this.activity = activity;

    // IMPORTANT: do not set calendar start day of week
    // if you set start day of week and today is in the middle of the week
    // you will get next week monday's week number when you call c.get(Calendar.WEEK_OF_YEAR
    Calendar c = Calendar.getInstance();
    int weekNo = c.get(Calendar.WEEK_OF_YEAR);

    FindAllWeekDelegate delegate = new FindAllWeekDelegate(this);
    delegate.execute(new Integer(weekNo));
  }

  public void onDisplay(int weekNo) {
    FindAllWeekDelegate delegate = new FindAllWeekDelegate(this);
    delegate.execute(new Integer(weekNo));
  }

  /**
   * Calls the Delegate function to receive Userdata from API.
   *
   * @param activity
   */
  public void onDisplayTargetUser(UserSchedulesActivity activity) {
    this.userSchedulesActivity = activity;
    FindAllUserDelegate delegate = new FindAllUserDelegate(this);
    delegate.execute(targetUser);
  }

  /**
   * Calls the Delegate function to receive RadioProgram data from API.
   *
   * @param activity
   */


  public void onDisplayTargetRadioProgram(RadioProgramSchedulesActivity activity) {
    this.radioProgramSchedulesActivity = activity;
    FindAllRadioProgramDelegate delegate = new FindAllRadioProgramDelegate(this);
    delegate.execute(targetRadioProgram);
  }

  /**
   * Lists the ProgramSlots in the Program List Screen.
   *
   * @param slots
   */
  public void showSchedules(List<ProgramSlot> slots) {
    activity.showSchedules(slots);
  }

  /**
   * Lists the ProgramSlots in the User Screen.
   *
   * @param slots
   */
  public void showUserSchedules(List<ProgramSlot> slots) {
    userSchedulesActivity.showSchedules(slots);
  }

  /**
   * Lists the ProgramSlots in the RadioProgram Screen.
   *
   * @param slots
   */

  public void showRadioProgramSchedules(List<ProgramSlot> slots) {
    radioProgramSchedulesActivity.showSchedules(slots);
  }

  public void showMessage(Boolean overlap) {
    activity.showCopySchedules();
    if (!overlap) {
      activity.showCopySaveSuccess();
    } else {
      activity.showCopyOverlap();
    }
  }

  public void copySchedules(List<String> stringList){
    CopyDelegate delegate = new CopyDelegate(this);
    delegate.execute(stringList);
  }
}
