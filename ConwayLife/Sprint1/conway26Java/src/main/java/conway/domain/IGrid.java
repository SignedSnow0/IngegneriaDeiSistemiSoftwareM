package main.java.conway.domain;

public interface IGrid {
	public int getWidth();
	public int getHeight();
	
	public ICell getCell(int x, int y);
	public void setCell(int x, int y, ICell cell);
}
