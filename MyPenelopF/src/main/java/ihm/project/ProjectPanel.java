package ihm.project;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Observer.ProjectListener;
import Observer.ProjectObserver;
import classes.Project;
import ihm.FormBuilder;
import utils.PenelopDevLogger;

public class ProjectPanel extends JPanel implements ProjectObserver {

	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = -1951975065883803116L;
	private static final PenelopDevLogger log = PenelopDevLogger.get();
    private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
    private CardLayout cl;
	private final Collection<ProjectListener> projectListeners = new ArrayList<ProjectListener>();

	public ProjectPanel(JPanel pan, CardLayout cl, ArrayList<Project> projects) {
        final ProjectPanel self = this;
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
		if (projects != null) {
			for (final Project project: projects)
			{
				JLabel tmp = new JLabel("Project N°" + project.getId() + " | Name: " + project.getName() + " | Description: " + project.getDescription());
				JPanel card = new JPanel();
				card.add(tmp);
				if (projectListeners.size() > 0) {
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
					card.add(up);
					card.add(del);
				}
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
