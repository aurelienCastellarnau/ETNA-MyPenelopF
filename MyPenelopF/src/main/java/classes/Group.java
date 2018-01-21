package classes;

import java.util.ArrayList;

/**
 * 
 * @author jean
 *
 */
public class Group {
	
	private Integer id;
	
	private String name;
	
	private ArrayList<Contact> users;
	
	private ArrayList<Project> projects;
	
	private ArrayList<Message> messages;
	
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Contact> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<Contact> users) {
		this.users = users;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ArrayList<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}
	
	public ArrayList<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public void addUser(Contact user) {
		if (!this.users.contains(user))
			this.users.add(user);
	}
	
	public void deleteUser(Contact user) {
		if (this.users.contains(user)) {
			this.users.remove(user);
		}
	}
	
	public void addProject(Project project) {
		if (!this.projects.contains(project))
			this.projects.add(project);
	}
	
	public void deleteProject(Project project) {
		if (this.projects.contains(project)) {
			this.projects.remove(project);
		}
	}
	
	public void addMessage(Message message) {
		if (!this.messages.contains(message))
			this.messages.add(message);
	}
	
	public void deleteMessage(Message message) {
		if (this.messages.contains(message)) {
			this.messages.remove(message);
		}
	}
	
}
