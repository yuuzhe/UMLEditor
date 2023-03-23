package main;

import java.awt.Point;
import java.awt.event.MouseEvent;

public interface State {
	public void onclick(Canvas canvas, MouseEvent e);
	public void onclick(BasicObject bo, MouseEvent e);
	public void onclick(Select so, MouseEvent e);
	public void pressed(Select so, MouseEvent e);
	public void pressed(BasicObject bo, MouseEvent e);
	public void pressed(Canvas canvas, MouseEvent e);
	public void dragged(BasicObject bo, MouseEvent e);
	public void dragged(Select so, MouseEvent e);
	public void dragged(Canvas canvas, MouseEvent e);
	public void released(Canvas canvas, MouseEvent e);
}

class SelectState implements State {
	// Previous mouse pressed point.
	private Point mousePt;
	private Canvas canvas;
	
	public SelectState(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		canvas.clearSelectedObjs();
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		
	}
	
	public void onclick(Select so, MouseEvent e) {
		so.setDepth(0);
		canvas.clearSelectedObjs();
		canvas.addtoSelected(so);
		so.select();
		so.setMI();
	}

	public void pressed(Canvas canvas, MouseEvent e) {
		canvas.setStartingPoint(e.getPoint());
	}

	public void pressed(BasicObject bo, MouseEvent e) {
	}
	
	public void pressed(Select so, MouseEvent e) {
		if (so.isSelected())
			this.mousePt = e.getPoint();
	}
	
	public void dragged(BasicObject bo, MouseEvent e) {
		
	}

	public void dragged(Canvas canvas, MouseEvent e) {
		canvas.setEndingPoint(e.getPoint());
		canvas.repaint();
	}

	public void dragged(Select so, MouseEvent e) {
		if (so.isSelected()) {
			int dx = e.getX() - mousePt.x;
			int dy = e.getY() - mousePt.y;
			so.movebyOffset(dx, dy);
			canvas.repaint();
		}
	}
	
	public void released(Canvas canvas, MouseEvent e) {
		canvas.clearSelectedObjs();
		canvas.resetRect();
		canvas.repaint();
	}
}

class ALState implements State {
	public ALState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {

	}
	
	public void onclick(Select so, MouseEvent e) {
		
	}
	
	public void pressed(Select so, MouseEvent e) {
		// Ugly dispatch?
		try {
			pressed((BasicObject) so, e);
		} catch(Exception exception) {}
	}

	public void pressed(BasicObject bo, MouseEvent e) {
		Canvas canvas = UIComponent.canvas;
		ConnectionLine vl = canvas.getVL();
		int x = bo.getX() + e.getX();
		int y = bo.getY() + e.getY();

		vl.setStartingPoint(x, y);
		canvas.repaint();
	}

	public void pressed(Canvas canvas, MouseEvent e) {

	}

	public void dragged(BasicObject bo, MouseEvent e) {
		Canvas canvas = UIComponent.canvas;
		ConnectionLine vl = canvas.getVL();
		int x = bo.getX() + e.getX();
		int y = bo.getY() + e.getY();

		vl.setEndingPoint(x, y);
		canvas.repaint();
	}

	public void dragged(Select so, MouseEvent e) {
		try {
			dragged((BasicObject) so, e);
		} catch(Exception exception) {}
	}

	public void dragged(Canvas canvas, MouseEvent e) {
	}

	public void released(Canvas canvas, MouseEvent e) {

	}
}

class CLState implements State {
	public CLState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		
	}
	
	
	public void onclick(Select so, MouseEvent e) {
		
	}
	
	public void pressed(Select so, MouseEvent e) {
		
	}

	public void pressed(BasicObject bo, MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}
	

	public void dragged(BasicObject bo, MouseEvent e) {
		
	}

	public void dragged(Select so, MouseEvent e) {
		
	}

	public void dragged(Canvas canvas, MouseEvent e) {
		
	}

	public void released(Canvas canvas, MouseEvent e) {
		
	}
}

class ClassState implements State {
	public ClassState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		createClassObject(canvas, e.getX(), e.getY());
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		createClassObject(UIComponent.canvas, 
				bo.getX() + e.getX(), bo.getY() + e.getY());
	}
	
	
	public void onclick(Select so, MouseEvent e) {
		createClassObject(UIComponent.canvas, 
				so.getX() + e.getX(), so.getY() + e.getY());
	}

	private void createClassObject(Canvas canvas, int x, int y) {
		ClassObject co = new ClassObject();
		// Set the depth (z-index) to the lowest.
		canvas.add(co, 0);
		co.setLocation(x, y);
		canvas.repaint();
	}
	
	public void pressed(Select so, MouseEvent e) {
		
	}
	
	public void pressed(BasicObject bo, MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}

	public void dragged(BasicObject bo, MouseEvent e) {
		
	}

	public void dragged(Select so, MouseEvent e) {
		
	}
	
	public void dragged(Canvas canvas, MouseEvent e) {
		
	}

	public void released(Canvas canvas, MouseEvent e) {
		
	}
}

class GLState implements State {
	public GLState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {

	}
	
	
	public void onclick(Select so, MouseEvent e) {
		
	}
	
	public void pressed(Select so, MouseEvent e) {
		
	}

	public void pressed(BasicObject bo, MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}
	
	public void dragged(BasicObject bo, MouseEvent e) {
		
	}

	public void dragged(Select so, MouseEvent e) {
		
	}

	public void dragged(Canvas canvas, MouseEvent e) {
		
	}

	public void released(Canvas canvas, MouseEvent e) {
		
	}
}

class UCState implements State {
	public UCState() {
		
	}
	
	public void onclick(Canvas canvas, MouseEvent e) {
		createUCObject(canvas, e.getX(), e.getY());
	}
	
	public void onclick(BasicObject bo, MouseEvent e) {
		// The (x,y) is based on the BasicObject which listened the event.
		createUCObject(UIComponent.canvas, 
				bo.getX() + e.getX(), bo.getY() + e.getY());
	}
	
	
	public void onclick(Select so, MouseEvent e) {
		createUCObject(UIComponent.canvas, so.getX() + e.getX(), so.getY() + e.getY());
	}

	private void createUCObject(Canvas canvas, int x, int y) {
		UCObject uco = new UCObject();
		canvas.add(uco, 0);
		uco.setLocation(x, y);
		canvas.repaint();
	}
	
	public void pressed(Select so, MouseEvent e) {
		
	}
	
	public void pressed(BasicObject bo, MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}
	
	public void dragged(BasicObject bo, MouseEvent e) {

	}

	public void dragged(Select so, MouseEvent e) {

	}

	public void dragged(Canvas canvas, MouseEvent e) {
		
	}

	public void released(Canvas canvas, MouseEvent e) {
		
	}
}