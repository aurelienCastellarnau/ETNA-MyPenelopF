import java.io.IOException;

import controllers.ContactController;
import controllers.GroupController;

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
    	final GroupController gCtrl = new GroupController();
    	gCtrl.initGroup();
    }
}
