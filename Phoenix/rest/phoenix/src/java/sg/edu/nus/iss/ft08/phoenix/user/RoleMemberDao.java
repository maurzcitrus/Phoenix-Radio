package sg.edu.nus.iss.ft08.phoenix.user;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.RoleMember;

/**
 * Defines methods to find role members.
 * 
 * @author phyo
 */
public interface RoleMemberDao {
  
  /**
   * Finds all role members who has the given role.  
   * 
   * @param roleId
   * @return List containing zero or more role members.
   */
  public List<RoleMember> findAll(String roleId);
}
