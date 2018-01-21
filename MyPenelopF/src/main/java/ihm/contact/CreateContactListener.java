package ihm.contact;

import classes.Contact;

/**
 * 
 * @author aurelien
 * A class listening on triggerCreateContact method
 * called by a click on CreateContact submit JButton
 * must implement this interface
 */
public interface CreateContactListener {
	void CreateContactTriggered(Contact nContact);
}
