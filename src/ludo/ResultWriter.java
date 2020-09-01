package ludo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ludoGUI.RunTheGame;

public class ResultWriter{
	  //Writes out each players results to file, as follows: "Player,Points,Time"
	  public static void writeGameResults() throws IOException{
	   FileWriter fw = new FileWriter("GameResults.csv",true);
	   PrintWriter out = new PrintWriter(fw);
	   for(Player player: RunTheGame.ThePlayers) {
		   int piecesAtHome = 0;
		   for(Piece piece : player.getMyPieces()) {
			   if(piece.calculateMyCurrentAbsField()>999) {
				   piecesAtHome++;
			   }
		   }

		   out.print(player.getName()); //first row first column
		   out.print(",");

		   if(piecesAtHome==4) {
			   //Winner gets bonus of 5 points
			   out.print(piecesAtHome + 5); //first row second column
		   } else {
			   out.print(piecesAtHome); //first row second column
		   }

		   out.print(",");
		   out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

	   }
	   //Flush the output to the file
	   out.flush();

	   //Close the Print Writer
	   out.close();

	   //Close the File Writer
	   fw.close();
	  }

	  public static List<String[]> readGameResults() throws IOException {
		    String file = "GameResults.csv";
		    List<String[]> content = new ArrayList<>();
		    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		        String line = "";
		        while ((line = br.readLine()) != null) {
		            content.add(line.split(","));
		        }
		    } catch (FileNotFoundException e) {
		      System.out.println("No Game Result File");
		    }
		    return content;
	 }

	 public static void showFoundFile(List<String[]> list) {
		System.out.println(list);
	 }

}
