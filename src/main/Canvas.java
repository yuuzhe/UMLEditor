package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private State state;
	private ArrayList<Select> selectedObjs = new ArrayList();
	
	public Canvas() {
		this.state = new SelectState(this);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
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
	
	public void addtoSelected(Select s) {
		this.selectedObjs.add(s);
	}
	
	public void clearSelectedObjs() {
		for (Select obj : this.selectedObjs)
			obj.unselect();
		this.selectedObjs.clear();
	}
	
	private void onclick(MouseEvent e) {
		this.state.onclick(this, e);
	}
}
