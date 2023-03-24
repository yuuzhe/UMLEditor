package main;

import java.awt.Graphics;

public abstract class ConnectionLine {
	public int x0, y0 = 0;
	public int x1, y1 = 0;
	
	public ConnectionLine() {}
	
	public void setStartingPoint(int x, int y) {
		x0 = x;
		y0 = y;
	}
	
	public void setEndingPoint(int x, int y) {
		x1 = x;
		y1 = y;
	}
	
	public void reset() {
		x0 = 0;
		y0 = 0;
		x1 = 0;
		y1 = 0;
	}
	
	protected static void copyPoints(ConnectionLine dest, ConnectionLine src) {
		dest.x0 = src.x0;
		dest.y0 = src.y0;
		dest.x1 = src.x1;
		dest.y1 = src.y1;
	}
	
	abstract public void draw(Graphics g);
	abstract public ConnectionLine clone();
}

class AL extends ConnectionLine {
	private int arrowW = 10;
	private int arrowH = 10;

	public AL() {}
	
	public void draw(Graphics g) {
		g.drawLine(x0, y0, x1, y1);

		// 三角形的點, 考慮線條角度
		int dx = x1 - x0, dy = y1 - y0;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - arrowW, xn = xm, ym = arrowH, yn = -arrowH, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x0;
        ym = xm*sin + ym*cos + y0;
        xm = x;

        x = xn*cos - yn*sin + x0;
        yn = xn*sin + yn*cos + y0;
        xn = x;

        g.drawLine(x1, y1, (int) xm, (int) ym);
        g.drawLine(x1, y1, (int) xn, (int) yn);
	}
	
	public ConnectionLine clone() {
		AL al = new AL();
		super.copyPoints(al, this);
		return al;
	}
}

class GL extends ConnectionLine {
	private int arrowW = 10;
	private int arrowH = 10;

	public GL() {}
	
	public void draw(Graphics g) {
		g.drawLine(x0, y0, x1, y1);
		
		// 三角形的點, 考慮線條角度
		int dx = x1 - x0, dy = y1 - y0;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - arrowW, xn = xm, ym = arrowH, yn = -arrowH, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x0;
        ym = xm*sin + ym*cos + y0;
        xm = x;

        x = xn*cos - yn*sin + x0;
        yn = xn*sin + yn*cos + y0;
        xn = x;

        int[] xpoints = {x1, (int) xm, (int) xn};
        int[] ypoints = {y1, (int) ym, (int) yn};

        g.fillPolygon(xpoints, ypoints, 3);
	}
	
	public ConnectionLine clone() {
		GL gl = new GL();
		super.copyPoints(gl, this);
		return gl;
	}
}

class CL extends ConnectionLine {
	private int diamondW = 10;
	private int diamondH = 10;

	public CL() {}
	
	public void draw(Graphics g) {
		g.drawLine(x0, y0, x1, y1);

		// 三角形的點, 考慮線條角度
		int dx = x1 - x0, dy = y1 - y0;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - diamondW, xn = xm, ym = diamondH, yn = -diamondH, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x0;
        ym = xm*sin + ym*cos + y0;
        xm = x;

        x = xn*cos - yn*sin + x0;
        yn = xn*sin + yn*cos + y0;
        xn = x;
        
        // 分點公式算出線上的點, 三角形的三個點與這個點連線即為一個菱形
        double xq = (diamondH*2/D)*x0 + ((D-diamondH*2)/D)*x1;
        double yq = (diamondH*2/D)*y0 + ((D-diamondH*2)/D)*y1;
   
        int[] xpoints = {x1, (int) xm, (int) xq, (int) xn};
        int[] ypoints = {y1, (int) ym, (int) yq, (int) yn};

        g.fillPolygon(xpoints, ypoints, 4);
	}
	
	public ConnectionLine clone() {
		CL cl = new CL();
		super.copyPoints(cl, this);
		return cl;
	}
}