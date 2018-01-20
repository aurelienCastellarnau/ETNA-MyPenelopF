package ihm;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormBuilder {
	
	public JPanel getNavPanel(final CardLayout cl, final JPanel pan) {
	     JPanel boutonPane = new JPanel();
	     JButton next = new JButton("Next");
	     next.addActionListener(new ActionListener(){
	     	public void actionPerformed(ActionEvent event){
	     		cl.next(pan);
	     	}
	     });
	     JButton prev = new JButton("Previous");
	     prev.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent event) {
	     		cl.previous(pan);
	     	}
	     });
	     boutonPane.add(prev);
	     boutonPane.add(next);
	     return boutonPane;
	}
	
	public JPanel getTextField(String item) {
		JPanel nPanel = new JPanel();
		JLabel nLabel = new JLabel(item);
		JTextField tf = new JTextField();
		tf.setColumns(50);
		nPanel.setLayout(new FlowLayout());
		nPanel.add(nLabel);
		nPanel.add(tf);
		return nPanel;
	}
}
