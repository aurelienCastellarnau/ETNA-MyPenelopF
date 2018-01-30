package DataInterface;

import java.util.ArrayList;

import classes.Contact;
import classes.Group;
import classes.Project;

public interface DataInterface {
	public ArrayList<Contact>readContacts();
	public ArrayList<Project>readProjects();
	public ArrayList<Group>readGroups();
	public void writeContacts(ArrayList<Contact> users);
	public void writeProjects(ArrayList<Project> projects);
	public void writeGroups(ArrayList<Group> groups);
}
