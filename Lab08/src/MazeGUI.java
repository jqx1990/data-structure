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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A simple GUI for displaying and animating maze solvers.
 * 
 * @author David Wolff
 */
@SuppressWarnings("serial")
public class MazeGUI extends JPanel {
	
	private MazeView mazeView;
	private JTextField mazeFileTF;
	private JButton loadButton;
	private JLabel infoLabel;
	private JButton startButton;
	private QueueMazeSolver solver; // It used to be MazeSolver.
	private JTextArea queueState = new JTextArea("", 10, 10);
	private JScrollPane queueScroll = new JScrollPane(queueState);
	private int counter = 0;
	/**
	 * Creates and displays the Maze GUI.
	 */
	public MazeGUI(QueueMazeSolver slvr) { // It used to be MazeSolver.
		this.solver = slvr;
		buildGUI();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false); // Learned!
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Set the text of the status label displayed at the bottom of 
	 * this window.
	 * 
	 * @param text the text for the status bar.
	 */
	public void setStatusText( String text ) {
		this.infoLabel.setText(text);
	}
	
	private void buildGUI() {
		this.setLayout(new BorderLayout());
		JPanel inputPanel = new JPanel();
		ActionListener listener = new MazeGUIActionListener();
		
		mazeFileTF = new JTextField(6);
		mazeFileTF.addActionListener(listener);
		inputPanel.add(mazeFileTF);
		
		loadButton = new JButton("Load");
		loadButton.addActionListener(listener);
		inputPanel.add( loadButton );
		
		startButton = new JButton("Start");
		startButton.setEnabled(false);
		startButton.addActionListener(listener);
		inputPanel.add( startButton );
		
		this.add(inputPanel, BorderLayout.NORTH);
		
		mazeView = new MazeView();
		this.add(mazeView, BorderLayout.CENTER);
		JPanel infoPanel = new JPanel();
		this.infoLabel = new JLabel("Enter file name above.");
		infoPanel.add(infoLabel);
		this.add(infoPanel,BorderLayout.SOUTH);
		this.add(queueScroll, BorderLayout.EAST); // Add a scroll bar.
		queueScroll.setPreferredSize(new Dimension(670,600)); // Fix the size of
															  // scroll bar.
		queueState.setLineWrap(true);
	}
	
	/**
	 * This will cause the maze view to be re-drawn.  It will use
	 * the data in the parameter array to draw the maze.  It
	 * recognizes the following characters:
	 *   '#' - a wall
	 *   ' ' - an open space
	 *   '@' - an explored space
	 *   '%' - an ideal path
	 *   
	 * @param m the array containing the maze data.
	 */
	public void drawMaze(char[][] m) {
		mazeView.redrawMaze(m);
		if(counter != 0)
		{
			queueState.append(solver.displayEntry() + "\n");
		}
		counter ++;
	}
	
	/**
	 * This method will be called when the solver has finished
	 * executing.
	 */
	private void solverFinished() {
		startButton.setEnabled(true);
		loadButton.setEnabled(true);
	}

