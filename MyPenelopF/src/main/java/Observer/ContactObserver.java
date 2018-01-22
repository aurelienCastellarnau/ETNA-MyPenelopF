package Observer;

import classes.Contact;

public interface ContactObserver {
	void addContactListener(ContactListener listener);
	void removeContactListener(ContactListener listener);
	void triggerContactChange();
	void triggerShowUpdate(Contact contact);
	void triggerCreateContact(Contact contact);
	void triggerUpdateContact(Contact contact);
	void triggerDeleteContact(Contact contact);
}
