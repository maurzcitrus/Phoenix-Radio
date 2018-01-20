/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ft08.phoenix.user;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;
import sg.edu.nus.iss.ft08.phoenix.model.Role;
import sg.edu.nus.iss.ft08.phoenix.model.RoleMember;
import sg.edu.nus.iss.ft08.phoenix.model.User;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 * Implements UserDao for MySQL database.
 *
 * @author JAYENDRA
 */
public class UserDaoMySql implements UserDao {

  /**
   * Overridden method from user interface to get all user details. This method
   * internally reads all users and associated roles for that each user value
   * from database.
   *
   * @return list of users and list of roles for each user
   */
  @Override
  public List<User> findAll() {

    List<User> users = findAllUsers();
    List<RoleMember> roleMembers = findRoleMembers();

    if (users != null) {
      users.forEach(u -> {
        roleMembers.forEach(m -> {
          if (m.getUserId().equalsIgnoreCase(u.getUserId())) {
            List<Role> roles = u.getRoles();
            Role thisRole = new Role();
            thisRole.setRoleId(m.getRoleId());
            roles.add(thisRole);
          }
        });
        int count = findAssignedProgramSlots(u);
        u.setprogramSlots(count);
      });
    }
    return users;
  }

  /**
   * This method reads all users from database.
   *
   * @return all users
   */
  protected List<User> findAllUsers() {

    String sql = "select \n"
        + "u.id,\n"
        + "u.password,\n"
        + "u.name\n"
        + "from phoenix.user u";

    MySqlQuery query = new MySqlQuery(sql,
        (u) -> {
          User user = new User();
          user.setUserId(u.getString("id"));
          user.setPassword(u.getString("password"));
          user.setFullName(u.getString("name"));
          return user;
        });

    return query.findAll();
  }

