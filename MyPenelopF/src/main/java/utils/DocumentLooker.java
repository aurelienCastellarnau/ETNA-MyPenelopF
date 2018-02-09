package utils;

import java.io.File;
import java.util.ArrayList;

import classes.Document;
import classes.Project;
import controllers.ProjectController;

public class DocumentLooker implements Runnable{
	
	private String project_path        = System.getProperty("user.dir");
	private String documents_directory = "/documents";
	private File folder                = new File(project_path + documents_directory);
	private File[] listOfFiles         = folder.listFiles();
	private ProjectController pCtrl    = null;
	
	public DocumentLooker(ProjectController pCtrl) {
		this.pCtrl = pCtrl;
	}

	public void run(){
		ArrayList<Project> pList = this.pCtrl.getPDAO().get();
		Project currentP         = null;
		
		for ( File file:listOfFiles) {
			if (file.isFile()) {
				System.out.println("File " + file.getName());
			} else if (file.isDirectory()) {
				if (null == (currentP = this.pCtrl.getPDAO().getOneProject(file.getName())))
					createProjectFromDocuments(file, currentP);
				else 
					inspectProjectDirectory(file, currentP);
			}
		}
	}  
	
	private void createProjectFromDocuments(File folder, Project p) {
		System.out.println("In create Project Documents for " + folder.getName());
		p                         = new Project(folder.getName());
		File[] listOfProjectFiles = folder.listFiles();
		ArrayList<Document> d     = new ArrayList<Document>();
		
		for (File file: listOfProjectFiles) {
			Document document = new Document(file.getName(), file.getPath());
			d.add(document);
		}

		p.setDocuments(d);
		this.pCtrl.getPDAO().add(p);
		System.out.println("Project add");
	}
	
	private void inspectProjectDirectory(File file, Project p) {
		
	}
}
