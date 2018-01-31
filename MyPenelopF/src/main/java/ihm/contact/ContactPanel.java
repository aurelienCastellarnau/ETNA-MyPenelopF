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
import classes.Msg;
import classes.Project;
import ihm.FormBuilder;
import ihm.group.GroupPanel;
import ihm.project.ProjectPanel;
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
	private JPanel pan;
	private GroupPanel groupPan;
	private JPanel projectNav;
	private ProjectPanel projectPan;
	private FormBuilder _fb;
    private CardLayout cl;
	private final Collection<ContactListener> ContactListeners = new ArrayList<ContactListener>();

	public ContactPanel(JPanel pan, CardLayout cl, ArrayList<Contact> users, boolean edit) {
        final ContactPanel self = this;
        this._fb = new FormBuilder();
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
	    for (final Contact user: users)
	    {
	    	// Card and Panel init + layout
	    	JPanel card = new JPanel();
	        JPanel userPan = new JPanel();
			GridLayout gl = new GridLayout(6, 2, 5, 5);
			userPan.setLayout(gl);
			// groupPanel definition
	    	CardLayout groupCl = new CardLayout();
	    	ArrayList<Group> userGroups = user.getGroups();
	    	this.groupPan = new GroupPanel(new JPanel(), groupCl, userGroups);
	    	CardLayout projectCl = new CardLayout();
	    	// projectPanel definition
	    	// with external navPanel
	    	ArrayList<Project> userProjects = user.getProjects();
	    	this.projectPan = new ProjectPanel(new JPanel(), projectCl, userProjects, false);
	        this.projectNav = this._fb.getNavPanel(this.projectPan.getCard(), this.projectPan.getPan());
	    	// contact content
	        JLabel tmp = new JLabel("User N°" + user.getId() + " | Email: " + user.getEmail() + " | Surname: " + user.getSurname() + " | Name: " + user.getName());
	    	// related messages
	        ArrayList<Msg> messages = user.getMessages();
	    	JPanel compiledMsgs = new JPanel();
	    	if (messages.size() > 0) {
	    		compiledMsgs.add(new JLabel("Message: "));
	    	}
	    	for (int iterator = 0; iterator < messages.size(); iterator++) {
	    		compiledMsgs.add(new JLabel(messages.get(iterator).getContent()));
	    	}
	    	userPan.add(compiledMsgs);
	        userPan.add(tmp);
	        // Delete and Update rely on listeners
	        if (edit) {
				log._("EDIT MODE FOR CONTACT");
		        userPan.add(this.groupPan.getPan());
		        userPan.add(this.projectNav);
		        userPan.add(this.projectPan.getPan());
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
	        	userPan.add(up);
	        	userPan.add(del);
	        }
	     	card.add(userPan);
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
		this.ContactListeners.add(listener);
	}
	public void removeContactListener(ContactListener listener) {
		this.ContactListeners.remove(listener);
	}
	public void triggerDeleteContact(Contact contact) {
		for (ContactListener listener: this.ContactListeners) {
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