	/**
	 * The listener for the GUI's buttons and text field.
	 *
	 */
	private class MazeGUIActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getSource() == loadButton || e.getSource() == mazeFileTF ) {
				infoLabel.setText("Loading maze: " + mazeFileTF.getText());
				try {
					mazeView.loadMaze(mazeFileTF.getText());
					mazeView.drawMaze();
					infoLabel.setText("Maze " + mazeFileTF.getText() + " loaded." );
					startButton.setEnabled(true);
					counter = 0; // Reset the counter.
					queueState.setText(""); // Reset the textArea.
					Random rand = new Random(); // Generates random numbers.
					int r = rand.nextInt(255);
					int g = rand.nextInt(255);
					int b = rand.nextInt(255);
					Color c = new Color(r, g, b); // Random color.
					queueState.setBackground(c);
				} catch(IOException ex) {
					String message = "Unable to load maze file: " 
						+ ex.getMessage();
					JOptionPane.showMessageDialog(MazeGUI.this, message);
					infoLabel.setText(message);
				}
			}
			
			if( e.getSource() == startButton ) {
				startButton.setEnabled(false);
				loadButton.setEnabled(false);
				mazeView.startSolver();
			}
		}
	}
	
	private class MazeView extends JPanel {

		private BufferedImage img;
		private Maze originalMaze = null;
		
		// Width and height of maze view
		private static final int WIDTH = 600;
		private static final int HEIGHT = 600;
		
		// Extra space between cell boundary and start/goal marker
		private static final int BUFFER = 4;
		
		/**
		 * Creates a "blank" MazeView.  The view is initially
		 * black with a message in the center.
		 */
		public MazeView() {
			this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
			Graphics g = img.getGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);
			g.drawString("To load a maze, type in the file name, and click Load.", 
					WIDTH/2 - 200, HEIGHT/2);
		}
		
		/**
		 * Draws the content for this MazeView.
		 */
		public void paintComponent( Graphics g ) {
			super.paintComponent(g);
			
			g.drawImage(img, 0, 0, this);
		}
		
		public void loadMaze( String fileName ) throws IOException {
			originalMaze = new Maze(fileName);
		}
		
		/**
		 * Initiates a redraw of the maze.  If the Maze is not set
		 * (see setMaze) this does nothing.
		 */
		public void redrawMaze(char[][] maze) {
			Graphics g = img.getGraphics();
			this.draw(g, maze);
			this.repaint();
		}
		
		public void drawMaze() {
			Graphics g = img.getGraphics();
			this.draw(g, originalMaze.getArray());
			this.repaint();
		}
		
		public void startSolver() {
			if( this.originalMaze == null ) return;
			Thread t = new Thread(new Runnable() {
				public void run() {
					solver.solve(originalMaze.getArray(),originalMaze.getStartRow(),
							originalMaze.getStartCol(),originalMaze.getGoalRow(),
							originalMaze.getGoalCol());
					solverFinished();
				}
			});
			t.start();
		}
		
		/**
		 * Draws this Maze using the given Graphics object.
		 * 
		 * @param g a Graphics object
		 * @param w the width of the canvas in pixels.
		 * @param h the height of the canvas in pixels.
		 */
		public void draw(Graphics g, char[][] maze) {
			if( this.originalMaze == null ) return;
			
			int cols = maze[0].length;
			int rows = maze.length;
			double cellW = (double)WIDTH / cols;
			double cellH = (double)HEIGHT / rows;
			int cellWi = (int)Math.round(cellW);
			int cellHi = (int)Math.round(cellH);
			
			int x, y;
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);
			for( int r = 0; r < rows; r++ ) {
				y = (int)Math.floor(r * cellH);
				for( int c = 0; c < cols; c++ ) {
					x = (int)Math.floor(c * cellW);
					char cell = maze[r][c];
					if( cell == '@' || cell == ' ' || cell == '%' ) {
						if( cell == '@' ) 
							g.setColor(Color.lightGray);
						else if (cell == '%')
							g.setColor(Color.CYAN);
						else
							g.setColor(Color.white);
						g.fillRect(x, y, cellWi, cellHi);
						g.setColor(Color.black);
						g.drawRect(x,y,cellWi,cellHi);
					}
				}
			}
			
			g.setColor(Color.green);
			x = (int)Math.round(cellW * originalMaze.getStartCol()) + BUFFER;
			y = (int)Math.round(cellH * originalMaze.getStartRow()) + BUFFER;
			g.fillOval(x, y, cellWi - 2 * BUFFER, cellHi - 2 * BUFFER );
			
			g.setColor(Color.red);
			x = (int)Math.round(cellW * originalMaze.getGoalCol()) + BUFFER;
			y = (int)Math.round(cellH * originalMaze.getGoalRow()) + BUFFER;
			g.fillOval(x, y, cellWi - 2 * BUFFER, cellHi - 2 * BUFFER );
		}
	}

}
