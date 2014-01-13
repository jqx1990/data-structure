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

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Qing-Xiang Jia
 * This test tests all the methods inside the Unimplemented methods.
 * I can but I don't think it's necessary to add more Java Docs for 
 * JUnit tests.
 */
public class BinarySearchTreeTest 
{
	BinarySearchTree<Integer> tree;
	@Before
	public void preparation()
	{
		tree = new BinarySearchTree<Integer>();
		tree.add(1000);
		tree.add(500);
		tree.add(2000);
		tree.add(1500);
		tree.add(3000);
		tree.add(700);
	}
	
	@Test
	public void findTest01()
	{
		assertEquals(3000, (int)tree.find(3000));
	}
	
	@Test
	public void sizeTest01()
	{
		assertEquals(6, tree.size());
	}
	
	@Test
	public void interiorNodesTest01()
	{
		assertEquals(3, tree.interiorNodes());
	}
	
	@Test
	public void findMinTest01()
	{
		assertEquals(500, (int) tree.findMin());
	}
	
	@Test
	public void findMaxTest01()
	{
		assertEquals(3000, (int) tree.findMax());
	}
	
	@Test
	public void removeMinTest01()
	{
		tree.removeMin();
		assertEquals(null, tree.find(500));
	}
	
	@Test
	public void removeMaxTest01()
	{
		tree.removeMax();
		assertEquals(null, tree.find(3000));
	}
	
	@Test
	public void leavesTest01()
	{
		assertEquals(3, tree.leaves());
	}
	
	@Test
	public void heightTest01()
	{
		assertEquals(3, tree.height());
	}
	
	@Test
	public void inorderTest01()
	{
		assertEquals("500 700 1000 1500 2000 3000", tree.inorder());
	}
	
	@Test
	public void preorderTest01()
	{
		assertEquals("1000 500 700 2000 1500 3000", tree.preorder());
	}
	
	@Test
	public void postorderTest01()
	{
		assertEquals("700 500 1500 3000 2000 1000", tree.postorder());
	}
	
	@Test
	public void breadthFirstOrderTest01()
	{
		assertEquals("1000 500 2000 700 1500 3000", tree.breadthFirstOrder());
	}
	
	@Test
	public void toStringTest01()
	{
		assertEquals("((500(700))1000((1500)2000(3000)))", tree.toString());
	}
}
