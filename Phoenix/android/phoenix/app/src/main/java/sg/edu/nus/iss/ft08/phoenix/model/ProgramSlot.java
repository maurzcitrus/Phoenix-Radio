package sg.edu.nus.iss.ft08.phoenix.model;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by cmani on 21/9/2017.
 */

public class ProgramSlot   {

  private int id;
  private int programId;
  private String start;
  private String end;
  private String presenterId;
  private String programName;
  private String presenterName;
  private String producerName;
  private String producerId;
  private String updatedBy;
  private String startDate;
  private String startTime;
  private String endDate;
  private String endTime;
  private String dayName;

    public int getProgramId() {
    return programId;
  }

  public void setProgramId(int programId) {
    this.programId = programId;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }


  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }


  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }


  public String getProgramName() {
    return programName;
  }

  public void setProgramName(String programName) {
    this.programName = programName;
  }

  public String getPresenterName() {
    return presenterName;
  }

  public void setPresenterName(String presenterName) {
    this.presenterName = presenterName;
  }

  public String getProducerName() {
    return producerName;
  }

  public void setProducerName(String producerName) {
    this.producerName = producerName;
  }


  public String getPresenterId() {
    return presenterId;
  }

  public void setPresenterId(String presenterId) {
    this.presenterId = presenterId;
  }

  public String getProducerId() {
    return producerId;
  }

  public void setProducerId(String producerId) {
    this.producerId = producerId;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("id", getId());
    json.put("programId", getProgramId());
    json.put("presenterId", getPresenterId());
    json.put("producerId", getProducerId());
    json.put("start", getStart());
    json.put("end", getEnd());
    json.put("startDate", getStartDate());
    json.put("startTime", getStartTime());
    json.put("endDate", getEndDate());
    json.put("endTime", getEndTime());
    json.put("updatedBy", getUpdatedBy());
    return json;
  }

  public static ProgramSlot fromJson(JSONObject json) throws JSONException {
    ProgramSlot s = new ProgramSlot();

    if (json.has("id"))
      s.setId(json.getInt("id"));

    if (json.has("programId"))
      s.setProgramId(json.getInt("programId"));

    if (json.has("presenterId"))
      s.setPresenterId(json.getString("presenterId"));

    if (json.has("producerId"))
      s.setProducerId(json.getString("producerId"));

    if (json.has("programName"))
      s.setProgramName(json.getString("programName"));

    if (json.has("presenterName"))
      s.setPresenterName(json.getString("presenterName"));

    if (json.has("producerName"))
      s.setProducerName(json.getString("producerName"));

    if (json.has("start"))
      s.setStart(json.getString("start"));

    if (json.has("end"))
      s.setEnd(json.getString("end"));

    if (json.has("startDate"))
      s.setStartDate(json.getString("startDate"));

    if (json.has("startTime"))
      s.setStartTime(json.getString("startTime"));

    if (json.has("endDate"))
      s.setEndDate(json.getString("endDate"));

    if (json.has("endTime"))
      s.setEndTime(json.getString("endTime"));

    if (json.has("updatedBy"))
      s.setUpdatedBy(json.getString("updatedBy"));

    if (json.has("dayName"))
      s.setDayName(json.getString("dayName"));
    return s;
  }


  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getDayName() {
    return dayName;
  }

  public void setDayName(String dayName) {
    this.dayName = dayName;
  }
}