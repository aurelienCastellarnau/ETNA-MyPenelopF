package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import classes.Contact;
import classes.Group;
/**
 * 
 * @author kumatetsu
 *
 */
public class GroupUtils {
	
	private final Collection<GroupListener> groupListeners = new ArrayList<GroupListener>();
	
	private GroupUtils() {}
	
	private static class SingletonHolder
    {       
        private final static GroupUtils instance = new GroupUtils();
    }
    public static GroupUtils get() {
    		return SingletonHolder.instance;
    }
    
    /**
     * Method to add one group to the file 'groups.json'
     * @param Group g
     * @throws IOException
     */
	public void addGroup(Group g) throws IOException {
		ArrayList<Group> groups = this.getGroups();
		groups.add(g);
		FileSystemManager.get().writeGroups(groups);
		this.triggerGroupChange();
	}
    
    /**
     * Method to load groups from file 'groups.json'
     * @return ArrayList<Group> groups
     * @throws IOException
     */
    public ArrayList<Group> getGroups() throws IOException{
		ArrayList<Group> groups = new ArrayList<Group>();
		String project_path = System.getProperty("user.dir");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(project_path + "/groups.json"));

        Gson gson = new Gson();
        Group[] json = gson.fromJson(bufferedReader, Group[].class);
        groups = new ArrayList<Group>(Arrays.asList(json));
        return groups;
	}
    
    public void addGroupListener(GroupListener listener) {
		this.groupListeners.add(listener);
	}
    
	public void removeGroupListener(GroupListener listener) {
		this.groupListeners.remove(listener);
	}
	
	public void triggerGroupChange() {
		for (GroupListener listener: this.groupListeners) {
			listener.GroupChangeTriggered();
		}
	}

}
