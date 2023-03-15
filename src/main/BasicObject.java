package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BasicObject extends JLabel {
	public BasicObject() {
		
	}
}

class ClassObject extends BasicObject {
	public ClassObject() {
		this.setSize(64, 64);
		this.setIcon(new ImageIcon("assets\\class.png"));
	}
}

class UCObject extends BasicObject {
	public UCObject() {
		this.setSize(64, 64);
		this.setIcon(new ImageIcon("assets\\uc.png"));
	}
}