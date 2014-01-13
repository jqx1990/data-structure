//  Name: Qingxiang Jia
//  Assignment: 04
//  Title: HugeInteger
//  Course: CSCE 270
//  Lab Section: 01
//  Semester: Spring 2011
//  Instructor: David Wolff
//  Date: 3/13/2011
//  Sources consulted: Java Doc, MediaLibraryGUI.java, Matthew and Google(I never 
//  copy codes, I only use it to know the syntax of a certain statement).
//  Program description: It processes huge integers in terms of addition and
//  subtraction. No digits limit. This is even better than M$ calculator.
//  Known Bugs: Found and killed.
//  Creativity: Subtraction and GUI. And even subtraction is similar to addition but
//  I used two different way to deal with them. There are also a lot of improvements
//  for GUI including the using of two different layout manager. I also used nested
//  panel. The GUI also prevents user from bad display due to irrational operation to
//  the GUI (for example, meaningless clicking to buttons).

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HugeIntegerGUI extends JFrame
{
	// basics
	
	/**
	 * I use three panel only because I want to make the GUI looks better.
	 */
	private JPanel mainPanel = new JPanel();
	private JPanel controlPanel = new JPanel();
	private JPanel pane = new JPanel();
	
	// textArea
	/**
	 * Three lines create enough space for user to store numbers. Also makes the 
	 * UI looks better.
	 */
	private JTextArea numArea = new JTextArea(3, 0);
	
	// label
	// This label occupies one cell so the GridLayout manager can center the
	// button "0".
	private JLabel emptyLabel = new JLabel("");
	
	// buttons
	private JButton addButton = new JButton("+");
	private JButton subtractButton = new JButton("-");
	private JButton getIt = new JButton("=");
	private JButton clean = new JButton("C");
	private JButton one = new JButton("1");
	private JButton two = new JButton("2");
	private JButton three = new JButton("3");
	private JButton four = new JButton("4");
	private JButton five = new JButton("5");
	private JButton six = new JButton("6");
	private JButton seven = new JButton("7");
	private JButton eight = new JButton("8");
	private JButton nine = new JButton("9");
	private JButton ten = new JButton("0");
	
	// HugeIntegers
	/**
	 * They have to be created in here. If they are created inside the ButtonListener,
	 * the a and b will be gone every time Java is done with the listener.
	 */
	HugeInteger a = new HugeInteger();
	HugeInteger b = new HugeInteger();
	
	// booleans
	/**
	 * These three boolean avoid bad display when user click button irrationally.
	 */
	boolean doGetIt = false;
	boolean doOperation = true;
	boolean canDial = true;
	
	// constructor
	/**
	 * Built up the GUI.
	 */
	public HugeIntegerGUI()
	{
		setTitle("Frontend for HugeInteger");
		numArea.setLineWrap(true);
		pane.setLayout(new BorderLayout());
		pane.add(numArea, BorderLayout.NORTH);
		controlPanel.add(addButton);
		controlPanel.add(subtractButton);
		controlPanel.add(getIt);
		controlPanel.add(clean);
		mainPanel.add(one);
		mainPanel.add(two);
		mainPanel.add(three);
		mainPanel.add(four);
		mainPanel.add(five);
		mainPanel.add(six);
		mainPanel.add(seven);
		mainPanel.add(eight);
		mainPanel.add(nine);
		mainPanel.add(emptyLabel);
		mainPanel.add(ten);
		ButtonListener listener = new ButtonListener();
		addButton.addActionListener(listener);
		subtractButton.addActionListener(listener);
		getIt.addActionListener(listener);
		clean.addActionListener(listener);
		one.addActionListener(listener);
		two.addActionListener(listener);
		three.addActionListener(listener);
		four.addActionListener(listener);
		five.addActionListener(listener);
		six.addActionListener(listener);
		seven.addActionListener(listener);
		eight.addActionListener(listener);
		nine.addActionListener(listener);
		ten.addActionListener(listener);
		mainPanel.setLayout(new GridLayout(0,3));
		pane.add(controlPanel, BorderLayout.CENTER);
		pane.add(mainPanel, BorderLayout.SOUTH);
		this.add(pane);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // Makes the GUI nicer.
	}
	
	// listeners
	/**
	 * This listener class is inspired by Dr.Wollf's MediaLibraryGUI.java.
	 */
	private class ButtonListener implements ActionListener 
	{
		String nextAction = "";
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			if(source == addButton && doOperation == true)
			{
				nextAction = "add";
				a.set(numArea.getText());
				System.out.println(a.toString());
				numArea.setText("");
				doGetIt = true;
				doOperation = false;
			}
			if(source == subtractButton && doOperation == true)
			{
				nextAction = "subtract";
				a.set(numArea.getText());
				System.out.println(a.toString());
				numArea.setText("");
				doGetIt = true;
				doOperation = false;
			}
			if(source == getIt)
			{
				if(nextAction.equals("add") && doGetIt == true)
				{
					b.set(numArea.getText());
					System.out.println(b.toString());
					numArea.setText(a.add(b).toString());
				}
				if(nextAction.equals("subtract") && doGetIt == true)
				{
					b.set(numArea.getText());
					System.out.println(b.toString());
					numArea.setText(a.subtract(b).toString());
				}
				doGetIt = false;
				canDial = false;
			}
			if(source == clean)
			{
				numArea.setText("");
				nextAction = "";
				doGetIt = false;
				doOperation = true;
				canDial = true;
			}
			if(source == one && canDial == true)
			{
				numArea.append("1");
			}
			if(source == two && canDial == true)
			{
				numArea.append("2");
			}
			if(source == three && canDial == true)
			{
				numArea.append("3");
			}
			if(source == four && canDial == true)
			{
				numArea.append("4");
			}
			if(source == five && canDial == true)
			{
				numArea.append("5");
			}
			if(source == six && canDial == true)
			{
				numArea.append("6");
			}
			if(source == seven && canDial == true)
			{
				numArea.append("7");
			}
			if(source == eight && canDial == true)
			{
				numArea.append("8");
			}
			if(source == nine && canDial == true)
			{
				numArea.append("9");
			}
			if(source == ten && canDial == true)
			{
				numArea.append("0");
			}
		}
	}
	
	// call itself
	public static void main(String[] args)throws IOException{

		HugeIntegerGUI test = new HugeIntegerGUI();
		
	}
}
