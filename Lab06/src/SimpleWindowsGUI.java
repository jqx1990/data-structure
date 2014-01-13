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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements a simple GUI with a blank canvas.
 * An instance of a class that implements SimpleWindowManager 
 * is responsible for all drawing within the canvas, and is 
 * responsible for updating the window information based on 
 * the location of the mouse clicks.
 */
public class SimpleWindowsGUI extends JFrame implements MouseListener {

	/** The canvas where the windows are drawn. */
	private RectanglesCanvas canvas;
	
	/** The object that manages the window data. */
	private SimpleWindowManager model;
	
	/** The default width of the canvas */
	public static final int CANVAS_WIDTH = 800;
	/** The default height of the canvas */
	public static final int CANVAS_HEIGHT = 600;
	
	/**
	 * Constructs the GUI and makes the window visible.
	 * 
	 * @param mod the window manager to be used by this GUI.
	 */
	public SimpleWindowsGUI(SimpleWindowManager mod) {
		this.model = mod;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Rectangles!");
		
		this.add(canvas = new RectanglesCanvas(), BorderLayout.CENTER);
		canvas.addMouseListener(this);

		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * This class implements the canvas where the windows 
	 * are drawn.
	 */
	private class RectanglesCanvas extends JPanel {
		
		/**
		 * Constructs the canvas with a white background.
		 */
		public RectanglesCanvas() {
			this.setPreferredSize( new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
			this.setBackground(Color.white);
		}
		
		/**
		 * Draws this component by calling the draw method
		 * of the window manager.
		 */
		public void paintComponent( Graphics g ) {
			super.paintComponent(g);
			model.draw(g);
		}
	}

	/**
	 * When mouse is clicked, the handleClick method in the
	 * window manager is called.
	 * @param e the mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		model.handleClick(e.getX(), e.getY());
		canvas.repaint();
	}

	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed
	 * (java.awt.event.MouseEvent)
	 * This method is built to handle mouse pressed
	 * event for creativity points.
	 */
	public void mousePressed(MouseEvent e) {
		model.handlePress();
		canvas.repaint();
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased
	 * (java.awt.event.MouseEvent)
	 * This method is built to handle mouse release
	 * event for creativity points.
	 */
	public void mouseReleased(MouseEvent e) {
		model.handleRelease();
		canvas.repaint();
	}
}
