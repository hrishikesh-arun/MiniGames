package games.nim;

import myLib.InputField;
import myLib.FileMethods;
import java.util.Random;

public class NimGame
{
	boolean hasQuit=false;
	public static String nimVersion="v0.0.0";
	public String dir = ".\\games\\nim\\GameData\\";
	Random r;
	public NimGame()
	{
		r = new Random();
		// Start
		System.out.println("Nim "+nimVersion);
		System.out.println("\nWelcome to The 100 game a.k.a Nim!");
		String op;
		viewInstructions(true,false);
		do
		{
			
			op=InputField.enterField_str("\nMiniGames.Nim> ",false);
			switch(op)
			{
				case "0":
					hasQuit=true;
					break;
				case "1":
					System.out.println("\nDifficulty: Normal");
					playGame(false);
					break;
				case "1i":
					System.out.println("\nDifficulty: ASIAN");
					playGame(true);
					break;
				case "i":
					viewInstructions(true,true);
					break;
				case "e":
					viewPatchNotes();
					break;
				default:
					System.out.println("\nError! Invalid Input! Try Again!");
					break;
			}
			
		}while(!hasQuit);
		System.out.println("\nThanks for playing Nim!");
	}
	void viewInstructions(boolean showInstructions,boolean showHowToPlay)
	{
		String text = "";
		if(showInstructions)
			text = FileMethods.readFile(dir+"options.txt");
			System.out.println("\n"+text);
		if(showHowToPlay)
		{
			text = FileMethods.readFile(dir+"htp.txt");
			System.out.println("\n"+text);
		}
	}
	void viewPatchNotes()
	{
		String patchNotes = FileMethods.readFile(dir+"patchNotes.txt");
		System.out.println("\n"+patchNotes);
	}
	// Main Game
	void playGame(boolean mode) // Mode (Normal/false ASIAN/true)
	{
		int ip;
		boolean hasEnded = false,hasWon = false;
		do
		{
			//Take Input
			try
			{
				ip=InputField.enterField_int("\n",false);
			}
			catch(Exception e)
			{
				System.out.println("Error! Invalid Input! Try Again!");
				continue;
			}
			// Game Loop Continuation
			
			hasEnded= true;
		}while(!hasEnded);
		
		if(hasWon)
		{
			System.out.println("\nVictory! Congratulations!");
		}
		else
		{
			System.out.println("\nGame Over!\n\nBetter luck next time!");
		}
	}
	
	// Calculates Next Move
	int calculateNextMove(boolean mode) // Mode (Normal/false ASIAN/true)
	{
		int m=0;
		if(!mode)
		{
			m =  r.nextInt(10);
		}
        else
		{
			// Coming Soon!
		}
		return m;
	}
	
	public static void main(String arg[])
	{
		NimGame ng = new NimGame();
	}
}