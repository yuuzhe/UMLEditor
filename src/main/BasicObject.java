package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class BasicObject extends JLabel implements Select {
	private ImageIcon selectedIcon = new ImageIcon();
	private ImageIcon unselectedIcon = new ImageIcon();
	protected Port[] ports = new Port[4];
	protected boolean selected = false;
	protected boolean grouped = false;
	
	public BasicObject(String unselectedIconPath, String selectedIconPath) {
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
				UIComponent.renameMI.setEnabled(true);
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
		
		this.selectedIcon = new ImageIcon(selectedIconPath);
		this.unselectedIcon = new ImageIcon(unselectedIconPath);
		
		super.setIcon(this.unselectedIcon);
	}
	
	public boolean isGrouped() {
		return this.grouped;
	}
	
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	
	// The implementation of Select interface
	public void setDepth(int depth) {
		UIComponent.canvas.setComponentZOrder(this, 0);
	}
	
	public void setMI() {
		UIComponent.renameMI.setEnabled(true);
	}
	
	public boolean isSelected() {
		return this.selected;
	}

	public void select() {
		this.selected = true;
		this.showPort();
	}
	
	public void unselect() {
		this.selected = false;
		this.hidePort();
	}
	
	public void movebyOffset(int dx, int dy) {
		int x = this.getX();
		int y = this.getY();
		this.setLocation(x + dx, y + dy);
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);
	}
	
	public void showPort() {
		setIcon(this.selectedIcon);
	}
	
	public void hidePort() {
		setIcon(this.unselectedIcon);
	}
	
	protected void onclick(MouseEvent e) {
		UIComponent.canvas.getState().onclick((Select) this, e);
	}
	
	protected void pressed(MouseEvent e) {
		if (this.selected)
			UIComponent.canvas.getState().pressed(e);
	}
	
	protected void dragged(MouseEvent e) {
		if (this.selected)
			UIComponent.canvas.getState().dragged((Select) this, e);
	}
	
}

class Port {
	private ArrayList<ConnectionLine> lines = new ArrayList();
	
	public Port() {
		
	}
}

class ClassObject extends BasicObject {
	public ClassObject() {
		super("assets\\class.png", "assets\\selected_class.png");
		super.setSize(64, 64);
	}
}

class UCObject extends BasicObject {
	public UCObject() {
		super("assets\\uc.png", "assets\\selected_uc.png");
		// Ugly workaround
		super.setSize(64, 44);
	}
}