package controllers;

import java.util.ArrayList;

import Observer.ContactListener;
import DAO.ContactDAO;
import classes.Contact;
import ihm.BaseFrame;
import ihm.dashboardPanel;
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
	private ContactDAO cDAO = null;
	// View elements
	public dashboardPanel dashboard;
	public BaseFrame uForm;
	// Constructor
	public ContactController(ContactDAO cDAO) {
		this.cDAO = cDAO;
	}
	// Link DAO and Controller
	public void init() {
		cDAO.addContactListener(this);
	}
	// DAO accessor
	final public ContactDAO getContactDAO() {
		return this.cDAO;
	}
	// Global View accessor
	public dashboardPanel getDashboard() {
		return this.dashboard;
	}
	// Global View mutator
	public void setDashboard(dashboardPanel dashboard) {
		this.dashboard = dashboard;
	}
	
	/**
	 *  Observer pattern => ContactListener
	 *  Interact with ihm.contact and ContactDAO
	 *  ModelView linked by Observer pattern in controller
	 */
	public void CreateContactTriggered(Contact nContact) {
    	log._("CREATE CONTACT");
		log._(nContact);
		this.cDAO.add(nContact);
	}
	
	public void DeleteContactTriggered(Contact dContact) {
    	log._("DELETE CONTACT");
		log._(dContact);
		this.cDAO.remove(dContact);
	}

	public void ShowUpdateTriggered(Contact c) {
    	log._("SHOW UPDATE CONTACT WITH:");
		log._(c);
		this.uForm = new BaseFrame(this, c);
	}
	
	public void UpdateContactTriggered(Contact uContact) {
    	log._("CREATE CONTACT");
		log._(uContact);
		this.cDAO.update(uContact);
	}
	
	public void ContactChangeTriggered() {
		this.refreshContact();
	}
	
	//
	private void refreshContact() {
        ArrayList<Contact> retrievedContacts = this.cDAO.get();
        log._("REFRESH CONTACT DISCONNECTED FROM DASHBOARD");
        log.contacts(retrievedContacts);
        this.dashboard.displayContactPanel();
	}
}
