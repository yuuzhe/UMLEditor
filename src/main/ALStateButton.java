package main;

import javax.swing.ImageIcon;

public class ALStateButton extends StateButton {
	public ALStateButton() {
		this.setSize(64, 64);
		this.setToolTipText("Association Line");
		this.setIcon(new ImageIcon("assets\\association.png"));
	}
	
	public void onclick(Canvas canvas, int x, int y) {

	}
}