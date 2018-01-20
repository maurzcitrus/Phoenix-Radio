package sg.edu.nus.iss.ft08.phoenix.model;

/**
 * Represents a role member.
 * 
 * @author phyo
 */
public class RoleMember {
  private String userId;
  private String userName;
  private String roleId;
  private String roleName;

  /**
   * Get user ID.
   * 
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Set user ID.
   * 
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Get user name.
   * 
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Set user name.
   * 
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Get role ID.
   * 
   * @return the roleId
   */
  public String getRoleId() {
    return roleId;
  }

  /**
   * Set role ID.
   * 
   * @param roleId the roleId to set
   */
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  /**
   * Get role name.
   * 
   * @return the roleName
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * Set role name.
   * 
   * @param roleName the roleName to set
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
