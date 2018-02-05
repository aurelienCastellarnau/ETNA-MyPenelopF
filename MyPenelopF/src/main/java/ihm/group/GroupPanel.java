package ihm.group;

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
import Observer.GroupListener;
import Observer.GroupObserver;
import classes.Contact;
import classes.Group;
import ihm.FormBuilder;
import ihm.contact.ContactPanel;

public class GroupPanel extends JPanel implements GroupObserver {

	private static final long serialVersionUID = 1L;
	private JPanel pan;
    private CardLayout cl;
    private FormBuilder _fb = new FormBuilder();
    private final Collection<GroupListener> GroupListeners = new ArrayList<GroupListener>();

	public GroupPanel(JPanel pan, CardLayout cl, ArrayList<Group> groups) {
		final GroupPanel self = this;
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
	    for (final Group group: groups) {
	    		JPanel gPan = new JPanel();
	    		GridLayout gl = new GridLayout(6, 2, 5, 5);
	    		gPan.setLayout(gl);
	    		JPanel groupNavPan = this._fb.getNavPanel(this.getCard(), this.getPan());
	    		JLabel tmp = new JLabel("Group N°" + group.getId() + " | Name: " + group.getName());
	    		JPanel card = new JPanel();
	    		JButton del = new JButton("Delete");
	    		del.addActionListener(new ActionListener() {
			     	public void actionPerformed(ActionEvent event) {
			     		self.triggerDeleteGroup(group);
			     	}
		        });
	    		JButton up = new JButton("Update");
	    		up.addActionListener(new ActionListener() {
			     	public void actionPerformed(ActionEvent event) {
			     		self.triggerShowUpdate(group);
			     	}
		        });
	        card.add(tmp);
	        card.add(del);
	        card.add(up);
	        card.add(groupNavPan);
	        this.pan.add(card, group.getId().toString());
	    }
	}

	// Individual components accessors
		public JPanel getPan() {
			return this.pan;
		}

		public CardLayout getCard() {
			return this.cl;
		}

		public void addGroupListener(GroupListener listener) {
			this.GroupListeners.add(listener);

		}

		public void removeGroupListener(GroupListener listener) {
			this.GroupListeners.remove(listener);
		}

		public void triggerGroupChange() {
			// TODO Auto-generated method stub

		}

		public void triggerCreateGroup(Group group) {
		}

		public void triggerUpdateGroup(Group group) {
			// TODO Auto-generated method stub

		}

		public void triggerDeleteGroup(Group group) {
			System.out.println("triggerDelete");
			for (GroupListener listener: this.GroupListeners) {
				listener.DeleteGroupTriggered(group);
			}
		}

		public void triggerShowUpdate(Group group) {
			for (GroupListener listener: this.GroupListeners) {
				listener.ShowUpdateTriggered(group);
			}
		}
}
