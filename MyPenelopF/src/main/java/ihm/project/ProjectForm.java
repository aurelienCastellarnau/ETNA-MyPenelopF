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
	private JPanel description = this._fb.getTextField("Content Description");
	// chiche: 
	private ArrayList<CreateGroup> groupsForm = new ArrayList<CreateGroup>();
	private final Collection<ProjectListener> projectListeners = new ArrayList<ProjectListener>();
	
	// create constructor
	public ProjectForm(JPanel pan) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.name);
		this.pan.add(this.description);
		final ProjectForm self = this;
		this.createButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Project p = new Project(
	     					self.getNameInput().getText(),
	     					self.getDescriptionInput().getText()
	     				);
	     		self.triggerCreateProject(p);
	     	}
		});
		this.pan.add(this.createButton);
	}
	
	// update Constructor
	public ProjectForm(JPanel pan, Project project) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.title = new JLabel("Update project n°" + project.getId().toString());
		this.pan.add(this.title);
		this.pan.add(this.name);
		this.getNameInput().setText(project.getName());
		this.pan.add(this.description);
		this.getDescriptionInput().setText(project.getDescription());
		final ProjectForm self = this;
		final Integer id = project.getId();
		this.updateButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Project p = new Project(
	     					id,
	     					self.getNameInput().getText(),
	     					self.getDescriptionInput().getText()
	     				);
	     		self.triggerUpdateProject(p);
	     	}
		});
		this.pan.add(this.updateButton);
	}
	
	// View components accessors
	public JPanel getPan() {
		return this.pan;
	}
	public JPanel getNamePanel() {
		return this.name;
	}
	public JTextField getNameInput() {
		return (JTextField)this.name.getComponent(1);
	}
	public JPanel getDescriptionPanel() {
		return this.description;
	}
	public JTextField getDescriptionInput() {
		return (JTextField)this.description.getComponent(1);
	}
	
	/**
	 * Oberver pattern,ProjectObserver implementation
	 */
	public void addProjectListener(ProjectListener listener) {
		if (!this.projectListeners.contains(listener)) {
			this.projectListeners.add(listener);
		}
	}

	public void removeProjectListener(ProjectListener listener) {
		if (this.projectListeners.contains(listener)) {
			this.projectListeners.remove(listener);
		}
	}

	public void triggerProjectChange() {
		// DAO working part
	}

	public void triggerShowUpdate(Project project) {
		for (ProjectListener listener: this.projectListeners) {
			listener.ShowUpdateTriggered(project);
		}
	}

	public void triggerCreateProject(Project project) {
		for (ProjectListener listener: this.projectListeners) {
			log._("Create project trigger from form");
			listener.CreateProjectTriggered(project);
		}
	}

	public void triggerUpdateProject(Project project) {
		for (ProjectListener listener: this.projectListeners) {
			log._("Update project trigger from form");
			listener.UpdateProjectTriggered(project);
		}
	}

	public void triggerDeleteProject(Project project) {
		// TODO Auto-generated method stub
		
	}

}
