package DataInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import classes.Contact;
import classes.Group;
import utils.PenelopDevLogger;

/**
 * 
 * @author jean
 * 
 */
public class FileSystemManager implements DataInterface {

	private static final PenelopDevLogger log = PenelopDevLogger.get();
	String project_path = System.getProperty("user.dir");
	
	private FileSystemManager() {}
	private static class SingletonHolder
	{
		private final static FileSystemManager instance = new FileSystemManager();
	}
	public static FileSystemManager get() {
		return SingletonHolder.instance;
	}
	
	public ArrayList<Contact>readContacts() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/users.json"));
			Contact[] json = new Gson().fromJson(bufferedReader, Contact[].class);
			ArrayList<Contact> contacts = json != null ? new ArrayList<Contact>(Arrays.asList(json)) : new ArrayList<Contact>();
			return contacts;
		} catch (IOException e) {
			log._("Throwed in readContacts: " + e.getMessage());
			return null;
		}
	}

	public ArrayList<Group>readGroups() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/groups.json"));
			Group[] json = new Gson().fromJson(bufferedReader, Group[].class);
			ArrayList<Group> groups = json != null ? new ArrayList<Group>(Arrays.asList(json)) : new ArrayList<Group>();
			return groups;
		} catch (IOException e) {
			log._("Throwed in readGroups: " + e.getMessage());
			return null;
		}
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
			log._("Exception trhowed in writeContacts(): " + e.getMessage());
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
			log._("Exception trhowed in writeContacts(): " + e.getMessage());
		}
	}
}
