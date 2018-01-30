/**
 * 
 */
package classes;

import java.util.ArrayList;

import DAO.ContactDAO;
import DataInterface.FileSystemManager;
import classes.Contact;
import classes.Group;
import classes.Msg;

/**
 * @author aurelien
 *
 */
public class Contact extends Item {
	
	// contact id
	private Integer id;

	// contact email
	private String email;
	
	// contact surname
	private String surname;
	
	// contact name
	private String name;
	
	
	// contact groups
	private transient ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Integer> gIds = new ArrayList<Integer>();
	
	
	// contact projects
	private transient ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<Integer> pIds = new ArrayList<Integer>();

	// Database auto-increment id simulation, must be enhanced by a "retrieveLastId" method
	static private int autoincrement = 0;
	static private int increment() {
		return ++Contact.autoincrement;
	}
	private int lastId() {
		ArrayList<Contact> users = ContactDAO.getInstance(FileSystemManager.get()).get();
		int id = 0;
		if (users != null) {
			for (int iterator = 0; iterator < users.size(); iterator++) {
				Contact c = users.get(iterator);
				if (c.getId() > id)
					id = c.getId();
				}
		}
		return id;
	}
	// default constructor
	public Contact() { super(); }
	// surcharged minimal constructor
	public Contact(String email, String surname, String name) {
		super();
		Contact.autoincrement = this.lastId();
		this.id = Contact.increment();
		this.email = email;
		this.name = name;
		this.surname = surname;
	}
	// surcharged 'existing user' constructor
	public Contact(int id, String email, String surname, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		// Logic to retrieve groups is in ContactDAO
	}
	// surcharged 'no-project' constructor
	public Contact(String email, String surname, String name, ArrayList<Group> groups, ArrayList<Msg> messages) {
		super(messages);
		this.id = Contact.increment();
		this.email = email;
		this.surname = surname;
		this.name = name;
		this.setGroups(groups);
		this.setMessages(messages);
	}
	// surcharged 'full' constructor
	public Contact(String email, String surname, String name, ArrayList<Project>projects, ArrayList<Group> groups, ArrayList<Msg> messages) {
		super(messages);
		this.id = Contact.increment();
		this.email = email;
		this.surname = surname;
		this.name = name;
		this.setProjects(projects);
		this.setGroups(groups);
		this.setMessages(messages);
	}
	
	// accessors
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return this.email;
	}
	public String getSurname() {
		return this.surname;
	}
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Group> getGroups() {
		return this.groups;
	}
	public ArrayList<Integer> getGIds() {
		return this.gIds;
	}
	
	
	public ArrayList<Project> getProjects() {
		return this.projects;
	}
	public ArrayList<Integer> getPIds() {
		return this.pIds;
	}
	
	// mutators
	public void setId(Integer id) {
		this.id = id;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
		for (int iterator = 0; iterator < this.groups.size(); iterator++) {
			this.gIds.add(this.groups.get(iterator).getId());
		}
	}
	public void addGroup(Group g) {
		if (!this.groups.contains(g)) {
			this.groups.add(g);
			this.gIds.add(g.getId());
		}
	}
	public void deleteGroup(Group g) {
		if (this.groups.contains(g)) {
			this.groups.remove(g);
			this.gIds.remove(g.getId());
		}
	}
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
		for (int iterator = 0; iterator < this.projects.size(); iterator++) {
			this.pIds.add(this.projects.get(iterator).getId());
		}
	}
	public void addProject(Project p) {
		if (!this.projects.contains(p)) {
			this.projects.add(p);
			this.pIds.add(p.getId());
		}
	}
	public void deleteProject(Project p) {
		if (this.projects.contains(p)) {
			this.projects.remove(p);
			this.pIds.remove(p.getId());
		}
	}
}
