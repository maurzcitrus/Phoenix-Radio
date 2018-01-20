package sg.edu.nus.iss.ft08.phoenix.model;

/**
 * Represents PRMS summary such as total user, total program slot,
 * and total radio programs that exist in database.
 *
 * @author phyo
 */
public class Summary {
  private int totalUsers;
  private int totalProgramSlots;
  private int totalRadioPrograms;

  /**
   * Get total users.
   * 
   * @return total users count.
   */
  public int getTotalUsers() {
    return totalUsers;
  }

  /**
   * Set total users.
   * 
   * @param totalUsers the total users count to be set.
   */
  public void setTotalUsers(int totalUsers) {
    this.totalUsers = totalUsers;
  }

  /**
   * Get total program slots.
   * 
   * @return total count of program slots.
   */
  public int getTotalProgramSlots() {
    return totalProgramSlots;
  }

  /**
   * Set total program slots.
   *
   * @param totalProgramSlots the total program slots count to be set.
   */
  public void setTotalProgramSlots(int totalProgramSlots) {
    this.totalProgramSlots = totalProgramSlots;
  }

  /**
   * Get total radio programs.
   * 
   * @return total radio program count.
   */
  public int getTotalRadioPrograms() {
    return totalRadioPrograms;
  }

  /**
   * Set total radio programs
   * 
   * @param totalRadioPrograms the total radio programs count to be set.
   */
  public void setTotalRadioPrograms(int totalRadioPrograms) {
    this.totalRadioPrograms = totalRadioPrograms;
  }

}
