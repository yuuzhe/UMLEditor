package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class BasicObject extends JLabel implements Select {
	private ImageIcon icon = new ImageIcon();
	protected Port[] ports = new Port[4];
	protected boolean selected = false;
	
	public BasicObject(String iconPath) {
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter( ) {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragged(e);
			}
		});
		
		this.icon = new ImageIcon(iconPath);
		
		super.setSize(64, 64);
		super.setIcon(this.icon);
	}
	
	protected void onclick(MouseEvent e) {
		Canvas canvas = (Canvas) this.getParent();
		canvas.getState().onclick((Select) this, e);
	}
	
	protected void pressed(MouseEvent e) {
		Canvas canvas = (Canvas) this.getParent();
		canvas.getState().pressed(e);
	}
	
	protected void dragged(MouseEvent e) {
		Canvas canvas = (Canvas) this.getParent();
		canvas.getState().dragged((Select) this, e);
	}
	
	// The implementation of Select interface
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
	
	public void toggleSelect() {
		if (isSelected()) {
			unselect();
		} else {
			select();
		}
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
	
	public abstract void showPort();
	public abstract void hidePort();
}

class Port {
	private ArrayList<ConnectionLine> lines = new ArrayList();
	
	public Port() {
		
	}
}

class ClassObject extends BasicObject {
	public ClassObject() {
		super("assets\\class.png");
	}
	
	public void showPort() {
		this.setText("12345678");
	}
	
	public void hidePort() {
		this.setText("");
	}
}

class UCObject extends BasicObject {
	public UCObject() {
		super("assets\\uc.png");
	}

	public void showPort() {

	}

	public void hidePort() {
		
	}
}