import myLib.InputField;
import myLib.FileMethods;
class WordleGame
{
	boolean hasQuit=false;
	public static String wordleVersion="v0.0.0p10";
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
		System.out.println("\nPlay Mode Coming soon!");
	}
	
	public static void main(String arg[])
	{
		WordleGame wg = new WordleGame();
	}
}