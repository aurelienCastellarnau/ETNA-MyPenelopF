package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import classes.Contact;

public class ContactUtils {
	
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
    
	public ArrayList<Contact> createDummyContacts() throws IOException{
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
		
	}
	
	public ArrayList<Contact> getContacts() throws IOException{
		int id = 0;
		ArrayList<Contact> users = new ArrayList<Contact>();
		String project_path = System.getProperty("user.dir");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(project_path + "/users.json"));

        Gson gson = new Gson();
        Object json = gson.fromJson(bufferedReader, ArrayList.class);
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
        return users;
	}
}