  /**
   * This method reads all the roles from database
   *
   * @return
   */
  protected List<RoleMember> findRoleMembers() {

    String sql = "select\n"
        + "ur.user_id,\n"
        + "ur.role_id\n"
        + "from\n"
        + "phoenix.user_role ur\n"
        + "order by\n"
        + "user_id";

    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          RoleMember rm = new RoleMember();
          rm.setRoleId(p.getString("role_id"));
          rm.setUserId(p.getString("user_id"));
          return rm;
        });

    return query.findAll();
  }

  /**
   * Overridden method from user interface to get a requested(single) user
   * details. This method internally find user and associated roles data from
   * database
   *
   * @param record
   * @return single user details and list of roles associated with that
   * particular user
   */
  @Override
  public User findOne(User record) {

    User user = findOneUser(record);
    List<RoleMember> roleMembers = findRoleMember(record);
    //List<Role> roles;

    if (user != null) {
      roleMembers.forEach(m -> {
        List<Role> roles = user.getRoles();
        Role thisRole = new Role();
        thisRole.setRoleId(m.getRoleId());
        roles.add(thisRole);
      });

      int count = findAssignedProgramSlots(user);
      user.setprogramSlots(count);
    }
    return user;
  }

  /**
   * This method find one user based on particular user ID
   *
   * @param record User must have ID
   * @return one user
   */
  protected User findOneUser(User record) {
    String sql = "Select\n"
        + "u.id,\n"
        + "u.password,\n"
        + "u.name\n"
        + "from\n"
        + "phoenix.user u\n"
        + "where (u.id = ?)";

    MySqlQuery query = new MySqlQuery(sql,
        (s) -> {
          s.setString(1, record.getUserId());
        },
        (r) -> {
          User user = new User();
          user.setUserId(r.getString("id"));
          user.setPassword(r.getString("password"));
          user.setFullName(r.getString("name"));
          return user;
        });

    return query.findOne();
  }

  /**
   * This method find all assigned roles for one particular user
   *
   * @param record User must have ID
   * @return list of all roles for one user
   */
  protected List<RoleMember> findRoleMember(User record) {
    String sql = "select\n"
        + "ur.user_id,\n"
        + "ur.role_id\n"
        + "from\n"
        + "phoenix.user_role ur\n"
        + "where \n"
        + "ur.user_id = ?";

    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        },
        (m) -> {
          RoleMember rm = new RoleMember();
          rm.setRoleId(m.getString("role_id"));
          return rm;
        });

    return query.findAll();
  }

  /**
   * Overridden method, which extracts details form users and insert user
   * details and roles separately into database
   *
   * @param newRecord User object
   * @return newly created user
   */
  @Override
  public User createUser(User newRecord) {
    String sql = "INSERT INTO `user` (`id`, `password`, `name`) VALUES (?,?,?)";

    MySqlQuery query = new MySqlQuery(sql,
        (c) -> {
          c.setString(1, newRecord.getUserId());
          c.setString(2, newRecord.getPassword());
          c.setString(3, newRecord.getFullName());
        });

    query.update(newRecord);
    createRole(newRecord);
    
    User example = new User();
    example.setUserId(newRecord.getUserId());
    
    User created = findOne(example);
    return created;   
  }

  private void createRole(User newrecord) {
    List<Role> roles = newrecord.getRoles();

    for (int i = 0; i < roles.size(); i++) {
      Role current = roles.get(i);
      String sql = "INSERT into `user_role` (`user_id`, `role_id`, `updated_by`) \n"
          + "VALUES (?,?,'system')";

      MySqlQuery query = new MySqlQuery(sql,
          (p) -> {
            p.setString(1, newrecord.getUserId());
            p.setString(2, current.getRoleId());
          });
      query.update(newrecord);
    }
  }

  /**
   * Overridden method, which extracts details form users and insert updated
   * user details and roles separately into database.
   *
   * @param newRecord
   * @return newly updated record
   */
  @Override
  public User updateUser(User newRecord) {
    String sql = "UPDATE `user` set `password` = ?, `name` = ? WHERE (`id` = ? )";

    MySqlQuery query = new MySqlQuery(sql,
        (s) -> {
          s.setString(1, newRecord.getPassword());
          s.setString(2, newRecord.getFullName());
          s.setString(3, newRecord.getUserId());
        });

    query.update(newRecord);
    updateRole(newRecord);

    User example = new User();
    example.setUserId(newRecord.getUserId());
    
    User updated = findOne(example);
    return updated;
  }

  private void updateRole(User newRecord) {

    deleteRole(newRecord);
    createRole(newRecord);
  }

  /**
   * Overridden method deletes user, before deleting user first it check whether
   * user is assigned to any program slot if not then it will delete user.
   *
   * @param record
   */
  @Override
  public void deleteUser(User record) {

    String sql = "DELETE FROM `user` WHERE (`id` = ?)";

    MySqlQuery query = new MySqlQuery(sql,
        (d) -> {
          d.setString(1, record.getUserId());
        });

    query.update();
    deleteRole(record);

  }

  private void deleteRole(User record) {
    String sql = "Delete from `user_role` where (`user_id` = ?)";

    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        });

    query.update();
  }

  @Override
  public User findAssignedUser(User record) {

    String sql = "select * \n"
        + "from user u\n"
        + "where \n"
        + "(u.id = ?)\n"
        + "and\n"
        + "exists\n"
        + "(\n"
        + "select null\n"
        + "from program_slot ps\n"
        + "where (ps.presenter_id = u.id or ps.producer_id = u.id)\n"
        + ")";

    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
        },
        (m) -> {
          User user = new User();
          user.setUserId(m.getString("id"));
          user.setPassword(m.getString("password"));
          user.setFullName(m.getString("name"));
          return user;
        });

    return query.findOne();

  }

  private int findAssignedProgramSlots(User record) {

    String sql = "select\n"
        + "(Select count(1) \n"
        + "from program_slot ps \n"
        + "where (ps.presenter_id = ? or ps.producer_id = ?)) as programSlots;";

    MySqlQuery query = new MySqlQuery(sql,
        (p) -> {
          p.setString(1, record.getUserId());
          p.setString(2, record.getUserId());
        },
        (m) -> {
          int count = m.getInt("programSlots");
          return count;
        });

    return query.findOne();
  }
}
