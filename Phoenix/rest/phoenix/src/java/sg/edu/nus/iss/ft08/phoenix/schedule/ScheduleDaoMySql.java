package sg.edu.nus.iss.ft08.phoenix.schedule;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 * Implements ScheduleProgramDao for MySQL database.
 *
 * @author Karthik
 */
public class ScheduleDaoMySql implements ScheduleDao {

  @Override
  public List<ProgramSlot> findAll() {
    String sql = "select ps.slot_id, ps.program_id, r.name as program_name,\n"
      + "ps.presenter_id, pr.name as presenter_name, ps.producer_id,\n"
      + "pd.name as producer_name, ps.start, ps.end, ps.updated_by\n"
      + "from phoenix.program_slot ps left join phoenix.radio_program r\n"
      + "on r.program_id = ps.program_id left join phoenix.user pr\n"
      + "on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id;";

    MySqlQuery query = new MySqlQuery(sql,
      (r) -> {
        ProgramSlot p = new ProgramSlot();
        p.setId(r.getInt("slot_id"));
        p.setPresenterId(r.getString("presenter_id"));
        p.setProducerId(r.getString("producer_id"));
        p.setProgramId(r.getInt("program_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setProgramName(r.getString("program_name"));
        p.setPresenterName(r.getString("presenter_name"));
        p.setProducerName(r.getString("producer_name"));
        return p;
      });

    return query.findAll();
  }

  @Override
  public ProgramSlot findOne(ProgramSlot record) {
    String sql = "select ps.slot_id, ps.program_id, r.name as program_name,\n"
      + "ps.presenter_id, pr.name as presenter_name, ps.producer_id,\n"
      + "pd.name as producer_name, ps.start, ps.end, ps.updated_by\n"
      + "from phoenix.program_slot ps left join phoenix.radio_program r\n"
      + "on r.program_id = ps.program_id left join phoenix.user pr\n"
      + "on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id where ps.slot_id = ?;";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getId());
      },
      (r) -> {
        ProgramSlot p = new ProgramSlot();
        p.setId(r.getInt("slot_id"));
        p.setPresenterId(r.getString("presenter_id"));
        p.setProducerId(r.getString("producer_id"));
        p.setProgramId(r.getInt("program_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setProgramName(r.getString("program_name"));
        p.setPresenterName(r.getString("presenter_name"));
        p.setProducerName(r.getString("producer_name"));
        return p;
      });

    return query.findOne();
  }

  @Override
  public List<ProgramSlot> findOverlap(ProgramSlot record) {
    String sql = "select ps.slot_id, ps.program_id, r.name as program_name,\n"
      + "ps.presenter_id, pr.name as presenter_name, ps.producer_id,\n"
      + "pd.name as producer_name, ps.start, ps.end, ps.updated_by\n"
      + "from phoenix.program_slot ps left join phoenix.radio_program r\n"
      + "on r.program_id = ps.program_id left join phoenix.user pr\n"
      + "on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id where\n"
      + "(ps.start between ? and ?)\n"
      + "or (ps.end between ? and ?)\n"
      + "or (ps.start = ? and ps.end = ?)\n"
      + "or (ps.start <= ? and ps.end >= ?)\n"
      + "or (ps.start >= ? and ps.end <= ?);";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setTimestamp(1,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(2,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(3,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(4,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(5,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(6,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(7,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(8,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(9,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(10,
          new java.sql.Timestamp(record.getEnd().getTime()));
      },
      (r) -> {
        ProgramSlot p = new ProgramSlot();
        p.setId(r.getInt("slot_id"));
        p.setPresenterId(r.getString("presenter_id"));
        p.setProducerId(r.getString("producer_id"));
        p.setProgramId(r.getInt("program_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setProgramName(r.getString("program_name"));
        p.setPresenterName(r.getString("presenter_name"));
        p.setProducerName(r.getString("producer_name"));
        return p;
      });

    return query.findAll();
  }

  @Override
  public List<ProgramSlot> findRange(ProgramSlot record) {
    String sql = "select ps.slot_id, ps.program_id, r.name as program_name,\n"
      + "ps.presenter_id, pr.name as presenter_name, ps.producer_id,\n"
      + "pd.name as producer_name, ps.start, ps.end, ps.updated_by\n"
      + "from phoenix.program_slot ps left join phoenix.radio_program r\n"
      + "on r.program_id = ps.program_id left join phoenix.user pr\n"
      + "on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id where (ps.start between ? and ?)\n"
      + "or (ps.end between ? and ?);";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setTimestamp(1,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(2,
          new java.sql.Timestamp(record.getEnd().getTime()));
        s.setTimestamp(3,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(4,
          new java.sql.Timestamp(record.getEnd().getTime()));
      },
      (r) -> {
        ProgramSlot p = new ProgramSlot();
        p.setId(r.getInt("slot_id"));
        p.setPresenterId(r.getString("presenter_id"));
        p.setProducerId(r.getString("producer_id"));
        p.setProgramId(r.getInt("program_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setProgramName(r.getString("program_name"));
        p.setPresenterName(r.getString("presenter_name"));
        p.setProducerName(r.getString("producer_name"));
        return p;
      });

    return query.findAll();
  }

  @Override
  public ProgramSlot update(ProgramSlot newRecord) {
    String sql = "update phoenix.program_slot set\n"
      + "program_id = ?,\n"
      + "presenter_id = ?,\n"
      + "producer_id =?,\n"
      + "updated_by = ?,\n"
      + "start = ?,\n"
      + "end = ?\n"
      + "where (slot_id = ?);";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, newRecord.getProgramId());
        s.setString(2, newRecord.getPresenterId());
        s.setString(3, newRecord.getProducerId());
        s.setString(4, newRecord.getUpdatedBy());
        s.setTimestamp(5, new java.sql.Timestamp(
          newRecord.getStart().getTime()));
        s.setTimestamp(6, new java.sql.Timestamp(
          newRecord.getEnd().getTime()));
        s.setInt(7, newRecord.getId());
      });

    query.update(newRecord);

    ProgramSlot example = new ProgramSlot();
    example.setId(newRecord.getId());

    ProgramSlot updated = findOne(example);
    return updated;
  }

  @Override
  public ProgramSlot create(ProgramSlot newRecord) {
    String sql = "insert into phoenix.program_slot\n"
      + "(program_id, presenter_id,\n"
      + "producer_id, updated_by, start, end)\n"
      + " values (?,?,?,?,?,?);";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, newRecord.getProgramId());
        s.setString(2, newRecord.getPresenterId());
        s.setString(3, newRecord.getProducerId());
        s.setString(4, newRecord.getUpdatedBy());
        s.setTimestamp(5,
          new java.sql.Timestamp(newRecord.getStart().getTime()));
        s.setTimestamp(6,
          new java.sql.Timestamp(newRecord.getEnd().getTime()));
      });

    return query.update(newRecord);
  }

  @Override
  public void delete(ProgramSlot record) {
    String sql = "delete from phoenix.program_slot\n"
      + "where (slot_id = ?);";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getId());
      });

    query.update();
  }

  @Override
  public List<ProgramSlot> findAllSchedulesRadioProgram(ProgramSlot record) {
    String sql = "select ps.slot_id, ps.program_id, r.name as program_name,\n"
      + "ps.presenter_id, pr.name as presenter_name, ps.producer_id,\n"
      + "pd.name as producer_name, ps.start, ps.end, ps.updated_by\n"
      + "from phoenix.program_slot ps left join phoenix.radio_program r\n"
      + "on r.program_id = ps.program_id left join phoenix.user pr\n"
      + "on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id where ps.program_id = ?";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, record.getProgramId());
      },
      (r) -> {
        ProgramSlot p = new ProgramSlot();
        p.setId(r.getInt("slot_id"));
        p.setPresenterId(r.getString("presenter_id"));
        p.setProducerId(r.getString("producer_id"));
        p.setProgramId(r.getInt("program_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setProgramName(r.getString("program_name"));
        p.setPresenterName(r.getString("presenter_name"));
        p.setProducerName(r.getString("producer_name"));
        return p;
      });

    return query.findAll();
  }

  @Override
  public List<ProgramSlot> findAllSchedulesUser(ProgramSlot record) {
    String sql = "select ps.slot_id, ps.program_id, r.name as program_name,\n"
      + "ps.presenter_id, pr.name as presenter_name, ps.producer_id,\n"
      + "pd.name as producer_name, ps.start, ps.end, ps.updated_by\n"
      + "from phoenix.program_slot ps left join phoenix.radio_program r\n"
      + "on r.program_id = ps.program_id left join phoenix.user pr\n"
      + "on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id where (ps.presenter_id = ? or\n"
      + "ps.producer_id = ?);";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, record.getPresenterId());
        s.setString(2, record.getPresenterId());
      },
      (r) -> {
        ProgramSlot p = new ProgramSlot();
        p.setId(r.getInt("slot_id"));
        p.setPresenterId(r.getString("presenter_id"));
        p.setProducerId(r.getString("producer_id"));
        p.setProgramId(r.getInt("program_id"));
        p.setStart(r.getTimestamp("start"));
        p.setEnd(r.getTimestamp("end"));
        p.setUpdatedBy(r.getString("updated_by"));
        p.setProgramName(r.getString("program_name"));
        p.setPresenterName(r.getString("presenter_name"));
        p.setProducerName(r.getString("producer_name"));
        return p;
      });

    return query.findAll();
  }
  
  @Override
  public void copy(ProgramSlot record, int interval) {
    String sql = "insert into phoenix.program_slot (program_id, presenter_id,\n"
      + "producer_id, updated_by, start, end) select p.program_id,\n"
      + "p.presenter_id, p.producer_id, p.updated_by,\n"
      + "date_add(p.start, interval ? day), date_add(p.end, interval ? day)\n"
      + "from phoenix.program_slot p where p.start >= ? and p.end <= ?;";

    MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setInt(1, interval);
        s.setInt(2, interval);
        s.setTimestamp(3,
          new java.sql.Timestamp(record.getStart().getTime()));
        s.setTimestamp(4,
          new java.sql.Timestamp(record.getEnd().getTime()));
      });

    query.update();
  }

}
