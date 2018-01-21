/**
 * 
 */
package classes;

import java.io.IOException;
import java.util.ArrayList;

import classes.Contact;
import classes.Dashboard;
import classes.Group;
import classes.Message;
import utils.ContactUtils;
import utils.FileSystemManager;

/**
 * @author aurelien
 *
 */
public class Contact {
	
	// contact id
	private Integer id;

	// contact email
	private String email;
	
	// contact surname
	private String surname;
	
	// contact name
	private String name;
	
	// contact dashboard
	private Dashboard dashboard;
	
	// contact groups
	private ArrayList<Group> groups;
	
	// contact messages
	private Message[] messages;
	
	private Project[] projects;

	// Database auto-increment id simulation, must be enhanced by a "retrieveLastId" method
	static private int autoincrement = 0;
	static private int increment() {
		return ++Contact.autoincrement;
	}
	private int lastId() {
		try {
			ArrayList<Contact> users = ContactUtils.get().getContacts();
			int id = 0;
			for (Contact c: users) {
				if (c.getId() > id)
					id = c.getId();
			}
			return id;
		} catch (IOException e) {
			System.out.println("Exception throwed from Contact.lastId(): " + e.getMessage());
		}
		return 0;
	}
	// default constructor
	public Contact() {}
	// surcharged minimal constructor
	public Contact(String email, String surname, String name) {
		Contact.autoincrement = this.lastId();
		this.id = Contact.increment();
		this.email = email;
		this.name = name;
		this.surname = surname;
	}
	// surcharged 'existing user' constructor
	public Contact(int id, String email, String surname, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		// We need a "retrieve group and dashboard" logic
	}
	// surcharged full constructor
	public Contact(String email, String surname, String name, Dashboard dashboard, ArrayList<Group> groups, Message[] messages) {
		this.id = Contact.increment();
		this.email = email;
		this.surname = surname;
		this.name = name;
		this.dashboard = dashboard;
		this.setGroups(groups);
		this.setMessages(messages);
	}
	
	// accessors
	public String getEmail() {
		return this.email;
	}
	public String getSurname() {
		return this.surname;
	}
	public String getName() {
		return this.name;
	}
	public Dashboard getDashboard() {
		return this.dashboard;
	}
	
	// mutators

	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	public Message[] getMessages() {
		return messages;
	}
	public void setMessages(Message[] messages) {
		this.messages = messages;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Project[] getProjects() {
		return projects;
	}
	public void setProjects(Project[] projects) {
		this.projects = projects;
	}
}
