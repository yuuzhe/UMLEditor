package main;

import javax.swing.ImageIcon;

public class CLStateButton extends StateButton {
	public CLStateButton() {
		this.setSize(64, 64);
		this.setIcon(new ImageIcon("assets\\composition.png"));
		this.setToolTipText("Composition Line");
	}
	
	public void onclick(Canvas canvas, int x, int y) {

	}
}