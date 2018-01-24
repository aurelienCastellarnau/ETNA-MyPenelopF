package ihm;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import classes.Contact;
import classes.Group;
import controllers.ContactController;
import ihm.contact.ContactPanel;
import ihm.group.GroupPanel;
import utils.ContactUtils;
import utils.GroupUtils;

/**
 * 
 * @author kumatetsu
 * Front Controller for the dashboard view
 */
public class dashboardPanel implements ViewListener {
	
	/**
	 * Declaration du Panel Principal
	 */
	private JPanel  mPan;
	private JPanel navPan;
	private FormBuilder _fb;
	
	/**
	 * Panels pouvant etre appelles dans le panel parent mPan
	 */
	private ContactPanel contactPanel;
	private GroupPanel groupPanel;

	/**
	 * Utils appeles en fonction des listeners;
	 */
	private ContactUtils cUtils = ContactUtils.get();   
	private GroupUtils gUtils = GroupUtils.get();
	
	/**
	 * Controller permettant la gestion des Listeners;
	 */
	private ContactController cCtrl;

	
	private CardLayout cl = new CardLayout();
	
	public dashboardPanel() {
		this.mPan = new JPanel();
	}
	
	public JPanel getPan() {
		return this.mPan;
	}

	public void showContactsTriggered() {
		ArrayList<Contact> users = null;
		try {
			users = this.cUtils.getContacts();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.contactPanel = new ContactPanel(new JPanel(), this.cl, users);
        this.contactPanel.addContactListener(this.cCtrl);
        this._fb = new FormBuilder();
        this.navPan = new JPanel();
        this.navPan = this._fb.getNavPanel(this.contactPanel.getCard(), this.contactPanel.getPan());
		this.mPan.removeAll();
		this.mPan.add(navPan);
		this.mPan.add(contactPanel.getPan());
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
