package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import classes.Contact;
import classes.Group;

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
	
	/**
	 * Wrtie the new contacts List in the file groups
	 * @param users
	 */
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
	
	/**
	 * Write the new groups List in the file groups.
	 * @param groups
	 */
	public void writeGroups(ArrayList<Group> groups) {
		Gson gson = new Gson();
    		String json = gson.toJson(groups);
		try {
			FileWriter fileWriter = new FileWriter(this.project_path + "/groups.json");
    			fileWriter.write(json);
            fileWriter.close();
		} catch (IOException e) {
			System.out.println("Exception trhowed in writeContacts(): " + e.getMessage());
		}
	}
}
