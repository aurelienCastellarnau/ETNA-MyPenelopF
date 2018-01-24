package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.google.gson.Gson;

import DataInterface.FileSystemManager;
import Observer.ContactListener;
import Observer.ContactObserver;
import classes.Contact;

/**
 * 
 * @author jean
 * Classe permettant les opérations du CRUD sur l'entité Contact
 * Implemente un pattern Singleton
 * Implémente un pattern Observer
 * l'observer permet de déclencher un refresh du model
 * lors d'une modification 
 */
public class ContactUtils implements ContactObserver {
	
	private final Collection<ContactListener> contactListeners = new ArrayList<ContactListener>();
	private PenelopDevLogger log = PenelopDevLogger.get();
	// Singleton implementation
	private ContactUtils() {}
	/** Holder */
    private static class SingletonHolder
    {       
        /** Instance unique non préinitialisée 
         * la classe interne ne sera chargée en mémoire 
         * que lorsque l'on y fera référence pour la première fois
         * Permet de protéger d'un double appel en environnement multithreadé.
         * C'est overkill pour le moment, mais c'est une bonne pratique.
         * Dans le soucis de pouvoir faire évoluer l'app, cette implémentation
         * anticipe les problèmes.
        */
        private final static ContactUtils instance = new ContactUtils();
    }
    public static ContactUtils get() {
    		return SingletonHolder.instance;
    }
    
	public ArrayList<Contact> createDummyContacts() {
		ArrayList<Contact> al = new ArrayList<Contact>();
    		Contact user1 = new Contact("test@etna-alternance.net", "Jean", "Billaud");
    		Contact user2 = new Contact("test2@etna-alternance.net", "Aurel", "Castellarnau");
    		Contact user3 = new Contact("test3@etna-alternance.net", "Adolf", "Trump");
    		al.add(user1);
    		al.add(user2);
    		al.add(user3);
    		return al;
	}
	
	public void addContact(Contact c) throws IOException {
		ArrayList<Contact> users = this.getContacts();
		users.add(c);
		FileSystemManager.get().writeContacts(users);
		this.triggerContactChange();
	}

	public void updateContact(Contact c) throws IOException {
		ArrayList<Contact> users = this.getContacts();
		for (Contact user: users) {
			if (user.getId() == c.getId()) {
				int i = users.indexOf(user);
				users.set(i, c);
				System.out.println("ContactUtils.UpdateContact :");
				log.contacts(users);
				FileSystemManager.get().writeContacts(users);
				this.triggerContactChange();
				return;
			}
		}
	}
	
	public void removeContact(Contact c) throws IOException {
		System.out.println("into remove contact");
		ArrayList<Contact> users = this.getContacts();
		System.out.println("remove contact");
		for (Contact user: users) {
			if (user.getId() == c.getId()) {
				users.remove(user);
				FileSystemManager.get().writeContacts(users);
				this.triggerContactChange();
				return;
			}
		}
	}
	
	public ArrayList<Contact> getContacts() throws IOException{
		ArrayList<Contact> users = new ArrayList<Contact>();
		String project_path = System.getProperty("user.dir");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(project_path + "/users.json"));

        Gson gson = new Gson();
        Contact[] json = gson.fromJson(bufferedReader, Contact[].class);
        users = new ArrayList<Contact>(Arrays.asList(json));
        return users;
	}
	
	// Observer pattern
	public void addContactListener(ContactListener listener) {
		this.contactListeners.add(listener);
	}
	public void removeContactListener(ContactListener listener) {
		this.contactListeners.remove(listener);
	}
	public void triggerContactChange() {
		for (ContactListener listener: this.contactListeners) {
			listener.ContactChangeTriggered();
		}
	}
	public void triggerCreateContact(Contact c) {}
	public void triggerUpdateContact(Contact c) {}
	public void triggerDeleteContact(Contact c) {}
	public void triggerShowUpdate(Contact c) {}
}
