package DAO;

import java.util.ArrayList;

import classes.Contact;
import classes.Group;

/**
 * 
 * @author aurelien
 * Permet de définir ce que DOIT implémenter la classe ContactDAO
 * Dans le cas d'une refonte, la classe abstraite DAO et cette interface
 * décrivent comment doit se comporter ContactDAO. 
 */
public interface ContactDAOReceipe {
	// retrieve groups where c.id appear
	// don't use Contact.gIds but Group.uIds
	public ArrayList<Group> getGroups(Contact c);
	// Same as getGroups but for an ArrayList
	public void buildContactsGroups(ArrayList<Contact> contacts);
}
