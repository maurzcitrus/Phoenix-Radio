package sg.edu.nus.iss.ft08.phoenix;

import sg.edu.nus.iss.ft08.phoenix.user.RoleMemberDao;
import sg.edu.nus.iss.ft08.phoenix.user.RoleMemberDaoMySql;
import sg.edu.nus.iss.ft08.phoenix.user.UserDao;
import sg.edu.nus.iss.ft08.phoenix.user.UserDaoMySql;
import sg.edu.nus.iss.ft08.phoenix.account.AccountDao;
import sg.edu.nus.iss.ft08.phoenix.account.AccountDaoMySql;
import sg.edu.nus.iss.ft08.phoenix.radioprogram.RadioProgramDao;
import sg.edu.nus.iss.ft08.phoenix.radioprogram.RadioProgramDaoMySql;
import sg.edu.nus.iss.ft08.phoenix.schedule.ScheduleDao;
import sg.edu.nus.iss.ft08.phoenix.schedule.ScheduleDaoMySql;
import sg.edu.nus.iss.ft08.phoenix.summary.SummaryDao;
import sg.edu.nus.iss.ft08.phoenix.summary.SummaryDaoMySql;

/**
 * Decouples DAO instance creation from Resource classes so that we have
 * flexibility to swap DAO implementation that use other databases instead of
 * MySQL.
 *
 * @author phyo
 */
public class DaoFactory {

  /**
   * Creates an instance of AccountDao.
   *
   * @return AccountDao instance.
   */
  public static AccountDao getAcountDao() {
    return new AccountDaoMySql();
  }

  /**
   * Creates an instance of RadioProgramDao.
   *
   * @return RadioProgramDao instance.
   */
  public static RadioProgramDao getRadioProgramDao() {
    return new RadioProgramDaoMySql();
  }

  /**
   * Creates an instance of ScheduleDao.
   *
   * @return ScheduleDao instance.
   */
  public static ScheduleDao getScheduleDao() {
    return new ScheduleDaoMySql();
  }

  /**
   * Creates an instance of SummaryDao.
   *
   * @return SummaryDao instance.
   */
  public static SummaryDao getSummaryDao() {
    return new SummaryDaoMySql();
  }

  /**
   * Creates an instance of UserDao.
   *
   * @return UserDao instance.
   */
  public static UserDao getUserDao() {
    return new UserDaoMySql();
  }

  /**
   * Creates an instance of RoleMemberDao.
   *
   * @return RoleMemberDao instance.
   */
  public static RoleMemberDao getRoleMemberDao() {
    return new RoleMemberDaoMySql();
  }

}
