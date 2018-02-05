package DAO;

import java.util.ArrayList;

import classes.Group;
import classes.Project;

public interface ProjectDAOReceipe {
	public ArrayList<Group> getGroups(Project p);
}
