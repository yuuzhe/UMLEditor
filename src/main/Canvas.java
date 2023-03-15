package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private State state;
	
	public Canvas() {
		this.state = new SelectState();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
			}
		});
	}
	
	public void setState(State s) {
		this.state = s;
	}
	
	private void onclick(MouseEvent e) {
		this.state.onclick(this, e);
	}
}
