package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import classes.Contact;

/**
 * 
 * @author jean
 * 
 */
public class FileSystemManager {

	String project_path = System.getProperty("user.dir");
	
	private FileSystemManager() {}
	private static class SingletonHolder
	{
		private final static FileSystemManager instance = new FileSystemManager();
	}
	public static FileSystemManager get() {
		return SingletonHolder.instance;
	}
	
	public void writeContacts(ArrayList<Contact> users) {
		Gson gson = new Gson();
    	String json = gson.toJson(users);
		try {
			FileWriter fileWriter = new FileWriter(this.project_path + "/users.json");
    		fileWriter.write(json);
            fileWriter.close();
		} catch (IOException e) {
			System.out.println("Exception trhowed in writeContacts(): " + e.getMessage());
		}
	}
}
