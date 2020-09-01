package ludo;
//PieceColor holds information about:
//- the absolute starting point of the piece,
//- the starting field number when a piece is already at home, safe from other players
//- display color - GUI
//- display coordinates of the starting point - GUI

import java.awt.Color;

public enum PieceColor {
	GREEN(0, 2000, Color.GREEN,100,150),
	YELLOW(10, 3000, Color.YELLOW, 700,150),
	BLUE(20, 4000, Color.BLUE, 700,750),
	RED(30, 5000, Color.RED, 100,750);

	private int startingPositionX;
	private int startingPositionY;
	private int absoluteStartField;
	private int absFilednumberWhileSafe;
	private Color graphColor;

	PieceColor(int absStart, int aFwS, Color graphColor, int xBegin, int yBegin) {
		this.absoluteStartField = absStart;
		this.absFilednumberWhileSafe = aFwS;
		this.graphColor = graphColor;
		this.startingPositionX = xBegin;
		this.startingPositionY = yBegin;
	}

	public int getAbsoluteStartField() {
		return absoluteStartField;
	}
	public int getAbsFilednumberWhileSafe() {
		return absFilednumberWhileSafe;
	}
	public Color getGraphColor() {
		return graphColor;
	}
	public int getStartingPositionX() {
		return startingPositionX;
	}
	public int getStartingPositionY() {
		return startingPositionY;
	}
}
