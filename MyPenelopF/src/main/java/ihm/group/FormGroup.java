package ihm.group;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import classes.Group;
import classes.Project;
import controllers.GroupController;
import controllers.ProjectController;
import ihm.FormBuilder;

public class FormGroup extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
	private JLabel title = new JLabel("Create New Group");
	private JButton submit = new JButton("Create");
	private JPanel name = this._fb.getTextField("Name");
	private JList pList = null;
	private final Collection<CreateGroupListener> createGroupListeners = new ArrayList<CreateGroupListener>();
	
	public FormGroup(JPanel pan, GroupController gCtrl, ProjectController pCtrl ) {
		ArrayList<Project> p = pCtrl.getPDAO().get();
		this.pList = new JList(p.toArray());
		this.pList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.name);
		this.pan.add(new JScrollPane(this.pList));
		final FormGroup self = this;
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
		Object[] projects =  this.pList.getSelectedValues();
		ArrayList<Integer> pIds = new ArrayList<Integer>();
		for (Object project: projects) {
			Project newProject = (Project)project;
			System.out.println(newProject.getName());
			pIds.add(newProject.getId());
		}
		
		if (!pIds.isEmpty()) {
			group.setPids(pIds);
		}
		
		for (CreateGroupListener listener: this.createGroupListeners) {
			listener.CreateGroupTriggered(group);
		}
	}
}
