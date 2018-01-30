package controllers;

import java.util.HashMap;

import DAO.ContactDAO;
import DAO.DAOFactory;
import DAO.GroupDAO;
import DAO.ProjectDAO;
import DAO.TaskDAO;
import DataInterface.DataInterface;
import ihm.BaseFrame;
import ihm.dashboardPanel;
import utils.PenelopDevLogger;

public class AppController implements PenelopeController {
	private static final PenelopDevLogger log = PenelopDevLogger.get();
	private HashMap<String, PenelopeController> ctrls = new HashMap<String, PenelopeController>();
	private ContactDAO cDAO = null;
	private ProjectDAO pDAO = null;
	private GroupDAO gDAO = null;
	private TaskDAO tDAO = null;
	
	private BaseFrame Dashboard = null;
	
	public AppController(DataInterface di) {
		this.cDAO = (ContactDAO)DAOFactory.getContactDAO(di);
		this.pDAO = (ProjectDAO)DAOFactory.getProjectDAO(di);
		this.gDAO = (GroupDAO)DAOFactory.getGroupDAO(di);
		this.tDAO = (TaskDAO)DAOFactory.getTaskDAO(di);
	}
	public void init() {
    	this.ctrls.put("group", new GroupController(this.gDAO));
    	this.ctrls.put("contact", new ContactController(this.cDAO));
    	this.ctrls.put("project", new ProjectController(this.pDAO));
    	this.ctrls.put("task", new TaskController(this.tDAO));
    	for (String key: this.ctrls.keySet())
    		ctrls.get(key).init();
    	this.initViews(ctrls);
    	log._("APP PENELOPE INITIALISED");
	}
	private void initViews(HashMap<String, PenelopeController> ctrls) {
		ContactController cCtrl = (ContactController)ctrls.get("contact");
		ProjectController pCtrl = (ProjectController)ctrls.get("project");
		GroupController gCtrl = (GroupController )ctrls.get("group");
		TaskController tCtrl = (TaskController)ctrls.get("task");
		if (this.Dashboard == null)
			this.setBaseFrame(new BaseFrame(ctrls));
		if (this.viewIsInit()) {
			cCtrl.setDashboard(this.getDashboard());
			pCtrl.setDashboard(this.getDashboard());
			gCtrl.setDashboard(this.getDashboard());
			tCtrl.setDashboard(this.getDashboard());
		}
	}
	public ContactDAO getContactDAO() {
		return this.cDAO;
	}
	
	public void testCtrl() {
		log._("TEST App Controller");
	}
	
	public boolean viewIsInit() {
		return this.Dashboard != null && this.Dashboard.getDashboardPanel() != null;
	}
	
	public dashboardPanel getDashboard() {
		return this.Dashboard.getDashboardPanel();
	}
	public void setBaseFrame(BaseFrame dashboard) {
		this.Dashboard = dashboard;
	}
}
