import java.io.IOException;
import java.util.ArrayList;

import classes.Contact;
import controllers.ContactController;
import utils.PenelopDevLogger;
import utils.ContactUtils;
import utils.FileSystemManager;

import ihm.BaseFrame;

/*
 * MyPenelopeF entry point
 */
public class App 
{    
    public static void main( String[] args ) throws IOException 
    {
    	// Singletons calls on utilitaries classes
    	final PenelopDevLogger log = PenelopDevLogger.get();
    	final ContactUtils cUtils = ContactUtils.get();
    	final FileSystemManager fsm = FileSystemManager.get();
    	final ContactController cCtrl = new ContactController();
    	// Local variables declaration
        ArrayList<Contact> retrievedContacts;
        ArrayList<Contact> al = cUtils.createDummyContacts();
        
        fsm.writeContacts(al);
        // retrieved contacts 
        retrievedContacts = cUtils.getContacts();
        log.contacts(retrievedContacts);
        new BaseFrame(cCtrl, retrievedContacts);
    }
}
