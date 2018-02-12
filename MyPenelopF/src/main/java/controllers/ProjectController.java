package controllers;

import java.util.ArrayList;

import DAO.ProjectDAO;
import Observer.ProjectListener;
import classes.Project;
import ihm.BaseFrame;
import ihm.dashboardPanel;
import utils.PenelopDevLogger;

public class ProjectController implements PenelopeController, ProjectListener {
	// Singletons calls on utilitarie classes
	final static PenelopDevLogger log = PenelopDevLogger.get();
	private ProjectDAO pDAO = null;
	// View elements
	public dashboardPanel dashboard;
	public BaseFrame uForm;

	public ProjectController(ProjectDAO pDAO) {
		this.pDAO = pDAO;
	}

	public void init() {
		log._("INIT ProjectController");
		this.pDAO.addProjectListener(this);
	}

	public ProjectDAO getPDAO() {
		return this.pDAO;
	}

	public dashboardPanel getDashboard() {
		return this.dashboard;
	}

	public void setDashboard(dashboardPanel dashboard) {
		this.dashboard = dashboard;
	}

	public void ProjectChangeTriggered() {
		this.refreshProject();
	}

	public void ShowUpdateTriggered(Project p) {
		log._("SHOW UPDATE Project WITH:");
		log._(p);
		this.uForm = new BaseFrame(this, p);
	}

	public void DeleteProjectTriggered(Project dProject) {
		log._("Delete Project triggered");
		log._(dProject);
		this.pDAO.remove(dProject);
	}

	public void CreateProjectTriggered(Project nProject) {
		log._("Create Project triggered");
		log._(nProject);
		this.pDAO.add(nProject);
	}

	public void UpdateProjectTriggered(Project uProject) {
		log._("Update project triggered");
		log._(uProject);
		this.pDAO.update(uProject);
	}

	//
	private void refreshProject() {
		ArrayList<Project> retrievedProjects = this.pDAO.get();
		log._("REFRESH CONTACT DISCONNECTED FROM DASHBOARD");
		log.projects(retrievedProjects);
		this.dashboard.displayProjectPanel();
	}

	public void testCtrl() {
		log._("INIT ProjectController");
	}
}
