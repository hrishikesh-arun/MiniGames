import myLib.InputField;
class WordleGame
{
	boolean hasQuit=false;
	public static String wordleVersion="v0.0.0p9";
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
					System.out.println("\nPlay Mode Coming soon!\n");
					break;
				case "i":
					viewInstructions(true,true);
					break;
				case "e":
					System.out.println("\nPatch Notes Page Coming Soon!");
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
			System.out.println("\nHow to Play\nThe objective of the game is to guess the 5 letter word correctly.\nIf you get a letter that is in the word and in the correct position, It is signified by a # symbol.\nIf you get a letter that is in the word but not in its correct position, then it will be signified by a *\nIf you get a letter that is not in the word, then it will be signified by a X\nYou have only 6 guesses, after which the word will be revealed.\nHope you will find the game interesting. Enjoy!");
	}
	void viewCredits()
	{
		System.out.println("\n\nWordle CMD "+wordleVersion+"\nCreated by KityStudio as part of the MiniGames Project\nWordle and MiniGames uses runJava 2.0 and myLib 0.1 also made by KityStudio to work\nProgrammed in Java\n\nHope you like this game! - KityStudio");
	}
	public static void main(String arg[])
	{
		WordleGame wg = new WordleGame();
	}
}