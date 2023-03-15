package main;

import java.awt.event.MouseEvent;

public interface State {
	public void onclick(Canvas canvas, MouseEvent e);
}

class SelectState implements State {
	public SelectState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
}

class ALState implements State {
	public ALState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
}

class CLState implements State {
	public CLState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
}

class ClassState implements State {
	public ClassState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		ClassObject co = new ClassObject();
		canvas.add(co);
		co.setLocation(e.getPoint());
		canvas.repaint();
	}
}

class GLState implements State {
	public GLState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
}

class UCState implements State {
	public UCState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		UCObject uco = new UCObject();
		canvas.add(uco);
		uco.setLocation(e.getPoint());
		canvas.repaint();
	}
}