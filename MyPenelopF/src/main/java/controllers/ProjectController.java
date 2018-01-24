package controllers;

import DAO.ProjectDAO;
import Observer.ProjectListener;
import classes.Project;
import ihm.dashboardPanel;
import utils.PenelopDevLogger;

public class ProjectController implements PenelopeController, ProjectListener {
	// Singletons calls on utilitarie classes
	final static PenelopDevLogger log = PenelopDevLogger.get();
	private ProjectDAO pDAO = null;
	// View elements
	public dashboardPanel dashboard;
	public ProjectController(ProjectDAO pDAO) {
		this.pDAO = pDAO;
	}
	public void init() {
		log._("INIT PROJECT CONTROLLER");
		this.pDAO.addProjectListener(this);
	}
	public ProjectDAO getPDAO() {
		return this.pDAO;
	}
	public void ProjectChangeTriggered() {
		// TODO Auto-generated method stub
		
	}
	public void ShowUpdateTriggered(Project p) {
		// TODO Auto-generated method stub
		
	}
	public void DeleteProjectTriggered(Project dProject) {
		// TODO Auto-generated method stub
		
	}
	public void CreateProjectTriggered(Project nProject) {
		// TODO Auto-generated method stub
		
	}
	public void UpdateProjectTriggered(Project uProject) {
		// TODO Auto-generated method stub
		
	}
}
