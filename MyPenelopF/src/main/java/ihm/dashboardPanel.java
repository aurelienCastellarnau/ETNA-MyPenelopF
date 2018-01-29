package ihm;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import classes.Contact;
import classes.Group;
import controllers.ContactController;
import ihm.contact.ContactForm;
import ihm.contact.ContactPanel;
import ihm.group.GroupPanel;
import utils.GroupUtils;
import utils.PenelopDevLogger;

/**
 * 
 * @author kumatetsu
 * Front Controller for the dashboard view
 */
public class dashboardPanel implements ViewListener {
	
	private static final PenelopDevLogger log = PenelopDevLogger.get();
	
	/**
	 * Declaration du Panel Principal
	 */
	private JPanel  mPan;
	private JPanel navPan;
	private FormBuilder _fb;
	
	/**
	 * Panels pouvant etre appelles dans le panel parent mPan
	 */
	private ContactForm contactForm;
	private ContactPanel contactPanel;
	private GroupPanel groupPanel;

	private GroupUtils gUtils = GroupUtils.get();
	
	/**
	 * Controller permettant la gestion des Listeners;
	 */
	private ContactController cCtrl;
	
	private CardLayout cl = new CardLayout();
	
	public dashboardPanel(ContactController cCtrl) {
		this.cCtrl = cCtrl;
		this.mPan = new JPanel();
	}
	
	public JPanel getPan() {
		return this.mPan;
	}

	public void showContactsTriggered() {
		this.displayContactPanel();
	}

	public void displayContactPanel() {
		ArrayList<Contact> contacts = this.cCtrl.getContactDAO().get();
		// add contact view
		this.contactForm = new ContactForm(new JPanel());
		this.contactForm.addContactListener(this.cCtrl);
		// get, update and delete contact view
		this.contactPanel = new ContactPanel(new JPanel(), this.cl, contacts);
        this.contactPanel.addContactListener(this.cCtrl);
        this._fb = new FormBuilder();
        this.navPan = new JPanel();
        this.navPan = this._fb.getNavPanel(this.contactPanel.getCard(), this.contactPanel.getPan());
        // Panel construction
		this.mPan.removeAll();
		this.mPan.add(this.navPan);
		this.mPan.add(this.contactPanel.getPan());
		this.mPan.add(this.contactForm.getPan());
		this.mPan.setBackground(Color.red);
		this.mPan.revalidate();
		this.mPan.repaint();
	}
	
	public void showGroupsTriggered() {
		ArrayList<Group> groups = null;
		try {
			groups = this.gUtils.getGroups(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.groupPanel = new GroupPanel(new JPanel(), this.cl, groups);
		this.mPan.removeAll();
		this.mPan.add(groupPanel.getPan());
		this.mPan.revalidate();
		this.mPan.repaint();
	}
}
