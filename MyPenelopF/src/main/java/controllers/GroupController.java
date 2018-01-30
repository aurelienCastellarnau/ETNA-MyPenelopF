package controllers;

import DAO.GroupDAO;
import classes.Group;
import ihm.BaseFrame;
import ihm.dashboardPanel;
import Observer.GroupListener;
import utils.PenelopDevLogger;

public class GroupController implements PenelopeController, GroupListener {

	public BaseFrame base;
	private dashboardPanel dashboard;
	final static PenelopDevLogger log = PenelopDevLogger.get();
	
	private GroupDAO gDAO = null;
	
	public GroupController(GroupDAO gDAO) {
		this.gDAO = gDAO;
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
	
	private void refreshGroup() {}

	public void DeleteGroupTriggered(Group dGroup) {
		// TODO Auto-generated method stub
		
	}

	public void UpdateGroupTriggered(Group uGroup) {
		// TODO Auto-generated method stub
		
	}
	
	public void testCtrl() {
		log._("INIT GroupController");
	}

	public dashboardPanel getDashboard() {
		return this.dashboard;
	}

	public void setDashboard(dashboardPanel dashboard) {
		this.dashboard = dashboard;
	}
}
