package controllers;

import java.util.HashMap;

import DAO.ContactDAO;
import DAO.DAOFactory;
import DAO.ProjectDAO;
import DataInterface.DataInterface;
import ihm.BaseFrame;
import classes.Project;

public class AppController implements PenelopeController {
	private HashMap<String, PenelopeController> ctrls = new HashMap<String, PenelopeController>();
	private ContactDAO cDAO = null;
	private ProjectDAO pDAO = null;
	// protected GroupDAO gDAO = (GroupDAO) DAOFactory.getGroupDAO(FileSystemManager.get());
	
	private BaseFrame Dashboard = null;
	
	public AppController(DataInterface di) {
		this.cDAO = (ContactDAO)DAOFactory.getContactDAO(di);
		this.pDAO = (ProjectDAO)DAOFactory.getProjectDAO(di);
	}
	public void init() {
    	//App.ctrls.put("group", new GroupController(this.gDAO));
    	this.ctrls.put("contact", new ContactController(this.cDAO));
    	this.ctrls.put("project", new ProjectController(this.pDAO));
    	// App.ctrls.put("xxx", new xxxController(this.xDAO));
    	for (String key: this.ctrls.keySet())
    		ctrls.get(key).init();
    	this.initViews(ctrls);
	}
	private void initViews(HashMap<String, PenelopeController> ctrls) {
		ContactController cCtrl = (ContactController)ctrls.get("contact");
		if (this.Dashboard == null)
			this.Dashboard = new BaseFrame(ctrls);
		cCtrl.setDashboard(this.Dashboard.getDashboardPanel());
	}
	public ContactDAO getContactDAO() {
		return this.cDAO;
	}
}
