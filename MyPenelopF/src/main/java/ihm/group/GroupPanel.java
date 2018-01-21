package ihm.group;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Group;

public class GroupPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pan;
    private CardLayout cl;
	
	public GroupPanel(JPanel pan, CardLayout cl, ArrayList<Group> groups) {
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
	    for (Group group: groups) 
	    {
	    	JLabel tmp = new JLabel("Group N°" + group.getId() + " | Name: " + group.getName());
	     	JPanel card = new JPanel();
	        card.add(tmp);
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
}
