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
 * A program simulates the function of Boggle™.
 * @author Qing-xiang Jia (Lee)
 */
public class BoggleBoard 
{
	// Instance fields
	Dictionary dict = new Dictionary();
	Cell[][] board;
	int sideLength;
	static int score = 0; // Score has to be static since 
						  // it won't change with the object.
	
	/**
	 * The constructor loads the "word.txt" and the "board.txt".
	 */
	public BoggleBoard()
	{
		dict.corpusLoader();
		boardLoader();
	}
	
	/**
	 * @param args
	 * Main method. In this method, it is the main controller
	 * of the program. It prompts the user to input words and
	 * run boggleSolver to check whether the word is on the 
	 * board.
	 */
	public static void main(String [] args)
	{
		Scanner keyboard = new Scanner(System.in);
		boolean go = true;
		while(go)
		{
			BoggleBoard bg = new BoggleBoard();
			bg.printBoard(); // This method prints out the board.
			System.out.print("Enter a word: ");
			String target = keyboard.nextLine();
			if(target.equalsIgnoreCase("enter")||target.equals(""))
			{
				System.out.println("Total score: " + BoggleBoard.getFinalScore());
				go = false; // Prints total score first then ends the loop.
			}
			else
				System.out.println(bg.boggleSolver(target));
		}
	}
	
	// Methods
	
	/**
	 * BoardLoader is in charge of loading board from "board.txt"
	 * and writing the board into a two dimensional array of Cell.
	 * It can handle 4x4 board or higher.
	 */
	public void boardLoader()
	{
		File newBoard = new File("board.txt");
		Scanner scan = null;
		try 
		{
			scan = new Scanner(newBoard);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File Not Found, program will exit.");
			System.exit(0);
		}
		sideLength = scan.nextInt(); // SideLength is a class variable.
		board = new Cell[sideLength][sideLength];
		int row; 
		int col;
		for(row = 0; row < sideLength; row++)
		{
			for(col = 0; col < sideLength; col++)
				board[row][col] = new Cell(scan.next());
		}
	}
	
	/**
	 * @param target This parameter is what user inputs.
	 * @return It is a wrapper method, so what it will
	 * pass the substring of the target to the real 
	 * method. It is substring because the for loop which
	 * is used to find the first matched letter, cuts 
	 * the first letter of the original parameter.
	 * This method can handle 4x4 or higher board size.
	 * This method also in charge of telling the user 
	 * assessment of the word input. And when it works 
	 * with GUI, it also return the result to GUI to pop
	 * up a dialog. Without GUI, it will return the result
	 * to main method.
	 */
	public String boggleSolver(String target)
	{
		boolean isOnBoard = false;
		boolean isValidWord = false;
		String originalTarget = target;
		if(target.length() < 3)
			return "The word '" + originalTarget + "' is too short!";
		if(target.length() >= 3 && dict.binarySearch(target))
			isValidWord = true;
		for(int row = 0; row < sideLength; row++)
		{
			if(isOnBoard)
				break;
			for(int col = 0; col < sideLength; col++)
			{
				if(board[row][col].toString().
						equalsIgnoreCase(target.substring(0, 1)))
				{
					board[row][col].setVisited();
					if(boggleSolver(row, col, target.substring(1)))
					{
						isOnBoard = true;
						break;
					}
				}
			}
		}
		if(!isValidWord)
			return "The word '" + originalTarget + "' is not a valid word.";
		else if(isValidWord && !isOnBoard)
		{
			return "The word '" + originalTarget + "' is a valid word,"
					+ " but is not on the board.";
		}
		else
		{
			score = score + boggleScorer(originalTarget);
			return "The word '" + originalTarget + "' is good!  "
					+ "You score " + boggleScorer(originalTarget) + " points.";
		}
	}
	
