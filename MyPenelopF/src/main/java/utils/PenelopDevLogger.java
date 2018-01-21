package utils;

import java.util.ArrayList;

import classes.Contact;

public class PenelopDevLogger {

	private PenelopDevLogger() {}
	private static class SingletonHolder
	{
		private final static PenelopDevLogger instance = new PenelopDevLogger();
	}
	public static PenelopDevLogger get() {
		return SingletonHolder.instance;
	}
	
	public void contacts(ArrayList<Contact> contacts) {
		for (Contact c: contacts) {
        	System.out.println("User n°" + c.getId() + " added to users.");
        	System.out.println("Email: " + c.getEmail());
        	System.out.println("Surname: " + c.getSurname());
        	System.out.println("Name: " + c.getName());
        }
	}
	public void contact(Contact c) {
        	System.out.println("User n°" + c.getId() + " added to users.");
        	System.out.println("Email: " + c.getEmail());
        	System.out.println("Surname: " + c.getSurname());
        	System.out.println("Name: " + c.getName());
	}
}
