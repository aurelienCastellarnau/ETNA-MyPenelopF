package Observer;

import classes.Project;

public interface ProjectObserver {
	void addProjectListener(ProjectListener listener);
	void removeProjectListener(ProjectListener listener);
	void triggerProjectChange();
	void triggerShowUpdate(Project project);
	void triggerCreateProject(Project project);
	void triggerUpdateProject(Project project);
	void triggerDeleteProject(Project project);
}
