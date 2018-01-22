package DAO;

import DataInterface.DataInterface;
import classes.Contact;

public class DAOFactory {
	  
	  public static DAO<Contact> getContactDAO(DataInterface di) {
		  return new ContactDAO(di);
	  }
}
