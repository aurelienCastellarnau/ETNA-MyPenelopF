package classes;

import java.util.ArrayList;
import java.util.List;

public class Project extends Item {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private transient ArrayList<Group> groups = new ArrayList<Group>();
	private List<Integer> gIds = new ArrayList<Integer>();
	
	private transient ArrayList<Contact> users = new ArrayList<Contact>();
	private List<Integer> uIds = new ArrayList<Integer>();
	
	private transient ArrayList<Task> tasks = new ArrayList<Task>();
	private List<Integer> tIds = new ArrayList<Integer>();
	
	private transient ArrayList<Document> documents = new ArrayList<Document>();
	private List<Integer> dIds = new ArrayList<Integer>();
	
	static private int autoincrement = 0;
	static private int increment() {
		return ++Project.autoincrement;
	}
	
	public Project() {
		super();
		this.id = Project.increment(); 
	}
	
	public Project(String name, String description) {
		this.id = Project.increment();
		this.name = name;
		this.description = description;
	}
	public Project(Integer id, String name, String description) {
		this.id = id;
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
	public List<Integer> getGIds() {
		return this.gIds;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	public ArrayList<Contact> getContacts() {
		return this.users;
	}
	public List<Integer> getUIds() {
		return this.uIds;
	}
	

	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	public List<Integer> getTIds() {
		return this.tIds;
	}

	
	public ArrayList<Document> getDocuments() {
		return this.documents;
	}
	public List<Integer> getDIds() {
		return this.dIds;
	}

	// Contacts mutators
	public void setContacts(ArrayList<Contact> users) {
		this.users = users;
		for (int iterator = 0; iterator < this.users.size(); iterator ++) {
			this.uIds.add(this.users.get(iterator).getId());
		}
	}
	public void addContact(Contact c) {
		if (!this.users.contains(c)) {
			this.users.add(c);
			this.uIds.add(c.getId());
		}
	}
	public void deleteContact(Contact c) {
		if (this.users.contains(c)) {
			this.users.remove(c);
			this.uIds.remove(c.getId());
		}
	}
	
	// Documents mutators
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
		for (int iterator = 0; iterator < this.documents.size(); iterator++) {
			this.dIds.add(this.documents.get(iterator).getId());
		}
	}
	public void addDocument(Document d) {
		if (!this.documents.contains(d)) {
			this.documents.add(d);
			this.dIds.add(d.getId());		
		}
	}
	public void deleteDocument(Document d) {
		if (this.documents.contains(d)) {
			this.documents.remove(d);
			this.dIds.remove(d.getId());
		}
	}

	// Task mutators
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
		for (int iterator = 0; iterator < this.tasks.size(); iterator++) {
			this.tIds.add(this.tasks.get(iterator).getId());
		}
	}
	public void addTask(Task t) {
		if (!this.tasks.contains(t)) {
			this.tasks.add(t);
			this.tIds.add(t.getId());
		}
	}
	public void deleteTask(Task task) {
		if (this.tasks.contains(task)) {
			this.tasks.remove(task);
		}
	}
}
