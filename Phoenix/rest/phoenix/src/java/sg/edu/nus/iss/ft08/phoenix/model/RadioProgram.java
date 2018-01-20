package sg.edu.nus.iss.ft08.phoenix.model;

/**
 * Represents a radio program.
 *
 * @author phyo
 */
public class RadioProgram {
  private int id;
  private String name;
  private String description;
  private int duration;
  private String updatedBy;
  private int assignedCount;

    /**
     * Get count of program slots that contains this radio program.
     * 
     * @return the assigned program slot count.
     */
    public int getAssignedCount() {
        return assignedCount;
    }

    /**
     * Set count of program slots that contains this radio program.
     * 
     * @param assignedCount the assigned program slots count to be set.
     */
    public void setAssignedCount(int assignedCount) {
        this.assignedCount = assignedCount;
    }


  /**
   * Get radio program name.
   * 
   * @return the radio program name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set radio program name.
   * 
   * @param name the radio program name to be set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get radio program description.
   * 
   * @return radio program description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set radio program description.
   *
   * @param description the radio program description to be set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get radio program ID.  This is auto generated by database.
   *
   * @return radio program ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Set radio program ID.
   *
   * @param id the radio program ID to be set.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get radio program duration in minutes.  Example, 30 minutes.
   *
   * @return radio program duration.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Set radio program duration in minutes.  Example, 30 minutes.
   * 
   * @param duration the radio program duration to be set.
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Get user ID who last updated this record.
   * 
   * @return updated by user ID.
   */
  public String getUpdatedBy() {
    return updatedBy;
  }

  /**
   * Set user ID who last updated this record.
   * 
   * @param updatedBy the updated by user ID to be set.
   */
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }
  
}