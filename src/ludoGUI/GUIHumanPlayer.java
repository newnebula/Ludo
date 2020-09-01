package ludoGUI;
import ludo.PieceColor;
import ludo.Player;

public class GUIHumanPlayer extends Player {

	// In the constructor, the name is set and a list of four pieces of the right color is created.
	public GUIHumanPlayer(String aName, PieceColor aColor){
		super(aName, aColor);
	}

	private void waitForUserToSelectPiece() {
		try {
			RunTheGame.semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Play contains actions of the currently playing player.
	@Override
	public void play() {

		ButtonsForPieces.setActivePieces(this.getPlayablePieces());

		if (this.getPlayablePieces().size() > 0) {
			this.waitForUserToSelectPiece();

			// Checks whether all his pieces are safe.
			this.didIwin();
		} else {
			// No playable pieces, next player
		}
	}
}
