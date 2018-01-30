package ihm.task;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Observer.TaskListener;
import Observer.TaskObserver;
import classes.Task;
import ihm.FormBuilder;
import utils.PenelopDevLogger;

public class TaskPanel extends JPanel implements TaskObserver {

	/**
	 * JPanel requirement
	 */
	private static final long serialVersionUID = -2146571445384665490L;
	private static final PenelopDevLogger log = PenelopDevLogger.get();
    private FormBuilder _fb = new FormBuilder();
	private JPanel pan;
    private CardLayout cl;
	private final Collection<TaskListener> tasksListeners = new ArrayList<TaskListener>();

	public TaskPanel(JPanel pan, CardLayout cl, ArrayList<Task> tasks) {
        final TaskPanel self = this;
		this.pan = pan;
		this.cl = cl;
		this.pan.setLayout(this.cl);
		if (tasks != null) {
			for (final Task task: tasks)
			{
				JLabel tmp = new JLabel("Task N°" + task.getId() + " | Description: " + task.getDescription() + " | Project n°" + task.getPId());
				JPanel card = new JPanel();
				JButton del = new JButton("Delete");
				del.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						self.triggerDeleteTask(task);
					}
				});
				JButton up = new JButton("Update");
				up.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						self.triggerShowUpdate(task);
					}
				});
				card.add(tmp);
				card.add(up);
				card.add(del);
				this.pan.add(card, task.getId().toString());
			}
		}
	}

	public JPanel getPan() {
		return this.pan;
	}

	public CardLayout getCard() {
		return this.cl;
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

	public void triggerTaskChange() {
		// TODO Auto-generated method stub
		
	}

	public void triggerShowUpdate(Task task) {
		for (TaskListener listener: this.tasksListeners) {
			listener.ShowUpdateTriggered(task);
		}
	}


	public void triggerDeleteTask(Task task) {
		for (TaskListener listener: this.tasksListeners) {
			listener.DeleteTaskTriggered(task);
		}
	}

	public void triggerCreateTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	public void triggerUpdateTask(Task task) {
		// TODO Auto-generated method stub
		
	}
}
