package ihm;

import ihm.BasePanel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JList;

import classes.Contact;

public class BaseFrame extends JFrame {
	/**
	 * JFrame implementation requirement
	 */
	private static final long serialVersionUID = 1L;

	public BaseFrame() {
        JFrame frame = new JFrame("FrameDemo");

        frame.setTitle("Ma première fenêtre Java");
        frame.setSize(400, 100);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        frame.setContentPane(new BasePanel());
        frame.setVisible(true);
	}
	
	public BaseFrame(ArrayList<Contact> users) {
        JFrame frame = new JFrame("FrameDemo");
        int ref = 0;
        String[] tmp = new String[users.size()];
        for (Contact user: users) {
        	tmp[ref] = "User N°" + user.getId() + " | Email: " + user.getEmail() + " | Surname: " + user.getSurname() + " | Name: " + user.getName();
        	ref++;
        }
        frame.setTitle("Users: ");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(250, 250);
        frame.setSize(800, 800);
        JList list = new JList(tmp);
        frame.add(list);
        frame.setVisible(true);
	}
}
