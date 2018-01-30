package Observer;

import classes.Task;

public interface TaskObserver {
	void addTaskListener(TaskListener listener);
	void removeTaskListener(TaskListener listener);
	void triggerTaskChange();
	void triggerShowUpdate(Task task);
	void triggerCreateTask(Task task);
	void triggerUpdateTask(Task task);
	void triggerDeleteTask(Task task);
}
