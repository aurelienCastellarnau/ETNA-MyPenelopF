package classes;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private ArrayList<Group> groups = new ArrayList<Group>();
	private List<Integer> gIds = new ArrayList<Integer>();
	
	private ArrayList<Contact> users = new ArrayList<Contact>();
	private List<Integer> uIds = new ArrayList<Integer>();
	
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private List<Integer> tIds = new ArrayList<Integer>();
	
	private ArrayList<Document> documents = new ArrayList<Document>();
	private List<Integer> dIds = new ArrayList<Integer>();
	
	private ArrayList<Message> messages = new ArrayList<Message>();
	private List<Integer> mIds = new ArrayList<Integer>();
	
	static private int autoincrement = 0;
	static private int increment() {
		return ++Project.autoincrement;
	}
	
	public Project() {
		this.id = Project.increment(); 
	}
	
	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public Project(String name, String description, ArrayList<Task> tasks) {
		this.name = name;
		this.description = description;
		this.tasks = tasks;
	}
	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	public ArrayList<Contact> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<Contact> users) {
		this.users = users;
	}

	public ArrayList<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	public ArrayList<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public void addTask(Task task) {
		if (!this.tasks.contains(task)) {
			this.tasks.add(task);
		}
	}
	
	public void delete(Task task) {
		if (this.tasks.contains(task)) {
			this.tasks.remove(task);
		}
	}
}
