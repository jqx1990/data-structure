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
import java.util.ArrayList;
import java.util.Random;

public class BinarySearchTreeAnalysisTwo 
{
	/**
	 * @param args
	 * @throws FileNotFoundException This is used for PrintWriter.
	 * This main method is used to solve the last problem on work sheet.
	 * It adds 10000, 50000, 100000 and 250000 elements into an
	 * instance of binary search tree (BST) and an instance of ArrayList.
	 * It times the time taken by the search method of BST and indexOf of
	 * ArrayList. Finally it outputs the time to a file called "dataTwo.csv."
	 */
	public static void main(String args[]) throws FileNotFoundException
	{
		Random rand = new Random();
		int[] elementNum = new int[4]; // It assigns required number of adding.
		elementNum[0] = 10000;
		elementNum[1] = 50000;
		elementNum[2] = 100000;
		elementNum[3] = 250000;
		PrintWriter out = new PrintWriter("dataTwo.csv");
		/**
		 * Instantiate BST and ArrayList later so each loop has brand new
		 * BST and ArrayList.
		 */
		BinarySearchTree<Long> tree;
		ArrayList<Long> list;
		for(int h = 0; h < 4; h++)
		{
			tree = new BinarySearchTree<Long>();
			list = new ArrayList<Long>();
			for(int i = 0; i < elementNum[h]; i++)
			{
				long itemToBeAdded = rand.nextLong();
				/** The loop below makes sure that the tree gets added
				 * 	enough elements.
				 */
				while(!tree.add(itemToBeAdded))
				{
					itemToBeAdded = rand.nextLong();
				}
				list.add(itemToBeAdded);
			}
			long A = System.currentTimeMillis(); // Time it.
			for(int i = 0; i < 100000; i++)
			{
				tree.find(rand.nextLong());
			}
			long B = System.currentTimeMillis();
			for(int i = 0; i < 100000; i++)
			{
				list.indexOf(rand.nextLong());
			}
			long C = System.currentTimeMillis();
			out.printf("%f, %f\n", (double)(B - A) / 100000, (double)(C - B) / 100000);
		}
		out.close();
	}
}
