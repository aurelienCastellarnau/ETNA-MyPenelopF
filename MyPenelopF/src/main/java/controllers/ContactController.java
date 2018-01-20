package controllers;

import classes.Contact;
import ihm.contact.CreateContactListener;
import utils.PenelopDevLogger;

public class ContactController implements CreateContactListener {
	final static PenelopDevLogger log = PenelopDevLogger.get();
	
	public void CreateContactTriggered(Contact nContact) {
		System.out.println("Triggered contact");
		log.contact(nContact);
	}
	
}
