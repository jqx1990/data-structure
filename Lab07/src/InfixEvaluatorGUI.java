/**
 * Name: Qingxiang Jia
 * Assignment: Lab #7
 * Title: Expression Evaluator
 * Course: CSCE 270
 * Lab Section: 01
 * Semester: Spring 2011
 * Instructor: David Wolff
 * Date: 4/10/2011
 * Sources consulted: Java Docs, Tutor Matt,
 * http://www.codeguru.com/forum/showthread.php?t=277281
 * and http://junit.sourceforge.net/javadoc/org/junit/BeforeClass.html.
 * Program description: It implements all the required functionalities and it also
 * does a lot of creativity. It has three files. They are: InfixEvaluator.java,
 * InfixEvaluatorGUI.java and InfixEvaluatorTest.java. The first file does all the
 * required works and InfixEvaluatorGUI.java does creativity. The last file does
 * both required works and creativity. 
 * Known Bugs: Not found yet.
 * 
 * Creativity: This program can evaluate % and it also has a GUI to display 
 * the graphic representation of the stack in the test program. Every time you 
 * use InfixEvaluator it will generate a file and record all the stack state 
 * information into the file report.txt. If you use it at the second time,
 * the previous record will be kept so even though you test multiple @test
 * in JUnit, the report will be kept. This function actually create a problem.
 * When you test the program several times the report.txt will get really big.
 * So in order to solve this problem I use @BeforeClass in JUnit class to clean
 * previous records so the report you need can be kept and the thing you don't
 * want will be eliminated. I think this should be another creativity. Another 
 * creativity is in the GUI, I use % to determine where should the program print
 * a bar to separate the pair of operand stack and operator stack. There is a small
 * creativity is that when you click "Display report" button, the program will not
 * generate new information because the .setText(""). The last creativity is my
 * program can detect whether it is caused by operand stack or operator stack when
 * an exception happens and it will point out which exception(an prematurely empty
 * operand stack or a prematurely empty operand stack).
 * 
 * Quick questions:
 * Why don't you create a InfixEvaluator object when test?
 * Why does output file lose all formating?
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This program will read the report.txt which is generated during the 
 * JUnit test and display the formatted details about the both stacks.
 */
public class InfixEvaluatorGUI extends JFrame
{
	// Declare instance variables.
	private JPanel mainPanel = new JPanel();
	private JTextArea testReport = new JTextArea("", 10, 0);
	private JScrollPane report = new JScrollPane(testReport);
	private JLabel readMe = new JLabel("This GUI  displays a graphical" +
			" representation of the stacks after each operation " +
			"in the test program", 10);
	private JButton displayReport = new JButton("Display report");
	
	// Tried but it doesn't work
	private ImageIcon logo = new ImageIcon("csce_logo.png");
	private JLabel picContainer = new JLabel(logo);
	
	/**
	 * Constructor for the GUI.
	 */
	public InfixEvaluatorGUI()
	{
		mainPanel.setLayout(new BorderLayout());
		// Tried but it doesn't work
		picContainer = new JLabel(logo);
		mainPanel.add(picContainer, BorderLayout.EAST);
		
		mainPanel.add(readMe, BorderLayout.NORTH);
		mainPanel.add(report, BorderLayout.CENTER);
		mainPanel.add(displayReport, BorderLayout.SOUTH);
		displayReport.addActionListener(new LoadReportListener());
		testReport.setLineWrap(true);
		this.add(mainPanel);
		setVisible(true);
		pack();
	}
	
	/**
	 * @param args
	 * Main method, so that you can call it and run it.
	 */
	public static void main(String[] args)
	{
		InfixEvaluatorGUI test = new InfixEvaluatorGUI();
	}
	
	/**
	 * @throws FileNotFoundException
	 * This method reads the information in the report.txt and formats it.
	 */
	public void reader() throws FileNotFoundException
	{
		File inputFile = new File("report.txt");
		Scanner scan = new Scanner(inputFile);
		while(scan.hasNext())
		{
			String tempLine = scan.nextLine();
			String[] temp = tempLine.split("ja");
			for(int i = 0; i < temp.length; i++)
			{
				testReport.append(temp[i]+"\n");
				if(i % 2 != 1)
				{
					testReport.append("____________________________" +
					"_________________________________________________" + "\n");
				}
			}
		}
	}
	
	/**
	 * I modified the one in mediaLibrary.
	 */
	private class LoadReportListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				testReport.setText("");// Clean the screen.
				reader();	
			} 
			catch (FileNotFoundException event) 
			{
				System.out.println("For some reason, it doesn't work.");
			}
		}
		
	}
}
