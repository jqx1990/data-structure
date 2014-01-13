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
import java.util.ListIterator;
import java.util.Random;

public class Window 
{
	/**
	 * Instance fields
	 */
	Random rand = new Random();
	LinkedList<Square> squares = new LinkedList<Square>();
	final int WIDTH = 400;
	final int HEIGHT = 300;
	int x,y;
	Color BorderColor;
	boolean highlighted = false;
	
	/**
	 * Random color to fill the square
	 */
	int color1 = rand.nextInt(255);
	int color2 = rand.nextInt(255);
	int color3 = rand.nextInt(255);
	boolean isPressed;
	
	/**
	 * Methods
	 */
	public Window()
	{
		// Random position but in the real window
		x = rand.nextInt(400);
		y = rand.nextInt(300);
	}
	
	/**
	 * @param g Graphics object
	 */
	public void drawMe(Graphics g)
	{
		// Set color for window border
		if(highlighted == true)
		{
			g.setColor(BorderColor.RED);
		}
		else
		{
			g.setColor(BorderColor.DARK_GRAY);
		}
		
		// Display where mouse is pressed
		if(isPressed == true)
		{
			g.drawString("Spring break is coming!", x, y);
			g.drawString("GNOME 3 will beat OSX!", x, y + 318);
		}
		
		// Set color for shadow
		g.setColor(Color.gray);
		
		// Draw shadow
		g.fillRect(x + 1, y + 0, WIDTH, HEIGHT);
		g.fillRect(x + 2, y + 1, WIDTH, HEIGHT);
		g.fillRect(x + 3, y + 2, WIDTH, HEIGHT);
		g.fillRect(x + 4, y + 3, WIDTH, HEIGHT);
		g.fillRect(x + 5, y + 4, WIDTH, HEIGHT);
		g.fillRect(x + 6, y + 5, WIDTH, HEIGHT);
		g.fillRect(x + 7, y + 6, WIDTH, HEIGHT);
		g.fillRect(x + 8, y + 7, WIDTH, HEIGHT);
		g.fillRect(x + 9, y + 8, WIDTH, HEIGHT);
		
		// Draw the window with color GRAY
		g.drawRect(x, y, WIDTH, HEIGHT);
		
		// Set color to fill the window
		g.setColor(Color.lightGray);
		
		// Use the color to fill the window
		g.fillRect(x + 1, y + 1, WIDTH - 1, HEIGHT - 1);
		
		// Draw squares
		ListIterator<Square> iter = squares.listIterator();
		while(squares != null && iter.hasNext())
		{
			iter.next().drawMe(g, color1, color2, color3);
		}
	}
	
	/**
	 * @param mX The x location of the cursor
	 * @param mY The y location of the cursor
	 * @return
	 */
	public boolean isInside(int mX, int mY)
	{
		// Position of the mouse
		int mouseX = mX;
		int mouseY = mY;
		// Variable needs to be returned
		boolean result = false;
		if((mouseX > x && mouseX < x + 400) && 
				(mouseY > y && mouseY < y + 300))
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * Change the border color of the window under the cursor
	 */
	public void setHighlighted()
	{
		highlighted = true;
	}
	
	/**
	 * Change the border color of the window under the cursor 
	 * back to original 
	 */
	public void undoHighlight()
	{
		highlighted = false;
	}
	
	/**
	 * This method is built to handle mouse pressed event
	 */
	public void setPressed()
	{
		isPressed = true;
	}
	
	/**
	 * This method is built to handle mouse release event
	 */
	public void unpressed()
	{
		isPressed = false;
	}
}
