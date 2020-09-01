package ludo;
//Automated player is used for demo (4 automated players created)
//or when a single player plays against the computer.
//The automated player chooses randomly one of the playable pieces.
//Making the choice repeats up to 4 times,
//in order to avoid moving a piece which is already at home (if possible).

import java.util.List;
import java.util.Random;

public class AutomatedPlayer extends Player {

	public AutomatedPlayer(String aName, PieceColor aColor){
		super(aName, aColor);
	}
	protected Piece selectPiece() {
		Piece autoSelectedPiece = null;
		List<Piece> playablePieces = this.getPlayablePieces();
		if (playablePieces.size() > 0) {
			// Try picking a piece which is not yet at home.
			int counter = 0;
			autoSelectedPiece = playablePieces.get(new Random().nextInt(playablePieces.size()));
			while ((counter<=playablePieces.size()+1) && (autoSelectedPiece.isHome() || !autoSelectedPiece.hasStarted())){
		    	autoSelectedPiece = playablePieces.get(new Random().nextInt(playablePieces.size()));
				counter++;
			}
		}

		return autoSelectedPiece;
	}
}
