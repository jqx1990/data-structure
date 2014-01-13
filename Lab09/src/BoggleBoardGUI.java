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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This program is the GUI for boggleBoard. In this program the 
 * units of the board are replaced by buttons. The only reason
 * I did this is because I think JButton has better looking and
 * when you feel that you can click on them (although there is no
 * operation will be implemented), it becomes even better. The 
 * GUI can handle 4x4 or higher size of the board. I try to make 
 * the font looks good by establish a function relates both the 
 * sideLength and font size. So, in most cases, the fonts should 
 * look good but I only test 4x4, 5x5, 6x6, 7x7 and 8x8. The 
 * function may not keep the font size good in all the situations,
 * but in most normal situation it should work. All the interactions
 * between the user and the program are carried by pop-up dialog.
 * The GUI will not prompt the user to input the next word by pop-up
 * a dialog window 'cause it doesn't make sense to let user click 
 * one more time. But the GUI does notify the user to do the next
 * word inputing implicitly by cleaning up the text field in which
 * the user inputs the word.
 * @author Qing-xiang Jia (Lee)
 */

public class BoggleBoardGUI extends JFrame
{
	// Instance fields
	private JPanel mainPanel;
	private JPanel upperPanel;
	private JPanel midPanel;
	private JPanel lowerPanel;
	private BoggleBoard board;
	private JLabel endGameNotice;
	private JButton showTotalScore;
	private BoggleBoardView boardView;
	private JTextField wordFromUser;
	private JButton checkWord;
	
	/**
	 * @param bgb The instance of BoggleBoard will be passed into
	 * the GUI, so the GUI can manipulate the board. 
	 * The constructor sets up all the necessary GUI-relating 
	 * components and refers the parameter so it can be accessed
	 * by the whole GUI codes.
	 */
	public BoggleBoardGUI( BoggleBoard bgb )
	{
		board = bgb;
		mainPanel = new JPanel();
		upperPanel = new JPanel();
		midPanel = new JPanel();
		lowerPanel = new JPanel();
		endGameNotice = new JLabel("When you want to stop " +
				"playing this game, please push →");
		showTotalScore = new JButton("Show total score");
	    boardView = new BoggleBoardView();
		wordFromUser = new JTextField("Type the word you think " +
				"is on the board.");
		checkWord = new JButton("        Check the word       ");
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upperPanel, BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(endGameNotice, BorderLayout.WEST);
		upperPanel.add(showTotalScore, BorderLayout.EAST);
		midPanel.add(boardView);
		lowerPanel.setLayout(new BorderLayout());
		lowerPanel.add(wordFromUser, BorderLayout.LINE_START);
		lowerPanel.add(checkWord, BorderLayout.LINE_END);
		wordFromUser.setPreferredSize(new Dimension(377,10));
		endGameNotice.setPreferredSize(new Dimension(412,10));
		endGameNotice.setFont(new Font("Arial", Font.ITALIC, 16));
		ButtonListener listener = new ButtonListener();
		showTotalScore.addActionListener(listener);
		checkWord.addActionListener(listener);
		this.add(mainPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	/**
	 * It displays the board. It has fixed size, so no matter
	 * how many boxes on the board, the whole board has the same
	 * size. The boxes' size will be reduced.
	 */
	private class BoggleBoardView extends JPanel
	{
		// Instance fields
		private JPanel boardPanel = new JPanel();
		
		/**
		 * This constructor sets up the GUI of the board.
		 * The number of buttons is decided by sideLength.
		 */
		public BoggleBoardView()
		{
			this.setPreferredSize(new Dimension(490,490));
			generateButtons(board.getSideLength());
			boardPanel.setPreferredSize(new Dimension(480,480));
			boardPanel.setLayout(new GridLayout(0, board.getSideLength()));
			this.add(boardPanel);
			setVisible(true);
		}
		
		/**
		 * @param sideLength The sideLength is used to assign letters
		 * to buttons. Matt also gave me the suggestion to use tempButton
		 * so that I can modify the font of each button.
		 */
		private void generateButtons(int sideLength)
		{
			int row = 0;
			for(row = 0; row < sideLength; row++)
			{
				for(int col = 0; col < sideLength; col++)
				{
					JButton tempButton = new JButton(board.
							getBoardElements(row, col));
					tempButton.setFont(new Font("Arial", Font.ITALIC, 
							24 * 5 / board.getSideLength()));
					// Above is the function I mentioned before. It may not
					// work properly under all kinds of situations.
					boardPanel.add(tempButton);
				}
			}
		}
	}
	
	/**
	 * This action listener deals with all the button-clicking jobs.
	 */
	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			if(source == showTotalScore)
			{
				JOptionPane.showMessageDialog(mainPanel, "Your total " +
						"score is : " + board.getFinalScore() +
						"\nPlease click 'OK' and exit the prorgam.");
				// I did "click 'OK' and exit the prorgam" because
				// I didn't have enough time to make the "OK" button
				// capable of closing the program.
			}
			if(source == checkWord)
			{
				if(wordFromUser.getText().equalsIgnoreCase("enter")||
						wordFromUser.getText().equals(""))
				{
					JOptionPane.showMessageDialog(mainPanel, "Your total " +
							"score is : " + board.getFinalScore() +
							"\nPlease click 'OK' and exit the prorgam.");
				}
				else
				{
					// I create a new boggleBoard because there are 
					// some issues happen when user tries multiple
					// words. I didn't debug all of them, so I did
					// this way to avoid the bugs. This may slow down
					// the program when you click "Check the word".
					BoggleBoard bg = new BoggleBoard();
					JOptionPane.showMessageDialog(mainPanel, bg.
							boggleSolver(wordFromUser.getText()));
					// Cleans up the text field, and notifies the user
					// to input new word implicitly.
					wordFromUser.setText("");
				}
			}
		}
	}
	
	/**
	 * @param args This main method starts the whole GUI and passes the
	 * boggleBoard to GUI.
	 */
	public static void main( String[] args )
	{
		BoggleBoard bgb = new BoggleBoard();
		BoggleBoardGUI gui = new BoggleBoardGUI( bgb );
	}
}