	/**
	 * @param row The row is the position of the letter which is the first
	 * letter in the target.
	 * @param col Similar to the row, the col belongs to the first matched
	 * letter.
	 * @param target This parameter is the substring of the word which
	 * will be "solved" 'cause I use a for loop to find the first matched 
	 * letter, so when the boggleSolver gets the parameter, it already 
	 * loses its first letter.
	 * @return It returns a boolean to tell whether the word is found.
	 * This is where the recursion happens. In the recursion process,
	 * the boolean is used to tell whether the letter is found on the
	 * board.
	 * The program will check 8 situations. For each situation, the 
	 * program will go to next step only whenit's sure the next step 
	 * matches the target on the board. setVisited() mark the box 
	 * visited. Make current box as a center and search its surrounding.
	 * If correct, return true; else do nothing. So the program will go
	 * to the next if to check whether the situation matches the target.
	 * If matches, it will go along the new way, else it turns to the
	 * last else in this else, to return false and unvisited current box.
	 * This unvisited operation works for the for loop in the warper 
	 * method.
	 * CAUTIONS: Do not use if-else for 8 situations, 'cause it will screw
	 * up the back track. When it back track, because of the if-else,
	 * the program will not check other possible situation. That's why
	 * test 35, 38 failed at the beginning.
	 */
	private boolean boggleSolver(int row, int col, String target)
	{
		// Base case
		if(target.length() == 0)
			return true;
		else
		{
			if(row - 1 >= 0 && col - 1 >= 0 &&
					row - 1 < sideLength && col - 1 < sideLength &&
					!board[row - 1][col - 1].isVisited() &&
					board[row - 1][col - 1].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				// Mark the box visited.
				board[row - 1][col - 1].setVisited();
				if(boggleSolver(row - 1, col - 1, target.substring(1)))
					return true;
			}
			if(row - 1 >= 0 && col >= 0 && 
					row - 1 < sideLength && col < sideLength &&
					!board[row - 1][col].isVisited() &&
					board[row - 1][col].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row - 1][col].setVisited();
				if(boggleSolver(row - 1, col, target.substring(1)))
					return true;
			}
			if(row - 1 >= 0 && col + 1 >= 0 &&
					row - 1 < sideLength && col + 1 < sideLength &&
					!board[row - 1][col + 1].isVisited() &&
					board[row - 1][col + 1].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row - 1][col + 1].setVisited();
				if(boggleSolver(row - 1, col + 1, target.substring(1)))
					return true;
			}
			if(row >= 0 && col + 1 >= 0 &&
					row < sideLength && col + 1 < sideLength &&
					!board[row][col + 1].isVisited() &&
					board[row][col + 1].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row][col + 1].setVisited();
				if(boggleSolver(row, col + 1, target.substring(1)))
					return true;
			}
			if(row + 1 >= 0 && col + 1 >= 0 &&
					row + 1 < sideLength && col + 1 < sideLength &&
					!board[row + 1][col + 1].isVisited() &&
					board[row + 1][col + 1].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row + 1][col + 1].setVisited();
				if(boggleSolver(row + 1, col + 1, target.substring(1)))
					return true;
			}
			if(row + 1 >= 0 && col >= 0 &&
					row + 1 < sideLength && col < sideLength &&
					!board[row + 1][col].isVisited() &&
					board[row + 1][col].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row + 1][col].setVisited();
				if(boggleSolver(row + 1, col, target.substring(1)))
					return true;
			}
			if(row + 1 >= 0 && col - 1 >= 0 &&
					row + 1 < sideLength && col - 1 < sideLength &&
					!board[row + 1][col - 1].isVisited() &&
					board[row + 1][col - 1].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row + 1][col - 1].setVisited();
				if(boggleSolver(row + 1, col - 1, target.substring(1)))
					return true;
			}
			if(row >= 0 && col - 1 >= 0 &&  
					row < sideLength && col - 1 < sideLength &&
					!board[row][col - 1].isVisited() &&
					board[row][col - 1].toString().
					equalsIgnoreCase(target.substring(0, 1)))
			{
				board[row][col - 1].setVisited();
				if(boggleSolver(row, col - 1, target.substring(1)))
					return true;
			}
			else
			{
				// None of the situation has 
				// been hit, set Unvisited and back track.
				board[row][col].UndoVisited();
				return false;
			}
		}
		//Fail to figure out the situation where this would be hit.
		return false; 
	}
	
	/**
	 * @param target This parameter is the original target, not the
	 * one in the process of recursion or the one modified by the
	 * for loop.
	 * @return It returns the score for each word the user inputs 
	 * based on the rules from Wikipedia.
	 */
	private int boggleScorer(String target)
	{
		if(target.length() < 5)
			return 1;
		else if(target.length() == 5)
			return 2;
		else if(target.length() == 6)
			return 3;
		else if(target.length() == 7)
			return 5;
		else
			return 11;
	}
	
	/**
	 * This method prints out the board in a text style.
	 * It will not be called when GUI is enabled. It can
	 * handle 4x4 board or higher.
	 */
	public void printBoard()
	{
		int row = 0;
		int col = 0;
		System.out.print("+");
		for(int i = 0; i < sideLength * 2 - 1; i++)
			System.out.print("-");
		System.out.print("+\n");
		for(row = 0; row < sideLength; row++)
		{
			for(col = 0; col < sideLength; col++)
			{
				if(col == 0)
					System.out.print("|");
				System.out.print(board[row][col]);
				if(col != sideLength - 1)
					System.out.print(" ");
				if(col == sideLength - 1)
					System.out.print("|");
				if(col == sideLength - 1)
					System.out.print("\n");
			}
		}
		System.out.print("+");
		for(int i = 0; i < sideLength * 2 - 1; i++)
			System.out.print("-");
		System.out.print("+\n");
	}
	
	/**
	 * @return It returns the sideLength of the board.
	 * This method is specifically written for GUI.
	 */
	public int getSideLength()
	{
		return sideLength;
	}
	
	/**
	 * @param row The row of the letter on the board.
	 * @param col The column of the letter.
	 * @return It returns a string which is the letter.
	 * This method is specifically designed for GUI.
	 */
	public String getBoardElements(int row, int col)
	{
		return board[row][col].toString();
	}
	
	/**
	 * @return It returns the total scores.
	 * It only be called when the user wants to end 
	 * the program according to the lab requirements.
	 * Both GUI and the original program use this method.
	 */
	public static int getFinalScore()
	{
		return score;
	}
}
