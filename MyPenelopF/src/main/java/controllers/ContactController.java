package controllers;

import java.io.IOException;
import java.util.ArrayList;

import classes.Contact;
import ihm.BaseFrame;
import utils.ContactListener;
import utils.ContactUtils;
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
	final static ContactUtils cUtils = ContactUtils.get();
	
	public void initContact() {
        try {
        	ArrayList<Contact> retrievedContacts = cUtils.getContacts();
        	System.out.println("INIT CONTACT");
            log.contacts(retrievedContacts);
            this.base = new BaseFrame(this, retrievedContacts);
            cUtils.addContactListener(this);
        } catch (IOException e) {
        	System.out.println("ContactController.initContact/cUtils.getContacts throwed: " + e.getMessage());
        }
	}
	
	public void CreateContactTriggered(Contact nContact) {
		System.out.println("Triggered contact");
    	System.out.println("CREATE CONTACT");
		log.contact(nContact);
		try {
			cUtils.addContact(nContact);
		} catch (IOException e) {
			System.out.println("Exceptions throwed adding contact: " + e.getMessage());
		}
	}
	
	public void DeleteContactTriggered(Contact dContact) {
    	System.out.println("DELETE CONTACT");
		log.contact(dContact);
		try {
			cUtils.removeContact(dContact);
		} catch (IOException e) {
			System.out.println("Exceptions throwed removing contact: " + e.getMessage());
		}
	}

	public void ShowUpdateTriggered(Contact c) {
    	System.out.println("SHOW UPDATE CONTACT WITH:");
		log.contact(c);
		this.uForm = new BaseFrame(this, c);
	}
	
	public void UpdateContactTriggered(Contact uContact) {
    	System.out.println("CREATE CONTACT");
		log.contact(uContact);
		try {
			cUtils.updateContact(uContact);
		} catch (IOException e) {
			System.out.println("Exceptions throwed removing contact: " + e.getMessage());
		}
	}
	
	public void ContactChangeTriggered() {
		this.refreshContact();
	}
	
	private void refreshContact() {
        try {
        	ArrayList<Contact> retrievedContacts = cUtils.getContacts();
        	System.out.println("REFRESH CONTACT");
            log.contacts(retrievedContacts);
            this.base.refreshContactPanel(retrievedContacts);
        } catch (IOException e) {
        	System.out.println("ContactController.initContact/cUtils.getContacts throwed: " + e.getMessage());
        }
	}
}
