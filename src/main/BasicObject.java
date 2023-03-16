package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BasicObject extends JLabel {
	protected boolean selected;
	
	public BasicObject(String iconPath) {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
			}
		});
		this.selected = false;
		this.setSize(64, 64);
		this.setIcon(new ImageIcon(iconPath));
	}
	
	protected void onclick(MouseEvent e) {
		Canvas canvas = (Canvas) this.getParent();
		canvas.getState().onclick(this, e);
	}
	
	public void toggleSelected() {
		selected = !selected;
	}
}

class ClassObject extends BasicObject {
	public ClassObject() {
		super("assets\\class.png");
	}
}

class UCObject extends BasicObject {
	public UCObject() {
		super("assets\\uc.png");
	}
}