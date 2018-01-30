package Observer;

import classes.Project;

public interface ProjectListener {
	void ProjectChangeTriggered();
	void ShowUpdateTriggered(Project p);
	void DeleteProjectTriggered(Project dProject);
	void CreateProjectTriggered(Project nProject);
	void UpdateProjectTriggered(Project uProject);
}
