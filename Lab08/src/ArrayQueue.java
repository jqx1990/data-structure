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

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements PureQueue
{
	/**
	 * Instance variables
	 */
	private int capacity = 5;
	private E[] innerArray = (E[]) new Object[capacity];
	private int front = 0;
	private int rear = -1;
	
	/* (non-Javadoc)
	 * @see PureQueue#size()
	 * It is designed this way so you don't have to keep track of size.
	 */
	@Override
	public int size() 
	{
		int counter = 0;
		for(int i = 0; i < innerArray.length; i++)
		{
			if(innerArray[i] != null)
			{
				counter++;
			}
		}
		return counter;
	}

	/* (non-Javadoc)
	 * @see PureQueue#isEmpty()
	 * To know whether the queue is empty.
	 */
	@Override
	public boolean isEmpty() 
	{
		if(this.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see PureQueue#offer(java.lang.Object)
	 * Just regular offer method.
	 */
	@Override
	public boolean offer(Object o) 
	{
		if(this.size() == capacity)
		{
			reallocate();
		}
		rear = (rear + 1) % capacity;
		innerArray[rear] = (E)o;
		return true;
	}

	/* (non-Javadoc)
	 * @see PureQueue#poll()
	 * Just regular poll method.
	 */
	@Override
	public Object poll() 
	{
		Object result = null;
		if(!this.isEmpty())
		{
			result = innerArray[front];
		}
		innerArray[front] = null; // To make .size() easier.
		front++;
		front = front % capacity;
		return result;
	}

	/* (non-Javadoc)
	 * @see PureQueue#remove()
	 * Just regular remove method.
	 */
	@Override
	public Object remove() 
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		E result = innerArray[front];
		innerArray[front] = null; // To make .size() easier.
		front++;
		front = front % capacity;
		return result;
	}

	/* (non-Javadoc)
	 * @see PureQueue#peek()
	 * Just regular peek method.
	 */
	@Override
	public Object peek() 
	{
		Object result;
		if(this.isEmpty())
		{
			result = null;
		}
		result = innerArray[front];
		return result;
	}

	/* (non-Javadoc)
	 * @see PureQueue#element()
	 * Just regular element method.
	 */
	@Override
	public Object element() 
	{
		Object result;
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		result = innerArray[front];
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * This method returns a string representation of the queue.
	 */
	public String toString()
	{
		String result = "[";
		if(this.isEmpty())
		{
			result = "[]";
		}
		else
		{
			for(int i = front; i < this.size() + front - 1; i++)
			{
				result = result + innerArray[i % capacity].toString()
				+ ", ";
			}
			result = result + 
			innerArray[(front + this.size() - 1) % capacity] + "]";
		}
		return result;
	}
	
	/**
	 * This method reallocates a string to the queue when needed.
	 * This one is tricky. The ticky point is one has to keep
	 * tack of the front and rear at the end of the method.
	 */
	public void reallocate()
	{
		E[] newArray = (E[]) new Object[capacity * 2];
		int i = 0;
		for(i = 0; i < this.size(); i++)
		{
			newArray[i] = innerArray[(front + i) % capacity];
			System.out.println((front + i) % capacity);
		}
		front = 0;
		rear = this.size() - 1;
		capacity = capacity * 2;
		innerArray = newArray;
	}
}
