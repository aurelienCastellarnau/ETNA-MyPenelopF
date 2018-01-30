package controllers;

import DAO.TaskDAO;
import Observer.TaskListener;
import classes.Task;
import ihm.BaseFrame;
import ihm.dashboardPanel;
import utils.PenelopDevLogger;

public class TaskController implements PenelopeController, TaskListener {
	// Singletons calls on utilitarie classes
	final static PenelopDevLogger log = PenelopDevLogger.get();
	private TaskDAO tDAO = null;
	// View elements
	public dashboardPanel dashboard;
	public BaseFrame uForm;
	
	public TaskController(TaskDAO tDAO) {
		this.tDAO = tDAO;
	}

	public void init() {
		this.tDAO.addTaskListener(this);
	}
	
	public TaskDAO getDAO() {
		return this.tDAO;
	}

	public void testCtrl() {
		log._("INIT TaskController");
	}
	
	public void ShowUpdateTriggered(Task t) {
    	log._("SHOW UPDATE Project WITH:");
		log._(t);
		this.uForm = new BaseFrame(this, t);
	}

	public void DeleteTaskTriggered(Task dTask) {
		log._("Delete Task triggered");
		log._(dTask);
		this.tDAO.remove(dTask);
	}

	public void CreateTaskTriggered(Task nTask) {
		log._("Create Task triggered");
		log._(nTask);
		this.tDAO.add(nTask);
	}

	public void UpdateTaskTriggered(Task uTask) {
		log._("Update Task triggered");
		log._(uTask);
		this.tDAO.update(uTask);
	}

	public void TaskChangeTriggered() {
		this.refreshTasks();
	}
	
	private void refreshTasks() {
        this.dashboard.displayTaskPanel();
	}
	
	public void setDashboard(dashboardPanel dashboard) {
		this.dashboard = dashboard;
	}

	public dashboardPanel getDashboard() {
		return this.dashboard;
	}

}
