package sg.edu.nus.iss.ft08.phoenix.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Summary {
  private int totalUsers;
  private int totalProgramSlots;
  private int totalRadioPrograms;


  public int getTotalUsers() {
    return totalUsers;
  }

  public void setTotalUsers(int totalUsers) {
    this.totalUsers = totalUsers;
  }

  public int getTotalProgramSlots() {
    return totalProgramSlots;
  }

  public void setTotalProgramSlots(int totalProgramSlots) {
    this.totalProgramSlots = totalProgramSlots;
  }

  public int getTotalRadioPrograms() {
    return totalRadioPrograms;
  }

  public void setTotalRadioPrograms(int totalRadioPrograms) {
    this.totalRadioPrograms = totalRadioPrograms;
  }

  public static Summary fromJson(JSONObject json) throws JSONException {
    Summary s = new Summary();
    s.setTotalUsers(json.getInt("totalUsers"));
    s.setTotalProgramSlots(json.getInt("totalProgramSlots"));
    s.setTotalRadioPrograms(json.getInt("totalRadioPrograms"));
    return s;
  }
}
