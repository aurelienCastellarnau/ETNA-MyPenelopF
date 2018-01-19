import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import classes.Contact;

import utils.ContactUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException 
    {
    		String project_path = System.getProperty("user.dir");
    		ArrayList<Contact> al = new ArrayList<Contact>();
    		Contact user1 = new Contact("test@etna-alternance.net", "Billaud", "Jean");
    		Contact user2 = new Contact("test2@etna-alternance.net", "Castellarnau", "Aurel");
    		Contact user3 = new Contact("test3@etna-alternance.net", "Trump", "Adolf");
    		al.add(user1);
    		al.add(user2);
    		al.add(user3);
    		Gson gson = new Gson();
    		String json = gson.toJson(al);
        System.out.println( json );
        
        FileWriter fileWriter = new FileWriter(project_path + "/users.json");
        fileWriter.write(json);
        fileWriter.close();
        
        ContactUtils utils = new ContactUtils();
        utils.getUsers();
    }
}
