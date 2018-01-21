package ihm;

import ihm.contact.ContactForm;
import ihm.contact.ContactPanel;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import classes.Contact;
import controllers.ContactController;

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
	ContactController cCtrl;
	FormBuilder _fb = new FormBuilder();
	ContactPanel contactPanel;
	ContactForm createContact;
	ContactForm updateContact;
	JPanel buttonPane;
	CardLayout cl = new CardLayout();

	public BaseFrame() {
        JFrame frame = new JFrame("FrameDemo");
        frame.setTitle("Ma première fenêtre Java");
        frame.setSize(400, 100);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        frame.setVisible(true);
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
}

