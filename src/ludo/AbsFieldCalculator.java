package ludo;
//Fields on the board are numbered starting from 0, where the red piece starts - absolute field.
//Also every piece keeps track of the distance from its own starting point which is dependent on the color - own field.
//This calculator computes absolute field number given a piece's own field and color.
//Fields at home, where the pieces are safe from other players are numbered:
//1001, 1002, 1003, 1004 for a red piece, and alike for other colors.
//These numbers are stored in PieceColor enum.

public class AbsFieldCalculator {

	public AbsFieldCalculator(int pieceOwnField, PieceColor pieceColor) {
		this.pieceOwnField = pieceOwnField;
		this.pieceColor = pieceColor;
	}
	private int pieceOwnField;
	private PieceColor pieceColor;

	public int calculateAbsField() {
		int absoluteField;
		//Should the piece occupy field bigger than 40 it is safe from other players.
		if(this.pieceOwnField <= 40) {
			if(this.pieceOwnField <= 40 - this.pieceColor.getAbsoluteStartField()) {
				absoluteField = this.pieceOwnField + this.pieceColor.getAbsoluteStartField();
			}else{
				absoluteField= this.pieceOwnField -(40- this.pieceColor.getAbsoluteStartField());
			}
		// If a piece is already further than on its field 40 it can still be kicked out
		// by another piece of the same pieceColor.
		}else{
			absoluteField = (this.pieceColor.getAbsFilednumberWhileSafe() + (this.pieceOwnField - 40));
		}
		return absoluteField;
	}
}
