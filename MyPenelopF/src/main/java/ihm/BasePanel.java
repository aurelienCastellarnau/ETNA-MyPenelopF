package ihm;

import javax.swing.JPanel;
import java.awt.Graphics;

public class BasePanel extends JPanel {
	/**
	 * JPanel implementation requirement
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		int x = this.getWidth() / 4;
		int y = this.getHeight() / 4;
		g.fillOval(x, y, this.getWidth() / 2, this.getHeight() / 2);
	}
}
