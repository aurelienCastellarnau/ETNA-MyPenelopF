/**
 * 
 */
package classes;

import classes.Contact;
import classes.Dashboard;
import classes.Group;
import classes.Message;

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
	private Group[] groups;
	
	// contact messages
	private Message[] messages;
	
	private Project[] projects;

	// default constructor
	public Contact() {}
	// surcharged minimal constructor
	public Contact(String email, String surname, String name) {
		this.email = email;
		this.name = name;
		this.surname = surname;
	}
	// surcharged full constructor
	public Contact(String email, String surname, String name, Dashboard dashboard, Group[] groups, Message[] messages) {
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

	public Group[] getGroups() {
		return groups;
	}
	public void setGroups(Group[] groups) {
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
