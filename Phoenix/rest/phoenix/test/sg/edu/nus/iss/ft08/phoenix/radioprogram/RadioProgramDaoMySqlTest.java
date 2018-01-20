/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 *
 * @author phyo
 */
public class RadioProgramDaoMySqlTest {

  private static RadioProgram dummy;

  public RadioProgramDaoMySqlTest() {
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

    RadioProgramDaoMySql dao = new RadioProgramDaoMySql();

    List<RadioProgram> results = dao.findAll();

    assertNotNull(results);
    assertTrue(results.stream().anyMatch(x -> x.getId() == dummy.getId()));

  }

  @Test
  public void testFindOne() {
    RadioProgramDaoMySql dao = new RadioProgramDaoMySql();

    RadioProgram result = dao.findOne(dummy);

    assertNotNull(result);

    assertEquals(dummy.getId(), result.getId());
    assertEquals(dummy.getName(), result.getName());
    assertEquals(dummy.getDescription(), result.getDescription());
    assertEquals(dummy.getDuration(), result.getDuration());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
  }

  @Test
  public void testUpdate() {

    RadioProgram expected = dummy;
    expected.setName("new_name");
    expected.setDescription("new_description");
    expected.setDuration(45);
    expected.setUpdatedBy("tester");

    RadioProgramDaoMySql dao = new RadioProgramDaoMySql();

    RadioProgram result = dao.update(expected);

    assertNotNull(result);
    assertEquals(expected.getId(), result.getId());
    assertEquals(expected.getName(), result.getName());
    assertEquals(expected.getDescription(), result.getDescription());
    assertEquals(expected.getDuration(), result.getDuration());
    assertEquals(expected.getUpdatedBy(), result.getUpdatedBy());
  }

  @Test
  public void testCreate() {

    deleteDummyData();

    RadioProgramDaoMySql dao = new RadioProgramDaoMySql();

    RadioProgram result = dao.create(dummy);

    assertNotNull(result);

    assertNotEquals(dummy.getId(), result.getId()); // id should be auto generated for a new record
    assertEquals(dummy.getName(), result.getName());
    assertEquals(dummy.getDescription(), result.getDescription());
    assertEquals(dummy.getDuration(), result.getDuration());
    assertEquals(dummy.getUpdatedBy(), result.getUpdatedBy());
  }

  @Test
  public void testDelete() {
    RadioProgramDaoMySql dao = new RadioProgramDaoMySql();

    dao.delete(dummy);

    RadioProgram result = dao.findOne(dummy);

    assertNull(result);

  }

  private static RadioProgram createDummyData() {
    String createSql
        = "INSERT INTO phoenix.radio_program "
        + "(name, description, typical_duration, updated_by) "
        + "VALUES ('dummy','dummy description',30,'test')";

    MySqlQuery q = new MySqlQuery(createSql);
    q.update();

    String selectSql
        = "select program_id, name, description, typical_duration, updated_by "
        + "from phoenix.radio_program where name = 'dummy'";

    q = new MySqlQuery(selectSql,
        (r) -> {
          RadioProgram x = new RadioProgram();
          x.setId(r.getInt("program_id"));
          x.setName(r.getString("name"));
          x.setDescription(r.getString("description"));
          x.setDuration(r.getInt("typical_duration"));
          x.setUpdatedBy(r.getString("updated_by"));
          return x;
        });

    return q.findOne();
  }

  private static void deleteDummyData() {
    String sql = "DELETE FROM phoenix.radio_program WHERE name = ?";

    if (dummy != null) {
      MySqlQuery q = new MySqlQuery(sql,
          (s) -> {
            s.setString(1, dummy.getName());
          });

      q.update();
    }
  }

}
