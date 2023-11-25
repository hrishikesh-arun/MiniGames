package games.hangmanMini;

import java.util.Arrays;
import myLib.InputField;
import myLib.FileMethods;
import myLib.ColorPrint;
import java.util.Random;

public class HangmanMini
{
	boolean hasQuit=false;
	public static String hangmanVersion="v1.0.0";
	public String dir = ".\\games\\hangmanMini\\GameData\\";
	String[] dictionary;
	Random r;
	public HangmanMini()
	{
		r = new Random();
		dictionary = loadDictionary();
		// Start
		System.out.println("\nWelcome to "+ColorPrint.YELLOW+"Hangman"+ColorPrint.RESET+"!");
		String op;
		viewInstructions(false);
		do
		{
			
			op=InputField.enterField_str("\n"+ColorPrint.CYAN+"MiniGames"+ColorPrint.RESET+"."+ColorPrint.YELLOW+"Hangman"+ColorPrint.RESET+"> ",false);
			switch(op)
			{
				case "0":
					hasQuit=true;
					break;
				case "1":
					playGame(8);
					break;
				case "1e":
					playGame(8);
					break;
				case "1m":
					playGame(4);
					break;
				case "1h":
					playGame(2);
					break;
				case "1x":
					playGame(0);
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
		String text = FileMethods.readFile(dir+"instructions.txt");
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
	void playGame(int eChances)
	{
		// Load Dict
		System.out.println("\nGuess the Word\n");
		boolean hasEnded = false,hasWon = false,hasQuitInGame = false;
		int guessCount,correctLetters;
		String sWord = getRandomWord().toUpperCase();
		char[] wordArray = sWord.toCharArray();
		int wordLength=sWord.length();
		guessCount = wordLength+eChances;
		char[] playerGuess = new char[wordLength];
		Arrays.fill(playerGuess,'_');
		char ip;
		// No of words gotten
		int count=wordLength;
		// Show first dashes
		printPlayerGuess(playerGuess);
		while(!hasEnded)
		{
			System.out.println("\n");
			//Take Input
			ip=InputField.enterField_str("\nTries Left: "+guessCount+"\nEnter Guess: ",false).toUpperCase().charAt(0);
			// Check if player said quit
			try
			{
				if(ip=='0')
				{
					hasQuitInGame=true;
					hasEnded = true;
					break;
				}
			}
			catch(Exception e){}
			
			//Check if char is there in word. If there is, replace _ with that letter
			for(int i=0;i<wordArray.length;i++)
			{
				if(ip == wordArray[i] && playerGuess[i] == '_')
				{
					playerGuess[i] = ip;
					count--;
				}
			}
			
			System.out.println("\nYour Guesses:");
			printPlayerGuess(playerGuess);
			
			//Check if all words are there
			if(count == 0)
			{
				hasWon=true;
				hasEnded=true;
				break;
			}
			
			guessCount--;
			hasEnded= guessCount<=0;
		}
		
		if(hasWon)
		{
			System.out.println("\n\nVictory! Congratulations!");
		}
		else if(hasQuitInGame)
		{
			System.out.println("\nGame has been quit by player");
		}
		else
		{
			System.out.println("\n\nGame Over! The word was "+sWord+"\n\nBetter luck next time!");
		}
	}
	
	void printPlayerGuess(char[] playerGuess)
	{
		for(int i=0;i<playerGuess.length;i++)
		{
			System.out.print(playerGuess[i]+" ");
		}
	}
	
	// Gets Random word
	String getRandomWord()
	{
		int randomNumber = r.nextInt(dictionary.length);
        String word = dictionary[randomNumber];
        
		return word;
	}
	String[] loadDictionary()
	{
		String[] dic;
		dic = FileMethods.readFile_LineByLine(dir+"HangmanWords.txt",850);
		return dic;
	}
	
	public static void main(String arg[])
	{
		HangmanMini hg = new HangmanMini();
	}
}