package conway.io;

import main.java.conway.domain.Cell;
import main.java.conway.domain.GameController;
import main.java.conway.domain.Grid;
import main.java.conway.domain.IOutDev;

public class OutInWs implements IOutDev {
	public OutInWs() {
		javalin.setOutInWs(this);
	}
	
	public GameController getController() {
		return controller;
	}
	
	public void setController(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void display(String msg) {
		javalin.getContext().send(msg);
	}

	@Override
	public void displayCell(Cell cell, Grid grid) {
		System.out.println("Displaycell");
	}

	@Override
	public void close() {
		javalin.getContext().closeSession();
	}

	@Override
	public void displayGrid(Grid grid) {
		if (javalin.getContext() == null) {
			return;
		}
	
		for (int row = 0; row < grid.getWidth(); row++) {
			for (int column = 0; column < grid.getHeight(); column++) {
				var cellstate = controller.getLife().getCell(row, column).isAlive() ? "1" : "0";
				javalin.getContext().send("cell(" + row + "," + column + "," + cellstate + ")");
			}
		}
	}
	
	private IoJavalin javalin = new IoJavalin();
	private GameController controller;
}
