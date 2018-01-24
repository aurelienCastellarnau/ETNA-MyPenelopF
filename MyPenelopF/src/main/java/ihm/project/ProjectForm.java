package ihm.project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Observer.ContactListener;
import Observer.ProjectListener;
import Observer.ProjectObserver;
import classes.Contact;
import classes.Project;
import ihm.FormBuilder;
import ihm.contact.ContactForm;
import ihm.group.CreateGroup;
import utils.PenelopDevLogger;

public class ProjectForm extends JPanel implements ProjectObserver  {

	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = 7884244449386779509L;
	private static final PenelopDevLogger log = PenelopDevLogger.get();
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private JLabel title = new JLabel("Create New Contact");
	private JButton createButton = new JButton("Create");
	private JButton updateButton = new JButton("Update");
	private JPanel name = this._fb.getTextField("Name");
	private JPanel content = this._fb.getTextField("Content Description");
	// chiche: 
	private ArrayList<CreateGroup> groupsForm = new ArrayList<CreateGroup>();
	private final Collection<ContactListener> contactListeners = new ArrayList<ContactListener>();
	
	public ProjectForm(JPanel pan) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.name);
		this.pan.add(this.content);
		
		final ProjectForm self = this;
		this.createButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Project p = new Project(
	     					self.getNameInput().getText(),
	     					self.getContentInput().getText()
	     				);
	     		self.triggerCreateProject(p);
	     	}
		});
		this.pan.add(this.createButton);
	}
	
	// View components accessors
	public JPanel getPan() {
		return this.pan;
	}
	public JTextField getNameInput() {
		return (JTextField)this.name.getComponent(1);
	}
	public JTextField getContentInput() {
		return (JTextField)this.content.getComponent(1);
	}
	
	public void addProjectListener(ProjectListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void removeProjectListener(ProjectListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void triggerProjectChange() {
		// TODO Auto-generated method stub
		
	}

	public void triggerShowUpdate(Project project) {
		// TODO Auto-generated method stub
		
	}

	public void triggerCreateProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	public void triggerUpdateProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	public void triggerDeleteProject(Project project) {
		// TODO Auto-generated method stub
		
	}

}
