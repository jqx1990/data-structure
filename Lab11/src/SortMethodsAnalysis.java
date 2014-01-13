//  Name: Qing-Xiang Jia
//  Assignment: 11
//  Title: Sorting Experiments
//  Course: CSCE 270
//  Lab Section: 01
//  Semester: Spring 2011
//  Instructor: David Wolff
//  Date: 5/20/2011
//  Sources consulted: textbook, Dr. Wolff, Java Docs
//  Program description: Please see comments after "import".
//  Known Bugs: N/A
//  Creativity: Plot the O(n2) data sets on a single graph
//              and reason about their relative performance.
//              Plot the O(n log n) data sets on a single 
//              graph and reason about their relative performance.
//              (part of these are on the Sorting Report.)

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Qing-Xiang Jia
 * This program is designed to test five sorting algorithms. 
 * I use variables instead of actual number to represent
 * the array size, number of points and step size. Specifically,
 * step size represents the increment of array size as the test
 * processes. The program will prompt the user to chose which 
 * test the user would like to test and after the test is 
 * finished, the program will generate a ".csv" file whose
 * name is depend on the algorithm tested. For different 
 * algorithms I tried a large number of tests to find the best
 * combination of the array size, the increment of the size 
 * and the number of points. The result is that Heap, Quick,
 * and Shell share a set of arguments while Insertion and
 * Selection share anohter set of arguments so that no matter
 * what algorithm the user is testing, the time is basically 
 * the same and the graph generated from the ".cvs" file can
 * accurately reflect the characteristic of the algorithm.
 */
public class SortMethodsAnalysis 
{
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * This main method implements all the function I mentioned at the
	 * beginning of the program.
	 */
	public static void main(String [] args) throws FileNotFoundException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose what sorting method you want to test:\n" +
				"1 Heap\n2 Insertion\n3 Quick\n4 Selection\n5 Shell\n");
		int methodIndicator = keyboard.nextInt();
		Long[] testArray;
		Random rand = new Random();
		PrintWriter out = new PrintWriter("data" + methodIndicator + ".csv");
		int elementsBaseQuantity;
		int pointsQuantity;
		int stepSize;
		// This set of arguments are for O(nlog(n)) and O(n^(5/4))
		// sorting methods.
		if(methodIndicator == 1 || methodIndicator == 3
				|| methodIndicator == 5)
		{
			elementsBaseQuantity = 10000;
			pointsQuantity = 50;
			stepSize = 1000;
		}
		// These arguments are for O(n^2) sorting methods.
		else
		{
			elementsBaseQuantity = 1000;
			pointsQuantity = 40;
			stepSize = 100;
		}
		for(int i = 1; i <= pointsQuantity; i++)
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
				// The if may slow down the program a little bit
				// but according to Dr. Wolff, it's negligible.
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
			out.printf("%d, %f\n", elementsBaseQuantity + 
                    (i-1)*stepSize, stepTime/100.0);
		}
		out.close();
	}
}