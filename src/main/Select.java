package main;

public interface Select {
	public boolean isSelected();
	public void select();
	public void unselect();
	public void toggleSelect();
	public void movebyOffset(int dx, int dy);
}
