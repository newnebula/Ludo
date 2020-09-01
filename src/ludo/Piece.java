package ludo;

import java.util.List;

public class Piece {
	Piece(PieceColor aColor){
		pieceColor = aColor;
		myCurrentOwnfield = 0;
	}

	//PieceColor in an enum which holds information about absolute starting point for each color
	//as well as some info for painting the pieces on the board (GUI).
	private PieceColor pieceColor;
	public PieceColor getPieceColor() {
		return this.pieceColor;
	}

	//This piece keeps track of it's CURRENT distance from its OWN start point.
	private int myCurrentOwnfield;
	public int getMyCurrentOwnfield() {
		return(myCurrentOwnfield);
	}

	//This piece calculates its NEXT OWN field, should this piece be chosen to move.
	private int myNextOwnField;

	//Is this piece already at home?
	public boolean isHome() {
		return (this.calculateMyCurrentAbsField()>=1000);
	}

	// Checking whether this piece has already started playing.
	public boolean hasStarted() {
		if (this.myCurrentOwnfield==0) {
			return false;
		}else{
			return true;
		}
	}

	// When this piece is chosen to move. Ascribes a new field number to the chosen piece,
	// checks whether an other piece had been sent back to start, returns this piece.
	public Piece goAndPlay(int diceResult, List<Player> players) {
		this.calculateMyNextOwnField(diceResult);
		Piece kickedOut = this.kickingBack(players);
		this.movePiece();

		return kickedOut;
	}

	//ABSOLUTE fields are used while a piece sends another piece back home,
	//OWN fields are needed to check whether a piece has already reached home,
	//where it is save from other players pieces.
	private void calculateMyNextOwnField (int diceResult){
		myNextOwnField = myCurrentOwnfield + diceResult;
		if(myNextOwnField > 44) {
			myNextOwnField = (44 - (myNextOwnField-44));
		}
	}

	public int calculateMyCurrentAbsField() {
		AbsFieldCalculator calculator = new AbsFieldCalculator(this.myCurrentOwnfield, this.pieceColor);
		int myAbsCurrField = calculator.calculateAbsField();

		return myAbsCurrField;
	}

	private int calculateMyNextAbsField() {
		AbsFieldCalculator calculator = new AbsFieldCalculator(this.myNextOwnField, this.pieceColor);
		int myAbsCurrField = calculator.calculateAbsField();

		return myAbsCurrField;
	}

	//This piece checks for all other pieces whether it will kick one of them back to start.
	//The method returns the piece to be kicked out to goAndPlay(),
	//goAndPlay returns it to the ButtonListener.
	private Piece kickingBack(List<Player> players) {
		Piece kickedOut = null;
		for (Player player:players) {
			for (Piece p:player.getMyPieces()) {
				//Skip self and pieces at home/start field
				if (this == p || p.myCurrentOwnfield == 0) {
					continue;
				} else {
					if (this.calculateMyNextAbsField() == p.calculateMyCurrentAbsField()) {
						p.sendBackToStart();
						System.out.println("Just sent a piece back to start! Its pieceColor was " + p.pieceColor);
						kickedOut = p;
					}
				}
			}
		}

		return(kickedOut);
	}

	//Move this piece forward i.e. assign myNextOwnField to myCurrentOwnfield.
	private void movePiece() {
		System.out.println("Moved from " + this.myCurrentOwnfield + " (absolute: " + this.calculateMyCurrentAbsField() + ") to " + this.myNextOwnField + " (absolute: " + this.calculateMyNextAbsField() + ")");
		this.myCurrentOwnfield = this.myNextOwnField;
	}

	//This piece goes back back to start cause it has been kicked out.
	//This method is only called in kickingBack() which is only called in goAndPlay().
	private void sendBackToStart() {
		this.myCurrentOwnfield=0;
	}
}
