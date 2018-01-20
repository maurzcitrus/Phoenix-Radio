package sg.edu.nus.iss.ft08.phoenix.account;

import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Defines methods to find user account.
 * 
 * @author phyo
 */
public interface AccountDao {
  
  /**
   * Finds a user that has matching userId and password.
   * 
   * @param userId
   * @param password
   * @return Matching user or NULL.
   */
  public User findOne(String userId, String password);
}
