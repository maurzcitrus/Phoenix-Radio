package sg.edu.nus.iss.ft08.phoenix.summary;

import sg.edu.nus.iss.ft08.phoenix.model.Summary;

/**
 * Defines methods to find summary like total users,
 * program slots, radio programs.
 * 
 * @author phyo
 */
public interface SummaryDao {

  /**
   * Finds the total count of program slots, radio programs, users. 
   * 
   * @return
   */
  public Summary findOne();
  
}
