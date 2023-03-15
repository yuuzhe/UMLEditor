package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private StateButton stateButton;
	
	public Canvas() {
		this.stateButton = null;
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e.getX(), e.getY());
			}
		});
	}
	
	public void setState(StateButton sb) {
		this.stateButton = sb;
	}
	
	private void onclick(int x, int y) {
		this.stateButton.onclick(this, x, y);
	}
}
