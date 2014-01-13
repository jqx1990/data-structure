/**
 *
  Name: Qingxiang Jia (Lee)
  Assignment: 09
  Title: Boggle Board
  Course: CSCE 270
  Lab Section: 01
  Semester: Spring 2011
  Instructor: David Wolff
  Date: the 5/2/2011
  Sources consulted: Dr.Wolff, Matt, Java Doc and 
  http://www.java2s.com/Tutorial/Java/0240__Swing/MessagePopUps.htm
  Program description: This program will read a text file to generate the
  boggle board and the user can play with it. This program has three main
  part: Boggle Board (main program), GUI, and Dictionary. User can play both
  GUI and Boggle Board. Two parts use recursion: binary search in Dictionary and
  boggleSolver in BoggleBoard. GUI uses buttons to represent grids.
  Known Bugs: [SOLVED]When input more than one word, the program will not return
  correct answer BUT this bug has been avoided by creating a new boggleBoard. 
  And it works well. So, no bugs left.
  Creativity: A GUI for the program. It uses dialog windows to interact with the 
  user. It provides the same quality as the one provided by non-GUI program.
  In creativity part, the UI will change according to the "board.txt". This is a 
  major progress in my programming career. I also use a function to make sure 
  in most cases the fonts will be clearly displayed on the button. It's a GUI, but
  more than a GUI.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Qing-xiang Jia (Lee)
 * This class will read "words.txt" and store the words in an array of String
 * It uses binary search to search the word recursively. It accepts both 
 * upper, lower or mix expressions because it changes all letters into 
 * lower case.
 */
public class Dictionary 
{
	// Instance fields
	private String[] corpus = new String[172823];
	
	// Methods
	/**
	 * It loads words from "words.txt."
	 */
	public void corpusLoader()
	{
		File inputeFile = new File("words.txt");
		Scanner scan = null;
		int i = 0;
		try 
		{
			scan = new Scanner(inputeFile);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File Not Found, program will exit.");
			System.exit(0);
		}
		while(scan.hasNext())
		{
			corpus[i] = scan.next();
			i++;
		}
	}
	
	/**
	 * @param tar The to-be-searched word.
	 * @return By calling the real method to return
	 * a boolean indicating whether the word exists.
	 */
	public boolean binarySearch(String tar)
	{
		// Make lower case and upper case identical
		String target = tar.toLowerCase(); 
		return binarySearch(target, 0, corpus.length - 1);
	}
	
	/**
	 * @param target The to-be-searched word passed by the wrapper method.
	 * @param first The first word in the corpus.
	 * @param last The last word in the corpus.
	 * @return True if the word exists, false if not.
	 * It uses compare to recursively call itself to find the word.
	 */
	private boolean binarySearch (String target, int first, int last)
	{
		int middle;
		int compResult;
		if(first > last)
		{
			return false;
		}
		else
		{
			middle = (first + last) / 2;
			compResult = target.compareTo(corpus[middle]);
			if(compResult == 0)
			{
				return true;
			}
			if(compResult < 0)
			{
				return binarySearch(target, first, middle - 1);
			}
			else
			{
				return binarySearch(target, middle + 1, last);
			}
		}
	}
}
