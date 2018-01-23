package ihm;

import ihm.contact.ContactForm;
import ihm.contact.ContactPanel;
import ihm.group.CreateGroup;
import ihm.group.GroupPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import classes.Contact;
import classes.Group;
import controllers.ContactController;
import controllers.GroupController;

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
	
	/**
	 * Tools
	 */
	FormBuilder _fb = new FormBuilder();
	CardLayout cl = new CardLayout();
	private JSplitPane split;
	
	/**
	 * Panels
	 */
	ContactPanel contactPanel;
	ContactForm createContact;
	ContactForm updateContact;
	GroupPanel groupPanel;
	CreateGroup createGroup;
	JPanel buttonPane;

	public BaseFrame() {
        this.setTitle("MyPenelopF");
        this.setSize(400, 400);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(250, 250);
        
        menuPanel mPan = new menuPanel();
        Dashboard dPan = new Dashboard();
        
        mPan.addViewListener(dPan);
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mPan.getPan(), dPan.getPan());
        
        this.getContentPane().add(split, BorderLayout.CENTER);
        
        this.setVisible(true);
	}

	/**
	 *
	 * @param cCtrl
	 * @param users
	 * BaseFrame for Contact Crud
	 * Display result of getContacts in ContactPanel
	 * Allow to create and write a new Contact list in CreateContact
	 * buttonPane stock an instance of FormBuilder.getNavPanel
	 * wich can be reuse with another CardLayout/JPanel display
	 */
	public BaseFrame(ContactController cCtrl, ArrayList<Contact> users) {
		this.cCtrl = cCtrl;
        JFrame frame = new JFrame("Users");
		GridLayout gl = new GridLayout(3, 2, 5, 5);
        frame.setTitle("Users: ");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        this.contactPanel = new ContactPanel(new JPanel(), this.cl, users);
        this.contactPanel.addContactListener(this.cCtrl);
        this.createContact = new ContactForm(new JPanel());
        this.createContact.addContactListener(this.cCtrl);
        this.buttonPane = this._fb.getNavPanel(this.contactPanel.getCard(), this.contactPanel.getPan());
        this.setSize(800, 800);
        this.setLayout(gl);
        this.getContentPane().add(this.buttonPane);
        this.getContentPane().add(this.contactPanel.getPan());
        this.getContentPane().add(this.createContact.getPan());
        this.setVisible(true);
	}

	public BaseFrame(GroupController gCtrl, ArrayList<Group> groups) {
        JFrame frame = new JFrame("Groups");
		GridLayout gl = new GridLayout(3, 2, 5, 5);
        frame.setTitle("Groups: ");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        this.groupPanel = new GroupPanel(new JPanel(), this.cl, groups);
        this.createGroup = new CreateGroup(new JPanel());
        this.createGroup.addCreateGroupListener(gCtrl);
        this.buttonPane = this._fb.getNavPanel(this.groupPanel.getCard(), this.groupPanel.getPan());
        this.setSize(800, 800);
        this.setLayout(gl);
        this.getContentPane().add(this.buttonPane);
        this.getContentPane().add(this.groupPanel.getPan());
        this.getContentPane().add(this.createGroup.getPan());
        this.setVisible(true);
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
