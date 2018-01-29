package DAO;

import DataInterface.DataInterface;
import classes.Contact;

public class DAOFactory {
	  
	  public static DAO<Contact> getContactDAO(DataInterface di) {
		  // ContactDAO must implement ContactDAOReceipe
		  ContactDAOReceipe cDAO = new ContactDAO(di);
		  return (ContactDAO)cDAO;
	  }
}
