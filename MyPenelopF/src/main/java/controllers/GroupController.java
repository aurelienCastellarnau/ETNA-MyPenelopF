package controllers;

import java.io.IOException;
import java.util.ArrayList;

import classes.Group;
import ihm.BaseFrame;
import ihm.dashboardPanel;
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
        	log._("INIT GROUP");
            log.groups(retrievedGroups);
            // this.base = new BaseFrame(this, retrievedGroups);
            gUtils.addGroupListener(this);
        } catch (IOException e) {
        	System.out.println("ContactController.initContact/cUtils.getContacts throwed: " + e.getMessage());
        }
	}
	
	public void CreateGroupTriggered(Group nGroup) {
		log._("Triggered Group");
    	log._("CREATE Group");
		log._(nGroup);
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
        	log._("REFRESH Group DISCONNECTED FROM DASHBOARd");
            log.groups(retrievedGroups);
            // this.base.refreshGroupPanel(retrievedGroups);
        } catch (IOException e) {
        	log._("ContactController.initContact/cUtils.getContacts throwed: " + e.getMessage());
        }
	}
	
	public void testCtrl() {
		log._("TEST Group Controller");
	}

	public dashboardPanel getDashboard() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDashboard(dashboardPanel dashboard) {
		// TODO Auto-generated method stub
		
	}
}
