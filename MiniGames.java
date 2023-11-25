import java.io.IOException;

import myLib.InputField;
import myLib.FileMethods;
import myLib.ColorPrint;

// Import Games
import games.wordleCMD.WordleGame;
import games.nim.NimGame;
import games.hangmanMini.HangmanMini;

class MiniGames
{
	boolean hasQuit=false;
	public static String miniGamesVersion="v1.0.0";
	MiniGames()
	{
		ColorPrint.clearscreen(); 
		// Start
		System.out.println(ColorPrint.CYAN+"MiniGames "+ColorPrint.RED_BRIGHT+miniGamesVersion+ColorPrint.RESET+"\n\n"+ColorPrint.GREEN_BRIGHT+"-Main Menu-"+ColorPrint.RESET);
		String op;
		viewInstructions();
		do
		{
			op=InputField.enterField_str("\n"+ColorPrint.CYAN+"MiniGames"+ColorPrint.RESET+"> ",false);
			System.out.println();
			switch(op)
			{
				case "0":
					hasQuit=true;
					break;
				case "1":
					WordleGame wg = new WordleGame();
					break;
				case "2":
					NimGame ng = new NimGame();
					break;
				case "3":
					HangmanMini hg = new HangmanMini();
					break;
				// Add your games here
				
				case "i":
					viewInstructions();
					break;
				case "e":
					viewPatchNotes();
					viewCredits();
					break;
				default:
					System.out.println("\nError! Invalid Input! Try Again!");
					break;
			}
			
		}while(!hasQuit);
		System.out.println("\nThanks for playing MiniGames!");
	}
	void viewInstructions()
	{
		String text = FileMethods.readFile(".\\GameData\\instructions.txt");
		System.out.println("\n"+text);
	}
	void viewCredits()
	{
		String credits = FileMethods.readFile(".\\GameData\\credits.txt");
		System.out.println("\nCredits\n\n"+credits);
	}
	void viewPatchNotes()
	{
		String patchNotes = FileMethods.readFile(".\\GameData\\patchNotes.txt");
		System.out.println("\n"+patchNotes);
	}
	
	public static void main(String arg[])
	{
		MiniGames mg = new MiniGames();
	}
}