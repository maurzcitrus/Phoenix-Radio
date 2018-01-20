package sg.edu.nus.iss.ft08.phoenix.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.Constants;

public class User {

  private String userId;
  private String fullName;
  private String password;
  private List<Role> roles;
  private int assignedProgramSlot;
  private boolean authenticated;

  public List<Role> getRoles() {
    if (roles == null) {
      roles = new ArrayList<>();
    }
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAssignedProgramSlot() {
    return assignedProgramSlot;
  }

  public void setAssignedProgramSlot(int assignedProgramSlot) {
    this.assignedProgramSlot = assignedProgramSlot;
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("userId", getUserId());
    json.put("fullName", getFullName());
    json.put("password", getPassword());
    json.put("authenticated", isAuthenticated());

    return json;
  }

  public JSONObject toJsonCRUD() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("userId", getUserId());
    json.put("fullName", getFullName());
    json.put("password", getPassword());
    json.put("authenticated", isAuthenticated());
    json.put("programSlots", getAssignedProgramSlot());

    JSONArray roles = new JSONArray();
    for (Role r : getRoles()) {
      roles.put(r.toJson());
    }
    json.put("roles", roles);
    return json;
  }

  public static User fromJson(JSONObject json) throws JSONException {
    User u = new User();
    u.setUserId(json.getString("userId"));
    u.setFullName(json.getString("fullName"));
    u.setPassword(json.getString("password"));
    u.setAuthenticated(json.getBoolean("authenticated"));

    if (json.has("programSlots"))
      u.setAssignedProgramSlot(json.getInt("programSlots"));

    JSONArray array = json.optJSONArray("roles");
    List<Role> roles = new ArrayList<>();
    for (int i = 0; i < array.length(); i++) {
      JSONObject o = array.getJSONObject(i);
      Role r = Role.fromJson(o);
      roles.add(r);
    }
    u.setRoles(roles);
    return u;
  }

  public boolean isAdmin() {
    return hasRole(Constants.ROLE_ADMIN);
  }

  public boolean isManager() {
    return hasRole(Constants.ROLE_MANAGER);
  }

  public boolean isPresenter() {
    return hasRole(Constants.ROLE_PRESENTER);
  }

  public boolean isProducer() {
    return hasRole(Constants.ROLE_PRODUCER);
  }

  private boolean hasRole(String roleId) {
    if (getRoles().size() == 0)
      return false;

    for (Role r : roles) {
      if (roleId.equalsIgnoreCase(r.getRole_id()))
        return true;
    }

    return false;
  }
}
