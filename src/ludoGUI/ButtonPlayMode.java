package ludoGUI;

import javax.swing.JButton;

public class ButtonPlayMode extends JButton {
	private static final long serialVersionUID = 1L;

	ButtonPlayMode(String aSwitchCode){
		super(aSwitchCode);
		switchCode = aSwitchCode;
	};

	public String switchCode;
}
