package main;

import javax.swing.ImageIcon;

public class GLStateButton extends StateButton {
	public GLStateButton() {
		this.setSize(64, 64);
		this.setToolTipText("Generalization Line");
		this.setIcon(new ImageIcon("assets\\generalization.png"));
	}
	
	public void onclick(Canvas canvas, int x, int y) {

	}
}