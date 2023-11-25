/*
myLib.FileMethods v1.0
*/
package myLib;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileMethods
{
	public static void writeFile(String location,String data,boolean isAppend)
	{
		// Write File
		try
		{
			FileWriter fw= new FileWriter(location,isAppend);
			fw.write(data);
			fw.close();
		}
		catch(Exception e)
		{
			System.out.println("An unexpected error occurred");
			e.printStackTrace();
		}
	}
	public static String readFile(String location)
	{
		// Read File
		File f = new File(location);
		String textData = "";
		try
		{
			Scanner sc = new Scanner(f);
			String data = "";
			while(sc.hasNextLine())
			{
				data=sc.nextLine();
				textData+=data+"\n";
			}
		}
		catch(Exception e)
		{
			System.out.println("An unexpected error occurred");
			e.printStackTrace();
		}
		return textData;
	}
	public static String[] readFile_LineByLine(String location,int len)
	{
		// Read File
		File f = new File(location);
		String[] textData = new String[len];
		int count=0;
		try
		{
			Scanner sc = new Scanner(f);
			String data = "";
			while(sc.hasNextLine())
			{
				data=sc.nextLine();
				textData[count] = data;
				count++;
			}
		}
		catch(Exception e)
		{
			System.out.println("An unexpected error occurred");
			e.printStackTrace();
		}
		return textData;
	}
	/*public static void main(String arg[])
	{
		//Create File
		
		try
		{
			if(f.createNewFile())
			{
				System.out.println("File Created");
			}
			else
			{
				System.out.println("File Already Exists");
			}
		}
		catch(Exception e)
		{
			System.out.println("An unexpected error occurred");
		}

	}*/
}