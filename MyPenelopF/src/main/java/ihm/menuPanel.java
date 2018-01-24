package ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

public class menuPanel extends JPanel implements ViewObserver {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel  mPan;
	private JButton cButton;
	private JButton gButton;
	
	private final Collection<ViewListener> ViewListeners = new ArrayList<ViewListener>();
	
	public menuPanel() {
		final menuPanel self = this;
		this.mPan = new JPanel();
		this.mPan.setLayout(new GridLayout(2, 1));
		this.cButton = new JButton("Contacts");
		this.cButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		self.triggerShowContacts();
	     	}
        });
		this.gButton = new JButton("Groups");
		this.gButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				self.triggerShowGroups();
			}
		});
		this.mPan.add(cButton);
		this.mPan.add(gButton);
	}
	
	public JPanel getPan() {
		return this.mPan;
	}
	
	public void addViewListener(ViewListener listener) {
		this.ViewListeners.add(listener);
	}
	public void removeViewListener(ViewListener listener) {
		this.ViewListeners.remove(listener);
	}

	public void triggerShowContacts() {
		for (ViewListener listener: this.ViewListeners) {
			listener.showContactsTriggered();
		}
	}

	public void triggerShowGroups() {
		for (ViewListener listener: this.ViewListeners) {
			listener.showGroupsTriggered();
		}
	}
	
	
}
