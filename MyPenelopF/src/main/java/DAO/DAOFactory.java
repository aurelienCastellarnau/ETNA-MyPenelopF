package DAO;

import DataInterface.DataInterface;
import classes.Contact;
import classes.Project;

public class DAOFactory {
	  
	  public static DAO<Contact> getContactDAO(DataInterface di) {
		  // ContactDAO must implement ContactDAOReceipe
		  ContactDAOReceipe cDAO = new ContactDAO(di);
		  return (ContactDAO)cDAO;
	  }
	  
	  public static DAO<Project> getProjectDAO(DataInterface di) {
		  // ProjectDAO must implement ProjectDAOReceipe
		  ProjectDAOReceipe pDAO = new ProjectDAO(di);
		  return (ProjectDAO)pDAO;
	  }
}
