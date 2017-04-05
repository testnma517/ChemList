
import java.util.Scanner;
/**
 *  Provide some utilities for user input.  We
 *  want to enhance the Scanner class, so that
 *  our programs can recover from "bad" input,
 *  and also provide a way to limit numerical
 *  input to a range of values.
 *
 *  @author Nathan Ma
 *  @version  August 19th, 2015
 */

public class Prompt
{
	/**
	 *  Prompts for a string from the keyboard.
	 *
	 *  @param ask  string prompt
	 *  @return    the string gotten from the keyboard
	 */
	public static String getString (String ask)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print(ask);
		String input = keyboard.nextLine();
		//System.out.print(input);
		return input;
	}
	
	/**
	 * Prompts the user for a character and returns the character.
	 * @param ask the prompt line
	 * @return the character input 
	 */
	 
	 public static char getChar(String ask)
	 {
		String input = new String ("");
		char ch = '+';
		do
		{
			input = getString(ask);
		} 
		while(input.length() !=1);
		ch = input.charAt(0);
		return ch;
	 }
	/**
	 *  Prompts for an integer from the keyboard
	 *
	 *  @param ask  string prompt
	 *  @return    the integer gotten from the keyboard
	 */
	public static int getInt (String ask)
	{
		boolean badInput = false;
		String input = new String (ask);
		int value = 0;
		do
		{
			badInput = false;
			input = getString(ask);
			try 
			{
			value = Integer.parseInt(input);
			}
			catch (NumberFormatException e)
			{
				badInput = true;
			}
		}
		while(badInput);
		
		return value;
	}
	
	/**
	 *  Prompts for an integer from the keyboard within a range of values.
	 *
	 *  @param ask  string prompt
	 *  @param min  the minimum acceptable value
	 *  @param max  the maximum acceptable value
	 *  @return    the integer gotten from the keyboard
	 */
	public static int getInt (String ask, int min, int max)
	{
		boolean badInput = false;
		String input = new String (ask);
		int value = 0;
		boolean correctRange = false;

		do
		{
			input = getString(ask);
			badInput = false;
			try 
			{
				
				value = Integer.parseInt(input);
				
			}
			catch (NumberFormatException e)
			{
				badInput = true;
				
			}
			
			if(value >= min && value <= max)
				correctRange = true;
			else
				correctRange = false;
		}
		while(badInput || !correctRange);
		
		return value;
	}
}