package controllers;

import java.io.IOException;
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
 * implement observer pattern (interface CreateContactListener work with ihm.contact.CreateContact)
 * Calls on singletons.
 */
public class ContactController implements ContactListener {
	// Singletons calls on utilitarie classes
	public BaseFrame base;
	public BaseFrame uForm;
	final static PenelopDevLogger log = PenelopDevLogger.get();
	final static ContactDAO contactDAO = (ContactDAO) DAOFactory.getContactDAO(FileSystemManager.get());
	
	public void initContact() {
		contactDAO.createDummyContacts();
        ArrayList<Contact> retrievedContacts = contactDAO.get();
        System.out.println("INIT CONTACT");
        log.contacts(retrievedContacts);
        this.base = new BaseFrame(this, retrievedContacts);
        contactDAO.addContactListener(this);
	}
	
	public void CreateContactTriggered(Contact nContact) {
		System.out.println("Triggered contact");
    	System.out.println("CREATE CONTACT");
		log.contact(nContact);
		contactDAO.add(nContact);
	}
	
	public void DeleteContactTriggered(Contact dContact) {
    	System.out.println("DELETE CONTACT");
		log.contact(dContact);
		contactDAO.remove(dContact);
	}

	public void ShowUpdateTriggered(Contact c) {
    	System.out.println("SHOW UPDATE CONTACT WITH:");
		log.contact(c);
		this.uForm = new BaseFrame(this, c);
	}
	
	public void UpdateContactTriggered(Contact uContact) {
    	System.out.println("CREATE CONTACT");
		log.contact(uContact);
		contactDAO.update(uContact);
	}
	
	public void ContactChangeTriggered() {
		this.refreshContact();
	}
	
	private void refreshContact() {
        ArrayList<Contact> retrievedContacts = contactDAO.get();
        System.out.println("REFRESH CONTACT");
        log.contacts(retrievedContacts);
        this.base.refreshContactPanel(retrievedContacts);
	}
}
