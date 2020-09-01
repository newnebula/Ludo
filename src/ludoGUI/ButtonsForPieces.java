package ludoGUI;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import ludo.Piece;
import ludo.Player;


public class ButtonsForPieces {

	// Provides connection between pieces and their buttons.
	public static Map<Piece, JButton> PiecesAndButtons = new HashMap<Piece, JButton>();

	//Used to enable/disable the pieces that can't play in a given round for a human player.
	public static void setActivePieces(List<Piece> activePieces) {
	    Iterator<Entry<Piece, JButton>> it = PiecesAndButtons.entrySet().iterator();
	    while (it.hasNext()) {
			Map.Entry<Piece,JButton> pair = it.next();
	        if (activePieces.contains(pair.getKey())){
	        	pair.getValue().setEnabled(true);
	        	//System.out.println("Setting piece enabled");
	        } else {
	        	pair.getValue().setEnabled(false);
	        	//System.out.println("Setting piece disabled");
	        }
	    }
	    //System.out.println("Done setting");
	}


	// This method draws the buttons on the board in the start configuration.
	public static void drawPiecesToStart(JFrame frame, int pieceSize) {

		for(Player player:RunTheGame.ThePlayers) {
			for(Piece piece:player.getMyPieces()) {
				JButton ButtonForPiece = new JButton();
				ButtonForPiece.setBackground(piece.getPieceColor().getGraphColor());

				Coordinates c = Board.beginCoordinates.get(piece.getPieceColor().getGraphColor());
				ButtonForPiece.setBounds(c.getX(), c.getY(), pieceSize, pieceSize);
				ButtonForPiece.setBorder(new LineBorder(Color.BLACK));
				ButtonForPiece.addActionListener(new ButtonListener(piece));

				PiecesAndButtons.put(piece, ButtonForPiece);

				BoardShower.getLayeredPane().add(ButtonForPiece, 0);
			}
		}
	}
}
