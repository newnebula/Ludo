package ludo;
// A player has a name, a list of pieces and a PieceColor.
//There are two children to this class: AutomatedPlayer and HumanPlayer.

import java.util.ArrayList;
import java.util.List;


public class Player {
	public Player(String aName, PieceColor aColor){
		name = aName;
		myColor = aColor;
		myPieces = List.of(new Piece(aColor), new Piece(aColor), new Piece(aColor),new Piece(aColor));
	}

	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	private List<Piece> myPieces;
	public List<Piece> getMyPieces(){
		return myPieces;
	}

	private PieceColor myColor;
	public PieceColor getMyColor() {
		return myColor;
	}

	public List<Piece> getPlayablePieces(){
    	List<Piece> playablePieces = null;
		if(Dice.diceResult==6) {
			playablePieces = myPieces;
		}else{
			playablePieces = new ArrayList<Piece>();
			for(Piece piece:this.myPieces) {
				if(piece.hasStarted()) {
					playablePieces.add(piece);
				}
			}
		}

		return playablePieces;
    }

	// Play contains actions of the currently playing player. Is implemented in children classes.
	public void play() {
	}

	// Checks whether all the pieces are safe i.e. whether a player has won.
	public boolean didIwin() {
		int safe = 0;
		for(Piece p:myPieces) {
			if(p.calculateMyCurrentAbsField()>=1000) {
				safe = safe + 1;
			}
		}

		if(safe ==4) {
			return true;
		}

		return false;
	}
}
