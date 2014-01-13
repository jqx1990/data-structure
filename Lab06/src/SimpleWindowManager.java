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

/**
 * This interface should be implemented by the class that 
 * is responsible for managing the windows.  The SimpleWindowsGUI
 * will then call these methods when needed.
 */

public interface SimpleWindowManager {

	/**
	 * This method will be called when the windows need to
	 * be drawn.  This method should draw all of the windows
	 * and their contents in the proper order using the 
	 * provided Graphics object.
	 * 
	 * @param g the Graphics object used for the drawing.
	 */
	public void draw( Graphics g );
	
	/**
	 * This method will be called when the user clicks anywhere
	 * within the main area of the GUI.  This method should update
	 * the appropriate window lists based on the location of the
	 * click.  The draw method will be called automatically,
	 * shortly after this method returns.
	 * 
	 * @param x the x location of the mouse click.
	 * @param y the y location of the mouse click.
	 */
	public void handleClick( int x, int y );

	/**
	 * I added this for creativity.
	 */
	public void handlePress();

	/**
	 * I added this for creativity.
	 */
	public void handleRelease();
	
}
