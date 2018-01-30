package DAO;

import DataInterface.DataInterface;
import classes.Contact;
import classes.Group;
import classes.Project;

/**
 * La factory permet de creer une instance de DAO en lui obligeant d'implementer
 * les fonctions contenues dans l'interface receipe.
 * @author kumatetsu
 *
 */
public class DAOFactory {

	  public static DAO<Contact> getContactDAO(DataInterface di) {
		  // ContactDAO must implement ContactDAOReceipe
		  ContactDAOReceipe cDAO = new ContactDAO(di);
		  return (ContactDAO)cDAO;
	  }
	  
	  public static DAO<Group> getGroupDAO(DataInterface di) {
		  // GroupDAO must implement GroupDAOReceipe
		  GroupDAOReceipe gDAO = new GroupDAO(di);
		  return (GroupDAO)gDAO;
	  }

	  public static DAO<Project> getProjectDAO(DataInterface di) {
		  // ProjectDAO must implement ProjectDAOReceipe
		  ProjectDAOReceipe pDAO = new ProjectDAO(di);
		  return (ProjectDAO)pDAO;
	  }

}
