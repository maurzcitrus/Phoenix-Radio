package sg.edu.nus.iss.ft08.phoenix.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.ft08.phoenix.DaoFactory;
import sg.edu.nus.iss.ft08.phoenix.model.User;
import sg.edu.nus.iss.ft08.phoenix.user.UserDao;

/**
 * REST API for authenticating user
 * @author phyo
 */
@Path("/account")
public class AccountResource {

  private AccountDao accountDao;
  private UserDao userDao;

  public AccountResource() {
    accountDao = DaoFactory.getAcountDao();
    userDao = DaoFactory.getUserDao();
  }

  /**
   * Authenticates a user based on userId and password.
   * If valid userId and password, authenticated attribute set to TRUE.
   * Else authenticated attribute set to FALSE.
   * @param user
   * @return User with authenticated attribute set to TRUE or FALSE.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User authenticate(User user) {
    User target = accountDao.findOne(user.getUserId(), user.getPassword());

    if (target != null) {
       target = userDao.findOne(target);
       target.setAuthenticated(true);
    } else {
      target = new User();
      target.setAuthenticated(false);
    }
    
    return target;
  }
}
