/**
 * Name: Qing-Xiang Jia
   Assignment: 10
   Title: Binary Search Trees
   Course: CSCE 270
   Lab Section: 01
   Semester: Spring 2011
   Instructor: David Wolff
   Date: 5/15/2011
   Sources consulted: textbook, Dr. Wollf's slides and tutor Matt.
   Program description: This lab contains six files. BinaryTree.java and 
   SearchTree.java have never be changed becaue they are interface. The most
   important one is BinarySearchTree.java, BinarySearchTreeAnalysisOne.java
   and BinarySearchTreeAnalysisTwo.jave. The first one is half finished when
   is was given to us. The rest of its code implements the function of a basic
   binary tree. For AnalysisOne and Two, they solve the problem 7 to 9 and 10.
   The function is described on the work sheet. BinarySearchTreeTest is a set
   of JUnit tests used to test all "unimplemented methods".
   Known Bugs: description of any known problems
   Creativity: anything extra that you added to the lab, please be very specific here
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class BinarySearchTreeAnalysisOne
{
	/**
	 * @param args
	 * @throws FileNotFoundException It exists because of the PrintWriter.
	 * This main method solves problem 7, 8 and 9. It creates 10000 trees
	 * for each required amount of elements. After it adds the elements,
	 * it calculate the average height of the 10000 trees, records it as
	 * well as minimum height, maximum height and standard deviation. Then
	 * all these numbers are printed to a file called "data.csv."
	 */
	public static void main(String args[]) throws FileNotFoundException
	{
		Random rand = new Random();
		PrintWriter out = new PrintWriter("data.csv");
		for(int i = 0; i < 100; i++)
		{
			int[] heights = new int[10000];
			long totalHeight = 0;
			for(int j = 0; j < 10000; j++)
			{
				BinarySearchTree<Long> tree = new BinarySearchTree<Long>();
				for(int k = 0; k < 10*i; k++)
				{
					tree.add(rand.nextLong());
				}
				heights[j] = tree.height();
				totalHeight = totalHeight + tree.height();
			}
			double avgHeight = totalHeight / 10000;
			int min = heights[0];
			int max = heights[0];
			double SDAccumulator = 0;
			/** 
			 *  At first I was suggested to use an Array to hold heights then
			 *  I can just use Array.sort, then the fist element is min and 
			 *  the last is max, but it turns out this way is neat in code but
			 *  it is relatively slow when one actually runs the program.
			 */ 
			for(int l = 0; l < 10000; l++)
			{
				if(heights[l] > max)
					max = heights[l];
				if(heights[l] < min)
					min = heights[l];
				SDAccumulator = SDAccumulator + Math.pow(heights[l] - avgHeight, 2);
			}
			double SD = Math.sqrt(SDAccumulator / 10000);
			out.printf("%d, %d, %f, %f\n", min, max, avgHeight, SD);
		}
		out.close();
	}
}	
