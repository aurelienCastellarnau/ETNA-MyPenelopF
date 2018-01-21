package ihm.contact;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Contact;

/**
 * 
 * @author aurelien
 * ContactPanel define the JPanel to display an
 * ArrayList of Contact
 * It take an external JPanel and CardLayout
 * to allow us to reuse those container
 * from controller to add further logic
 */
public class ContactPanel extends JPanel {
	
	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pan;
    private CardLayout cl;
	
	public ContactPanel(JPanel pan, CardLayout cl, ArrayList<Contact> users) {
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
	    for (Contact user: users) 
	    {
	    	JLabel tmp = new JLabel("User N°" + user.getId() + " | Email: " + user.getEmail() + " | Surname: " + user.getSurname() + " | Name: " + user.getName());
	     	JPanel card = new JPanel();
	        card.add(tmp);
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
}
