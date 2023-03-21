package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CompositeObject extends JPanel implements Select {
	private boolean selected = false;
	private boolean grouped = false;
	private ArrayList<Select> members = new ArrayList<Select>();

	private int x0, y0 = -1;
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

		Select first = selected.get(0);
		first.setGrouped(true);
		this.x0 = first.getX() - 1;
		this.y0 = first.getY() - 1;
		this.x1 = first.getX() + first.getWidth() + 1;
		this.y1 = first.getY() + first.getHeight() + 1;
		this.members.add(first);

		selected.stream().skip(1).forEach((obj) -> {
			obj.setGrouped(true);
			this.x0 = Math.min(x0, obj.getX() - 1);
			this.y0 = Math.min(y0, obj.getY() - 1);
			this.x1 = Math.max(x1, obj.getX() + obj.getWidth() + 1);
			this.y1 = Math.max(y1, obj.getY() + obj.getHeight() + 1);
			this.members.add(obj);
		});
		
		UIComponent.canvas.clearSelectedObjs();
		// super.setBackground(new Color(62, 193, 115, 128));
		super.setLocation(x0, y0);
		super.setSize(x1 - x0, y1 - y0);
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

	public void select() {
		this.setBorder(true);
		this.selected = true;
	}

	public void unselect() {
		this.setBorder(false);
		this.selected = false;
	}

	public void movebyOffset(int dx, int dy) {
		for (Select member : this.members)
			member.movebyOffset(dx, dy);
		int x = this.getX();
		int y = this.getY();
		this.setLocation(x + dx, y + dy);
	}
	
	private void setBorder(boolean set) {
		int borderWidth = set ? 1 : 0;
		super.setBorder(BorderFactory.createLineBorder(Color.red, borderWidth));
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
