package Observer;

<<<<<<< HEAD
import classes.Contact;
=======
>>>>>>> Premiere modifs pour la gestion des DAO,
import classes.Group;

public interface GroupObserver {
	void addGroupListener(GroupListener listener);
	void removeGroupListener(GroupListener listener);
<<<<<<< HEAD
	void triggerShowUpdate(Group group);
=======
>>>>>>> Premiere modifs pour la gestion des DAO,
	void triggerGroupChange();
	void triggerCreateGroup(Group group);
	void triggerUpdateGroup(Group group);
	void triggerDeleteGroup(Group group);
}
