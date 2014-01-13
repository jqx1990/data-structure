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

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Square 
{
	/**
	 * Instance fields
	 */
	final int SIDE = 20;
	Color c;
	int X, Y;
	
	/**
	 * Methods
	 */
	/**
	 * @param x X location of the cursor
	 * @param y Y location of the cursor
	 * A constructor
	 */
	public Square(int x, int y)
	{
		X = x;
		Y = y;
	}
	/**
	 * @param g
	 * @param c1 Value for random color
	 * @param c2 Value for random color
	 * @param c3 Value for random color
	 */
	public void drawMe(Graphics g,
			 int c1, int c2, int c3)
	{	
		// Set random color to fill the square
		c = new Color(c1, c2, c3);
		
		// Set a color for the border of the square
		g.setColor(c);
		
		// Use the color to fill the square
		g.fillRect(X, Y, SIDE, SIDE);
		
		// Set color to draw the border
		g.setColor(Color.BLACK);
		
		// Draw the window with color GRAY
		g.drawRect(X, Y, SIDE, SIDE);
	}
	
	/**
	 * @param mX Mouse's location on x-axis
	 * @param mY Mouse's location on y-axis
	 * @return
	 */
	public boolean isInside(int mX, int mY)
	{
		// Position of the mouse
		int mouseX = mX;
		int mouseY = mY;
		// Variable needs to be returned
		boolean result = false;
		if((mouseX > X && mouseX < X + 20) && 
				(mouseY > Y && mouseY < Y + 20))
		{
			result = true;
		}
		return result;
	}
	
}
