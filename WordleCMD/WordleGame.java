import myLib.InputField;
import myLib.FileMethods;
class WordleGame
{
	boolean hasQuit=false;
	public static String wordleVersion="v0.1.0";
	WordleGame()
	{
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
		int count=1,correctLetters=0;
		String sWord = getRandomWord();
		char[] sWordArr = sWord.toCharArray();
		String ip;
		do
		{
			correctLetters=0;
			char[] result = {' ',' ',' ',' ',' '};
			//Take Input
			ip=InputField.enterField_str("\n"+count+": ",false).toUpperCase();
			//Check if word has 5 letters
			if(ip.length()!=5)
			{
				System.out.println("Error! Game Accepts only 5 letter words! Try Again!");
				continue;
			}
			char[] ipA = ip.toCharArray();
			//Check if letter is correct
			int checkCountI =0,checkCountJ;
			for(char i:ipA)
			{
				checkCountJ = 0;
				for(char j:sWordArr)
				{
					if (i == j)
					{
						if (checkCountI == checkCountJ)
						{
							result[checkCountI] = '#';
							correctLetters++;
						}
						else if (checkCountI != checkCountJ && result[checkCountI] != '#')
							result[checkCountI] = '*';
					}
					checkCountJ++;
				}
				checkCountI++;
			}
			//Check if letter is not there in word
			int countR = 0;
			for(char x: result)
			{
				if(x==' ')
					result[countR] = 'X';
				countR++;
			}
			//Show Output
			System.out.println("\n"+ip.toUpperCase());
			System.out.println(result);
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
			System.out.println("\nGame Over! Better luck next time!");
		}
	}
	
	// Gets Random 5 letter word
	String getRandomWord()
	{
		return "HELLO";
	}
	
	public static void main(String arg[])
	{
		WordleGame wg = new WordleGame();
	}
}