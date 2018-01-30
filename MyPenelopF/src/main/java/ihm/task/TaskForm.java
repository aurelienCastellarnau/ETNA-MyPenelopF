package ihm.task;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Observer.TaskListener;
import Observer.TaskObserver;
import classes.Task;
import ihm.FormBuilder;

public class TaskForm extends JPanel implements TaskObserver {

	/**
	 * JPanel requirement
	 */
	private static final long serialVersionUID = -7385022145032316437L;
	private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
<<<<<<< HEAD
	private JLabel title = new JLabel("Create New Task");
=======
	private JLabel title = new JLabel("Create New Contact");
>>>>>>> Task Workflow complete
	private JButton createButton = new JButton("Create");
	private JButton updateButton = new JButton("Update");
	private JPanel description = this._fb.getTextField("Content Description");
	private final Collection<TaskListener> tasksListeners = new ArrayList<TaskListener>();
	
	public TaskForm(JPanel pan) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.description);
		final TaskForm self = this;
		this.createButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Task t = new Task(
	     					self.getDescriptionInput().getText()
	     				);
	     		self.triggerCreateTask(t);
	     	}
		});
<<<<<<< HEAD
		this.pan.add(this.createButton);
=======
		this.pan.add(this.updateButton);
>>>>>>> Task Workflow complete
	}
	
	public TaskForm(JPanel pan, Task task) {
		GridLayout gl = new GridLayout(5, 1, 5, 5);
		this.pan = pan;
		this.pan.setLayout(gl);
		this.pan.add(this.title);
		this.pan.add(this.description);
		this.getDescriptionInput().setText(task.getDescription());
		final TaskForm self = this;
		final Integer id = task.getId();
		this.updateButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		Task t = new Task(
	     					id,
	     					self.getDescriptionInput().getText()
	     				);
	     		self.triggerUpdateTask(t);
	     	}
		});
		this.pan.add(this.updateButton);
	}
	
	public JTextField getDescriptionInput() {
		return (JTextField)this.description.getComponent(1);
	}
	
	public JPanel getPan() {
		return this.pan;
	}

	public void addTaskListener(TaskListener listener) {
		if (!this.tasksListeners.contains(listener)) {
			this.tasksListeners.add(listener);
		}
	}

	public void removeTaskListener(TaskListener listener) {
		if (this.tasksListeners.contains(listener)) {
			this.tasksListeners.remove(listener);
		}
	}

	public void triggerCreateTask(Task task) {
		for (TaskListener listener: this.tasksListeners) {
			listener.CreateTaskTriggered(task);
		}
	}

	public void triggerUpdateTask(Task task) {
		for (TaskListener listener: this.tasksListeners) {
			listener.UpdateTaskTriggered(task);
		}
	}

	public void triggerDeleteTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	public void triggerTaskChange() {
		// TODO Auto-generated method stub
		
	}

	public void triggerShowUpdate(Task task) {
		// TODO Auto-generated method stub
		
	}

}
