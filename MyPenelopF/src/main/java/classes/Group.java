package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kumatetsu
 *
 */
public class Group {
	
	private Integer id;
	
	private String name;
	
	private transient ArrayList<Contact> users = new ArrayList<Contact>();
	private List<Integer>uIds = new ArrayList<Integer>();
	
	private transient ArrayList<Project> projects = new ArrayList<Project>();
	private List<Integer>pIds = new ArrayList<Integer>();
	
	private transient ArrayList<Message> messages = new ArrayList<Message>();
	private List<Integer>mIds = new ArrayList<Integer>();
	
	// Database auto-increment id simulation, must be enhanced by a "retrieveLastId" method
	static private int autoincrement = 0;
	static private int increment() {
		return ++Group.autoincrement;
	}
	
	//default constructor
	public Group() {}
	//Surcharged minimal constructor
	public Group(String name) {
		this.id = Group.increment();
		this.name = name;
	}
	//Surchaged complete constructor
	public Group(String name, ArrayList<Contact> users) {
		this.id = Group.increment();
		this.name = name;
		this.users = users;
	}

	// Accessors
	public Integer getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Contact> getUsers() {
		return this.users;
	}
	public List<Integer> getUIds() {
		return this.uIds;
	}
	
	public ArrayList<Project> getProjects() {
		return this.projects;
	}
	public List<Integer> getPIds() {
		return this.pIds;
	}


	public ArrayList<Message> getMessages() {
		return this.messages;
	}
	public List<Integer> getMIds() {
		return this.mIds;
	}
	
	// Mutators
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsers(ArrayList<Contact> users) {
		this.users = users;
		for (int iterator = 0; iterator < this.users.size(); iterator++) {
			this.uIds.add(this.users.get(iterator).getId());
		}
	}
	public void addUser(Contact user) {
		if (!this.users.contains(user)) {
			this.users.add(user);
			this.uIds.add(user.getId());
		}
	}
	public void deleteUser(Contact user) {
		if (this.users.contains(user)) {
			this.users.remove(user);
			this.uIds.remove(user.getId());
		}
	}
	
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
		for (int iterator = 0; iterator < this.projects.size(); iterator++) {
			this.pIds.add(this.projects.get(iterator).getId());
		}
	}
	public void addProject(Project project) {
		if (!this.projects.contains(project)) {
			this.projects.add(project);
			this.pIds.add(project.getId());
		}
	}
	public void deleteProject(Project project) {
		if (this.projects.contains(project)) {
			this.projects.remove(project);
			this.pIds.remove(project.getId());
		}
	}
	
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
		for (int iterator = 0; iterator < this.messages.size(); iterator++) {
			this.mIds.add(this.messages.get(iterator).getId());
		}
	}
	public void addMessage(Message message) {
		if (!this.messages.contains(message)) {
			this.messages.add(message);
			this.mIds.add(message.getId());
		}
	}
	public void deleteMessage(Message message) {
		if (this.messages.contains(message)) {
			this.messages.remove(message);
			this.mIds.remove(message.getId());
		}
	}
}
