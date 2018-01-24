package utils;

import java.util.ArrayList;
import java.util.List;

import classes.Contact;
import classes.Group;

public class PenelopDevLogger {

	private PenelopDevLogger() {}
	private static class SingletonHolder
	{
		private final static PenelopDevLogger instance = new PenelopDevLogger();
	}
	public static PenelopDevLogger get() {
		return SingletonHolder.instance;
	}
	
	public void _(String str) {
    	System.out.println(str);
	}

	public void _(Contact c) {
    	System.out.println("User n°" + c.getId());
    	System.out.println("Email: " + c.getEmail());
    	System.out.println("Surname: " + c.getSurname());
    	System.out.println("Name: " + c.getName());
    	ArrayList<Group> groups = c.getGroups();
    	this._("Contact related Groups: ");
    	this.groups(groups);
	}
	
	public void contacts(ArrayList<Contact> contacts) {
		for (Integer iterator = 0; iterator < contacts.size(); iterator++) {
        	Contact c = contacts.get(iterator);
        	this._(c);
        }
	}

	public void _(Group g) {
    	System.out.println("Group n°" + g.getId());
    	System.out.println("Name: " + g.getName());
		System.out.println("Users in group: ");
		List<Integer>ids = g.getUIds();
    	for (int iterator = 0; iterator < ids.size(); iterator++) {
    		Integer id = ids.get(iterator);
    		System.out.println(id + "\n");
    	}
	}
	
	public void groups(ArrayList<Group> groups) {
		for (Integer iterator = 0; iterator < groups.size(); iterator++)
			this._(groups.get(iterator));
	}
}
