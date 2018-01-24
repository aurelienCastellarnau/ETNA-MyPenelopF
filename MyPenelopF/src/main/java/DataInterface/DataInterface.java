package DataInterface;

import java.util.ArrayList;

import classes.Contact;
import classes.Group;

public interface DataInterface {
	public ArrayList<Contact>readContacts();
	public ArrayList<Group>readGroups();
	public void writeContacts(ArrayList<Contact> users);
	public void writeGroups(ArrayList<Group> groups);
}
