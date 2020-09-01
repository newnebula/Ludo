package ludoGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ludo.Dice;
import ludo.Piece;
import ludo.PieceColor;

public class ButtonListener implements ActionListener {

	public ButtonListener(Piece aPiece) {
		myPiece = aPiece;
	}

	private Piece myPiece;

	@Override
	public void actionPerformed(ActionEvent e) {
		PieceColor validColor = RunTheGame.currentPlayerColor;

		if (myPiece.getPieceColor().equals(validColor)) {
			Piece kickedOutPiece = myPiece.goAndPlay(Dice.diceResult,RunTheGame.ThePlayers);

			JButton myButton= (JButton) e.getSource();
			Coordinates c = Board.boardMap.get(myPiece.calculateMyCurrentAbsField());
			myButton.setBounds(c.getX(), c.getY(), BoardShower.FIELDSIZE, BoardShower.FIELDSIZE);

			if(kickedOutPiece != null) {
				if(kickedOutPiece != myPiece) {
					JButton kickedButton = ButtonsForPieces.PiecesAndButtons.get(kickedOutPiece);
					Coordinates d = Board.beginCoordinates.get(kickedOutPiece.getPieceColor().getGraphColor());
					kickedButton.setBounds(d.getX(), d.getY(), BoardShower.FIELDSIZE, BoardShower.FIELDSIZE);
				}
			}
			RunTheGame.semaphore.release();
		}
	}
}
