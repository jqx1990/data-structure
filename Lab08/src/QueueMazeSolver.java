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

public class QueueMazeSolver implements MazeSolver {

	/**
	 * Instance fields
	 */
	private MazeGUI gui;
	boolean sovable = false;
	String queueStateEntry;
	
	/* (non-Javadoc)
	 * @see MazeSolver#solve(char[][], int, int, int, int)
	 * This is the "core" method of the whole project. It solves the
	 * maze passed into.
	 */
	@Override
	public void solve(char[][] maze, int startR, int startC, int endR, int endC) 
	{
		int row = startR;
		int col = startC;
		sovable = false; // Refresh the value when it loads a new maze.
		ArrayQueue<Cell> agenda = new ArrayQueue<Cell>();
		agenda.offer(new Cell(row, col));
		while(!agenda.isEmpty() && !sovable)
		{
			Cell temp = (Cell) agenda.poll();
			row = temp.getRow();
			col = temp.getCol();
			if(maze[row][col-1]==' ')
			{
				agenda.offer(new Cell(row, col-1));
				if(row == endR && col-1 == endC)
				{
					sovable = true;
				}
				maze[row][col-1] = '@';
				gui.drawMaze(maze);
				queueStateEntry = agenda.toString();// For creativity.
				try 
				{ 
					Thread.sleep(200); 
				}
				catch( InterruptedException e ) 
				{
					System.err.println("Thread interrupted!");
				}
			}
			if(maze[row-1][col]==' ')
			{
				agenda.offer(new Cell(row-1, col));
				if(row-1 == endR && col == endC)
				{
					sovable = true;
				}
				maze[row-1][col] = '@';
				gui.drawMaze(maze);
				queueStateEntry = agenda.toString();// For creativity.
				try 
				{ 
					Thread.sleep(100); 
				}
				catch( InterruptedException e ) 
				{
					System.err.println("Thread interrupted!");
				}
			}
			if(maze[row][col+1]==' ')
			{
				agenda.offer(new Cell(row, col+1));
				if(row == endR && col+1 == endC)
				{
					sovable = true;
				}
				maze[row][col+1] = '@';
				gui.drawMaze(maze);
				queueStateEntry = agenda.toString();// For creativity.
				try 
				{ 
					Thread.sleep(50); 
				}
				catch( InterruptedException e ) 
				{
					System.err.println("Thread interrupted!");
				}
			}
			if(maze[row+1][col]==' ')
			{
				agenda.offer(new Cell(row+1, col));
				if(row+1 == endR && col == endC)
				{
					sovable = true;
				}
				maze[row+1][col] = '@';
				gui.drawMaze(maze);
				queueStateEntry = agenda.toString();// For creativity.
				try 
				{ 
					Thread.sleep(80); 
				}
				catch( InterruptedException e ) 
				{
					System.err.println("Thread interrupted!");
				}
			}
		}
		if(sovable)
		{
			gui.setStatusText("The maze is sovable!");
		}
		else
		{
			gui.setStatusText("The maze is unsovable.");
		}
	}
	
	/**
	 * @return It returns the current state of the queue.
	 */
	public String displayEntry()
	{
		return queueStateEntry;
	}
	
	/**
	 * Constructor
	 */
	public QueueMazeSolver()
	{
		gui = new MazeGUI(this);
	}

	/**
	 * @param args
	 * Main method
	 */
	public static void main( String[] args ) 
	{
	     QueueMazeSolver solver = new QueueMazeSolver();
	}
}
