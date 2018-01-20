/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.ft08.phoenix.user;

import sg.edu.nus.iss.ft08.phoenix.user.RoleMemberDaoMySql;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.ft08.phoenix.model.RoleMember;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 *
 * @author phyo
 */
public class RoleMemberDaoMySqlTest {

  private static RoleMember dummy;

  public RoleMemberDaoMySqlTest() {
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

    String roleId = dummy.getRoleId();
    
    RoleMemberDaoMySql dao = new RoleMemberDaoMySql();

    List<RoleMember> results = dao.findAll(roleId);
    
    assertNotNull(results);
    assertTrue(results.stream().allMatch(x-> x.getRoleId().equalsIgnoreCase(roleId)));
    
  }

  private static RoleMember createDummyData() {
    String createUserSql
        = "INSERT INTO phoenix.user\n"
        + "(id, password, name)\n"
        + "VALUES \n"
        + "('dummy','dummy','dummy, the tester')";

    String createRoleSql
        = "insert into phoenix.role\n"
        + "(role_id, role_name)\n"
        + "values\n"
        + "('dummyrole', 'dummy role')";

    String createUserRoleSql
        = "insert into phoenix.user_role \n"
        + "(user_id, role_id, updated_by)\n"
        + "values\n"
        + "('dummy', 'dummyrole', 'tester')";

    MySqlQuery q = new MySqlQuery(createUserSql);
    q.update();

    q = new MySqlQuery(createRoleSql);
    q.update();

    q = new MySqlQuery(createUserRoleSql);
    q.update();

    String selectSql
        = "select\n"
        + "  u.id as userId,\n"
        + "  u.name as userName,\n"
        + "  ur.role_id as roleId,\n"
        + "  r.role_name as roleName\n"
        + "from\n"
        + "  phoenix.user u\n"
        + "left join\n"
        + "  phoenix.user_role ur \n"
        + "  on u.id = ur.user_id\n"
        + "left join\n"
        + "  phoenix.role r \n"
        + "  on ur.role_id = r.role_id\n"
        + "where\n"
        + "  ur.role_id = 'dummyrole' \n"
        + "order by\n"
        + "  userId";

    q = new MySqlQuery(selectSql,
        (r) -> {
          RoleMember m = new RoleMember();
          m.setUserId(r.getString("userId"));
          m.setUserName(r.getString("userName"));
          m.setRoleId(r.getString("roleId"));
          m.setRoleName(r.getString("roleName"));
          return m;
        });

    return q.findOne();

  }

  private static void deleteDummyData() {
    String deleteUserRoleSql = "delete from phoenix.user_role where role_id = 'dummyrole'";
    String deleteRoleSql = "delete from phoenix.role where role_id = 'dummyrole'";
    String deleteUserSql = "delete from phoenix.user where id = 'dummy'";

    if (dummy != null) {
      new MySqlQuery(deleteUserRoleSql).update();
      new MySqlQuery(deleteRoleSql).update();
      new MySqlQuery(deleteUserSql).update();
    }
  }

}
