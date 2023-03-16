package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

public interface State {
	public void onclick(Canvas canvas, MouseEvent e);
	public void onclick(BasicObject bo, MouseEvent e);
}

class SelectState implements State {
	public SelectState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		bo.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		bo.toggleSelected();
	}
}

class ALState implements State {
	public ALState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		
	}
}

class CLState implements State {
	public CLState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		
	}
}

class ClassState implements State {
	public ClassState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		createClassObject(canvas, e.getX(), e.getY());
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		Canvas canvas = (Canvas) bo.getParent();
		createClassObject(canvas, bo.getX() + e.getX(), bo.getY() + e.getY());
	}
	
	private void createClassObject(Canvas canvas, int x, int y) {
		ClassObject co = new ClassObject();
		// Set the depth (z-index) to the lowest.
		canvas.add(co, 0);
		co.setLocation(x, y);
		canvas.repaint();
	}
}

class GLState implements State {
	public GLState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {

	}
}

class UCState implements State {
	public UCState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		createUCObject(canvas, e.getX(), e.getY());
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		Canvas canvas = (Canvas) bo.getParent();
		createUCObject(canvas, bo.getX() + e.getX(), bo.getY() + e.getY());
	}
	
	private void createUCObject(Canvas canvas, int x, int y) {
		UCObject uco = new UCObject();
		canvas.add(uco, 0);
		uco.setLocation(x, y);
		canvas.repaint();
	}
}