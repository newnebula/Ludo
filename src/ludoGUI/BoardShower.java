package ludoGUI;
//BoardShower draws the board in the initial configuration.
//It displays and hides communication buttons and fields at the set up phase of the game.
//It displays pieces after each move.
//Last two with use of various Listeners.

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ludo.Dice;
import ludo.Player;

public class BoardShower {
	static JFrame theFrame;
	static int numberHumanPlayers;

	static final int FIELDSIZE = 20;
	static final int FRAMESIZE = 40*FIELDSIZE;

	//Labels
	private static JLabel diceResultLabel = new JLabel(String.valueOf(Dice.diceResult),SwingConstants.CENTER);
	private static JLabel playerLabel = new JLabel("Player Name",SwingConstants.CENTER);
	private static JLabel winnerLabel = new JLabel("Winner?",SwingConstants.CENTER);
	private static JLabel communicationLabel = new JLabel("Communication.",SwingConstants.CENTER);

	private static JLayeredPane layeredPane = new JLayeredPane();
	static JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	private static ArrayList<JButton> gameModeButtons = null;
	static ArrayList<JButton> getGameModeButtons(){
		return gameModeButtons;
	}

	static void showBoard(JFrame frame){
		ButtonsForPieces.drawPiecesToStart(frame, FIELDSIZE);
	}

	static void communicateToSetUp() {
		JFrame frame= new JFrame("Ludo - Chinczyk - Mens Erger Je Niet! - v0.1 by Ula");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(FRAMESIZE, FRAMESIZE);
		theFrame=frame;
		frame.setContentPane(layeredPane);

		Board board = new Board(FIELDSIZE);
		board.setBounds(0,0, FRAMESIZE, FRAMESIZE);
		board.setOpaque(false);
		layeredPane.add(board, 10);

		diceResultLabel.setBounds(18*FIELDSIZE, 20*FIELDSIZE, 70, 70);
		diceResultLabel.setFont(new Font("Serif", Font.BOLD, 60));
		diceResultLabel.setBackground(Color.LIGHT_GRAY);
		diceResultLabel.setOpaque(true);
		layeredPane.add(diceResultLabel, 0);

		playerLabel.setBounds(12*FIELDSIZE,12*FIELDSIZE, 300, 30);
		playerLabel.setFont(new Font("Serif", Font.BOLD, 20));
		playerLabel.setBackground(Color.LIGHT_GRAY);
		playerLabel.setOpaque(true);
		layeredPane.add(playerLabel, 0);

		winnerLabel.setBounds(12*FIELDSIZE, 33*FIELDSIZE, 300, 60);
		winnerLabel.setFont(new Font("Serif", Font.BOLD, 30));
		layeredPane.add(winnerLabel, 0);
		winnerLabel.setVisible(false);

		communicationLabel.setText("Choose a game mode:");
		communicationLabel.setBounds(0, 2*FIELDSIZE, FRAMESIZE, 40);
		communicationLabel.setFont(new Font("Serif", Font.BOLD, 30));
		layeredPane.add(communicationLabel, 0);

		gameModeButtons = addGameModeButtons();

		frame.setVisible(true);
		frame.setResizable(false);
	}

	static void fetchLabels(int diceResult, String playerName, String pieceColor, Color aColor) {
		playerLabel.setForeground(aColor);
		diceResultLabel.setForeground(aColor);
		playerLabel.setText(playerName);

		int[] diceTrick = {1,2,3,4,5,6};
		for(int aDiceTrick : diceTrick) {
			diceResultLabel.setText(String.valueOf(aDiceTrick));
			//Uncomment to slow automated demo game down
//			try {
//				TimeUnit.MILLISECONDS.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		diceResultLabel.setText(String.valueOf(diceResult));
	}

	static void addWinnerLabel(String name) {
		winnerLabel.setText(name + " won!");
		winnerLabel.setVisible(true);
	}

	private static ArrayList<JButton> addGameModeButtons() {
		ArrayList<JButton> gameModeButtons = new ArrayList<JButton>(3);

		ButtonPlayMode mode1human1auto = new ButtonPlayMode("Single player");
		mode1human1auto.setBounds(FIELDSIZE, 6*FIELDSIZE, 200, 30);
		mode1human1auto.addActionListener(new GameModeListener());

		BoardShower.theFrame.getContentPane().add(mode1human1auto);
		gameModeButtons.add(mode1human1auto);

		ButtonPlayMode mode2human = new ButtonPlayMode("Two players");
		mode2human.setBounds(FIELDSIZE + 220, 6*FIELDSIZE, 150, 30);
		mode2human.addActionListener(new GameModeListener());

		BoardShower.theFrame.getContentPane().add(mode2human);
		gameModeButtons.add(mode2human);

		ButtonPlayMode mode4human = new ButtonPlayMode("Four players");
		mode4human.setBounds(FIELDSIZE + 220 + 170, 6*FIELDSIZE, 150, 30);
		mode4human.addActionListener(new GameModeListener());

		BoardShower.theFrame.getContentPane().add(mode4human);
		gameModeButtons.add(mode4human);

		ButtonPlayMode modeDemo = new ButtonPlayMode("Demo");
		modeDemo.setBounds(10*FIELDSIZE + 220 + 170, 6*FIELDSIZE, 200, 30);
		modeDemo.addActionListener(new GameModeListener());

		BoardShower.theFrame.getContentPane().add(modeDemo);
		gameModeButtons.add(modeDemo);

		return gameModeButtons;
	}

	static void askAPlayerName() {
		System.out.println("Number of human players: " + numberHumanPlayers);

		communicationLabel.setText("Type your name:");
		int iterator = 0;
		for(Player humanPlayer : RunTheGame.ThePlayers){

			if(humanPlayer instanceof GUIHumanPlayer) {
				JTextField nameTekstField = new JTextField();
				nameTekstField.setBounds(FIELDSIZE + iterator, 5*FIELDSIZE, 150, 20);
				nameTekstField.addActionListener(new TextFieldListener());
				BoardShower.theFrame.getContentPane().add(nameTekstField);
				iterator = iterator + 200 ;
			}
		}
	}

	static void removeAPlayModeButtons(JComponent aJComponent, JFrame frame) {
		frame.getContentPane().remove(aJComponent);
		frame.revalidate();
		frame.repaint();
	}

	static void setCommunicationLabel(String aCommunicationLabel) {
		communicationLabel.setText(aCommunicationLabel);
	}
}
