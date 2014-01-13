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

public class WindowsManagerStarter 
{
	public static void main( String[] args ) 
	{
		MyWindowManager manager = new MyWindowManager();
		SimpleWindowsGUI gui = new SimpleWindowsGUI( manager );
	}
}
