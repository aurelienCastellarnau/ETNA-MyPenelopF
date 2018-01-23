package controllers;

import java.util.ArrayList;

import DAO.DAOFactory;
import DataInterface.FileSystemManager;
import Observer.ContactListener;
import DAO.ContactDAO;
import classes.Contact;
import ihm.BaseFrame;
import utils.PenelopDevLogger;

/**
 * 
 * @author aurelien
 * ContactController
 * Initialize view using ihm package,
 * manage logic and FileSystem update using ContactUtils classe
 * implement observer pattern 
 * (interface CreateContactListener work with ihm.contact.CreateContact)
 * Calls on singletons.
 */
public class ContactController implements PenelopeController, ContactListener {
	// Singletons calls on utilitarie classes
	final static PenelopDevLogger log = PenelopDevLogger.get();
	final static ContactDAO contactDAO = (ContactDAO) DAOFactory
			.getContactDAO(FileSystemManager.get());
	// View elements
	public BaseFrame base;
	public BaseFrame uForm;
	
	public void init() {
		contactDAO.createDummyContacts();
        ArrayList<Contact> retrievedContacts = contactDAO.get();
        log._("INIT CONTACT");
        log.contacts(retrievedContacts);
        this.base = new BaseFrame(this, retrievedContacts);
        contactDAO.addContactListener(this);
	}
	
	/**
	 *  Observer pattern => ContactListener
	 *  Interact with ihm.contact and ContactDAO
	 *  ModelView linked by Observer pattern in controller
	 */
	public void CreateContactTriggered(Contact nContact) {
    	log._("CREATE CONTACT");
		log.contact(nContact);
		contactDAO.add(nContact);
	}
	
	public void DeleteContactTriggered(Contact dContact) {
    	log._("DELETE CONTACT");
		log.contact(dContact);
		contactDAO.remove(dContact);
	}

	public void ShowUpdateTriggered(Contact c) {
    	log._("SHOW UPDATE CONTACT WITH:");
		log.contact(c);
		this.uForm = new BaseFrame(this, c);
	}
	
	public void UpdateContactTriggered(Contact uContact) {
    	log._("CREATE CONTACT");
		log.contact(uContact);
		contactDAO.update(uContact);
	}
	
	public void ContactChangeTriggered() {
		this.refreshContact();
	}
	
	//
	private void refreshContact() {
        ArrayList<Contact> retrievedContacts = contactDAO.get();
        log._("REFRESH CONTACT");
        log.contacts(retrievedContacts);
        this.base.refreshContactPanel(retrievedContacts);
	}
}
