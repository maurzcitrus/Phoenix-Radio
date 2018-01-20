package sg.edu.nus.iss.ft08.phoenix.model;

import java.util.Date;
import sg.edu.nus.iss.ft08.phoenix.DateUtil;

/**
 * Represents a program slot.
 *
 * @author Karthik
 */
public class ProgramSlot {

  private int id;
  private Date start;
  private Date end;
  private int programId;
  private String presenterId;
  private String producerId;
  private String updatedBy;
  private String programName;
  private String presenterName;
  private String producerName;
  private String startDate;
  private String startTime;
  private String endDate;
  private String endTime;
  private String dayName;

  /**
   * Get program slot start date day name
   *
   * @return program slot start date day name as "Mon", "Fri" etc.
   */
  public String getDayName() {
    return dayName;
  }

  /**
   * Set program slot start date day name
   *
   * @param dayName the program slot start date day name to be set.
   */
  public void setDayName(String dayName) {
    this.dayName = dayName;
  }

  /**
   * Get program slot ID.
   *
   * @return program slot ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Set program slot ID.
   *
   * @param id the program slot ID to be set.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get program slot start date and time.
   *
   * @return program slot start date and time.
   */
  public Date getStart() {
    if (startDate != null && startTime != null) {
      start = DateUtil.toDate_from_yyyy_MM_dd_HH_mm(startDate, startTime);
    }

    return start;
  }

  /**
   * Set program slot start date and time.
   *
   * @param start start date and time to be set.
   */
  public void setStart(Date start) {
    this.start = start;
    this.startDate = DateUtil.toString_yyyy_MM_dd(start);
    this.startTime = DateUtil.toString_HH_mm(start);
    this.dayName = DateUtil.toString_dayName(start);
  }

  /**
   * Get program slot end date and time.
   *
   * @return program slot end date and time.
   */
  public Date getEnd() {
    if (endDate != null && endTime != null) {
      end = DateUtil.toDate_from_yyyy_MM_dd_HH_mm(endDate, endTime);
    }
    return end;
  }

  /**
   * Set program slot end date and time.
   *
   * @param end the end date time to be set.
   */
  public void setEnd(Date end) {
    this.end = end;
    this.endDate = DateUtil.toString_yyyy_MM_dd(end);
    this.endTime = DateUtil.toString_HH_mm(end);
  }

  /**
   * Get program slot radio program ID.
   *
   * @return program slot radio program ID.
   */
  public int getProgramId() {
    return programId;
  }

  /**
   * Set program slot radio program ID.
   *
   * @param programId the program ID to be set.
   */
  public void setProgramId(int programId) {
    this.programId = programId;
  }

  /**
   * Get program slot presenter user ID.
   *
   * @return program slot presenter user ID.
   */
  public String getPresenterId() {
    return presenterId;
  }

  /**
   * Set program slot presenter user ID.
   *
   * @param presenterId the presenter ID to be set.
   */
  public void setPresenterId(String presenterId) {
    this.presenterId = presenterId;
  }

  /**
   * Get program slot producer user ID.
   *
   * @return program slot producer user ID.
   */
  public String getProducerId() {
    return producerId;
  }

  /**
   * Set program slot producer user ID.
   *
   * @param producerId the producer ID to be set.
   */
  public void setProducerId(String producerId) {
    this.producerId = producerId;
  }

  /**
   * Get program slot updated by user ID.
   *
   * @return program slot updated by user ID.
   */
  public String getUpdatedBy() {
    return updatedBy;
  }

  /**
   * Set program slot updated by user ID.
   *
   * @param updatedBy the updated by user ID to be set.
   */
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  /**
   * Get program slot radio program name.
   *
   * @return program slot radio program name.
   */
  public String getProgramName() {
    return programName;
  }

  /**
   * Set program slot radio program name.
   *
   * @param programName the radio program name to be set.
   */
  public void setProgramName(String programName) {
    this.programName = programName;
  }

  /**
   * Get program slot presenter user name.
   *
   * @return program slot presenter user name.
   */
  public String getPresenterName() {
    return presenterName;
  }

  /**
   * Set program slot presenter user name.
   *
   * @param presenterName the presenter name to be set.
   */
  public void setPresenterName(String presenterName) {
    this.presenterName = presenterName;
  }

  /**
   * Get program slot producer user name.
   *
   * @return program slot producer user name.
   */
  public String getProducerName() {
    return producerName;
  }

  /**
   * Set program slot producer user name.
   *
   * @param producerName the producer name to be set.
   */
  public void setProducerName(String producerName) {
    this.producerName = producerName;
  }

  /**
   * Get the program slot start date
   *
   * @return the startDate.
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Set the program slot start date
   *
   * @param startDate the startDate to set.
   */
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  /**
   * Get the program slot start time
   *
   * @return the startTime.
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Set the program slot start time.
   *
   * @param startTime the startTime to set.
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * Get the program slot end date.
   *
   * @return the endDate.
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Set the program slot end date.
   *
   * @param endDate the endDate to set.
   */
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  /**
   * Get the program slot end time.
   *
   * @return the endTime.
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * Set program slot end time.
   *
   * @param endTime the endTime to set.
   */
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

}
