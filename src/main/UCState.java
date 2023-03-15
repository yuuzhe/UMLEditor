package main;

import java.awt.event.MouseEvent;

public class UCState implements State {
	public UCState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		UCObject uco = new UCObject();
		canvas.add(uco);
		uco.setLocation(e.getPoint());
		canvas.repaint();
	}
}