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
	
	public BaseFrame uForm;

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
		System.out.println("in gCtrl CreateGroup");
		this.gDAO.add(nGroup);
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
		log._("CREATE CONTACT");
		log._(uGroup);
		this.gDAO.update(uGroup);

	}

	public void testCtrl() {
		log._("TEST Group Controller");
	}

	public void ShowUpdateTriggered(Group group) {
		log._("SHOW UPDATE Group WITH:");
		log._(group);
		this.uForm = new BaseFrame(this, group);
	}
	
}
