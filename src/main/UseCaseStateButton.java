package main;

import javax.swing.ImageIcon;

public class UseCaseStateButton extends StateButton {
	public UseCaseStateButton() {
		this.setSize(64, 64);
		this.setToolTipText("Use Case");
		this.setIcon(new ImageIcon("assets\\uc.png"));
	}
	
	public void onclick(Canvas canvas, int x, int y) {
		UseCaseObject uco = new UseCaseObject();
		// Set the depth of the basic object into the lowest.
		canvas.add(uco, 0);
		uco.setLocation(x, y);
		canvas.repaint();
	}
}
