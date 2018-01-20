package sg.edu.nus.iss.ft08.phoenix.user;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.ft08.phoenix.DaoFactory;
import sg.edu.nus.iss.ft08.phoenix.model.RoleMember;

/**
 * REST API for finding presenters, producers, admins, and managers.
 * 
 * @author phyo
 */
@Path("/")
public class RoleMemberResource {
  
  private RoleMemberDao dao;
  
  public RoleMemberResource() {
    dao = DaoFactory.getRoleMemberDao();
  }
  
  /**
   * Finds all presenters.
   * 
   * @return JSON array containing zero or more presenters .
   */
  @Path("presenters")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findPresenters(){
    List<RoleMember> presenters;
    
    presenters = dao.findAll(RoleIds.PRESENTER); 
    return presenters;
  }
  
  /**
   * Finds all producers.
   * 
   * @return JSON array containing zero or more producers.
   */
  @Path("producers")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findProducers(){
    List<RoleMember> producers;
    
    producers = dao.findAll(RoleIds.PRODUCER); 
    return producers;
  }
  
  /**
   * Finds all system administrators (aka) admins.
   * 
   * @return JSON array containing zero or more admins.
   */
  @Path("admins")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findAdmins(){
    List<RoleMember> admins;
    
    admins = dao.findAll(RoleIds.ADMIN); 
    return admins;
  }
  
  /**
   * Finds all managers.
   * 
   * @return JSON array containing zero or more managers.
   */
  @Path("managers")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RoleMember> findManagers(){
    List<RoleMember> managers;
    
    managers = dao.findAll(RoleIds.MANAGER); 
    return managers;
  }
}
