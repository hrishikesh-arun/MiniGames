package games.nim;

import myLib.InputField;
import myLib.FileMethods;
import myLib.ColorPrint;
import java.util.Random;

public class NimGame
{
	boolean hasQuit=false;
	public static String nimVersion="v1.0.0";
	public String dir = ".\\games\\nim\\GameData\\";
	Random r;
	public NimGame()
	{
		r = new Random();
		// Start
		System.out.println("\nWelcome to "+ColorPrint.PURPLE_BRIGHT+"Nim"+ColorPrint.RESET+"!");
		String op;
		viewInstructions(false);
		do
		{
			
			op=InputField.enterField_str("\n"+ColorPrint.CYAN+"MiniGames"+ColorPrint.RESET+"."+ColorPrint.PURPLE_BRIGHT+"Nim"+ColorPrint.RESET+"> ",false);
			switch(op.toLowerCase())
			{
				case "0":
					hasQuit=true;
					break;
				case "1":
					System.out.println("\nDifficulty: Normal");
					playGame(false,false);
					break;
				case "1i":
					System.out.println("\nDifficulty: ASIAN");
					playGame(false,true);
					break;
				case "2":
					System.out.println("\n2-Player Mode");
					playGame(true,false);
					break;
				case "i":
					viewInstructions(true);
					break;
				case "e":
					viewPatchNotes();
					break;
				default:
					System.out.println("\nError! Invalid Input! Try Again!");
					break;
			}
			
		}while(!hasQuit);
	}
	void viewInstructions(boolean showHTP)
	{
		String text = FileMethods.readFile(dir+"options.txt");
		System.out.println("\n"+text);
		if(showHTP)
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
	void playGame(boolean is2Player,boolean mode) // Mode (Normal/false ASIAN/true)
	{
		int ip,total=0;
		int currentPlayer=r.nextInt(2)+1; // Odd numbers refer to the user who plays first; Even Numbers refer to user who plays second
		if(!is2Player && currentPlayer == 2)
			System.out.println("\nComputer ("+(mode ? "ASIAN":"NORMAL" )+") plays first");
		else
			System.out.println("\nPlayer "+currentPlayer+" plays first");
		boolean hasEnded = false,player1Wins = false,hasQuitInGame = false;
		while(!hasEnded)
		{
			ip=0;
			//Take Input
			try
			{
				if(!is2Player && currentPlayer%2==0)
				{
					ip=calculateNextMove(mode,total);
					System.out.println("\nComputer: "+ip);
				}
				else
				{
					ip=InputField.enterField_int("\nPlayer "+(currentPlayer%2 == 0 ? 2:1)+": ",false);
					hasQuitInGame = checkInput(ip);
				}
				if(hasQuitInGame) break;
				currentPlayer++;
			}
			catch(Exception e)
			{
				System.out.println("Error! Invalid Input! Try Again!");
				continue;
			}
			// Player Adds to Total
			total+=ip;
			System.out.println("\nSum Total: "+total);
			
			player1Wins = total>=100 && currentPlayer%2==0; // It is 0 because step is increased by 1 so it takes next value
			hasEnded= total>=100;
		}
		
		if(player1Wins)
		{
			System.out.println("\nPlayer 1 wins!");
		}
		else if(hasQuitInGame)
		{
			System.out.println("\nGame has been quit by player");
		}
		else
		{
			System.out.println(is2Player ? "\nPlayer 2 wins!" : "\nComputer Wins!");
		}
	}
	
	// Calculates Next Move
	int calculateNextMove(boolean mode,int sum) // Mode (Normal/false ASIAN/true)
	{
		int m=0;
		if(!mode)
		{
			m =  r.nextInt(10)+1;
		}
        else
		{
			int pred=((((sum/10)+1)*11)-10)-sum; //Calculates next number in the winning arithmetic series and then subtracts total from it.
			m = pred<1 ? pred+11 : pred;
			m-= m>10 ? 10:0;
		}
		return m;
	}
	boolean checkInput(int input)
	{
		if(input>10 || input<0)
		{
			throw new InvalidInputException();
		}
		else if(input==0)
			return true;
		return false;
	}
	public static void main(String arg[])
	{
		NimGame ng = new NimGame();
	}
}

class InvalidInputException extends RuntimeException
{
	
}