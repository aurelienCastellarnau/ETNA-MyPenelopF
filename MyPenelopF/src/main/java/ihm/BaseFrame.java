package ihm;

import ihm.contact.ContactForm;
import ihm.contact.ContactPanel;
import ihm.group.FormGroup;
import ihm.group.GroupPanel;
import ihm.project.ProjectForm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import classes.Contact;
import classes.Group;
import classes.Project;
import controllers.ContactController;
import controllers.GroupController;
import controllers.PenelopeController;
import controllers.ProjectController;

/**
 *
 * @author aurelien
 * This classe allow us to declare base views for each entities
 * in few time we will split those constructors in several classes
 */
public class BaseFrame extends JFrame {
	/**
	 * JFrame implementation requirement
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Controllers
	 */
	ContactController cCtrl;
	GroupController gCtrl;
	ProjectController pCtrl;
	/**
	 * Tools
	 */
	FormBuilder _fb = new FormBuilder();
	CardLayout cl = new CardLayout();
	private JSplitPane split;
	
	/**
	 * Panels
	 */
	private menuPanel mPan;
	private dashboardPanel dPan;
	ContactPanel contactPanel;
	ContactForm createContact;
	ContactForm updateContact;
	ProjectForm createProject;
	ProjectForm updateProject;
	GroupPanel groupPanel;
	FormGroup createGroup;
	FormGroup updateGroup;
	JPanel buttonPane;

	public BaseFrame(HashMap<String, PenelopeController> ctrls) {
        this.cCtrl = (ContactController)ctrls.get("contact");
        this.gCtrl = (GroupController)ctrls.get("group");
        this.pCtrl = (ProjectController)ctrls.get("project");
		this.setTitle("MyPenelopF");
        this.setSize(800, 600);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(250, 250);
        
        this.mPan = new menuPanel();
        this.dPan = new dashboardPanel(this.cCtrl, this.pCtrl, this.gCtrl);
        
        mPan.addViewListener(dPan);
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mPan.getPan(), dPan.getPan());
        this.getContentPane().add(split, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }
	
	public menuPanel getMenuPanel() {
		return this.mPan;
	}
	
	public dashboardPanel getDashboardPanel() {
		return this.dPan;
	}
	
	/**
	 *
	 * @param Contact c
	 * BaseFrame for Contact Update
	 * Display ContactForm with c
	 * Allow to update a Contact
	 */
	public BaseFrame(ContactController cCtrl, Contact c) {
		this.cCtrl = cCtrl;
        JFrame frame = new JFrame("Users");
		GridLayout gl = new GridLayout(3, 2, 5, 5);
        frame.setTitle("Update User: ");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        this.updateContact = new ContactForm(new JPanel(), c);
        this.updateContact.addContactListener(this.cCtrl);
        this.setSize(800, 800);
        this.setLayout(gl);
        this.getContentPane().add(this.updateContact.getPan());
        this.setVisible(true);
	}
	
	/**
	 *
	 * @param Project p
	 * BaseFrame for Project Update
	 * Display ProjectForm with p
	 * Allow to update a Project
	 */
	public BaseFrame(ProjectController pCtrl, Project p) {
		
		this.pCtrl = pCtrl;
		JFrame frame = new JFrame("Projects");
		GridLayout gl = new GridLayout(3, 2, 5, 5);
		frame.setTitle("Update User: ");
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(250, 250);
		this.updateProject = new ProjectForm(new JPanel(), p);
		this.updateProject.addProjectListener(this.pCtrl);
		this.setSize(800, 800);
		this.setLayout(gl);
		this.getContentPane().add(this.updateProject.getPan());
		this.setVisible(true);
	}

	public BaseFrame(GroupController gCtrl, Group group) {
		this.gCtrl = gCtrl;
        JFrame frame = new JFrame("Groups");
		GridLayout gl = new GridLayout(3, 2, 5, 5);
        frame.setTitle("Update Group: ");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        this.updateGroup = new FormGroup(new JPanel(), group);
        this.updateGroup.addGroupListener(this.gCtrl);
        this.setSize(800, 800);
        this.setLayout(gl);
        this.getContentPane().add(this.updateGroup.getPan());
        this.setVisible(true);
	}

	/**
	 *
	 * @param contacts
	 * recreate the content of the ContactPanel view
	 * remove and add createContact to preserve order.
	 * See for a non-destroying solution...
	 */
	public void refreshContactPanel(ArrayList<Contact> contacts) {
		this.getContentPane().remove(this.buttonPane);
		this.getContentPane().remove(this.contactPanel.getPan());
		this.getContentPane().remove(this.createContact.getPan());
		this.cl = new CardLayout();
		this.contactPanel = new ContactPanel(new JPanel(), this.cl, contacts);
        this.contactPanel.addContactListener(this.cCtrl);
        this.buttonPane = this._fb.getNavPanel(this.contactPanel.getCard(), this.contactPanel.getPan());
        this.getContentPane().add(this.buttonPane);
        this.getContentPane().add(this.contactPanel.getPan());
		this.getContentPane().add(this.createContact.getPan());
        this.setVisible(true);
	}

	public void refreshGroupPanel(ArrayList<Group> groups) {
		this.getContentPane().remove(this.buttonPane);
		this.getContentPane().remove(this.groupPanel.getPan());
		this.getContentPane().remove(this.createGroup.getPan());
		this.cl = new CardLayout();
		this.groupPanel = new GroupPanel(new JPanel(), this.cl, groups);
        this.buttonPane = this._fb.getNavPanel(this.groupPanel.getCard(), this.groupPanel.getPan());
        this.getContentPane().add(this.buttonPane);
        this.getContentPane().add(this.groupPanel.getPan());
		this.getContentPane().add(this.createGroup.getPan());
        this.setVisible(true);
	}
}
