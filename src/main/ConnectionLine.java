package main;

public abstract class ConnectionLine {
	int x0, y0 = 0;
	int x1, y1 = 0;
	
	public ConnectionLine() {
	}
	
	public void setStartingPoint(int x, int y) {
		x0 = x;
		y0 = y;
	}
	
	public void setEndingPoint(int x, int y) {
		x1 = x;
		y1 = y;
	}
	
	public void reset() {
		this.x0 = 0;
		this.y0 = 0;
		this.x1 = 0;
		this.y1 = 0;
	}

	abstract void drawLine();
}

class AL extends ConnectionLine {
	public AL() {

	}
	
	public void drawLine() {
		
	}
}

class GL extends ConnectionLine {
	public GL() {

	}
	
	public void drawLine() {
		
	}
}

class CL extends ConnectionLine {
	public CL() {

	}
	
	public void drawLine() {
		
	}
}