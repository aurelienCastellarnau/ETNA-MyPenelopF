package ihm.contact;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Contact;
import ihm.FormBuilder;

public class CreateContact extends JPanel{

	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = 1L;
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private JLabel title = new JLabel("Create New Contact");
	private JButton submit = new JButton("Create");
	private JPanel email = this._fb.getTextField("Email");
	private JPanel surname = this._fb.getTextField("Surname");
	private JPanel name = this._fb.getTextField("Name");
	private final Collection<CreateContactListener> createContactListeners = new ArrayList<CreateContactListener>();
	
	public CreateContact(JPanel pan) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.email);
		this.pan.add(this.surname);
		this.pan.add(this.name);
		final CreateContact self = this;
		this.submit.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Contact c = new Contact(
	     					self.getEmailInput().getText(),
	     					self.getSurnameInput().getText(),
	     					self.getNameInput().getText()
	     				);
	     		self.triggerCreateContact(c);
	     	}
		});
		this.pan.add(this.submit);
	}
	
	public JPanel getPan() {
		return this.pan;
	}
	public JLabel getTitle() {
		return this.title;
	}
	public JButton getSubmit() {
		return this.submit;
	}
	public JTextField getEmailInput() {
		return (JTextField)this.email.getComponent(1);
	}
	public JTextField getSurnameInput() {
		return (JTextField)this.surname.getComponent(1);
	}
	public JTextField getNameInput() {
		return (JTextField)this.name.getComponent(1);
	}
	// Observer subscribe, unsubscribe and notify
	public void addCreateContactListener(CreateContactListener listener) {
		this.createContactListeners.add(listener);
	}
	public void removeCreateContactListener(CreateContactListener listener) {
		this.createContactListeners.remove(listener);
	}
	protected void triggerCreateContact(Contact contact) {
		for (CreateContactListener listener: this.createContactListeners) {
			listener.CreateContactTriggered(contact);
		}
	}
}
