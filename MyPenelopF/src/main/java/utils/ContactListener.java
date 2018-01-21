package utils;

import classes.Contact;

/**
 * 
 * @author aurelien
 * A class listening on triggerContactChange method
 * called by a modification on Contact model
 * must implement this interface
 */
public interface ContactListener {
	void ContactChangeTriggered();
	void ShowUpdateTriggered(Contact c);
	void DeleteContactTriggered(Contact dContact);
	void CreateContactTriggered(Contact nContact);
	void UpdateContactTriggered(Contact uContact);
}
