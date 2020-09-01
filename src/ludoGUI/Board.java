package ludoGUI;
//Board is a background upon which buttons are added.
//As fields of the board are drawn,
//their coordinates are stored in maps (coordinates - absolute field number).
//These will be used to draw pieces in the appropriate locations.
//All elements of the board are rescaled to a constant FIELDSIZE.
//The value is injected from the static BoardShower.

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Board extends JPanel{
	// Since Board extends JPanel.
	private static final long serialVersionUID = 1L;

	// This list stores the mapping between the field number and the coordinates of a point on graphical board.
	public static Map<Integer,Coordinates> boardMap = new HashMap<Integer,Coordinates>();
	public static Map<Color,Coordinates> beginCoordinates = new HashMap<Color,Coordinates>();

	private int FIELDSIZE;
	private static int STEP;
	private static int FIELDSIZE2;
	private static int MARGIN;

	public Board(int aFieldSize) {
		FIELDSIZE = aFieldSize;
		STEP = 2*FIELDSIZE;
		FIELDSIZE2 = 2 * FIELDSIZE;
		MARGIN = 10*FIELDSIZE;
		makeMaps();
	}

	@Override
	public void paintComponent(Graphics g){
		// To avoid graphical button artifacts
		// https://stackoverflow.com/questions/13281092/visual-artifacts-appearing-on-jpanel
		super.paintComponent(g);

		int i;
		for(i=0;i<10;i++) {
			g.setColor(Color.WHITE);
			g.fillOval(MARGIN+i*STEP, MARGIN, FIELDSIZE, FIELDSIZE);
			boardMap.put(i+1, new Coordinates(MARGIN+i*STEP, MARGIN));
		}

		for(i=0;i<10;i++) {
			g.setColor(Color.WHITE);
			g.fillOval(MARGIN+10*STEP , MARGIN+STEP+i*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(i+11, new Coordinates(MARGIN+10*STEP, MARGIN+STEP+i*STEP));
		}

		for(i=0;i<10;i++) {
			g.setColor(Color.WHITE);
			g.fillOval(MARGIN+i*STEP , MARGIN+11*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(30-i, new Coordinates(MARGIN+i*STEP, MARGIN+11*STEP));
		}


		for(i=0;i<10;i++) {
			g.setColor(Color.WHITE);
			g.fillOval(MARGIN-STEP , MARGIN+STEP+i*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(40-i, new Coordinates(MARGIN-STEP, MARGIN+STEP+i*STEP));
		}

		//Colored fields of home and start.
		for(i=0;i<4;i++) {
			g.setColor(Color.GREEN);
			g.fillOval(MARGIN+i*STEP , MARGIN+STEP+i*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(2001+i, new Coordinates(MARGIN+i*STEP, MARGIN+STEP+i*STEP));
		}

		g.setColor(Color.GREEN);
		g.fillOval(MARGIN+(-2)*STEP , MARGIN+(-2)*STEP+STEP, FIELDSIZE2, FIELDSIZE2);
		beginCoordinates.put(Color.GREEN, new Coordinates(MARGIN+(-2)*STEP , MARGIN+(-2)*STEP+STEP));


		for(i=6;i<10;i++) {
			g.setColor(Color.BLUE);
			g.fillOval(MARGIN+i*STEP , MARGIN+STEP+i*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(4004+6-i, new Coordinates(MARGIN+i*STEP, MARGIN+STEP+i*STEP));
		}

		g.setColor(Color.BLUE);
		g.fillOval(MARGIN+(10)*STEP , MARGIN+10*STEP+STEP, FIELDSIZE2, FIELDSIZE2);
		beginCoordinates.put(Color.BLUE, new Coordinates(MARGIN+(10)*STEP , MARGIN+10*STEP+STEP));

		for(i=0;i<4;i++) {
			g.setColor(Color.YELLOW);
			g.fillOval(MARGIN+9*STEP-i*STEP , MARGIN+STEP+i*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(3001+i, new Coordinates(MARGIN+9*STEP-i*STEP, MARGIN+STEP+i*STEP));
		}

		g.setColor(Color.YELLOW);
		g.fillOval(MARGIN+9*STEP-(-1)*STEP , MARGIN+(-2)*STEP+STEP, FIELDSIZE2, FIELDSIZE2);
		beginCoordinates.put(Color.YELLOW, new Coordinates(MARGIN+9*STEP-(-1)*STEP , MARGIN+(-2)*STEP+STEP));

		for(i=6;i<10;i++) {
			g.setColor(Color.RED);
			g.fillOval(MARGIN+9*STEP-i*STEP , MARGIN+STEP+i*STEP, FIELDSIZE, FIELDSIZE);
			boardMap.put(5004+6-i, new Coordinates(MARGIN+9*STEP-i*STEP, MARGIN+STEP+i*STEP));
		}

		g.setColor(Color.RED);
		g.fillOval(MARGIN-2*STEP , MARGIN+10*STEP+STEP, FIELDSIZE2, FIELDSIZE2);
		beginCoordinates.put(Color.RED, new Coordinates(MARGIN-2*STEP , MARGIN+10*STEP+STEP));
	}

	private static void makeMaps(){
		//White fields.
		int i;
		for(i=0;i<10;i++) {
			boardMap.put(i+1, new Coordinates(MARGIN+i*STEP, MARGIN));
		}
		for(i=0;i<10;i++) {
			boardMap.put(i+11, new Coordinates(MARGIN+10*STEP, MARGIN+STEP+i*STEP));
		}
		for(i=0;i<10;i++) {
			boardMap.put(30-i, new Coordinates(MARGIN+i*STEP, MARGIN+11*STEP));
		}
		for(i=0;i<10;i++) {
			boardMap.put(40-i, new Coordinates(MARGIN-STEP, MARGIN+STEP+i*STEP));
		}

		//Colored fields of home and start.
		for(i=0;i<4;i++) {
			boardMap.put(2001+i, new Coordinates(MARGIN+i*STEP, MARGIN+STEP+i*STEP));
		}
		beginCoordinates.put(Color.GREEN, new Coordinates(MARGIN+(-2)*STEP , MARGIN+(-2)*STEP+STEP));

		for(i=6;i<10;i++) {
			boardMap.put(4004+6-i, new Coordinates(MARGIN+i*STEP, MARGIN+STEP+i*STEP));
		}
		beginCoordinates.put(Color.BLUE, new Coordinates(MARGIN+(10)*STEP , MARGIN+10*STEP+STEP));

		for(i=0;i<4;i++) {
			boardMap.put(3001+i, new Coordinates(MARGIN+9*STEP-i*STEP, MARGIN+STEP+i*STEP));
		}
		beginCoordinates.put(Color.YELLOW, new Coordinates(MARGIN+9*STEP-(-1)*STEP , MARGIN+(-2)*STEP+STEP));

		for(i=6;i<10;i++) {
			boardMap.put(5004+6-i, new Coordinates(MARGIN+9*STEP-i*STEP, MARGIN+STEP+i*STEP));
		}
		beginCoordinates.put(Color.RED, new Coordinates(MARGIN-2*STEP , MARGIN+10*STEP+STEP));
	}
}
