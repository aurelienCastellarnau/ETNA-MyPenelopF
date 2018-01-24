package ihm.contact;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Observer.ContactListener;
import Observer.ContactObserver;
import classes.Contact;
import classes.Group;
import ihm.FormBuilder;
import ihm.group.GroupPanel;
import utils.PenelopDevLogger;

/**
 *
 * @author aurelien
 * ContactPanel define the JPanel to display an
 * ArrayList of Contact
 * It take an external JPanel and CardLayout
 * to allow us to reuse those container
 * from controller to add further logic
 */
public class ContactPanel extends JPanel implements ContactObserver{

	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = 1L;
	private static final PenelopDevLogger log = PenelopDevLogger.get();
    private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private GroupPanel groupPan;
    private CardLayout cl;
	private final Collection<ContactListener> ContactListeners = new ArrayList<ContactListener>();

	public ContactPanel(JPanel pan, CardLayout cl, ArrayList<Contact> users) {
        final ContactPanel self = this;
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
	    for (final Contact user: users)
	    {
	    	JPanel userPan = new JPanel();
			GridLayout gl = new GridLayout(6, 2, 5, 5);
			userPan.setLayout(gl);
	    	CardLayout groupCl = new CardLayout();
	    	ArrayList<Group>userGroups = user.getGroups();
	    	this.groupPan = new GroupPanel(new JPanel(), groupCl, userGroups);
	        JPanel groupNavPan = this._fb.getNavPanel(this.groupPan.getCard(), this.groupPan.getPan());
	    	JLabel tmp = new JLabel("User N°" + user.getId() + " | Email: " + user.getEmail() + " | Surname: " + user.getSurname() + " | Name: " + user.getName());
	     	JPanel card = new JPanel();
	        JButton del = new JButton("Delete");
	        del.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent event) {
		     		self.triggerDeleteContact(user);
		     	}
	        });
	        JButton up = new JButton("Update");
	        up.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent event) {
		     		self.triggerShowUpdate(user);
		     	}
	        });
	        userPan.add(tmp);
	        userPan.add(groupNavPan);
	        userPan.add(this.groupPan.getPan());
	        userPan.add(up);
	        userPan.add(del);
	     	card.add(userPan);
	     	card.add(groupPan);
	        this.pan.add(card, user.getId().toString());
	    }
	}

	// Individual components accessors
	public JPanel getPan() {
		return this.pan;
	}

	public CardLayout getCard() {
		return this.cl;
	}
	// Observer subscribe, unsubscribe and notify
	public void addContactListener(ContactListener listener) {
		System.out.println(listener);
		this.ContactListeners.add(listener);
	}
	public void removeContactListener(ContactListener listener) {
		this.ContactListeners.remove(listener);
	}
	public void triggerDeleteContact(Contact contact) {
		for (ContactListener listener: this.ContactListeners) {
			System.out.println(listener);
			listener.DeleteContactTriggered(contact);
		}
	}

	public void triggerContactChange() {
		// TODO Auto-generated method stub

	}

	public void triggerCreateContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void triggerUpdateContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void triggerShowUpdate(Contact c) {
		for (ContactListener listener: this.ContactListeners) {
			listener.ShowUpdateTriggered(c);
		}
	}
}
