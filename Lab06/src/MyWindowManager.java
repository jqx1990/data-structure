//Name: Qingxiang Jia
//Assignment: 06
//Title: A Window Manager
//Course: CSCE 270
//Lab Section: 01
//Semester: Spring 2011
//Instructor: David Wolff
//Date: 3/27/2011
//Sources consulted: Java Doc, Tutor Matt.
//Known Bugs: description of any known problems
//Creativity: #1 I add a shadow for every window it creates and this shadow gets
//nicer looking by placing several shadow together with slightly different 
//coordiantes. #2 I modified SimpleWindowManager interface and SimpleWindowsGUI
//class to add a few methods to handle the events happen when user clicks the 
//window and release the mouse. You can find a slogan "Spring break is coming!" on
//the top of the window and a slogan "GNOME 3 will beat OSX!" near the bottom of
//the window. And the charactor will change their colors along with the border of
//the window.

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;


public class MyWindowManager implements SimpleWindowManager 
{
	/**
	 * Instance field
	 */
	LinkedList<Window> windows = new LinkedList<Window>();
	Window temp;
	Window lastWindow;
	int X, Y;
	
	/* (non-Javadoc)
	 * @see SimpleWindowManager#draw(java.awt.Graphics)
	 * Methods
	 */
	
	/**
	 * Constructor
	 */
	public MyWindowManager()
	{
		// Create seven windows
		ListIterator<Window> iter = windows.listIterator();
		for(int i = 0; i < 7; i++)
		{
			iter.add(new Window());
		}
	}
	
	/* (non-Javadoc)
	 * @see SimpleWindowManager#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) 
	{
		ListIterator<Window> iter = windows.listIterator();
		while(iter.hasNext())
		{
			iter.next().drawMe(g);
		}
	}

	/* (non-Javadoc)
	 * @see SimpleWindowManager#handleClick(int, int)
	 */
	@Override
	public void handleClick(int x, int y) 
	{
		ListIterator<Window> iter = windows.listIterator(windows.size());
		boolean stop = false;
		while(iter.hasPrevious() && stop == false)
		{
			temp = iter.previous();
			if(temp.isInside(x, y) == true)
			{
				temp.setHighlighted();
				stop = true; // Tricky
			}
		}
		X = x;// Let other methods know the location as well.
		Y = y;// Let other methods know the location as well.
		/**
		 * Move the element referenced by temp to the tail
		 */
		while(iter.hasNext()) // iter goes to the end
		{
			iter.next();
		}
		iter.add(temp); // the activated window is on the top
		
		if(lastWindow != null 
				&& lastWindow.isInside(X, Y) == false) // The border color goes back
		{
			lastWindow.undoHighlight();
		}
		lastWindow = temp; // Set current window to last window
		//WHAT HAPPEN TO THE ELEMENT THAT WAS POINTED BY TEMP?
		/**
		 * Now, let's deal with squares on the window
		 */
		boolean needAddNewSquare = true;
		if(temp.squares.isEmpty() == false)
		{
			ListIterator squareIter = temp.squares.listIterator();
			while(squareIter.hasNext())
			{
				Square subSquare = (Square) squareIter.next();
				if(subSquare.isInside(X, Y) == true)
				{
					squareIter.remove();
					needAddNewSquare = false;
				}
			}
		}
		if(needAddNewSquare == true && notInAnyWindow() == false
				)
		{
			temp.squares.add(new Square(X, Y));
		}
	}
	
	public boolean notInAnyWindow()
	{
		boolean result = true;
		ListIterator iter = windows.listIterator();
		while(iter.hasNext() && result == true)
		{
			Window subTemp;
			subTemp = (Window) iter.next();
			boolean isTooNarrow;
			if((X > temp.x && X < temp.x + 380)
					 && (Y > temp.y && Y < temp.y + 280))
			{
				isTooNarrow = false;
			}
			else
			{
				isTooNarrow = true;
			}
			if(subTemp.isInside(X, Y) == true
					&& isTooNarrow == false)
			{
				result = false;
			}
		}
		return result;
	}
	
	public void handlePress()
	{
		ListIterator iter = windows.listIterator();
		while(iter.hasNext())
		{
			Window subTemp;
			subTemp = (Window) iter.next();
			subTemp.setPressed();
		}
	}

	@Override
	public void handleRelease() {
		ListIterator iter = windows.listIterator();
		while(iter.hasNext())
		{
			Window subTemp;
			subTemp = (Window) iter.next();
			subTemp.unpressed();
		}
	}
}
