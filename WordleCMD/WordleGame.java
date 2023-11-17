import myLib.InputField;
import myLib.FileMethods;
import java.util.Random;

class WordleGame
{
	boolean hasQuit=false;
	public static String wordleVersion="v0.3.0b1";
	String[] dictionary;
	Random r;
	WordleGame()
	{
		// Load Dict
		dictionary = loadDictionary();
		r = new Random();
		// Start
		System.out.println("Wordle CMD "+wordleVersion);
		System.out.println("\nWelcome to Wordle CMD!");
		String op;
		viewInstructions(true,false);
		do
		{
			
			op=InputField.enterField_str("\nMiniGames.WordleCMD> ",false);
			switch(op)
			{
				case "0":
					hasQuit=true;
					break;
				case "1":
					playGame();
					break;
				case "i":
					viewInstructions(true,true);
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
		System.out.println("\nThanks for playing Wordle CMD!");
	}
	void viewInstructions(boolean showInstructions,boolean showHowToPlay)
	{
		if(showInstructions)
			System.out.println("\nInstructions:\nPress 1 to play\nPress i to view Instructions and How to Play\nPress e to view Patch notes and Credits\nPress 0 to quit");
		if(showHowToPlay)
		{
			String htp = FileMethods.readFile(".\\WordleCMD\\GameData\\htp.txt");
			System.out.println("\n"+htp);
		}
	}
	void viewCredits()
	{
		String credits = FileMethods.readFile(".\\WordleCMD\\GameData\\credits.txt");
		System.out.println("\nCredits\n\n"+credits);
	}
	void viewPatchNotes()
	{
		String patchNotes = FileMethods.readFile(".\\WordleCMD\\GameData\\patchNotes.txt");
		System.out.println("\nWordle CMD "+wordleVersion+"\n"+patchNotes);
	}
	// Main Game
	void playGame()
	{
		System.out.println("\nGuess the 5 letter Word\n");
		boolean hasEnded = false,hasWon = false;
		int count=1,correctLetters;
		String sWord = getRandomWord().toUpperCase();
		String ip;
		do
		{
			correctLetters=0;
			char[] result = new char[5];
			//Take Input
			ip=InputField.enterField_str("\n"+count+": ",false).toUpperCase();
			//Check if word has 5 letters
			if(ip.length()!=5)
			{
				System.out.println("Error! Game Accepts only 5 letter words! Try Again!");
				continue;
			}
			//Check if letter is correct
			result=checkInput(ip,sWord);
			//Show Output
			System.out.println("\n"+ip.toUpperCase());
			System.out.println(result);
			correctLetters=countCorrectLetters(result);
			//Continue
			count++;
			if(correctLetters==5)
			{
				hasEnded = true;
				hasWon = true;
				break;
			}
			hasEnded= count>6;
		}while(!hasEnded);
		if(hasWon)
		{
			System.out.println("\nVictory! Congratulations!");
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
	String[] loadDictionary()
	{
		String[] dic = new String[5757];
		dic = FileMethods.readFile_LineByLine(".\\WordleCMD\\GameData\\dictionary.txt",5757);
		return dic;
	}
	
	public static void main(String arg[])
	{
		WordleGame wg = new WordleGame();
	}
}