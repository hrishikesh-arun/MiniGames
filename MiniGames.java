import myLib.InputField;
import myLib.FileMethods;
import games.wordleCMD.WordleGame;

class MiniGames
{
	boolean hasQuit=false;
	public static String miniGamesVersion="v0.0.0d1";
	MiniGames()
	{
		// Start
		System.out.println("MiniGames "+miniGamesVersion);
		System.out.println("\nWelcome to MiniGames!");
		String op;
		viewInstructions();
		do
		{
			
			op=InputField.enterField_str("\nMiniGames> ",false);
			switch(op)
			{
				case "0":
					hasQuit=true;
					break;
				case "1":
					playWordleCMD();
					break;
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
	void playWordleCMD()
	{
		System.out.println();
		WordleGame wg = new WordleGame();
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