package Observer;

import classes.Group;

public interface GroupListener {
	void GroupChangeTriggered();
	void DeleteGroupTriggered(Group dGroup);
	void CreateGroupTriggered(Group nGroup);
	void UpdateGroupTriggered(Group uGroup);
	void ShowUpdateTriggered(Group group);
}
