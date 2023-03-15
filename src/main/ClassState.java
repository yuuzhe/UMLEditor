package main;

import java.awt.event.MouseEvent;

public class ClassState implements State {
	public ClassState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		ClassObject co = new ClassObject();
		canvas.add(co);
		co.setLocation(e.getPoint());
		canvas.repaint();
	}
}