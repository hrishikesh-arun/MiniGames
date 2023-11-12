/*
myLib.InputField v0.0.1
*/
package myLib;
import java.util.Scanner;

public class InputField
{
	public static int enterField_int(String field,boolean startNextLine)
	{
		Scanner sc = new Scanner(System.in);
		showText(field,startNextLine);
		int output=sc.nextInt();
		return output;
	}
	public static float enterField_float(String field,boolean startNextLine)
	{
		Scanner sc = new Scanner(System.in);
		showText(field,startNextLine);
		float output=sc.nextFloat();
		return output;
	}
	public static String enterField_str(String field,boolean startNextLine)
	{
		String output;
		Scanner sc = new Scanner(System.in);
		showText(field,startNextLine);
		output=sc.nextLine();
		return output;
	}
	public static char enterField_char(String field,boolean startNextLine)
	{
		char output;
		Scanner sc = new Scanner(System.in);
		showText(field,startNextLine);
		output=sc.nextLine().charAt(0);
		return output;
	}
	static void showText(String field,boolean startNextLine)
	{
		if(startNextLine)
			System.out.println(field);
		else
			System.out.print(field);
	}
}