package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import DataInterface.DataInterface;
import Observer.ContactListener;
import classes.Contact;
import utils.PenelopDevLogger;

/**
 * 
 * @author jean
 * Classe permettant les opérations du CRUD sur l'entité Contact
 * Implemente un pattern Singleton
 * Implémente un pattern Observer
 * l'observer permet de déclencher un refresh du model
 * lors d'une modification 
 */		
public class ContactDAO extends DAO<Contact> {

		private final Collection<ContactListener> contactListeners = new ArrayList<ContactListener>();
		private PenelopDevLogger log = PenelopDevLogger.get();
		// Singleton implementation
		public ContactDAO(DataInterface di) {
			super(di);
		}
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
	         * Ici on appelle le singleton de la DataInterface FileSystemManager
	         * Si on passait sur une BDD classique, on enverrait ici un singleton
	         * différent mais respectant la DataInterface, contrat du bon déroulement
	         * du programme.
	        */
	        private static ContactDAO instance = null;
	        private final static ContactDAO get(DataInterface di) { 
	        	instance = (instance == null) ? new ContactDAO(di) : instance;
	        	return instance;
	        }
	    }
	    public static ContactDAO getInstance(DataInterface di) {
	    	return SingletonHolder.get(di);
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

		@Override
		public boolean add(Contact c) {
			ArrayList<Contact> users;
			users = this.get();
			users.add(c);
			this.di.writeContacts(users);
			this.triggerContactChange();
			return true;
		}

		@Override
		public boolean update(Contact c) {
			ArrayList<Contact> users = new ArrayList<Contact>();
			users = this.get();
			for (Contact user: users) {
				if (user.getId() == c.getId()) {
					int i = users.indexOf(user);
					users.set(i, c);
					System.out.println("ContactUtils.UpdateContact :");
					log.contacts(users);
					this.di.writeContacts(users);
					this.triggerContactChange();
					return true;
				}
			}
			return false;
		}
		
		@Override
		public boolean remove(Contact c) {
			System.out.println("into remove contact");
			ArrayList<Contact> users = new ArrayList<Contact>();
			users = this.get();
			System.out.println("remove contact");
			for (Contact user: users) {
				if (user.getId() == c.getId()) {
					users.remove(user);
					this.di.writeContacts(users);
					this.triggerContactChange();
					return true;
				}
			}
			return false;
		}
		 
		@Override
		public ArrayList<Contact> get(){
			int id = 0;
			ArrayList<Contact> users = new ArrayList<Contact>();
			String project_path = System.getProperty("user.dir");
			try {
				FileReader fr = new FileReader(project_path + "/users.json");
		        Gson gson = new Gson();
		        Object json = gson.fromJson(new BufferedReader(fr), ArrayList.class);
		        // safe casting emulation, we can't avoid the warning: https://stackoverflow.com/questions/20275623/type-safety-unchecked-cast-from-object-to-arraylistmyvariable
		        ArrayList<LinkedTreeMap> object = (json instanceof ArrayList<?>) ? (ArrayList<LinkedTreeMap>) json : null;
		        for (LinkedTreeMap obj: object) {
		        	try {
		        		id = new Float(obj.get("id").toString()).intValue();
		        	} catch (NumberFormatException e) {
		        		System.out.println("Exception throwed in getUsers(): " + e.getMessage());
		        	}
		        	if (id != 0) {
		        		Contact user = new Contact(id, obj.get("email").toString(), obj.get("surname").toString(), obj.get("name").toString());
		        		users.add(user);
		        	}
		        }
			} catch (IOException e) {
				System.out.println("Throwed in ContactGet: " + e.getMessage());
			}
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
