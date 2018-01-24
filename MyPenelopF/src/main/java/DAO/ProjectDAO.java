package DAO;

import java.util.ArrayList;

import DataInterface.DataInterface;
import Observer.ProjectListener;
import Observer.ProjectObserver;
import classes.Project;

public class ProjectDAO extends DAO<Project> implements ProjectDAOReceipe, ProjectObserver  {

	public ProjectDAO(DataInterface di) {
		super(di);
		// TODO Auto-generated constructor stub
	}

	public void addProjectListener(ProjectListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void removeProjectListener(ProjectListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void triggerProjectChange() {
		// TODO Auto-generated method stub
		
	}

	public void triggerShowUpdate(Project project) {
		// TODO Auto-generated method stub
		
	}

	public void triggerCreateProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	public void triggerUpdateProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	public void triggerDeleteProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean add(Project obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Project obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Project obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Project> get() {
		// TODO Auto-generated method stub
		return null;
	}

}
