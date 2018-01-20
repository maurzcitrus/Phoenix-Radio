package sg.edu.nus.iss.ft08.phoenix;

import sg.edu.nus.iss.ft08.phoenix.authenticate.LoginController;
import sg.edu.nus.iss.ft08.phoenix.radioprogram.RadioProgramDetailController;
import sg.edu.nus.iss.ft08.phoenix.radioprogram.RadioProgramsController;
import sg.edu.nus.iss.ft08.phoenix.schedule.ScheduleProgramDetailController;
import sg.edu.nus.iss.ft08.phoenix.schedule.SchedulesController;
import sg.edu.nus.iss.ft08.phoenix.user.MyProfileController;
import sg.edu.nus.iss.ft08.phoenix.user.UserDetailController;
import sg.edu.nus.iss.ft08.phoenix.user.UsersController;

public class ControlFactory {
  private static MainController mainController = null;
  private static LoginController loginController = null;
  private static RadioProgramsController radioProgramsController = null;
  private static SchedulesController schedulesController = null;
  private static UsersController usersController = null;
  private static RadioProgramDetailController radioProgramDetailController = null;
  private static ScheduleProgramDetailController scheduleProgramDetailController = null;
  private static UserDetailController userDetailController = null;
  private static MyProfileController myProfileController = null;

  public static MainController getMainController() {
    if (mainController == null) mainController = new MainController();
    return mainController;
  }

  public static LoginController getLoginController() {
    if (loginController == null) loginController = new LoginController();
    return loginController;
  }

  public static RadioProgramsController getRadioProgramsController() {
    if (radioProgramsController == null) radioProgramsController = new RadioProgramsController();
    return radioProgramsController;
  }

  public static SchedulesController getSchedulesController() {
    if (schedulesController == null)
      schedulesController = new SchedulesController();
    return schedulesController;
  }

  public static UsersController getUsersController() {
    if (usersController == null) usersController = new UsersController();
    return usersController;
  }

  public static RadioProgramDetailController getRadioProgramDetailController() {
    if (radioProgramDetailController == null)
      radioProgramDetailController = new RadioProgramDetailController();
    return radioProgramDetailController;
  }

  public static ScheduleProgramDetailController getScheduleProgramDetailController() {
    if (scheduleProgramDetailController == null)
      scheduleProgramDetailController = new ScheduleProgramDetailController();
    return scheduleProgramDetailController;
  }

  public static UserDetailController getUserDetailController() {
    if (userDetailController == null) userDetailController = new UserDetailController();
    return userDetailController;
  }

  public static MyProfileController getMyProfileController() {
    if(myProfileController == null) myProfileController = new MyProfileController();
    return myProfileController;
  }
}
