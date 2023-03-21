package main;

import javax.swing.JMenuItem;

// The almighty global UI component getter.
public class UIComponent {
	public static Canvas canvas;
	public static JMenuItem groupMI;
	public static JMenuItem ungroupMI;
	public static JMenuItem renameMI;

	public static void setCanvas(Canvas canv) {
		canvas = canv;
	}
	
	public static void setGroupMI(JMenuItem gMI) {
		groupMI = gMI;
	}

	public static void setUngroupMI(JMenuItem ugMI) {
		ungroupMI = ugMI;
	}
	
	public static void setRenameMI(JMenuItem rMI) {
		renameMI = rMI;
	}
}
