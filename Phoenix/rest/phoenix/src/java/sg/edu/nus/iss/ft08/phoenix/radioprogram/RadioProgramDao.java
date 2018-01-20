package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import java.util.List;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * Defines methods to find radio programs.
 *
 * @author phyo
 */
public interface RadioProgramDao {

  /**
   * Finds all radio programs in the database.
   *
   * @return List containing zero or more radio programs.
   */
  public List<RadioProgram> findAll();

  /**
   * Finds a radio program that matches any of the attribute values in given
   * example. Currently, it supports radio program ID and radio program name.
   *
   * @param example radio program with ID to be found.
   * @return Radio program that match ID or name, or NULL.
   */
  public RadioProgram findOne(RadioProgram example);

  /**
   * Creates new radio program in database.
   *
   * @param newRecord radio program object to be created.
   * @return Radio program that has been created.
   */
  public RadioProgram create(RadioProgram newRecord);

  /**
   * Updates radio program details.
   *
   * @param record radio program object to be updated.
   * @return Radio program that has been updated.
   */
  public RadioProgram update(RadioProgram record);

  /**
   * Deletes radio program based on ID.
   *
   * @param record radio program with ID value in it.
   */
  public void delete(RadioProgram record);

}
