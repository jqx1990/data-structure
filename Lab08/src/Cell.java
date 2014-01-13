/**
 *Name: Qingxiang Jia
 *Assignment: 08
 *Title: Queues and Mazes
 *Course: CSCE 270
 *Lab Section: 01
 *Semester: Spring 2011
 *Instructor: David Wolff
 *Date: 4/18/2011
 *Sources consulted: Textbook, Java docs and tutor Matt.
 *Program description: It solves the maze and shows the cells it pasts.
 *Known Bugs: N/A
 *Creativity: (1) Improvement of the GUI to include a visualization of the
 *                queue and/or stack as the solver is running.
 *            (2) Every time you click "load" button, the background color
 *                of the right-hand text area gets changed.
 *            (3) Every time you click "load", the text area gets cleaned.
 *            (4) Scroll bars would appear when needed.
 *            (5) Create a toString method for Cell class.
 */

public class Cell 
{
	/**
	 * Instance field
	 */
	private int row;
	private int col;
	
	/**
	 * @param r The row.
	 * @param c The col.
	 */
	public Cell(int r, int c)
	{
		row = r;
		col = c;
	}
	
	/**
	 * @return
	 * Getter
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * @return
	 * Getter
	 */
	public int getCol()
	{
		return col;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Prints out the string representation of the Cell object.
	 */
	public String toString()
	{
		String result = "(" + row + ", " + col + ")";
	    return result;
	}
}
