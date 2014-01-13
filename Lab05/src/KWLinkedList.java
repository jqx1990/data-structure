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

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class KWLinkedList implements a double linked list and
 * a ListIterator.
 * @author Koffman & Wolfgang
 **/
public class KWLinkedList<E> extends AbstractSequentialList<E> {

	// Data Fields
    /** A reference to the head of the list. */
    private Node<E> head = null;
    /** A reference to the end of the list. */
    private Node<E> tail = null;
    /** The size of the list. */
    private int size = 0;

    /** Return a ListIterator that begins at index
     * @param index - The position the iteration is to begin
     * @return a ListIterator that begins at index
     */
    public ListIterator<E> listIterator(int index)
    {
    	//Attention: size() won't cause the exception!
    	if(index < 0 || index > this.size()) 
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	else
    	{
    		return new KWListIter(index);
    	}
    }
    
    /**
     * Add an item at the specified index.
     * @param index The index at which the object is to be
     *        inserted
     * @param obj The object to be inserted
     * @throws IndexOutOfBoundsException if the index is out
     *         of range (i < 0 || i > size())
     */
    @Override
    public void add(int index, E obj) {
    	ListIterator<E> iter = this.listIterator(index);
        iter.add(obj);
    }

    /**
     * Get the element at position index.
     * @param index Position of item to be retrieved
     * @return The item at index
     */
    @Override
    public E get(int index) {
    	ListIterator<E> iter = this.listIterator(index);
        return iter.next();
    }

    /**
     * Return the size of the list
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    // Inner Classes
    /** 
     * A Node is the building block for a double-linked list.
     */
    private static class Node<E> {

        /** The data value. */
        private E data;
        /** The link to the next node. */
        private Node<E> next = null;
        /** The link to the previous node. */
        private Node<E> prev = null;

        /**
         * Construct a node with the given data value.
         * @param dataItem The data value
         */
        private Node(E dataItem) {
            data = dataItem;
        }
    } //end class Node

    /** Inner class to implement the ListIterator interface. */
    private class KWListIter implements ListIterator<E> {

        /** A reference to the next item. */
        private Node<E> nextItem;
        /** A reference to the last item returned. */
        private Node<E> lastItemReturned;
        /** The index of the current item. */
        private int index = 0;

