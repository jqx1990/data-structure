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

/**
 * @author Qing-xiang Jia (Lee)
 * Cell class represents a single element on the board.
 */
public class Cell 
{
	boolean isVisited = false;
	String content;
	
	/**
	 * @param con The letter will be hold by the cell.
	 */
	public Cell(String con)
	{
		content = con;
	}
	
	/**
	 * Set the cell visited.
	 */
	public void setVisited()
	{
		isVisited = true;
	}
	
	/**
	 * Undo the last method.
	 */
	public void UndoVisited()
	{
		isVisited = false;
	}
	
	/**
	 * @return True: it is visited; False: it's not.
	 */
	public boolean isVisited()
	{
		if(isVisited)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * It returns a string representation of the letter it holds.
	 */
	public String toString()
	{
		return content;
	}
}
