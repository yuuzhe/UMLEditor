package main;

import javax.swing.JToggleButton;

abstract public class StateButton extends JToggleButton {
	abstract void onclick(Canvas canvas, int x, int y);
}