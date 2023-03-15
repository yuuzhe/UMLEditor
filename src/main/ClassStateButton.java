package main;

import javax.swing.ImageIcon;

public class ClassStateButton extends StateButton {
	public ClassStateButton() {
		this.setSize(64, 64);
		this.setToolTipText("Class");
		this.setIcon(new ImageIcon("assets\\class.png"));
	}
	
	public void onclick(Canvas canvas, int x, int y) {
		ClassObject co = new ClassObject();
		// Set the depth of the basic object into the lowest.
		canvas.add(co, 0);
		co.setLocation(x, y);
		canvas.repaint();
	}
}
