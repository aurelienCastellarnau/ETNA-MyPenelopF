package Observer;

import classes.Contact;
import classes.Group;

public interface GroupObserver {
	void addGroupListener(GroupListener listener);
	void removeGroupListener(GroupListener listener);
	void triggerShowUpdate(Group group);
	void triggerGroupChange();
	void triggerCreateGroup(Group group);
	void triggerUpdateGroup(Group group);
	void triggerDeleteGroup(Group group);
}
