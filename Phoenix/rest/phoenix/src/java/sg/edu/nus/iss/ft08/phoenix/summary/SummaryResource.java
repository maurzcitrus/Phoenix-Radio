package sg.edu.nus.iss.ft08.phoenix.summary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.ft08.phoenix.DaoFactory;
import sg.edu.nus.iss.ft08.phoenix.model.Summary;

/**
 * REST API for Summary related operations.
 *
 * @author phyo
 */
@Path("/")
public class SummaryResource {

  private SummaryDao dao;

  public SummaryResource() {
    dao = DaoFactory.getSummaryDao();
  }

  /**
   * Finds the total count of program slots, users, radio programs.
   *
   * @return total count of program slots, users, radio programs.
   */
  @Path("summary")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Summary findOne() {
    Summary summary = dao.findOne();

    return summary;
  }

}
