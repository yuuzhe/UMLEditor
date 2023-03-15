package main;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
	private ButtonGroup buttonGroup;
	
	public ButtonsPanel() {
		this.buttonGroup = new ButtonGroup();
	}
	
	public void addtoButtonGroup(StateButton sb) {
		this.buttonGroup.add(sb);
	}
}
