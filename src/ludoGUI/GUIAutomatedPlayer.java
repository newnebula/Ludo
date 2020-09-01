package ludoGUI;
import javax.swing.JButton;

import ludo.AutomatedPlayer;
import ludo.Dice;
import ludo.Piece;
import ludo.PieceColor;

public class GUIAutomatedPlayer extends AutomatedPlayer {

	// In the constructor, the name is set and a list of four pieces of the right color is created.
	public GUIAutomatedPlayer(String aName, PieceColor aColor){
		super(aName, aColor);
	}

	// Play contains actions of the currently playing player.
	@Override
	public void play() {

		Piece autoSelectedPiece = this.selectPiece();

		if (autoSelectedPiece != null) {
			Piece kickedOutPiece = autoSelectedPiece.goAndPlay(Dice.diceResult,RunTheGame.ThePlayers);

			JButton myButton= ButtonsForPieces.PiecesAndButtons.get(autoSelectedPiece);
			Coordinates c = Board.boardMap.get(autoSelectedPiece.calculateMyCurrentAbsField());
			myButton.setBounds(c.getX(), c.getY(), BoardShower.FIELDSIZE, BoardShower.FIELDSIZE);

			if(kickedOutPiece != null) {
				if(kickedOutPiece != autoSelectedPiece) {
					JButton kickedButton = ButtonsForPieces.PiecesAndButtons.get(kickedOutPiece);
					Coordinates d = Board.beginCoordinates.get(kickedOutPiece.getPieceColor().getGraphColor());
					kickedButton.setBounds(d.getX(), d.getY(), BoardShower.FIELDSIZE, BoardShower.FIELDSIZE);
				}
			}

			// Checks whether all the pieces are safe.
			super.didIwin();
		} else {
			// No pieces to play, next player
		}
	}

}
