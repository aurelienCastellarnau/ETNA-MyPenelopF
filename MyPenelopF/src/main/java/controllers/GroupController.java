package controllers;

import java.io.IOException;
import java.util.ArrayList;

import classes.Contact;
import classes.Group;
import ihm.BaseFrame;
import ihm.group.CreateGroupListener;
import utils.GroupListener;
import utils.GroupUtils;
import utils.PenelopDevLogger;

public class GroupController implements PenelopeController, GroupListener, CreateGroupListener {

	public BaseFrame base;
	final static PenelopDevLogger log = PenelopDevLogger.get();
	final static GroupUtils gUtils = GroupUtils.get();
	
	public void init() {
        try {
        	ArrayList<Group> retrievedGroups = gUtils.getGroups();
        	System.out.println("INIT GROUP");
            log.groups(retrievedGroups);
            this.base = new BaseFrame(this, retrievedGroups);
            gUtils.addGroupListener(this);
        } catch (IOException e) {
        	System.out.println("ContactController.initContact/cUtils.getContacts throwed: " + e.getMessage());
        }
	}
	
	public void CreateGroupTriggered(Group nGroup) {
		System.out.println("Triggered Group");
    	System.out.println("CREATE Group");
		log.group(nGroup);
		try {
			gUtils.addGroup(nGroup);
		} catch (IOException e) {
			System.out.println("Exceptions throwed adding contact: " + e.getMessage());
		}
	}

	public void GroupChangeTriggered() {
		this.refreshGroup();
	}
	
	private void refreshGroup() {
        try {
        	ArrayList<Group> retrievedGroups = gUtils.getGroups();
        	System.out.println("REFRESH Group");
            log.groups(retrievedGroups);
            this.base.refreshGroupPanel(retrievedGroups);
        } catch (IOException e) {
        	System.out.println("ContactController.initContact/cUtils.getContacts throwed: " + e.getMessage());
        }
	}
}
