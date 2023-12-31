package games.wordleCMD;

import java.util.Arrays;
import myLib.InputField;
import myLib.FileMethods;
import myLib.ColorPrint;
import java.util.Random;

public class WordleGame
{
	boolean hasQuit=false;
	public static String wordleVersion="v1.0.0";
	public String dir = ".\\games\\wordleCMD\\GameData\\";
	String[] dictionary;
	Random r;
	public WordleGame()
	{
		r = new Random();
		// Start
		System.out.println("\nWelcome to "+ColorPrint.GREEN_UNDERLINED+"Wordle"+ColorPrint.YELLOW_UNDERLINED+"CMD"+ColorPrint.RESET+"!");
		String op;
		viewInstructions(false);
		do
		{
			
			op=InputField.enterField_str("\n"+ColorPrint.CYAN+"MiniGames"+ColorPrint.RESET+"."+ColorPrint.GREEN+"Wordle"+ColorPrint.YELLOW+"CMD"+ColorPrint.RESET+"> ",false);
			switch(op)
			{
				case "0":
					hasQuit=true;
					break;
				case "1e":
					System.out.println("\nDifficulty: Easy");
					playGame(1);
					break;
				case "1m":
					System.out.println("\nDifficulty: Medium");
					playGame(2);
					break;
				case "1h":
					System.out.println("\nDifficulty: Hard");
					playGame(3);
					break;
				case "1":
					System.out.println("\nDifficulty: Hard");
					playGame(3);
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
	void playGame(int lev)
	{
		// Load Dict
		dictionary = loadDictionary(lev);
		System.out.println("\nGuess the 5 letter Word\n");
		boolean hasEnded = false,hasWon = false,hasQuitInGame = false;
		int count=1,correctLetters;
		String sWord = getRandomWord().toUpperCase();
		String ip;
		while(!hasEnded)
		{
			correctLetters=0;
			char[] result;
			//Take Input
			ip=InputField.enterField_str("\n"+count+": ",false).toUpperCase();
			// Check if player said quit
			try
			{
				if(Integer.parseInt(ip)==0)
				{
					hasQuitInGame=true;
					hasEnded = true;
					break;
				}
			}
			catch(Exception e){}
			//Check if word has 5 letters
			if(ip.length()!=5)
			{
				System.out.println("Error! Game Accepts only 5 letter words! Try Again!");
				continue;
			}
			//Check if letter is correct
			result=checkInput(ip,sWord);
			correctLetters=countCorrectLetters(result);
			//Show Output
			String output = new String(result);
			System.out.println("   "+output);
			//Continue
			if(correctLetters==5)
			{
				hasEnded = true;
				hasWon = true;
				break;
			}
			count++;
			hasEnded= count>6;
		}
		
		if(hasWon)
		{
			System.out.println("\nVictory! Congratulations!");
			switch(count)
			{
				case 1:
					System.out.println("Extraordinary! Found in 1 guess");
					break;
				case 2:
					System.out.println("Brilliant! Found in 2 guesses");
					break;
				case 3:
					System.out.println("Great! Found in 3 guesses");
					break;
				case 4:
					System.out.println("Good! Found in 4 guesses");
					break;
				case 5:
					System.out.println("Fair! Found in 5 guesses");
					break;
				case 6:
					System.out.println("Phew! Found in 6 guesses");
					break;
				default:
					break;
			}
		}
		else if(hasQuitInGame)
		{
			System.out.println("\nGame has been quit by player");
		}
		else
		{
			System.out.println("\nGame Over! The word is "+sWord+"\n\nBetter luck next time!");
		}
	}
	
	char[] checkInput(String ip, String sWord)
	{
		//Check if letter is correct
		String tempWord = sWord;
		char[] result = new char[5];
		Arrays.fill(result,'X');
		for(int checkCount=0; checkCount < ip.length();checkCount++)
		{
			char i = ip.charAt(checkCount);
			if (i == sWord.charAt(checkCount))
			{
				result[checkCount] = '#';
				tempWord = tempWord.substring(0, checkCount) + ' ' + tempWord.substring(checkCount+1);
			}
			else
			{
				result[checkCount] = 'X';
			}
		}
		//Check if letter is in wrong place
		for(int checkCount=0; checkCount < ip.length();checkCount++)
		{
			char i = ip.charAt(checkCount);
			if (tempWord.contains(Character.toString(i)))
			{
				for (int index = 0; index < sWord.length(); index++)
				{
					if (tempWord.charAt(index) == i)
					{
						result[checkCount] = '*';
					}
					else if(result[index] != '#' && result[index] != '*')
					{
						result[index] = 'X';
					}
				}
			}
		}
		
		return result;
	}
	
	int countCorrectLetters(char[] r)
	{
		int cL=0;
		for(char i:r)
		{
			cL+= i=='#' ? 1:0;
		}
		return cL;
	}
	// Gets Random 5 letter word
	String getRandomWord()
	{
		int randomNumber = r.nextInt(dictionary.length);
        String word = dictionary[randomNumber];
        
		return word;
	}
	String[] loadDictionary(int level)
	{
		String[] dic;
		switch(level)
		{
			case 1:
				dic = FileMethods.readFile_LineByLine(dir+"dictionaryEasy.txt",20);
				break;
			case 2:
				dic = FileMethods.readFile_LineByLine(dir+"dictionaryMedium.txt",400);
				break;
			default:
				dic = FileMethods.readFile_LineByLine(dir+"dictionaryHard.txt",2000);
				break;
		}
		return dic;
	}
	
	public static void main(String arg[])
	{
		WordleGame wg = new WordleGame();
	}
}