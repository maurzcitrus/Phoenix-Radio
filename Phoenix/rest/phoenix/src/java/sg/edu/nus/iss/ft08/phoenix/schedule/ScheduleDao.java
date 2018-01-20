package sg.edu.nus.iss.ft08.phoenix.schedule;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;

/**
 * Defines methods to find program slots.
 *
 * @author Karthik
 */
public interface ScheduleDao {

  /**
   * Finds all program slots in the database.
   *
   * @return List containing zero or more program slots.
   */
  public List<ProgramSlot> findAll();

  /**
   * Finds a program slot that matches any of the attribute values in given
   * record. Currently, it supports program slot ID.
   *
   * @param record program slot ID to be found.
   * @return Program slot that match ID, or NULL.
   */
  public ProgramSlot findOne(ProgramSlot record);

  /**
   * Creates new program slot in database.
   *
   * @param newRecord new program slot to be created.
   * @return program slot that has been created.
   */
  public ProgramSlot create(ProgramSlot newRecord);

  /**
   * Updates program slot details.
   *
   * @param record program slot to be updated.
   * @return program slot that has been updated.
   */
  public ProgramSlot update(ProgramSlot record);

  /**
   * Deletes program slot based on ID.
   *
   * @param record program slot with ID to be deleted.
   */
  public void delete(ProgramSlot record);

  /**
   * Finds all program slots in the database based on the start and end
   * attribute specified in the given record.
   *
   * @param record program slot with start and end date time range.
   * @return List containing zero or more program slots.
   */
  public List<ProgramSlot> findRange(ProgramSlot record);

  /**
   * Finds all program slots in the database that overlaps based on the start
   * and end attribute specified in the given record.
   *
   * @param record program slot with start and end date time range.
   * @return List containing zero or more program slots.
   */
  public List<ProgramSlot> findOverlap(ProgramSlot record);

  /**
   * Finds all program slots in the database based on the programId attribute
   * specified in the given record. Finds all the program slots that contains
   * the radio program mentioned.
   *
   * @param record program slot with program ID (radio program) to be found.
   * @return List containing zero or more program slots.
   */
  public List<ProgramSlot> findAllSchedulesRadioProgram(ProgramSlot record);

  /**
   * Finds all program slots in the database assigned to the user mentioned in
   * the presenterID attribute. Finds all the program slots that contains the
   * specific user either in presenter or producer attributes.
   *
   * @param record program slot with presenter ID (user) to be found.
   * @return List containing zero or more program slots.
   */
  public List<ProgramSlot> findAllSchedulesUser(ProgramSlot record);
  
  /**
   * Copies all the program slot from the source start date and end date
   * specified inside the program slot to the target date based on interval days
   * 
   * @param record program slot record with start and end date
   * @param interval target number of days
   */
  public void copy(ProgramSlot record, int interval);

}
