package ludoGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import ludo.Player;

public class TextFieldListener implements ActionListener  {

	static int humCount=0;
	String playersName;
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField myTextField = (JTextField)e.getSource();
		playersName = myTextField.getText();
		BoardShower.setCommunicationLabel("Hi " + playersName);
		setRealName();
		BoardShower.removeAPlayModeButtons(myTextField, BoardShower.theFrame);

		humCount++;
		if(humCount==BoardShower.numberHumanPlayers) {

			RunTheGame.semaphoreForSetUp.release();
		}
	}

	private static List<Player> humanPlayers = RunTheGame.ThePlayers.stream().
			filter(player -> player instanceof GUIHumanPlayer).collect(Collectors.toList());

	private void setRealName() {
		humanPlayers.get(0).setName(playersName);
		humanPlayers.remove(0);
	}

}
