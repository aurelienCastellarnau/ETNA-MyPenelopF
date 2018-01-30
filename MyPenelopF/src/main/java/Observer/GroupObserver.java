package Observer;

import classes.Group;

public interface GroupObserver {
	void addGroupListener(GroupListener listener);
	void removeGroupListener(GroupListener listener);
	void triggerGroupChange();
	void triggerCreateGroup(Group group);
	void triggerUpdateGroup(Group group);
	void triggerDeleteGroup(Group group);
}
