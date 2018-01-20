package classes;

import java.util.ArrayList;

public class Group {
	
	private Integer id;
	
	private String name;
	
	private ArrayList<Contact> users;
	
	private ArrayList<Project> projects;
	
	// Database auto-increment id simulation, must be enhanced by a "retrieveLastId" method
	static private int autoincrement = 0;
	static private int increment() {
		return ++Group.autoincrement;
	}
	
	//default constructor
	public Group() {}
	
	//Surcharged minimal constructor
	public Group(String name) {
		this.setId(Group.increment());
	}
	
	public Group(String name, ArrayList<Contact> users) {
		this.setId(Group.increment());
		this.setName(name);
		this.setUsers(users); 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Contact> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Contact> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
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
	
}
