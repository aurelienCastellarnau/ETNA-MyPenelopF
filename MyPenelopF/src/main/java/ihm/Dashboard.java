package ihm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import classes.Contact;
import ihm.contact.ContactPanel;
import utils.ContactUtils;

public class Dashboard extends JFrame {
	
	private JSplitPane split;
	private static final long serialVersionUID = 1L;
	private JPanel menu = new JPanel();
	private JPanel dashboard = new JPanel();
	private JButton loadContacts = new JButton("Contacts");
	private JButton loadGroups   = new JButton("Groups");
	CardLayout cl = new CardLayout();
	
	ContactPanel contactPanel;
	
	public Dashboard() {
        this.setTitle("MyPenelopF");
        this.setSize(200, 200);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(250, 250);
        loadContacts.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		ContactUtils utils = ContactUtils.get();
	     		ArrayList<Contact> users = null;
				try {
					users = utils.getContacts();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     		contactPanel = new ContactPanel(new JPanel(), cl, users);
	     		dashboard.add(contactPanel.getPan());
	     	}
		});
        menu.setBackground(Color.blue);
        menu.setLayout(new GridLayout(2, 1));
        menu.add(loadContacts);
        menu.add(loadGroups);
        dashboard.setBackground(Color.red);
        //On construit enfin notre séparateur
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menu, dashboard);
          
        //On le passe ensuite au content pane de notre objet Fenetre
        //placé au centre pour qu'il utilise tout l'espace disponible
        this.getContentPane().add(split, BorderLayout.CENTER);
        this.setVisible(true);
	}
}
