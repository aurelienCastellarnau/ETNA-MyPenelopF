package ihm.contact;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Observer.ContactListener;
import Observer.ContactObserver;
import classes.Contact;
import classes.Group;
import classes.Project;
import controllers.GroupController;
import controllers.ProjectController;
import ihm.FormBuilder;
import utils.PenelopDevLogger;

public class ContactForm extends JPanel implements ContactObserver {

	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = 4029539107222146186L;
	private static final PenelopDevLogger log = PenelopDevLogger.get();
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private JLabel title = new JLabel("Create New Contact");
	private JButton createButton = new JButton("Create");
	private JButton updateButton = new JButton("Update");
	private JPanel email = this._fb.getTextField("Email");
	private JPanel surname = this._fb.getTextField("Surname");
	private JPanel name = this._fb.getTextField("Name");
	private JList pList = null;
	private JList gList = null;
	private final Collection<ContactListener> contactListeners = new ArrayList<ContactListener>();
	
	public ContactForm(JPanel pan) {
		GridLayout gl = new GridLayout(7, 4, 20, 20);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.setBorder(new EmptyBorder(100, 10, 10, 10));
		this.pan.add(this.title);
		this.pan.add(this.email);
		this.pan.add(this.surname);
		this.pan.add(this.name);
		final ContactForm self = this;
		this.createButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Contact c = new Contact(
	     					self.getEmailInput().getText(),
	     					self.getSurnameInput().getText(),
	     					self.getNameInput().getText()
	     				);
	     		self.triggerCreateContact(c);
	     	}
		});
		this.pan.add(this.createButton);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public ContactForm(JPanel pan, GroupController gCtrl, ProjectController pCtrl) {
		ArrayList<Project> p = pCtrl.getPDAO().get();
		ArrayList<Group> g = gCtrl.getGroupDAO().get();
		this.pList = new JList(p.toArray());
		this.gList = new JList(g.toArray());
		this.pList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.gList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.pList.setVisibleRowCount(3);
		this.gList.setVisibleRowCount(3);
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.email);
		this.pan.add(this.surname);
		this.pan.add(this.name);
		this.pan.add(new JScrollPane(this.pList));
		this.pan.add(new JScrollPane(this.gList));
		final ContactForm self = this;
		this.createButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Contact c = new Contact(
	     					self.getEmailInput().getText(),
	     					self.getSurnameInput().getText(),
	     					self.getNameInput().getText()
	     				);
	     		ArrayList<Project> projects = getProjectsFromList();
	     		ArrayList<Group> groups = getGroupsFromList();
	     		if (!projects.isEmpty()) {
	     			c.linkProjects(projects);
	     		}
	     		if (!groups.isEmpty()) {
	     			c.LinkGroups(groups);
	     		}
	     		self.triggerCreateContact(c);
	     	}
		});
		this.pan.add(this.createButton);
	}
	
	public ContactForm(JPanel pan, Contact contact) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.title = new JLabel("Update contact n°" + contact.getId().toString());
		this.pan.add(this.title);
		this.getEmailInput().setText(contact.getEmail());
		this.pan.add(this.email);
		this.getSurnameInput().setText(contact.getSurname());
		this.pan.add(this.surname);
		this.getNameInput().setText(contact.getName());
		this.pan.add(this.name);
		final ContactForm self = this;
		final int id = contact.getId();
		this.updateButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	    		final Contact c = new Contact(
	    					id,
	    					self.getEmailInput().getText(),
	    					self.getSurnameInput().getText(),
	    					self.getNameInput().getText()
	    				);
	     		self.triggerUpdateContact(c);
	     	}
		});
		this.pan.add(this.updateButton);
	}
	
	public JPanel getPan() {
		return this.pan;
	}
	public JLabel getTitle() {
		return this.title;
	}
	public JButton getCreateButton() {
		return this.createButton;
	}
	public JButton getUpdateButton() {
		return this.updateButton;
	}
	public JTextField getEmailInput() {
		return (JTextField)this.email.getComponent(1);
	}
	public JTextField getSurnameInput() {
		return (JTextField)this.surname.getComponent(1);
	}
	public JTextField getNameInput() {
		return (JTextField)this.name.getComponent(1);
	}
	// Observer subscribe, unsubscribe and notify
	public void addContactListener(ContactListener listener) {
		this.contactListeners.add(listener);
	}
	public void removeContactListener(ContactListener listener) {
		this.contactListeners.remove(listener);
	}
	public void triggerCreateContact(Contact contact) {
		for (ContactListener listener: this.contactListeners) {
			listener.CreateContactTriggered(contact);
		}
	}
	public void triggerUpdateContact(Contact contact) {
		System.out.println("UPDATE CONTACT");
		for (ContactListener listener: this.contactListeners) {
			listener.UpdateContactTriggered(contact);
		}
	}

	public void triggerContactChange() {
		// TODO Auto-generated method stub
		
	}

	public void triggerDeleteContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	public void triggerShowUpdate(Contact c) {
		// TODO Auto-generated method stub
		
	}
	
	ArrayList<Project> getProjectsFromList() {
		Object[] projects =  this.pList.getSelectedValues();
		ArrayList<Project> aProjects = new ArrayList<Project>();
		for (Object project: projects) {
			Project newProject = (Project)project;
			System.out.println(newProject.getName());
			aProjects.add(newProject);
		}
		return aProjects;
	}
	
	ArrayList<Group> getGroupsFromList() {
		Object[] groups =  this.gList.getSelectedValues();
		ArrayList<Group> aGroups = new ArrayList<Group>();
		for (Object group: groups) {
			Group newGroup = (Group)group;
			System.out.println(newGroup.getName());
			aGroups.add(newGroup);
		}
		return aGroups;
	}
}
