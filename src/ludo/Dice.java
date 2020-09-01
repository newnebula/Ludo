package ludo;

import java.util.Random;
public class Dice {
	public static int diceResult=0;
	public static void roll() {
		diceResult=new Random().nextInt(6)+1;
	}
}
