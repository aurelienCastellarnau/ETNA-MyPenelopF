package Observer;

import classes.Task;

public interface TaskListener {
	void TaskChangeTriggered();
	void ShowUpdateTriggered(Task p);
	void DeleteTaskTriggered(Task dTask);
	void CreateTaskTriggered(Task nTask);
	void UpdateTaskTriggered(Task uTask);
}
