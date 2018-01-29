package Observer;

import classes.Group;

public interface GroupListener {
	void GroupChangeTriggered();
	void DeleteGroupTriggered(Group dGroup);
	void CreateGroupTriggered(Group nGroup);
	void UpdateGroupTriggered(Group uGroup);
<<<<<<< HEAD
	void ShowUpdateTriggered(Group group);
=======
>>>>>>> Premiere modifs pour la gestion des DAO,
}
