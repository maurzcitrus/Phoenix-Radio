package sg.edu.nus.iss.ft08.phoenix.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.ft08.phoenix.DaoFactory;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;

/**
 * REST API for managing program slots.
 *
 * @author Karthik
 */
@Path("/")
public class ScheduleResource {

  private ScheduleDao dao;

  public ScheduleResource() {
    dao = DaoFactory.getScheduleDao();
  }

  /**
   * Finds all program slots.
   *
   * @return JSON array containing zero or more program slots.
   */
  @Path("schedules")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProgramSlot> findAll() {
    List<ProgramSlot> schedules;

    schedules = dao.findAll();

    return schedules;
  }

  /**
   * Finds all program slots for the given week number. Based on URS, Week start
   * will be - Monday 00:00:00 Week end will be - Sunday 23:59:59
   *
   * @param weekNumber week number
   * @return JSON array containing zero or more program slots.
   */
  @Path("schedules/week/{weekNumber}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProgramSlot> findWeek(@PathParam("weekNumber") int weekNumber) {

    Calendar c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    c.set(Calendar.WEEK_OF_YEAR, weekNumber);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);

    Date startDate = c.getTime();

    c.add(Calendar.DATE, 6); // adding 6 days to Monday gives us Sunday
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);

    Date endDate = c.getTime();

    List<ProgramSlot> schedules;
    ProgramSlot example = new ProgramSlot();
    example.setStart(startDate);
    example.setEnd(endDate);

    schedules = dao.findRange(example);

    return schedules;
  }

  /**
   * Finds all the program slots having the specified radio program
   *
   * @param programId
   * @return JSON array containing zero or more program slots.
   */
  @Path("schedules/radioprogram/{programId}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProgramSlot> findAllSchedulesRadioProgram(@PathParam("programId") int programId) {

    List<ProgramSlot> schedules;
    ProgramSlot example = new ProgramSlot();
    example.setProgramId(programId);

    schedules = dao.findAllSchedulesRadioProgram(example);

    return schedules;
  }

  /**
   * Finds all the program slots having the specified radio program
   *
   * @param programId
   * @return JSON array containing zero or more program slots.
   */
  @Path("schedules/user/{userId}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProgramSlot> findAllSchedulesUser(@PathParam("userId") String userId) {

    List<ProgramSlot> schedules;
    ProgramSlot example = new ProgramSlot();
    example.setPresenterId(userId);

    schedules = dao.findAllSchedulesUser(example);

    return schedules;
  }

  /**
   * Finds one program slot based on given ID.
   *
   * @param id
   * @return JSONObject containing program slot detail, or NULL.
   */
  @Path("schedule/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ProgramSlot findOne(@PathParam("id") int id) {

    ProgramSlot example = new ProgramSlot();
    example.setId(id);

    ProgramSlot result;
    result = dao.findOne(example);

    return result;
  }

  /**
   * Creates one program slot in database.
   *
   * @param programSlot
   * @return created program slot.
   */
  @Path("schedule")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ProgramSlot create(ProgramSlot programSlot) {
    ProgramSlot result = dao.create(programSlot);
    return result;
  }

  /**
   * Updates given program slot in database.
   *
   * @param programSlot
   * @return updated program slot.
   */
  @Path("schedule")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ProgramSlot update(ProgramSlot programSlot) {
    ProgramSlot result = dao.update(programSlot);
    return result;
  }

  /**
   * Finds all program slots for that overlaps the given program slot.
   *
   * @param programSlot
   * @return JSON array containing zero or more program slots.
   */
  @Path("schedule/overlap")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProgramSlot> overlap(ProgramSlot programSlot) {
    List<ProgramSlot> schedules = dao.findOverlap(programSlot);
    return schedules;
  }

  /**
   * Deletes a program slot based on given ID.
   *
   * @param id
   */
  @Path("schedule/{id}")
  @DELETE
  public void delete(@PathParam("id") int id) {

    ProgramSlot example = new ProgramSlot();
    example.setId(id);

    dao.delete(example);

  }

  /**
   * Copies all program slots from the source week number to target week number.
   * Based on URS, Week start will be - Monday 00:00:00 Week end will be -
   * Sunday 23:59:59
   *
   * @param sourceWeekNumber source week number to be copied
   * @param targetWeekNumber target week number to create copied slots.
   */
  @Path("schedules/copy/week/{sourceWeekNumber}/{targetWeekNumber}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public void copyWeek(
    @PathParam("sourceWeekNumber") int sourceWeekNumber,
    @PathParam("targetWeekNumber") int targetWeekNumber) {

    Calendar c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    c.set(Calendar.WEEK_OF_YEAR, sourceWeekNumber);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);

    Date startDate = c.getTime();

    c.add(Calendar.DATE, 6); // adding 6 days to Monday gives us Sunday
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);

    Date endDate = c.getTime();

    ProgramSlot example = new ProgramSlot();
    example.setStart(startDate);
    example.setEnd(endDate);

    int interval = (targetWeekNumber - sourceWeekNumber) * 7;

    dao.copy(example, interval);
  }

}
