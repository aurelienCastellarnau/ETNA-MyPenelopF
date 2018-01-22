package ihm.group;

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
import classes.Group;
import ihm.FormBuilder;

public class CreateGroup extends JPanel{

	private static final long serialVersionUID = 1L;
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private JLabel title = new JLabel("Create New Group");
	private JButton submit = new JButton("Create");
	private JPanel name = this._fb.getTextField("Name");
	private final Collection<CreateGroupListener> createGroupListeners = new ArrayList<CreateGroupListener>();
	
	public CreateGroup(JPanel pan) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.name);
		final CreateGroup self = this;
		this.submit.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Group g = new Group(
	     					self.getNameInput().getText()
	     				);
	     		self.triggerCreateGroup(g);
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
	public JTextField getNameInput() {
		return (JTextField)this.name.getComponent(1);
	}
	// Observer subscribe, unsubscribe and notify
	public void addCreateGroupListener(CreateGroupListener listener) {
		this.createGroupListeners.add(listener);
	}
	public void removeCreateGroupListener(CreateGroupListener listener) {
		this.createGroupListeners.remove(listener);
	}
	protected void triggerCreateGroup(Group group) {
		for (CreateGroupListener listener: this.createGroupListeners) {
			listener.CreateGroupTriggered(group);
		}
	}
}
