package main;

import javax.swing.ImageIcon;

public class SelectStateButton extends StateButton {
	public SelectStateButton() {
		this.setSize(64, 64);
		this.setToolTipText("Select");
		this.setIcon(new ImageIcon("assets\\select.png"));
		this.setSelected(true);
	}
	
	public void onclick(Canvas canvas, int x, int y) {
		
	}
}