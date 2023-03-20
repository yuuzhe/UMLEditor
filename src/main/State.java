package main;

import java.awt.Point;
import java.awt.event.MouseEvent;

public interface State {
	public void onclick(Canvas canvas, MouseEvent e);
	public void onclick(BasicObject bo, MouseEvent e);
	public void onclick(Select selectObj, MouseEvent e);
	public void pressed(MouseEvent e);
	public void pressed(Canvas canvas, MouseEvent e);
	public void dragged(Select bo, MouseEvent e);
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
		// Workaround.
		this.onclick((Select) bo, e);
	}
	
	public void onclick(Select selectObj, MouseEvent e) {
		selectObj.setDepth(0);
		canvas.clearSelectedObjs();
		canvas.addtoSelected(selectObj);
		selectObj.select();
	}

	public void pressed(Canvas canvas, MouseEvent e) {
		canvas.setStartingPoint(e.getPoint());
	}

	public void pressed(MouseEvent e) {
		// You still pressing when you are dragging.
		this.mousePt = e.getPoint();
	}

	public void dragged(Canvas canvas, MouseEvent e) {
		canvas.setEndingPoint(e.getPoint());
		canvas.repaint();
	}

	public void dragged(Select selectObj, MouseEvent e) {
		int dx = e.getX() - mousePt.x;
		int dy = e.getY() - mousePt.y;
		selectObj.movebyOffset(dx, dy);
		canvas.repaint();
	}
	
	public void released(Canvas canvas, MouseEvent e) {
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
	
	public void onclick(Select selectObj, MouseEvent e) {
		
	}

	public void pressed(MouseEvent e) {
		
	}

	public void pressed(Canvas canvas, MouseEvent e) {
		
	}

	public void dragged(Select selectObj, MouseEvent e) {
		
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
	
	
	public void onclick(Select selectObj, MouseEvent e) {
		
	}

	public void pressed(MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}
	
	public void dragged(Select selectObj, MouseEvent e) {
		
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
		Canvas canvas = (Canvas) bo.getParent();
		createClassObject(canvas, bo.getX() + e.getX(), bo.getY() + e.getY());
	}
	
	
	public void onclick(Select selectObj, MouseEvent e) {
		
	}

	private void createClassObject(Canvas canvas, int x, int y) {
		ClassObject co = new ClassObject();
		// Set the depth (z-index) to the lowest.
		canvas.add(co, 0);
		co.setLocation(x, y);
		canvas.repaint();
	}
	
	public void pressed(MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}

	public void dragged(Select selectObj, MouseEvent e) {
		
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
	
	
	public void onclick(Select selectObj, MouseEvent e) {
		
	}

	public void pressed(MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}
	
	public void dragged(Select selectObj, MouseEvent e) {
		
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
		Canvas canvas = (Canvas) bo.getParent();
		// The (x,y) is based on the BasicObject which listened the event.
		createUCObject(canvas, bo.getX() + e.getX(), bo.getY() + e.getY());
	}
	
	
	public void onclick(Select selectObj, MouseEvent e) {
		
	}

	private void createUCObject(Canvas canvas, int x, int y) {
		UCObject uco = new UCObject();
		canvas.add(uco, 0);
		uco.setLocation(x, y);
		canvas.repaint();
	}
	
	public void pressed(MouseEvent e) {
		
	}
	
	public void pressed(Canvas canvas, MouseEvent e) {
		
	}
	
	public void dragged(Select selectObj, MouseEvent e) {
		
	}

	public void dragged(Canvas canvas, MouseEvent e) {
		
	}

	public void released(Canvas canvas, MouseEvent e) {
		
	}
}