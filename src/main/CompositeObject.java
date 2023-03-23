package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CompositeObject extends JPanel implements Select {
	private boolean selected = false;
	private boolean grouped = false;
	private ArrayList<Select> members = new ArrayList<Select>();

	private int x0 = UIComponent.canvas.getWidth();
	private int y0 = UIComponent.canvas.getHeight();
	private int x1, y1 = -1;
	
	public CompositeObject(ArrayList<Select> selected) {
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
				UIComponent.ungroupMI.setEnabled(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}
		});
		
		super.addMouseMotionListener(new MouseAdapter( ) {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragged(e);
			}
		});

		for (Select obj : selected) {
			obj.setGrouped(true);
			this.x0 = Math.min(x0, obj.getX() - 1);
			this.y0 = Math.min(y0, obj.getY() - 1);
			this.x1 = Math.max(x1, obj.getX() + obj.getWidth() + 1);
			this.y1 = Math.max(y1, obj.getY() + obj.getHeight() + 1);
			this.members.add(obj);
		}
		
		UIComponent.canvas.clearSelectedObjs();
		super.setBackground(new Color(193, 62, 140, 128));
		super.setLocation(x0, y0);
		super.setSize(x1 - x0, y1 - y0);
		super.repaint();
	}
	
	public boolean isGrouped() {
		return this.grouped;
	}
	
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	
	public void ungroup() {
		for (Select obj : this.members)
			obj.setGrouped(false);
		UIComponent.canvas.remove(this);
		UIComponent.canvas.repaint();
	}
	
	public boolean isSelected() {
		return this.selected;
	}

	public void setDepth(int depth) {
		UIComponent.canvas.setComponentZOrder(this, 0);
	}
	
	public void setMI() {
		UIComponent.ungroupMI.setEnabled(true);
	}

	public void select() {
		this.selected = true;
		super.setBackground(new Color(62, 193, 140, 128));
		UIComponent.canvas.repaint();
	}

	public void unselect() {
		this.selected = false;
		super.setBackground(new Color(193, 62, 140, 128));
		UIComponent.canvas.repaint();
	}

	public void movebyOffset(int dx, int dy) {
		for (Select member : this.members)
			member.movebyOffset(dx, dy);
		int x = this.getX();
		int y = this.getY();
		this.setLocation(x + dx, y + dy);
	}
	
	private void onclick(MouseEvent e) {
		UIComponent.canvas.getState().onclick((Select) this, e);
	}

	private void pressed(MouseEvent e) {
		if (this.selected)
			UIComponent.canvas.getState().pressed(e);
	}

	private void dragged(MouseEvent e) {
		if (this.selected)
			UIComponent.canvas.getState().dragged((Select) this, e);
	}
}
