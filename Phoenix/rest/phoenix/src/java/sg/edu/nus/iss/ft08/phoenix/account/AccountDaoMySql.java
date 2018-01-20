package sg.edu.nus.iss.ft08.phoenix.account;

import sg.edu.nus.iss.ft08.phoenix.model.User;
import sg.edu.nus.iss.ft08.phoenix.util.MySqlQuery;

/**
 * Implements AccountDao for MySQL database.
 * @author phyo
 */
public class AccountDaoMySql implements AccountDao {

  @Override
  public User findOne(String userId, String password) {

    String sql = "SELECT id, password, name FROM user WHERE (id = ? and password = ?) ";

    MySqlQuery query = new MySqlQuery(sql,
        (statement) -> {
          statement.setString(1, userId);
          statement.setString(2, password);
        },
        (result) -> {
          User user = new User();
          user.setUserId(result.getString("id"));
          user.setPassword(result.getString("password"));
          user.setFullName(result.getString("name"));
          user.setAuthenticated(true);
          return user;
        });

    return query.findOne();
  }

}
