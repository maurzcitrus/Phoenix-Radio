package sg.edu.nus.ft08.phoenix.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.schedule.ScheduleDaoMySql;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 * Test class for ScheduleDaoMySql
 *
 * @author Karthik
 */
public class ScheduleDaoMySqlTest {

  private static ProgramSlot dummy;

  public ScheduleDaoMySqlTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    deleteDummyData();
  }

  @AfterClass
  public static void tearDownClass() {
    deleteDummyData();
  }

  @Before
  public void setUp() {
    dummy = createDummyData();
  }

  @After
  public void tearDown() {
    deleteDummyData();
  }

  @Test
  public void testFindAll() {

    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    List<ProgramSlot> results = dao.findAll();

    assertNotNull(results);
    assertTrue(results.stream().anyMatch(x -> x.getId() == dummy.getId()));

  }

  private void new_slot_start_end_is_in_btw_dummy_start_end() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 18:30:00
    new end time - 19:30:00
    findOverlap() should return list with dummy */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 18, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 30, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void dummy_start_end_is_in_btw_new_slot_start_end() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 17:30:00
    new end time - 18:30:00
    findOverlap() should return list with dummy */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 17, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 18, 30, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void new_slot_start_end_equals_dummy_start_end() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 18:00:00
    new end time - 19:00:00
    findOverlap() should return list with dummy */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 18, 00, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 00, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void new_slot_start_end_inside_dummy_start_end() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 18:15:00
    new end time - 18:45:00
    findOverlap() should return list with dummy */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 18, 15, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 18, 45, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void dummy_start_end_inside_new_slot_start_end() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 17:30:00
    new end time - 19:30:00
    findOverlap() should return list with dummy */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 17, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 30, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findOverlap(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void new_slot_start_end_not_overlap_dummy_start_end() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 19:00:00
    new end time - 20:00:00
    findOverlap() should return empty list */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

//    cal.set(2010, 8, 26, 19, 00);
    cal.set(2010, 8, 26, 19, 01);
    start = cal.getTime();
    cal.set(2010, 8, 26, 20, 00);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findOverlap(newSlot);
    assertEquals(results.size(), 0);

  }

  @Test
  public void testFindOverlap() {

    new_slot_start_end_equals_dummy_start_end();
    dummy_start_end_inside_new_slot_start_end();
    new_slot_start_end_inside_dummy_start_end();
    new_slot_start_end_is_in_btw_dummy_start_end();
    dummy_start_end_is_in_btw_new_slot_start_end();
    new_slot_start_end_not_overlap_dummy_start_end();

  }

  private void dummy_inside_range() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 17:30:00
    new end time - 19:30:00
    findRange() should return list with dummy */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 17, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 19, 30, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findRange(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void dummy_outside_range() {

    /* dummy start time - 18:00:00
    dummy end time - 19:00:00
    new start time - 16:30:00
    new end time - 17:30:00
    findRange() should return empty list */
    ProgramSlot newSlot = new ProgramSlot();
    Date start, end;
    List<ProgramSlot> results;
    Calendar cal = Calendar.getInstance();
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    cal.set(2010, 8, 26, 16, 30, 0);
    start = cal.getTime();
    cal.set(2010, 8, 26, 17, 30, 0);
    end = cal.getTime();

    newSlot.setStart(start);
    newSlot.setEnd(end);

    results = dao.findRange(newSlot);
    assertEquals(results.size(), 0);

  }

  @Test
  public void testFindRange() {

    dummy_inside_range();
    dummy_outside_range();

  }

  private void find_all_program_slot_based_on_program_id_true() {

    ProgramSlot newSlot = new ProgramSlot();
    int program_id = 1;
    List<ProgramSlot> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    newSlot.setProgramId(program_id);

    results = dao.findAllSchedulesRadioProgram(newSlot);
    assertNotEquals(results.size(), 0);

  }

  private void find_all_program_slot_based_on_program_id_false() {

    ProgramSlot newSlot = new ProgramSlot();
    int program_id = 2;
    List<ProgramSlot> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    newSlot.setProgramId(program_id);

    results = dao.findAllSchedulesRadioProgram(newSlot);
    assertEquals(results.size(), 0);

  }

  @Test
  public void testFindAllSchedulesRadioProgram() {
    find_all_program_slot_based_on_program_id_false();
    find_all_program_slot_based_on_program_id_true();
  }

  private void find_all_program_slot_based_on_user_id_true() {

    ProgramSlot newSlot = new ProgramSlot();
    String user_id = "dummyPresenter";
    List<ProgramSlot> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    newSlot.setPresenterId(user_id);
    results = dao.findAllSchedulesUser(newSlot);
    assertNotEquals(results.size(), 0);

    user_id = "dummyProducer";
    newSlot.setPresenterId(user_id);
    results = dao.findAllSchedulesUser(newSlot);
    assertNotEquals(results.size(), 0);
  }

  private void find_all_program_slot_based_on_user_id_false() {

    ProgramSlot newSlot = new ProgramSlot();
    String user_id = "dummy";
    List<ProgramSlot> results;
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    newSlot.setPresenterId(user_id);

    results = dao.findAllSchedulesUser(newSlot);
    assertEquals(results.size(), 0);

  }

  @Test
  public void testFindAllSchedulesUser() {
    find_all_program_slot_based_on_user_id_false();
    find_all_program_slot_based_on_user_id_true();
  }

  @Test
  public void testFindOne() {
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    ProgramSlot result = dao.findOne(dummy);

    assertNotNull(result);

    assertEquals(dummy.getId(), result.getId());
    assertEquals(dummy.getProgramId(), result.getProgramId());
    assertEquals(dummy.getProducerId(), result.getProducerId());
    assertEquals(dummy.getPresenterId(), result.getPresenterId());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getStart()),
      to_dd_mm_yyyy_hh_mm(result.getStart()));
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getEnd()),
      to_dd_mm_yyyy_hh_mm(result.getEnd()));

  }

  @Test
  public void testUpdate() {

    Calendar cal = Calendar.getInstance();
    cal.set(2010, 9, 26, 18, 0, 0);
    Date start = cal.getTime();
    cal.set(2010, 9, 26, 19, 0, 0);
    Date end = cal.getTime();

    ProgramSlot expected = dummy;
    expected.setProgramId(2);
    expected.setPresenterId("new_presenter");
    expected.setProducerId("new_producer");
    expected.setUpdatedBy("new_tester");
    expected.setStart(start);
    expected.setEnd(end);

    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    ProgramSlot result = dao.update(expected);

    assertNotNull(result);
    assertEquals(expected.getId(), result.getId());
    assertEquals(expected.getProgramId(), result.getProgramId());
    assertEquals(expected.getPresenterId(), result.getPresenterId());
    assertEquals(expected.getProducerId(), result.getProducerId());
    assertEquals(expected.getUpdatedBy(), result.getUpdatedBy());

    System.out.println(expected.getStart().toString());
    System.out.println(result.getStart().toString());
    System.out.println(expected.getStart().getTime());
    System.out.println(result.getStart().getTime());

    System.out.println(to_dd_mm_yyyy_hh_mm(expected.getStart()));
    System.out.println(to_dd_mm_yyyy_hh_mm(result.getStart()));

    assertEquals(to_dd_mm_yyyy_hh_mm(expected.getStart()),
      to_dd_mm_yyyy_hh_mm(result.getStart()));
    assertEquals(to_dd_mm_yyyy_hh_mm(expected.getEnd()),
      to_dd_mm_yyyy_hh_mm(result.getEnd()));
  }

  @Test
  public void testCreate() {

    deleteDummyData();

    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    ProgramSlot result = dao.create(dummy);

    assertNotNull(result);

    assertEquals(dummy.getProgramId(), result.getProgramId());
    assertEquals(dummy.getPresenterId(), result.getPresenterId());
    assertEquals(dummy.getProducerId(), result.getProducerId());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getStart()),
      to_dd_mm_yyyy_hh_mm(result.getStart()));
    assertEquals(to_dd_mm_yyyy_hh_mm(dummy.getEnd()),
      to_dd_mm_yyyy_hh_mm(result.getEnd()));

  }

  @Test
  public void testDelete() {
    ScheduleDaoMySql dao = new ScheduleDaoMySql();

    dao.delete(dummy);

    ProgramSlot result = dao.findOne(dummy);

    assertNull(result);

  }
  
  @Test
  public void testCopy() {
    ScheduleDaoMySql dao = new ScheduleDaoMySql();
    
    ProgramSlot newSlot = new ProgramSlot();
    int interval = 7;
    Date start, end;
    
    start = dummy.getStart();
    end = dummy.getEnd();
    
    Calendar cal = Calendar.getInstance();
    cal.setTime(start);
    cal.add(Calendar.DATE, interval);
    start = cal.getTime();
    
    cal.setTime(end);
    cal.add(Calendar.DATE, interval);
    end = cal.getTime();
    
    newSlot.setStart(start);
    newSlot.setEnd(end);
    
    assertEquals(dao.findRange(newSlot).size(), 0);
    dao.copy(dummy, 7);
    assertNotEquals(dao.findRange(newSlot).size(), 0);
  }

  private static ProgramSlot createDummyData() {
    String createSql
      = "INSERT INTO phoenix.program_slot\n"
      + "(program_id, presenter_id, producer_id, updated_by, start, end)\n"
      + "VALUES (1,'dummyPresenter','dummyProducer','schedule_tester',\n"
      + "'2010-09-26 18:00:00','2010-09-26 19:00:00');";

    MySqlQuery q = new MySqlQuery(createSql);
    q.update();

    String selectSql = "select ps.slot_id, ps.program_id, r.name as\n"
      + "program_name, ps.presenter_id, pr.name as presenter_name,\n"
      + "ps.producer_id, pd.name as producer_name, ps.start, ps.end,\n"
      + "ps.updated_by from phoenix.program_slot ps left join\n"
      + "phoenix.radio_program r on r.program_id = ps.program_id left join\n"
      + "phoenix.user pr on pr.id = ps.presenter_id left join phoenix.user pd\n"
      + "on pd.id = ps.producer_id where\n"
      + "ps.updated_by = 'schedule_tester';";

    q = new MySqlQuery(selectSql,
      (r) -> {
        ProgramSlot x = new ProgramSlot();
        x.setId(r.getInt("slot_id"));
        x.setProgramId(r.getInt("program_id"));
        x.setPresenterId(r.getString("presenter_id"));
        x.setProducerId(r.getString("producer_id"));
        x.setUpdatedBy(r.getString("updated_by"));
        x.setStart(r.getTimestamp("start"));
        x.setEnd(r.getTimestamp("end"));
        x.setProgramName("program_name");
        x.setPresenterName(r.getString("presenter_name"));
        x.setProducerName(r.getString("producer_name"));
        return x;
      });

    return q.findOne();
  }

  private static void deleteDummyData() {
    String sql = "DELETE FROM phoenix.program_slot WHERE updated_by = ? ";

    if (dummy != null) {
      MySqlQuery q = new MySqlQuery(sql,
        (s) -> {
          s.setString(1, dummy.getUpdatedBy());
        });

      q.update();
    }
  }

  // Date's second and millisecond values are slightly different 
  // after saving in database.
  // At the moment, we check only up to minute.
  private String to_dd_mm_yyyy_hh_mm(Date value) {
    SimpleDateFormat sdf = new SimpleDateFormat(
      "dd-MM-yyyy HH:mm", Locale.getDefault());
    return sdf.format(value);
  }
}
