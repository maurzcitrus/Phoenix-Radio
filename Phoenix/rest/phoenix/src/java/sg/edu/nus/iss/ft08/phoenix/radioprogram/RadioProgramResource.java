package sg.edu.nus.iss.ft08.phoenix.radioprogram;

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
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

/**
 * REST API for managing radio programs.
 *
 * @author phyo
 */
@Path("/")
public class RadioProgramResource {

  private RadioProgramDao dao;

  public RadioProgramResource() {
    dao = DaoFactory.getRadioProgramDao();
  }

  /**
   * Finds all radio programs.
   *
   * @return JSON array containing zero or more radio programs.
   */
  @Path("radioprograms")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RadioProgram> findAll() {
    List<RadioProgram> programs;

    programs = dao.findAll();

    return programs;
  }

  /**
   * Finds one radio program based on given ID.
   *
   * @param id radio program ID.
   * @return JSONObject containing radio program detail, or NULL.
   */
  @Path("radioprogram/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RadioProgram findOne(@PathParam("id") int id) {
    RadioProgram program;

    RadioProgram example = new RadioProgram();
    example.setId(id);

    program = dao.findOne(example);

    return program;
  }

  /**
   * Creates one radio program in database.
   *
   * @param radioProgram radio program to be created.
   * @return created radio program.
   */
  @Path("radioprogram")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public RadioProgram create(RadioProgram radioProgram) {
    RadioProgram result = dao.create(radioProgram);
    return result;
  }

  /**
   * Updates given radio program in database.
   *
   * @param radioProgram radio program to be updated.
   * @return updated radio program
   */
  @Path("radioprogram")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public RadioProgram update(RadioProgram radioProgram) {
    RadioProgram result = dao.update(radioProgram);
    return result;
  }

  /**
   * Deletes a radio program based on given ID.
   *
   * @param id radio program ID.
   */
  @Path("radioprogram/{id}")
  @DELETE
  public void delete(@PathParam("id") int id) {

    RadioProgram example = new RadioProgram();
    example.setId(id);

    dao.delete(example);

  }

}
