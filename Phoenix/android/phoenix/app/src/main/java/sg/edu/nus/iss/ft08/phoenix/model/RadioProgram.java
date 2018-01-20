package sg.edu.nus.iss.ft08.phoenix.model;

import org.json.JSONException;
import org.json.JSONObject;

public class RadioProgram {

  private int id;
  private String name;
  private String description;
  private int duration;
  private String updatedBy;
  private int assignedCount;

  public int getAssignedCount() {
    return assignedCount;
  }

  public void setAssignedCount(int assignedCount) {
    this.assignedCount = assignedCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("id", getId());
    json.put("name", getName());
    json.put("description", getDescription());
    json.put("duration", getDuration());
    json.put("updatedBy", getUpdatedBy());
    return json;
  }

  public static RadioProgram fromJson(JSONObject json) throws JSONException {
    RadioProgram p = new RadioProgram();
    p.setId(json.getInt("id"));
    p.setName(json.getString("name"));
    p.setDescription(json.getString("description"));
    p.setDuration(json.getInt("duration"));
    p.setUpdatedBy(json.getString("updatedBy"));
    p.setAssignedCount(json.getInt("assignedCount"));
    return p;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof RadioProgram) {
      RadioProgram that = (RadioProgram) obj;
      return this.getId() == that.getId();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Integer.toString(id).hashCode();
  }
}
