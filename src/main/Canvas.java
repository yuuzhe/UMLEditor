package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private SelectRect selectRect = new SelectRect(0, 0, 0, 0);

	private State state;
	private ArrayList<Select> selectedObjs = new ArrayList<Select>();
	
	public Canvas() {
		this.state = new SelectState(this);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				released(e);
			}
		});

		this.addMouseMotionListener(new MouseAdapter( ) {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragged(e);
			}
		});
	}
	
	public State getState() {
		return this.state;
	}
	
	public void setState(State s) {
		// Reset selected before changing state.
		this.clearSelectedObjs();
		this.state = s;
	}
	
	public ArrayList<Select> getSelected() {
		return this.selectedObjs;
	}
	
	public void addtoSelected(Select s) {
		this.selectedObjs.add(s);
	}
	
	public void clearSelectedObjs() {
		for (Select obj : this.selectedObjs)
			obj.unselect();
		this.selectedObjs.clear();
		UIComponent.groupMI.setEnabled(false);
		UIComponent.ungroupMI.setEnabled(false);
		UIComponent.renameMI.setEnabled(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.selectRect.drawSelectRect(g);
	}
	
	public void setStartingPoint(Point point) {
		this.selectRect.setStartingPoint(point);
	}
	
	public void setEndingPoint(Point point) {
		this.selectRect.setEndingPoint(point);
	}
	
	public void resetRect() {
		this.clearSelectedObjs();
		int selectedCount = 0;

		for (int i = 0; i < this.getComponentCount(); i++) {
			Select obj = (Select) this.getComponent(i);
			if (!obj.isGrouped() && this.selectRect.in(obj)) {
				selectedCount++;
				obj.select();
				this.addtoSelected(obj);
			}
		}

		if (selectedCount > 1)
			UIComponent.groupMI.setEnabled(true);
		this.selectRect.reset();
	}
	
	private void onclick(MouseEvent e) {
		this.state.onclick(this, e);
	}
	
	private void pressed(MouseEvent e) {
		this.state.pressed(this, e);
	}

	private void dragged(MouseEvent e) {
		this.state.dragged(this, e);
	}
	
	private void released(MouseEvent e) {
		this.state.released(this, e);
	}

	private class SelectRect {
		private int x0, y0;
		private int x1, y1;
		
		public SelectRect(int x0, int y0, int x1, int y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}

		public void setStartingPoint(Point point) {
			this.x0 = (int) point.getX();
			this.y0 = (int) point.getY();
		}
		
		public void setEndingPoint(Point point) {
			this.x1 = (int) point.getX();
			this.y1 = (int) point.getY();
		}
	
		public void drawSelectRect(Graphics g) {
			int px = Math.min(x0,x1);
			int py = Math.min(y0,y1);
			int pw = Math.abs(x0-x1);
			int ph = Math.abs(y0-y1);
			g.setColor(new Color(62, 193, 115, 128));
			g.fillRect(px, py, pw, ph);
			g.drawRect(px, py, pw, ph);
		}
		
		public void reset() {
			this.x0 = 0;
			this.x1 = 0;
			this.y0 = 0;
			this.y1 = 0;
		}
		
		public boolean in(Select obj) {
			int minX = Math.min(x0, x1);
			int maxX = Math.max(x0, x1);
			int minY = Math.min(y0, y1);
			int maxY = Math.max(y0, y1);
			
			int objX    = obj.getX();
			int objY    = obj.getY();
			int objEndX = objX + obj.getWidth();
			int objEndY = objY + obj.getHeight();

			return objX > minX && objEndX < maxX && 
					objY > minY && objEndY < maxY;
		}
	}
}
