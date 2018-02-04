package ihm.group;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Observer.ContactListener;
import Observer.GroupListener;
import Observer.GroupObserver;
import classes.Contact;
import classes.Group;
import classes.Project;
import controllers.ContactController;
import controllers.GroupController;
import controllers.ProjectController;
import ihm.FormBuilder;
import ihm.contact.ContactForm;

public class FormGroup extends JPanel implements GroupObserver{
	
	private static final long serialVersionUID = 1L;
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private JLabel title = new JLabel("Create New Group");
	private JButton submit = new JButton("Create");
	private JPanel name = this._fb.getTextField("Name");
	private JButton updateButton = new JButton("Update");
	private JList pList = null;
	private JList uList = null;
	private final Collection<GroupListener> formGroupListeners = new ArrayList<GroupListener>();
	
	public FormGroup(JPanel pan, GroupController gCtrl, ProjectController pCtrl, ContactController cCtrl ) {
		ArrayList<Project> p = pCtrl.getPDAO().get();
		ArrayList<Contact> c = cCtrl.getContactDAO().get();
		this.pList = new JList(p.toArray());
		this.uList = new JList(c.toArray());
		this.pList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.uList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.pList.setVisibleRowCount(3);
		this.uList.setVisibleRowCount(3);
		GridLayout gl = new GridLayout(7, 4, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.name);
		this.pan.add(new JScrollPane(this.pList));
		this.pan.add(new JScrollPane(this.uList));
		final FormGroup self = this;
		this.submit.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Group g = new Group(self.getNameInput().getText());
	     		ArrayList<Integer> pIds = formatPids();
	     		ArrayList<Integer> uIds = formatUids();
	    		
	     		if (!pIds.isEmpty()) {
	     			g.setPids(pIds);
	     		}
	     		if (!uIds.isEmpty()) {
	     			g.setUids(uIds);
	     		}
	     		self.triggerCreateGroup(g);
	     	}
		});
		this.pan.add(this.submit);
	}
	
	public FormGroup(JPanel jPanel, Group group) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = jPanel;
		this.pan.setLayout(gl);
		this.title = new JLabel("Update contact n°" + group.getId().toString());
		this.pan.add(this.title);
		this.getNameInput().setText(group.getName());
		this.pan.add(this.name);
		final FormGroup self = this;
		final int id = group.getId();
		this.updateButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	    		final Group g = new Group(
	    					id,
	    					self.getNameInput().getText()
	    				);
	     		self.triggerUpdateGroup(g);
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
	public JButton getSubmit() {
		return this.submit;
	}
	// Observer subscribe, unsubscribe and notify
	public void addGroupListener(GroupListener listener) {
		this.formGroupListeners.add(listener);
	}
	public void removeFormGroupListener(CreateGroupListener listener) {
		this.formGroupListeners.remove(listener);
	}
	
	public JTextField getNameInput() {
		return (JTextField)this.name.getComponent(1);
	}
	
	ArrayList<Integer> formatPids() {
		Object[] projects =  this.pList.getSelectedValues();
		ArrayList<Integer> pIds = new ArrayList<Integer>();
		for (Object project: projects) {
			Project newProject = (Project)project;
			System.out.println(newProject.getName());
			pIds.add(newProject.getId());
		}
		return pIds;
	}
	
	ArrayList<Integer> formatUids() {
		Object[] users =  this.uList.getSelectedValues();
		ArrayList<Integer> uIds = new ArrayList<Integer>();
		for (Object user: users) {
			Contact newUser = (Contact)user;
			System.out.println(newUser.getName());
			uIds.add(newUser.getId());
		}
		return uIds;
	}
	
	public void triggerCreateGroup(Group group) {
		System.out.println("trigger the create Group !");
		for (GroupListener listener: this.formGroupListeners) {
			listener.CreateGroupTriggered(group);
		}
	}

	public void removeGroupListener(GroupListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void triggerGroupChange() {
		// TODO Auto-generated method stub
		
	}

	public void triggerUpdateGroup(Group group) {
		System.out.println("UPDATE Group");
		for (GroupListener listener: this.formGroupListeners) {
			listener.UpdateGroupTriggered(group);
		}
	}

	public void triggerDeleteGroup(Group group) {
		// TODO Auto-generated method stub
		
	}

	public void triggerShowUpdate(Group group) {
		// TODO Auto-generated method stub
		
	}
}
