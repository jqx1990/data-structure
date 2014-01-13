import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SortMethodsGUI extends JFrame 
{
	// Class variables
	JPanel pane;
	JPanel controlPanel;
	JPanel canvas;
	JButton heapSortButton;
	JButton insertionSortButton;
	JButton quickSortButton;
	JButton selectionSortButton;
	JButton shellSortButton;
	JTextArea arrayBaseSizeInputArea;
	JButton readMe;
	PlotCanvas plots;
	ButtonListener listener;
	int[] x;
	int[] y;
	int methodIndicator = 1;
	String status = "initial";

	public SortMethodsGUI()
	{
		pane = new JPanel();
		controlPanel = new JPanel();
		canvas = new JPanel();
		canvas.setPreferredSize(new Dimension(1000, 600));
		heapSortButton = new JButton("Heap Sort");
		insertionSortButton = new JButton("Insertion Sort");
		quickSortButton = new JButton("Quick Sort");
		selectionSortButton = new JButton("Selection Sort");
		shellSortButton = new JButton("Shell Sort");
		arrayBaseSizeInputArea = new JTextArea("Input the base size of the array.");
		arrayBaseSizeInputArea.setPreferredSize(new Dimension(150, 30));
		readMe = new JButton("Read Me");
		plots = new PlotCanvas();
		plots.setPreferredSize(new Dimension(1000, 600));
		pane.setLayout(new BorderLayout());
		pane.add(controlPanel, BorderLayout.NORTH);
		pane.add(canvas, BorderLayout.CENTER);
		canvas.add(plots);
		this.add(pane);
		controlPanel.add(heapSortButton);
		controlPanel.add(insertionSortButton);
		controlPanel.add(quickSortButton);
		controlPanel.add(selectionSortButton);
		controlPanel.add(shellSortButton);
		controlPanel.add(arrayBaseSizeInputArea);
		controlPanel.add(readMe);
		listener = new ButtonListener();
		heapSortButton.addActionListener(listener);
		insertionSortButton.addActionListener(listener);
		quickSortButton.addActionListener(listener);
		selectionSortButton.addActionListener(listener);
		shellSortButton.addActionListener(listener);
		readMe.addActionListener(listener);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String [] args)
	{
		SortMethodsGUI gui = new SortMethodsGUI();
	}
	
	public class PlotCanvas extends Canvas 
	{
		public void paint(Graphics g) 
		{
			if(status.equals("initial"));
			{
				g.drawRect(0, 0, 1000, 600);
				g.setFont(new Font("Arial", Font.ITALIC, 20));
				g.drawString("This is where the polts gonna be!", 200, 200);
				g.drawString("When you click a sorting method, the", 200, 240);
				g.drawString("program will get stuck for about se" +
						"veral minutes,", 200, 280);
				g.drawString("please be patient. Thanks.", 200, 320);
				g.drawRect(0, 0, 1000, 600);
			}
			if(status.equals("sortFinished"))
			{
				g.setColor(Color.WHITE);
				g.drawRect(0, 0, 1000, 600);
				g.drawPolyline(x, y, 10);
				System.out.println("redrawed");
			}
		}
	}
	
	public void SortMethodsAnalysis()
	{
		Long[] testArray;
		Random rand = new Random();
		int elementsBaseQuantity;
		int pointsQuantity;
		int stepSize;
		if(methodIndicator == 1 || methodIndicator == 3
				|| methodIndicator == 5)
		{
			elementsBaseQuantity = 10000;
			pointsQuantity = 10;//should be 50
			stepSize = 1000;
		}
		else
		{
			elementsBaseQuantity = 1000;
			pointsQuantity = 40;
			stepSize = 100;
		}
		x = new int[pointsQuantity];
		y = new int[pointsQuantity];
		for(int i = 0; i < pointsQuantity; i++)
		{
			long stepTime = 0;
			for(int j = 0; j < 100; j++)
			{
				testArray = new Long[elementsBaseQuantity + 
				                     (i-1)*stepSize];
				for(int k = 0; k < elementsBaseQuantity + 
                (i-1)*stepSize; k++)
				{
					testArray[k] = rand.nextLong();
				}
				long start = System.currentTimeMillis();
				if(methodIndicator == 1)
					HeapSort.sort(testArray);
				if(methodIndicator == 2)
					InsertionSort.sort(testArray);
				if(methodIndicator == 3)
					QuickSort.sort(testArray);
				if(methodIndicator == 4)
					SelectionSort.sort(testArray);
				if(methodIndicator == 5)
					ShellSort.sort(testArray);
				long end = System.currentTimeMillis();
				stepTime = stepTime + (end - start);
			}
			x[i] = elementsBaseQuantity + (i-1)*stepSize;
			y[i] = (int)stepTime;
			System.out.println(i);
		}
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			if(source == heapSortButton)
			{
				methodIndicator = 1;
				SortMethodsAnalysis();
				status = "sortFinished";
				//plots.paint(Graphics g);
				System.out.println("plots.repaint()");
			}
			if(source == insertionSortButton)
			{
				methodIndicator = 2;
				SortMethodsAnalysis();
			}
			if(source == quickSortButton)
			{
				methodIndicator = 3;
				SortMethodsAnalysis();
			}
			if(source == selectionSortButton)
			{
				methodIndicator = 4;
				SortMethodsAnalysis();
			}
			if(source == shellSortButton)
			{
				methodIndicator = 5;
				SortMethodsAnalysis();
			}
			if(source == readMe)
			{
				JOptionPane.showMessageDialog(pane, "Your total " +
						"score is : " +
						"\nPlease click 'OK' and exit the prorgam.");
			}
		}
	}
}
