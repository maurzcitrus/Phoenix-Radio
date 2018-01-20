package sg.edu.nus.iss.ft08.phoenix;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * REST API configuration class used by Java EE container
 * to initialize the REST APIs.  All APIs are published at path/api .
 * 
 * @author phyo
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method. It is automatically
   * populated with all resources defined in the project. If required, comment
   * out calling this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(sg.edu.nus.iss.ft08.phoenix.account.AccountResource.class);
    resources.add(
      sg.edu.nus.iss.ft08.phoenix.radioprogram.RadioProgramResource.class);
    resources.add(sg.edu.nus.iss.ft08.phoenix.schedule.ScheduleResource.class);
    resources.add(sg.edu.nus.iss.ft08.phoenix.summary.SummaryResource.class);
    resources.add(sg.edu.nus.iss.ft08.phoenix.user.RoleMemberResource.class);
    resources.add(sg.edu.nus.iss.ft08.phoenix.user.UserResource.class);
  }

}
