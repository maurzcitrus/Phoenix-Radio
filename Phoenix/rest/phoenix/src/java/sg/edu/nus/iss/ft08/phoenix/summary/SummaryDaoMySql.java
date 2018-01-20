package sg.edu.nus.iss.ft08.phoenix.summary;

import sg.edu.nus.iss.ft08.phoenix.model.Summary;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 * Implements SummaryDao for MySQL Database.
 * 
 * @author phyo
 */
public class SummaryDaoMySql implements SummaryDao {

  @Override
  public Summary findOne() {

    String sql
        = "select \n"
        + "(select count(1) from phoenix.user) as totalUsers,\n"
        + "(select count(1) from phoenix.program_slot) as totalProgramSlots,\n"
        + "(select count(1) from phoenix.radio_program) as totalRadioPrograms";
    
    MySqlQuery query = new MySqlQuery(sql, 
        (r) -> {
          Summary s = new Summary();
          s.setTotalUsers(r.getInt("totalUsers"));
          s.setTotalProgramSlots(r.getInt("totalProgramSlots"));
          s.setTotalRadioPrograms(r.getInt("totalRadioPrograms"));          
          return s;
        });
    
    return query.findOne();    
  }

}
