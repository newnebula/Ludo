package ludoGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import ludo.PieceColor;

public class GameModeListener implements ActionListener  {

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonPlayMode modeButton = (ButtonPlayMode) e.getSource();
		int numberOfPlayers = 0;
		switch (modeButton.switchCode) {
			case "Single player":
				numberOfPlayers = 1;
				break;
			case "Two players":
				numberOfPlayers = 2;
				break;
			case "Four players":
				numberOfPlayers = 4;
				break;
			case "Demo":
				numberOfPlayers = 0;
				break;
		}

		setUp(numberOfPlayers);
		for(JButton button : BoardShower.getGameModeButtons()) {
			BoardShower.removeAPlayModeButtons(button, BoardShower.theFrame);
		}

		BoardShower.numberHumanPlayers = numberOfPlayers;
		if(numberOfPlayers>0) {
			BoardShower.askAPlayerName();
		}else {
			RunTheGame.semaphoreForSetUp.release();
		}
	}

	private static void setUp(int aNumberOfPlayers) {
		if(aNumberOfPlayers==0) {
			RunTheGame.ThePlayers = List.of(new GUIAutomatedPlayer("1st player", PieceColor.RED),
										new GUIAutomatedPlayer("2nd player", PieceColor.BLUE),
										new GUIAutomatedPlayer("3rd player", PieceColor.YELLOW),
										new GUIAutomatedPlayer("4th player", PieceColor.GREEN));
		}

		if(aNumberOfPlayers==1) {
			RunTheGame.ThePlayers = List.of(new GUIHumanPlayer("First player", PieceColor.RED),
										new GUIAutomatedPlayer("Computer", PieceColor.YELLOW) );
		}
		if(aNumberOfPlayers==2) {
			RunTheGame.ThePlayers = List.of(new GUIHumanPlayer("First player", PieceColor.RED),
										new GUIHumanPlayer("Second player", PieceColor.YELLOW) );
		}

		if(aNumberOfPlayers==4) {
			RunTheGame.ThePlayers = List.of(new GUIHumanPlayer("1st player", PieceColor.RED),
										new GUIHumanPlayer("2nd player", PieceColor.BLUE),
										new GUIHumanPlayer("3rd player", PieceColor.YELLOW),
										new GUIHumanPlayer("4th player", PieceColor.GREEN));
		}
	}
}
