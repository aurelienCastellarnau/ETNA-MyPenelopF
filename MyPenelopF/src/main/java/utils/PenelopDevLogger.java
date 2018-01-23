package utils;

import java.util.ArrayList;

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
	
	public void contacts(ArrayList<Contact> contacts) {
		for (Contact c: contacts) {
        	System.out.println("User n°" + c.getId());
        	System.out.println("Email: " + c.getEmail());
        	System.out.println("Surname: " + c.getSurname());
        	System.out.println("Name: " + c.getName());
        }
	}
	public void contact(Contact c) {
        	System.out.println("User n°" + c.getId());
        	System.out.println("Email: " + c.getEmail());
        	System.out.println("Surname: " + c.getSurname());
        	System.out.println("Name: " + c.getName());
	}
	
	public void groups(ArrayList<Group> groups) {
		for (Group g: groups) {
			System.out.println("Group n" + g.getId());
			System.out.println("Name: " + g.getName());
		}
	}
	public void group(Group g) {
    	System.out.println("Group n°" + g.getId() + " added to users.");
    	System.out.println("Name: " + g.getName());
	}
}
