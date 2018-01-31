package controllers;

import java.util.ArrayList;

import DAO.GroupDAO;
import Observer.GroupListener;
import classes.Group;
import ihm.BaseFrame;
import ihm.dashboardPanel;
import utils.PenelopDevLogger;

public class GroupController implements PenelopeController, GroupListener {

	public BaseFrame base;
	final static PenelopDevLogger log = PenelopDevLogger.get();
	public dashboardPanel dashboard;

	private GroupDAO gDAO = null;

	public GroupController(GroupDAO gDAO) {
		this.gDAO = gDAO;
	}

	public dashboardPanel getDashboard() {
		return this.dashboard;
	}
	
	public void setDashboard(dashboardPanel dashboard) {
		this.dashboard = dashboard;
	}
	
	public void init() {
		gDAO.addGroupListener(this);
	}

	final public GroupDAO getGroupDAO() {
		return this.gDAO;
	}

	public void CreateGroupTriggered(Group nGroup) {

	}

	public void GroupChangeTriggered() {
		this.refreshGroup();
	}

	private void refreshGroup() {
		ArrayList<Group> retrievedGroups = this.gDAO.get();
        log._("REFRESH GROUP DISCONNECTED FROM DASHBOARD");
        log.groups(retrievedGroups);
        log._("AFTER GROUP");
        this.dashboard.displayGroupPanel();
	}

	public void DeleteGroupTriggered(Group dGroup) {
		System.out.println("IN DELETEGROUP TRIGERED");
		this.gDAO.remove(dGroup);
	}

	public void UpdateGroupTriggered(Group uGroup) {
		// TODO Auto-generated method stub

	}

	public void testCtrl() {
		log._("TEST Group Controller");
	}
	
}
