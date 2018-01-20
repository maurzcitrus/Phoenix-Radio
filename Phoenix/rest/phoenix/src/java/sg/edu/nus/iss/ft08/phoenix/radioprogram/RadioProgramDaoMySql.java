package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;
import sg.edu.nus.iss.ft08.phoenix.util.ParameterSetter;
import sg.edu.nus.iss.ft08.phoenix.util.ResultSetMapper;

/**
 * Implements RadioProgramDao for MySQL database.
 *
 * @author phyo
 */
public class RadioProgramDaoMySql implements RadioProgramDao {

  @Override
  public RadioProgram create(RadioProgram newRecord) {
    String sql
      = "INSERT INTO phoenix.radio_program\n"
      + "(\n"
      + "  name,\n"
      + "  description,\n"
      + "  typical_duration,\n"
      + "  updated_by\n"
      + ")\n"
      + "VALUES\n"
      + "(\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?\n"
      + ")";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, newRecord.getName());
        s.setString(2, newRecord.getDescription());
        s.setInt(3, newRecord.getDuration());
        s.setString(4, newRecord.getUpdatedBy());
      });

    query.update();

    // search by name, because we do not know the new Id yet
    RadioProgram example = new RadioProgram();
    example.setName(newRecord.getName());

    RadioProgram created = findOne(example);
    return created;
  }

  @Override
  public RadioProgram update(RadioProgram newRecord) {

    String sql
      = "UPDATE \n"
      + "  phoenix.radio_program\n"
      + "SET\n"
      + "  name = ?,\n"
      + "  description = ?,\n"
      + "  typical_duration = ?,\n"
      + "  updated_by = ?\n"
      + "WHERE program_id = ?";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, newRecord.getName());
        s.setString(2, newRecord.getDescription());
        s.setInt(3, newRecord.getDuration());
        s.setString(4, newRecord.getUpdatedBy());
        s.setInt(5, newRecord.getId());
      });

    query.update();

    RadioProgram example = new RadioProgram();
    example.setId(newRecord.getId());

    RadioProgram updated = findOne(example);
    return updated;
  }

  @Override
  public void delete(RadioProgram record) {
    String sql = "DELETE FROM phoenix.radio_program WHERE program_id = ?";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getId());
      });

    query.update();

  }

  @Override
  public List<RadioProgram> findAll() {

    String findSql = prepareFindSql(null);
    ResultSetMapper mapper = prepareFindResultMapper();

    MySqlQuery query = new MySqlQuery(findSql, mapper);
    return query.findAll();
  }

  @Override
  public RadioProgram findOne(RadioProgram example) {

    String findSql = prepareFindSql(example);
    ParameterSetter parameters = prepareFindParameters(example);
    ResultSetMapper mapper = prepareFindResultMapper();

    MySqlQuery query = new MySqlQuery(findSql, parameters, mapper);
    return query.findOne();

  }

  private ParameterSetter prepareFindParameters(RadioProgram p) {
    // IMPORTANT: 
    // the if-else logic and sequence of s.setXyz method call 
    // must be the same as 
    // the if-else logic and sequence of sb.append method call 
    // in prepareFindSql() 
    // because we are setting parameter values based on the sql statement
    // generated in prepareFindSql()
    return (s) -> {
      int i = 1;
      if (p.getId() > 0) {
        s.setInt(i++, p.getId());
      }

      if (p.getName() != null) {
        s.setString(i++, p.getName());
      }
    };

  }

  private String prepareFindSql(RadioProgram p) {
    StringBuilder sb = new StringBuilder(
      "select\n"
      + "  r.program_id,\n"
      + "  r.name,\n"
      + "  r.description,\n"
      + "  r.typical_duration,\n"
      + "  r.updated_by,\n"
      + "(select count(1) from phoenix.program_slot s\n"
      + "where s.program_id = r.program_id) as total_assigned\n"
      + "from\n"
      + "  phoenix.radio_program r "
      + "where 1 = 1 ");

    if (p != null) {

      if (p.getId() > 0) {
        sb.append(" and r.program_id = ? ");
      }

      if (p.getName() != null) {
        sb.append(" and r.name = ? ");
      }
    }

    sb.append(" order by name");

    return sb.toString();
  }

  private ResultSetMapper prepareFindResultMapper() {
    return (r) -> {
      RadioProgram p = new RadioProgram();
      p.setId(r.getInt("program_id"));
      p.setName(r.getString("name"));
      p.setDescription(r.getString("description"));
      p.setDuration(r.getInt("typical_duration"));
      p.setUpdatedBy(r.getString("updated_by"));
      p.setAssignedCount(r.getInt("total_assigned"));
      return p;
    };
  }

}
