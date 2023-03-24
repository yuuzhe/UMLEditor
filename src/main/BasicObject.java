package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class BasicObject extends JLabel implements Select {
	private ImageIcon selectedIcon = new ImageIcon();
	private ImageIcon unselectedIcon = new ImageIcon();

	protected boolean selected = false;
	protected boolean grouped = false;

	protected Port[] ports = new Port[4];

	public BasicObject(String unselectedIconPath, String selectedIconPath) {
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onclick(e);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				released(e);
			}
		});
		
		super.addMouseMotionListener(new MouseAdapter( ) {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragged(e);
			}
		});
		
		this.selectedIcon = new ImageIcon(selectedIconPath);
		this.unselectedIcon = new ImageIcon(unselectedIconPath);
		
		super.setIcon(this.unselectedIcon);
	}
	
	public boolean isGrouped() {
		return this.grouped;
	}
	
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	
	// The implementation of Select interface
	public void setDepth(int depth) {
		UIComponent.canvas.setComponentZOrder(this, 0);
	}
	
	public void setMI() {
		UIComponent.renameMI.setEnabled(true);
	}
	
	public boolean isSelected() {
		return this.selected;
	}

	public void select() {
		this.selected = true;
		setIcon(this.selectedIcon);
	}
	
	public void unselect() {
		this.selected = false;
		setIcon(this.unselectedIcon);
	}
	
	public void movebyOffset(int dx, int dy) {
		this.setLocation(this.getX() + dx, this.getY() + dy);
		for (Port port : this.ports)
			port.movebyOffset(dx, dy);
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);
	}
	
	public void setFromLine(ConnectionLine cl) {
		for (Port port : this.ports) {
			int offsetX = cl.x0 - this.getX();
			int offsetY = cl.y0 - this.getY();

			if (port.inRegion(offsetX, offsetY)) {
				cl.setStartingPoint(this.getX() + port.x, this.getY() + port.y);
				port.addFL(cl);
				break;
			}
		}
	}
	
	public void setToLine(ConnectionLine cl) {
		for (Port port : this.ports) {
			int offsetX = cl.x1 - this.getX();
			int offsetY = cl.y1 - this.getY();

			if (port.inRegion(offsetX, offsetY)) {
				cl.setEndingPoint(this.getX() + port.x, this.getY() + port.y);
				port.addTL(cl);
				break;
			}
		}
	}
	
	protected void onclick(MouseEvent e) {
		UIComponent.canvas.getState().onclick((Select) this, e);
	}
	
	protected void pressed(MouseEvent e) {
		UIComponent.canvas.getState().pressed((Select) this, e);
	}
	
	protected void dragged(MouseEvent e) {
		UIComponent.canvas.getState().dragged((Select) this, e);
	}
	
	protected void released(MouseEvent e) {
		UIComponent.canvas.getState().released(this, e);
	}

	protected class Port {
		// Port position.
		int x, y;
		int xs[] = new int[3];
		int ys[] = new int[3];
		
		private ArrayList<ConnectionLine> fromLines = new ArrayList<ConnectionLine>();
		private ArrayList<ConnectionLine> toLines = new ArrayList<ConnectionLine>();

		public Port(int x, int y, int[] xs, int[] ys) {
			for (int i = 0; i < 3; i++) {
				this.x = x;
				this.y = y;
				this.xs[i] = xs[i];
				this.ys[i] = ys[i];
			}
		}
		
		public void addFL(ConnectionLine cl) {
			this.fromLines.add(cl);
		}

		public void addTL(ConnectionLine cl) {
			this.toLines.add(cl);
		}

		public boolean inRegion(int x, int y) {
	        double d1, d2, d3;
	        boolean has_neg, has_pos;

	        d1 = sign(x, y, xs[0], ys[0], xs[1], ys[1]);
	        d2 = sign(x, y, xs[1], ys[1], xs[2], ys[2]);
	        d3 = sign(x, y, xs[0], ys[0], xs[2], ys[2]);

	        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
	        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);
	        
	        return !(has_neg && has_pos);
		}
		
		public void movebyOffset(int dx, int dy) {
			for (ConnectionLine cl : this.fromLines)
				cl.setStartingPoint(cl.x0 + dx, cl.y0 + dy);
			for (ConnectionLine cl : this.toLines)
				cl.setEndingPoint(cl.x1 + dx, cl.y1 + dy);
		}

	    private static double sign(int x1, int y1, int x2, int y2, int x3, int y3)
	    {
	       return (x1 - x3)*(y2 - y3) - (x2 - x3)*(y1 - y3);
	    }
		
	}
}

class ClassObject extends BasicObject {
	public ClassObject() {
		super("assets\\class.png", "assets\\selected_class.png");

		int w = 64;
		int h = 64;
		super.setSize(w, h);
		
		// ???
		int[] x = {0, w/2, w, w/2};
		int[] y = {h/2, 0, h/2, h};
		
		int[][] xss = { {0, w, w/2}, {w, w, w/2}, {w, 0, w/2}, {0, 0, w/2}, };
		int[][] yss = { {0, 0, h/2}, {0, h, h/2}, {h, h, h/2}, {h, 0, h/2}, };

		
		for (int i = 0; i < 4; i++)
			this.ports[i] = new Port(x[i], y[i], xss[i], yss[i]);
	}
}

class UCObject extends BasicObject {
	public UCObject() {
		super("assets\\uc.png", "assets\\selected_uc.png");

		int w = 64;
		int h = 44;
		super.setSize(w, h);
		
		// ???
		int[] x = {0, w/2, w, w/2};
		int[] y = {h/2, 0, h/2, h};
		
		int[][] xss = { {0, w, w/2}, {w, w, w/2}, {w, 0, w/2}, {0, 0, w/2}, };
		int[][] yss = { {0, 0, h/2}, {0, h, h/2}, {h, h, h/2}, {h, 0, h/2}, };

		
		for (int i = 0; i < 4; i++)
			this.ports[i] = new Port(x[i], y[i], xss[i], yss[i]);
	}
}