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

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Qingxiang Jia (Lee)
 *
 */
public class ArrayQueueTest {
	/**
	 * Preparation
	 */
	ArrayQueue<String> testQueue;
	
	/**
	 * Preparation
	 */
	@Before
	public void preparations()
	{
		testQueue = new ArrayQueue<String>();
		testQueue.offer("Element01");
		testQueue.offer("Element02");
	}
	
	/**
	 * It tests size method.
	 */
	@Test
	public void testSize01() 
	{
		assertEquals( 2, testQueue.size() );
	}

	/**
	 * This one tests isEmpty method.
	 */
	@Test
	public void testIsEmpty01() 
	{
		assertEquals( false, testQueue.isEmpty());
	}

	/**
	 * It tests offer method.
	 */
	@Test
	public void testOffer01() 
	{
		testQueue.offer("Element03");
		testQueue.poll();
		testQueue.poll();
		assertEquals( "Element03", testQueue.poll());
	}
	
	/**
	 * It tests poll method.
	 */
	@Test
	public void testPoll01() 
	{
		assertEquals( "Element01", testQueue.poll());
	}
	
	/**
	 * It tests whether the poll method would return a null
	 * when there is nothing to return.
	 */
	@Test
	public void testPoll02()
	{
		testQueue.poll();
		testQueue.poll();
		assertEquals( null, testQueue.poll());
	}
	
	/**
	 * It tests remove method.
	 */
	@Test
	public void testRemove01() 
	{
		testQueue.remove();
		assertEquals( "Element02", testQueue.remove());
	}
	
	/**
	 * It tests when there is nothing to remove, whether the 
	 * method would throw an exception.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemove02()
	{
		testQueue.remove();
		testQueue.remove();
		testQueue.remove();
	}

	/**
	 * It tests peek method.
	 */
	@Test
	public void testPeek01() 
	{
		assertEquals( "Element01", testQueue.peek());
	}
	
	/**
	 * It tests peek method in the situation where the peek follows a remove
	 * operation.
	 */
	@Test
	public void testPeek02()
	{
		testQueue.remove();
		assertEquals( "Element02", testQueue.peek());
	}

	/**
	 * It tests the element method.
	 */
	@Test
	public void testElement01() 
	{
		assertEquals( "Element01", testQueue.element());
	}
	
	/**
	 * It tests peek method in the situation where the peek follows a poll
	 * operation.
	 */
	@Test
	public void testElement02()
	{
		testQueue.poll();
		assertEquals( "Element02", testQueue.element());
	}
	
	/**
	 * It tests the situation where an exception should be thrown.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testElement03()
	{
		testQueue.poll();
		testQueue.remove();
		testQueue.element();
	}

	/**
	 * It test the situation where the reallocate method need to be called
	 * and the toString method is also be tested in this test.
	 */
	@Test
	public void testToString()
	{
		testQueue.remove();
		testQueue.poll();
		testQueue.offer("1");
		testQueue.offer("2");
		testQueue.offer("3");
		testQueue.offer("4");
		testQueue.offer("5");
		testQueue.offer("6");
		assertEquals( "[1, 2, 3, 4, 5, 6]", testQueue.toString());
	}
}
