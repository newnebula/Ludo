package ludoGUI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;

import ludo.Dice;
import ludo.PieceColor;
import ludo.Player;
import ludo.ResultWriter;

public class RunTheGame {

	public static Semaphore semaphore = new Semaphore(0);
	public static Semaphore semaphoreForSetUp = new Semaphore(0);
	public static PieceColor currentPlayerColor;
	public static List<Player> ThePlayers;

	public static void main(String[] args) throws IOException, InterruptedException {

		//Displays the board without the pieces.
		//Displays the game mode buttons and text fields.
		BoardShower.communicateToSetUp();

		semaphoreForSetUp.acquire();

		// Waits a second to display the last greeting.
		try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}

		BoardShower.setCommunicationLabel(null);
		// Displays the board with the pieces in the start configuration.
		BoardShower.showBoard(BoardShower.theFrame);

		boolean isWinner = false;
		while(!isWinner) {

			for(Player player:ThePlayers) {
				Dice.roll();
				BoardShower.fetchLabels(Dice.diceResult, player.getName(),
						player.getMyColor().name(), player.getMyColor().getGraphColor());

				currentPlayerColor = player.getMyColor();

				player.play();

				if(player.didIwin()) {
					System.out.println("There is a winner, we can stop!");
					isWinner = true;
					BoardShower.addWinnerLabel(player.getName());
					break;
				}
			}
		}

		//BoardShower.addWinnerLabel();
		ResultWriter.writeGameResults();

		//In order to clean the GameResults.csv file use the MySQL script executor:
		//https://www.mkyong.com/jdbc/how-to-run-a-mysql-script-using-java/
		//https://www.invidio.us/watch?v=HXV3zeQKqGY
	}
}
