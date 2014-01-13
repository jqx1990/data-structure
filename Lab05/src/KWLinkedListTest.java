//	Name: Qingxiang Jia (Lee)
//  Assignment: 5
//  Title: Linked List Iterator
//  Course: CSCE 270
//  Lab Section: 01
//  Semester: Spring 2011
//  Instructor: David Wolff
//  Date: 3/21/2011
//  Sources consulted: Java Doc, Lab requirement and tutor matt.
//  Known Bugs: N/A
//  Comments: the java test has more lines than the program itself.

import static org.junit.Assert.*;
import java.util.ListIterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KWLinkedListTest 
{
	KWLinkedList<String> list;
	ListIterator<String> iter;
	@Before
	public void setUpTest()
	{
		list = new KWLinkedList<String>();
		iter = list.listIterator(0);
		iter.add("Ubuntu"); // index == 0
		iter.add("Kubuntu");// index == 1
		iter.add("Xubuntu");// index == 2
		iter.add("openSUSE");//index == 3
	}
	
	/**
	 * Attention: the iterator will stay at its position in @Before
	 * This test doesn't make any sense. I just feel it makes me more
	 * confident to the result of the @Before
	 */
	@Test
	public void testInitialTest()
	{
		assertEquals("[Ubuntu, Kubuntu, Xubuntu, openSUSE]"
				, list.toString());;
	}
	
	/**
	 * Attention: nothing is changed after you executed a @Test
	 * This one tests the situation where remove happens on head
	 */
	@Test
	public void testRemove1()
	{
		for(int i = 0; i < 4; i++)
		{
			iter.previous();
		}
		iter.remove();
		assertEquals("[Kubuntu, Xubuntu, openSUSE]", list.toString());
		//Test whether size is appropriately updated
		assertEquals(3, list.size());
		//Test whether index is appropriately updated
		assertEquals(0, iter.nextIndex());
		//Test whether head is appropriately updated
		assertEquals("Kubuntu", list.get(0));
		//Test whether tail is appropriately updated
		assertEquals("openSUSE", list.get(2));
	}
	
	/**
	 * This one tests the situation where remove happens on tail
	 */
	@Test
	public void testRemove2()
	{
		for(int i = 0; i < 1; i++)
		{
			iter.previous();
		}
		iter.remove();
		assertEquals("[Ubuntu, Kubuntu, Xubuntu]", list.toString());
		//Test whether size is appropriately updated
		assertEquals(3, list.size());
		//Test whether index is appropriately updated
		assertEquals(3, iter.nextIndex());
		//Test whether head is appropriately updated
		assertEquals("Ubuntu", list.get(0));
		//Test whether tail is appropriately updated
		assertEquals("Xubuntu", list.get(2));
	}
	
	/**
	 * This test tests the situation where remove happens on a middle one
	 */
	@Test
	public void testRemove3()
	{
		for(int i = 0; i < 2; i++)
		{
			iter.previous();
		}
		iter.remove();
		assertEquals("[Ubuntu, Kubuntu, openSUSE]", list.toString());
		//Test whether size is appropriately updated
		assertEquals(3, list.size());
		//Test whether index is appropriately updated
		assertEquals(2, iter.nextIndex());
		//Test whether head is appropriately updated
		assertEquals("Ubuntu", list.get(0));
		//Test whether tail is appropriately updated
		assertEquals("openSUSE", list.get(2));
	}
	
	/**
	 * This one tests the situation where set happens on tail
	 */
	@Test
	public void testSet1()
	{
		for(int i = 0; i < 1; i++)
		{
			iter.previous();
		}
		iter.set("Mandriva");
		assertEquals("[Ubuntu, Kubuntu, Xubuntu, Mandriva]", list.toString());
		//Test whether size is appropriately updated
		assertEquals(4, list.size());
		//Test whether index is appropriately updated
		assertEquals(3, iter.nextIndex());
		//Test whether head is appropriately updated
		assertEquals("Ubuntu", list.get(0));
		//Test whether tail is appropriately updated
		assertEquals("Mandriva", list.get(3));
	}
	
	/**
	 * This one tests the situation where set happens on head
	 */
	@Test
	public void testSet2()
	{
		for(int i = 0; i < 4; i++)
		{
			iter.previous();
		}
		iter.set("Mandriva");
		assertEquals("[Mandriva, Kubuntu, Xubuntu, openSUSE]", list.toString());
		//Test whether size is appropriately updated
		assertEquals(4, list.size());
		//Test whether index is appropriately updated
		assertEquals(0, iter.nextIndex());
		//Test whether head is appropriately updated
		assertEquals("Mandriva", list.get(0));
		//Test whether tail is appropriately updated
		assertEquals("openSUSE", list.get(3));
	}
	
	/**
	 * This one tests the situation where set happens on a middle
	 */
	@Test
	public void testSet3()
	{
		for(int i = 0; i < 2; i++)
		{
			iter.previous();
		}
		iter.set("Mandriva");
		assertEquals("[Ubuntu, Kubuntu, Mandriva, openSUSE]", list.toString());
		//Test whether size is appropriately updated
		assertEquals(4, list.size());
		//Test whether index is appropriately updated
		assertEquals(2, iter.nextIndex());
		//Test whether head is appropriately updated
		assertEquals("Ubuntu", list.get(0));
		//Test whether tail is appropriately updated
		assertEquals("openSUSE", list.get(3));
	}
	
	/**
	 * It tests the situation where the iterator is created
	 *  at the first position
	 */
	@Test
	public void testListIterator0()
	{
		ListIterator<String> iter1 = list.listIterator(0);
		assertEquals(0, iter1.nextIndex());
	}
	
	/**
	 * It tests the situation where the iterator is created 
	 * at the second position
	 */
	@Test
	public void testListIterator1()
	{
		ListIterator<String> iter1 = list.listIterator(1);
		assertEquals(1, iter1.nextIndex());
	}
	
	/**
	 * It tests the situation where the iterator is created 
	 * at the third position
	 */
	@Test
	public void testListIterator2()
	{
		ListIterator<String> iter1 = list.listIterator(2);
		assertEquals(2, iter1.nextIndex());
	}
	
	/**
	 * It tests the situation where the iterator is created 
	 * at the fourth position
	 */
	@Test
	public void testListIterator3()
	{
		ListIterator<String> iter1 = list.listIterator(3);
		assertEquals(3, iter1.nextIndex());
	}
	
	/**
	 * It tests the situation where the iterator is created at 
	 * the fifth position Although there will be no elements 
	 * at the fifth location, but based on the code for 
	 * nextIndex(), if the iterator is created successfully, 
	 * the nextIndex()should return 5.
	 */
	@Test
	public void testListIterator4()
	{
		ListIterator<String> iter1 = list.listIterator(4);
		assertEquals(4, iter1.nextIndex());
	}
	
	/**
	 * Test if it can return right one when there is more than one
	 * elements have the same value
	 */
	@Test
	public void testIndexOf1()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(3, list.indexOf("openSUSE"));
	}
	
	/**
	 * Test if it can return right one when IndexOf happens 
	 * to the tail
	 */
	@Test
	public void testIndexOf2()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(5, list.indexOf("ReactOS"));
	}
	
	/**
	 * Test if it's OK when IndexOf happens to a middle one
	 */
	@Test
	public void testIndexOf3()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(2, list.indexOf("Xubuntu"));
	}
	
	/**
	 * Test if it's OK when IndexOf happens to a middle one
	 */
	@Test
	public void testIndexOf4()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(1, list.indexOf("Kubuntu"));
	}
	
	/**
	 * Test if it's OK when IndexOf happens to the head
	 */
	@Test
	public void testIndexOf5()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(0, list.indexOf("Ubuntu"));
	}
	
	/**
	 * Test if it's OK when IndexOf happens to a middle one
	 */
	@Test
	public void testLastIndexOf1()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(4, list.lastIndexOf("openSUSE"));
	}

	/**
	 * Test if it's OK when lastIndexOf happens to the tail
	 */
	@Test
	public void testLastIndexOf2()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(5, list.lastIndexOf("ReactOS"));
	}

	/**
	 * Test if it returns the right one when there are more than one
	 * elements have the same value
	 */
	@Test
	public void testLastIndexOf3()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(4, list.lastIndexOf("openSUSE"));
	}
	
	/**
	 * Test if it's OK when lastIndexOf happens to a middle one
	 */
	@Test
	public void testLastIndexOf4()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(2, list.lastIndexOf("Xubuntu"));
	}

	/**
	 * Test if it's OK when lastIndexOf happens to a middle one
	 */
	@Test
	public void testLastIndexOf5()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(1, list.lastIndexOf("Kubuntu"));
	}

	/**
	 * Test if it's OK when lastIndexOf happens to the head
	 */
	@Test
	public void testLastIndexOf6()
	{
		iter.add("openSUSE");//index == 4
		iter.add("ReactOS"); //index == 5
		//iter is now at position 6 (It's unimportant, just make it clear)
		/**
		 * so the list now should be:
		 * [Ubuntu, Kubuntu, Xubuntu, openSUSE, openSUSE, ReactOS]
		 */
		assertEquals(0, list.lastIndexOf("Ubuntu"));
	}
	
	/**
	 * Now I start doing test for exceptions.
	 */
	
	/**
	 * For this exception, I think one test is enough.
	 */
	@Test(expected=IllegalStateException.class)
	public void removeException1() 
	{
		list.add("M$ Windows 7"); //index == 4
		// Calling remove after add
		iter.remove();  // Should cause the exception
	}
	
	/**
	 * For this exception, I think one test is enough.
	 */
	@Test(expected=IllegalStateException.class)
	public void testSetException1() 
	{
		iter.remove();  // Should cause the exception
	}
	
	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException1()
	{
		list.listIterator(5); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException2()
	{
		list.listIterator(-1); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException3()
	{
		list.listIterator(10); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException4()
	{
		list.listIterator(-10); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException5()
	{
		list.listIterator(100); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException6()
	{
		list.listIterator(-100); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException7()
	{
		list.listIterator(1000); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException8()
	{
		list.listIterator(-1000); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException9()
	{
		list.listIterator(6); // Should cause the exception
	}

	/**
	 * It's impossible to test every number. I try to
	 * test some typical ones.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorException10()
	{
		list.listIterator(-6); // Should cause the exception
	}
}