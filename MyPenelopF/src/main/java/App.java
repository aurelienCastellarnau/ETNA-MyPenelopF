import java.io.IOException;
import java.util.ArrayList;

import classes.Contact;
import controllers.ContactController;
import utils.ContactUtils;
import utils.FileSystemManager;

/*
 * MyPenelopeF entry point
 */
public class App 
{   
    public static void main( String[] args ) throws IOException 
    {
    	// delete once FileSystem is legit and safe
    	// App.setDummyData();
    	// Controllers are the only actors in main
    	final ContactController cCtrl = new ContactController();
    	cCtrl.initContact();
    }
    
    static private void setDummyData() {
        ArrayList<Contact> al = ContactUtils.get().createDummyContacts();
        FileSystemManager.get().writeContacts(al);
    }
}
