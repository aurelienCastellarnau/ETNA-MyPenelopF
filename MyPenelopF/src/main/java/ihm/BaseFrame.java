package ihm;

import ihm.contact.ContactPanel;
import ihm.contact.CreateContact;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import classes.Contact;
import controllers.ContactController;

public class BaseFrame extends JFrame {
	/**
	 * JFrame implementation requirement
	 */
	private static final long serialVersionUID = 1L;
	FormBuilder _fb = new FormBuilder();
	ContactPanel contactPanel;
	CreateContact createContact;
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
	
	public BaseFrame(ContactController cCtrl, ArrayList<Contact> users) {
        JFrame frame = new JFrame("Users");
		GridLayout gl = this.getGridLayout(2, 3, 5);
        frame.setTitle("Users: ");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        this.contactPanel = new ContactPanel(new JPanel(), cl, users);
        this.createContact = new CreateContact(new JPanel());
        this.createContact.addCreateContactListener(cCtrl);
        JPanel boutonPane = this._fb.getNavPanel(this.contactPanel.getCard(), this.contactPanel.getPan());
        this.setSize(800, 800);
        this.setLayout(gl);
        this.getContentPane().add(boutonPane);
        this.getContentPane().add(this.contactPanel.getPan());
        this.getContentPane().add(this.createContact.getPan());
        this.setVisible(true);
	}
	
	private GridLayout getGridLayout(int cols, int rows, int gap) {
		GridLayout gl = new GridLayout();
		gl.setColumns(cols);
		gl.setRows(rows);
		gl.setHgap(gap);
		gl.setVgap(gap);
		return gl;
	}
}

