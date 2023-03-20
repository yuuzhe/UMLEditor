package main;

public interface Select {
	public boolean isSelected();
	public void setDepth(int depth);
	public void select();
	public void unselect();
	public void movebyOffset(int dx, int dy);
	
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
}