        /**
         * Construct a KWListIter that will reference the ith item.
         * @param i The index of the item to be referenced
         */
        public KWListIter(int i) {
            // Validate i parameter.
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException(
                        "Invalid index " + i);
            }
            lastItemReturned = null; // No item returned yet.
            // Special case of last item.
            if (i == size) {
                index = size;
                nextItem = null;
            } else { // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /**
         * Construct a KWListIter that is a copy of another KWListIter
         * @param other The other KWListIter
         */
        public KWListIter(KWListIter other) {
            KWListIter itr = new KWListIter(0);
            itr.index = other.index;
            itr.lastItemReturned = other.lastItemReturned;
            itr.nextItem = other.nextItem;
        }

        /**
         * Indicate whether movement forward is defined.
         * @return true if call to next will not throw an exception
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /** Move the iterator forward and return the next item.
        @return The next item in the list
        @throws NoSuchElementException if there is no such object
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /**
         * Indicate whether movement backward is defined.
         * @return true if call to previous will not throw an exception
         */
        @Override
        public boolean hasPrevious() {
        	
        	if( nextItem == null ) return size != 0;
        	return nextItem.prev != null;
        	
        	// The author's version.  What's wrong with this one?
            //  return (nextItem == null && size != 0)
            //          || nextItem.prev != null;
        }

        /**
         * Return the index of the next item to be returned by next
         * @return the index of the next item to be returned by next
         */
        @Override
        public int nextIndex() {
            return index;
        }

        /**
         * Return the index of the next item to be returned by previous
         * @return the index of the next item to be returned by previous
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        /**
         * Move the iterator backward and return the previous item.
         * @return The previous item in the list
         * @throws NoSuchElementException if there is no such object
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { // Iterator past the last element
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        /**
         * Add a new item between the item that will be returned
         * by next and the item that will be returned by previous.
         * If previous is called after add, the element added is
         * returned.
         * @param obj The item to be inserted
         */
        @Override
        public void add(E obj) {
            if (head == null) { // Add to an empty list.
                head = new Node<E>(obj);
                tail = head;
            } else if (nextItem == head) { // Insert at head.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 1
                // Link nextItem to the new node.
                nextItem.prev = newNode; // Step 2
                // The new node is now the head.
                head = newNode; // Step 3
            } else if (nextItem == null) { // Insert at tail.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link the tail to the new node.
                tail.next = newNode; // Step 1
                // Link the new node to the tail.
                newNode.prev = tail; // Step 2
                // The new node is the new tail.
                tail = newNode; // Step 3
            } else { // Insert into the middle.
                // Create a new node.
                Node<E> newNode = new Node<E>(obj);
                // Link it to nextItem.prev.
                newNode.prev = nextItem.prev; // Step 1
                nextItem.prev.next = newNode; // Step 2
                // Link it to the nextItem.
                newNode.next = nextItem; // Step 3
                nextItem.prev = newNode; // Step 4
            }
            // Increase size and index and set lastItemReturned.
            size++;
            index++;
            lastItemReturned = null;
        } // End of method add.

        /** Remove the last item returned. This can only be
         *  done once per call to next or previous.
         *  @throws IllegalStateException if next or previous
         *  was not called prior to calling this method
         */
        @Override
        public void remove() {
        	// Maybe should classify different situation: head tile and normal.
        	if(lastItemReturned == null)
        	{
        		throw new IllegalStateException();
        	}
        	else if(lastItemReturned == head)
        	{
        		if(lastItemReturned != nextItem)
        		{
        			index--;
        		}
        		// Change the head to the second one
        		head = head.next;
        		// The old head is gone
        		head.prev = null;
        		// LTR is gone
        		lastItemReturned = null;
        	}
        	else if(lastItemReturned == tail)
        	{
        		if(lastItemReturned != nextItem)
        		{
        			index--;
        		}
        		// Change the tail to the second last one
        		tail = tail.prev;
        		// The tail is gone
        		tail.next = null;
        		// LTR is gone
        		lastItemReturned = null;
        	}
        	else
        	{
        		if(lastItemReturned != nextItem)
        		{
        			index--;
        		}
        		// Connect one before LIR and the one after LTR 1
        		lastItemReturned.prev.next = lastItemReturned.next;
        		// Connect one before LIR and the one after LTR 2
        		lastItemReturned.next.prev = lastItemReturned.prev;
        		// LTR is gone.
        		lastItemReturned = null;
        	}
        	size--;
        }

        /** Replace the last item returned with a new value
         *  @param item The new value
         *  @throws IllegalStateException if next or previous
         *  was not called prior to calling this method
         */
        @Override
        public void set(E item) {
        	if(lastItemReturned == null)
        	{
        		throw new IllegalStateException();
        	}
        	else
        	{
        		lastItemReturned.data = item;
        		lastItemReturned = null;
        	}
        	
        }
    } //end class KWListIter

    /**
     * Method to find the index of the first occurrence of an item in the list
     * @param target The item being sought
     * @return The index of the first occurrence of the target item
     *         or -1 if the item is not found.
     */
    @Override
    public int indexOf(Object target) 
    {
    	ListIterator<E> iterator = this.listIterator(0);
    	int result = -1;
    	boolean stop = false;
    	target = (E)target;
    	while(iterator.hasNext() && stop == false)
    	{
    		if(iterator.next().equals(target))
    		{
    			result = iterator.nextIndex() - 1;
    			stop = true;
    		}
    		else
    		{
    			result = -1;
    		}
    	}
    	return result;
    }

    /**
     * Method to find the index of the last occurrence of an item in the list
     * @param target The item being sought
     * @return The index of the last occurrence of the target item
     *         or -1 if the item is not found.
     */
    @Override
    public int lastIndexOf(Object target) {
    	ListIterator<E> iterator = this.listIterator(size());
    	int result = -1;
    	boolean stop = false;
    	target = (E)target;
    	while(iterator.hasPrevious() && stop == false)
    	{
    		if(iterator.previous().equals(target))
    		{
    			result = iterator.previousIndex() + 1;
    			stop = true;
    		}
    		else
    		{
    			result = -1;
    		}
    	}
    	return result;
    }
}