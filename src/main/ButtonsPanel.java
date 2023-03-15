package main;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonsPanel extends JPanel {
	private ButtonGroup buttonGroup;
	
	public ButtonsPanel() {
		this.buttonGroup = new ButtonGroup();
	}
	
	public void addtoButtonGroup(JToggleButton button) {
		this.buttonGroup.add(button);
	}
}
