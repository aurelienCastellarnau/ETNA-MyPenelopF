package ihm.group;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Group;
import ihm.FormBuilder;

public class GroupPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pan;
    private CardLayout cl;
    private FormBuilder _fb = new FormBuilder();
	
	public GroupPanel(JPanel pan, CardLayout cl, ArrayList<Group> groups) {
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
	    for (Group group: groups) {
	    		JPanel gPan = new JPanel();
	    		GridLayout gl = new GridLayout(6, 2, 5, 5);
	    		gPan.setLayout(gl);
	    		JPanel groupNavPan = this._fb.getNavPanel(this.getCard(), this.getPan());
	    		JLabel tmp = new JLabel("Group N°" + group.getId() + " | Name: " + group.getName());
	     	JPanel card = new JPanel();
	        card.add(tmp);
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
}
