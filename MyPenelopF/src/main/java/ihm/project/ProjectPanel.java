package ihm.project;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Observer.ProjectListener;
import Observer.ProjectObserver;
import classes.Contact;
import classes.Group;
import classes.Msg;
import classes.Project;
import classes.Task;
import ihm.FormBuilder;
import ihm.contact.ContactPanel;
import ihm.group.GroupPanel;
import ihm.task.TaskPanel;
import utils.PenelopDevLogger;

public class ProjectPanel extends JPanel implements ProjectObserver {

	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = -1951975065883803116L;
	private static final PenelopDevLogger log = PenelopDevLogger.get();
    private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private GroupPanel groupPan;
	private ContactPanel contactPan;
	private JPanel contactNav;
	private TaskPanel taskPan;
	private JPanel taskNav;
    private CardLayout cl;
	private final Collection<ProjectListener> projectListeners = new ArrayList<ProjectListener>();

	public ProjectPanel(JPanel pan, CardLayout cl, ArrayList<Project> projects, boolean edit) {
        final ProjectPanel self = this;
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
		if (projects != null) {
			for (final Project project: projects)
			{
				// Card and Panel init + layout
				JPanel card = new JPanel();
				JPanel projectPan = new JPanel();
				GridLayout gl = new GridLayout(4, 3, 5, 5);
				projectPan.setLayout(gl);
				// project content
				JLabel tmp = new JLabel("Project N°" + project.getId() + " | Name: " + project.getName() + " | Description: " + project.getDescription());
				projectPan.add(tmp);
				if (edit) {
					log._("EDIT MODE FOR PROJECT");
					// Associations
					ArrayList<Group> groups = project.getGroups();
					ArrayList<Contact> contacts = project.getContacts();
					ArrayList<Task> tasks = project.getTasks();
			        this.taskPan = new TaskPanel(new JPanel(), new CardLayout(), tasks);
			        this.taskNav = this._fb.getNavPanel(this.taskPan.getCard(), this.taskPan.getPan());
			        projectPan.add(this.taskNav);
			        projectPan.add(this.taskPan.getPan());
			        this.groupPan = new GroupPanel(new JPanel(), new CardLayout(), groups);
			        projectPan.add(this.groupPan.getPan());
			        this.contactPan = new ContactPanel(new JPanel(), new CardLayout(), contacts, false);
			        this.contactNav = this._fb.getNavPanel(this.contactPan.getCard(), this.contactPan.getPan());
			        projectPan.add(this.contactNav);
			        projectPan.add(this.contactPan.getPan());
			    	// related messages
			        ArrayList<Msg> messages = project.getMessages();
			    	JPanel compiledMsgs = new JPanel();
			    	if (messages.size() > 0) {
			    		compiledMsgs.add(new JLabel("Message: "));
			    	}
			    	for (int iterator = 0; iterator < messages.size(); iterator++) {
			    		compiledMsgs.add(new JLabel(messages.get(iterator).getContent()));
			    	}
			    	projectPan.add(compiledMsgs);
					JButton del = new JButton("Delete");
					del.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							self.triggerDeleteProject(project);
						}
					});
					JButton up = new JButton("Update");
					up.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							self.triggerShowUpdate(project);
						}
					});
					projectPan.add(up);
					projectPan.add(del);
				}
				card.add(projectPan);
				this.pan.add(card, project.getId().toString());
			}
		}
	}

	public JPanel getPan() {
		return this.pan;
	}

	public CardLayout getCard() {
		return this.cl;
	}

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
		// ProjectForm working part
	}

	public void triggerUpdateProject(Project project) {
		for (ProjectListener listener: this.projectListeners) {
			listener.UpdateProjectTriggered(project);
		}
	}

	public void triggerDeleteProject(Project project) {
		for (ProjectListener listener: this.projectListeners) {
			listener.DeleteProjectTriggered(project);
		}
	}

}
