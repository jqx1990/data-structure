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

import java.util.ArrayList;
public class HugeInteger implements Comparable<Object>
{
	// Variable declaration
	private ArrayList<Integer> digits = new ArrayList<Integer>();
	static boolean isNegative;
	
	// Constructors
	/**
	 * This constructor set the first element of the arrayList as 1.
	 */
	public HugeInteger()
	{
		this.digits.add(0);
	}
	
	/**
	 * @param S
	 * It is used for the situation when user passes a string.
	 */
	public HugeInteger(String S)
	{
		int k = 0;// an independent version of "i".
		for(int i = S.length() - 1; i >= 0; i--) // the equal sign is very 
												 // important
		{
			char c = S.charAt(i);
			int num = c - '0'; // This idea is provided by Dr.Wollf.
			this.digits.add(k, new Integer(num));
			k++;
		}
	}
	
	/**
	 * @param i 
	 * This parameter is used in the situation when the user passes a integer.
	 * Basically this just convert the integer into a string and then we can
	 * use the same code dealing with the string to deal with integer. If we
	 * made the code dealing with string a method, it may be better. But I just
	 * kind of lack of time.
	 */
	public HugeInteger(int integer)
	{
		Integer num = new Integer(integer);
		String S = num.toString();
		int k = 0;// an independent version of "i".
		for(int i = S.length() - 1; i >= 0; i--) // the equal sign is very 
												 // important
		{
			char c = S.charAt(i);
			int number = c - '0';
			this.digits.add(k, new Integer(number));
			k++;
		}
	}
	
	// Methods
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * This equal method is based on conpareTo.
	 */
	public boolean equals(Object o)
	{
		HugeInteger other = (HugeInteger)o;
		if(this.compareTo(other) == 0)
		{
			return true;
		}
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * It is a override method of comparable.
	 */
	@Override
	public int compareTo(Object o) 
	{
		HugeInteger other = (HugeInteger)o;
		int result = 0;
		boolean need = true; // Once one of the statement is executed there's no 
							 // need to do any more comparison. And if it continue
							 // compare, it will return wrong message.
		if(this.digits.size() == other.digits.size() && need == true)
		{
			for(int k = this.digits.size() - 1; k >= 0; k--)
			{
				if(this.digits.get(k) - other.digits.get(k) < 0
						&& need == true)
				{
					result = -1;
					System.out.println(result);
					need = false;
				}
				if(this.digits.get(k) - other.digits.get(k) > 0
						&& need == true)
				{
					result = 1;
					System.out.println(result);
					need = false;
				}
			}
		}
		if(this.digits.size() > other.digits.size())
		{
			result = 1;
		}
		if(this.digits.size() < other.digits.size())
		{
			result = -1;
		}
		return result;
	}
	
	/**
	 * @return
	 * If the HugeInteger object contains only zero, it will return true.
	 */
	public boolean isZero()
	{
		int size = this.digits.size();
		boolean isZero = true;
		for(int i = 0; i < size; i++)
		{
			if(this.digits.get(i) != 0)
			{
				isZero = false;
			}
		}
		return isZero;
	}
	
	/**
	 * @param rhs
	 * rhs is a parameter stands for a generic HugeInteger object passed in.
	 */
	public HugeInteger add(HugeInteger rhs)
	{
		HugeInteger result = new HugeInteger(); // the one will be returned.
		int largerSize;
		int smallerSize;
		if(this.digits.size() == rhs.digits.size())
		{
			boolean successive = true;
			int carry = 0;
			for(int i = 0; i <= this.digits.size(); i++) 
			{
				result.digits.add(new Integer(0));
			}
			/**
			* For loop above add one more element in the object in case of 
			* the carry at the last position of the arrayList.
			*/
			for(int i = 0; i < this.digits.size(); i++)
			{
				if(this.digits.get(i) + rhs.digits.get(i) + carry < 10)
				{
					result.digits.set(i, new Integer(this.digits.get(i)
							+ rhs.digits.get(i) + carry));
					carry = 0;
				}
				// It's very important to add carry!
				else
				{
					result.digits.set(i, new Integer((this.digits.get(i) 
							+ rhs.digits.get(i) + carry)%10));
					// put carry after % will cause wired 3+7=10 problem.
					carry = 1;
				}
			}
			if(this.digits.get(this.digits.size() - 1) 
					+ rhs.digits.get(this.digits.size() - 1) >= 10)
			{
				result.digits.set(this.digits.size(), new Integer(1));
			}
			/**
			 * If statement above is used to deal with the carry at the last 
			 * place of the arryList.
			 */
			for(int i = result.digits.size() - 1; i > 0; i--)
			{
				if(result.digits.get(i) != 0)
				{
					successive = false;
				}
				if(result.digits.get(i) == 0 && successive == true)
				{
					result.digits.remove(i);
				}
			}
			/**
			 * The for loop above can process any leading zeros in object result.
			 */
		}
		else // This else part deals with numbers with different number of digits.
		{
			boolean successive = true;
			HugeInteger larger;
			int carry = 0;
			if(this.digits.size() > rhs.digits.size()) // Just a little preparation.
			{
				largerSize = this.digits.size();
				smallerSize = rhs.digits.size();
				larger = this;
			}
			else // if-else instead of if-if makes compiler happy.
			{
				largerSize = rhs.digits.size();
				smallerSize = this.digits.size();
				larger = rhs;
			}
			for(int i = 0; i <= largerSize; i++)
			{
				result.digits.add(new Integer(0));
			}
			/**
			 * Add one more digit in case the last carry.
			 */
			for(int i = 0; i < smallerSize; i++)
			{
				if(this.digits.get(i) + rhs.digits.get(i) + carry < 10)
				{
					result.digits.set(i, new Integer(this.digits.get(i) 
							+ rhs.digits.get(i) + carry));
					carry = 0;
				}
				// Again, don't forget carry in the equation.
				else
				{
					result.digits.set(i, new Integer(((this.digits.get(i) 
							+ rhs.digits.get(i) + carry)%10)));
					carry = 1;
				}
			}
			for(int i = smallerSize; i < largerSize; i++)
			{
				if(larger.digits.get(i) + carry < 10)
				{
					result.digits.set(i, new Integer(larger.digits.get(i) 
							+ carry));
					carry = 0;
				}
				else
				{
					result.digits.set(i, new Integer(((larger.digits.get(i) 
							+ carry)%10)));
					carry = 1;
				}
			}
			/**
			 * Two for loops above process numbers with different digits. Basically
			 * it splits the larger number and process in two steps.
			 */
			if(carry == 1)
			{
				result.digits.set(larger.digits.size(), new Integer(1));
			}
			// If statement above deals with the last carry.
			for(int i = result.digits.size() - 1; i > 0; i--)
			{
				if(result.digits.get(i) != 0)
				{
					successive = false;
				}
				if(result.digits.get(i) == 0 && successive == true)
				{
					result.digits.remove(i);
				}
			}
			// For loop above removes all leading zeros.
		}
		return result;
	}
	
	/**
	 * @param rhs
	 * See previous method to know what rhs is.
	 * @return
	 * This method returns a HugeInteger.
	 */
	public HugeInteger subtract(HugeInteger rhs)
	{
		int longerDigits;
		int borrow = 0; // Similar to carry.
		boolean successive = true;
		if(this.digits.size() < rhs.digits.size())
		{
			longerDigits = rhs.digits.size();
		}
		else
		{
			longerDigits = this.digits.size();
		}
		/**
		 * If-else above decides with one has larger number of digits.
		 */
		HugeInteger subtractedInt = new HugeInteger();
		HugeInteger subtractInt = new HugeInteger();
		HugeInteger result = new HugeInteger();
		/**
		 * Create three new HugeInteger object so I can freely modify them.
		 */
		for(int i = 0; i <= longerDigits; i++)
		{
			subtractedInt.digits.add(i, new Integer(0));
			subtractInt.digits.add(i, new Integer(0));
			result.digits.add(i, new Integer(0));
		}
		/**
		 * Gives all HugeIntegers the same number of digits. It looks stupid but 
		 * actually it saves a lot of codes below. Inspired by K.I.S.S..
		 */
		for(int i = this.digits.size() - 1; i >= 0; i--)
		{
			subtractedInt.digits.set(i, new Integer(this.digits.get(i)));
		}
		for(int i = rhs.digits.size() - 1; i >= 0; i--)
		{
			subtractInt.digits.set(i, new Integer(rhs.digits.get(i)));
		}
		if(subtractedInt.compareTo(subtractInt) < 0)
		{
			HugeInteger temp = subtractedInt;
			subtractedInt = subtractInt;
			subtractInt = temp;
			isNegative = true;
			System.out.println(isNegative + "237424");
		}
		/**
		 * If statement above decides which HugeInteger is larger and so we can reverse
		 * the order of subtraction so no more codes needed to deal with a smaller 
		 * number subtracts a larger one.
		 */
		for(int i = 0; i <= longerDigits; i++)
		{
			int difference = 0;
			if(subtractedInt.digits.get(i) 
					- subtractInt.digits.get(i) - borrow >= 0)
			{
				difference = subtractedInt.digits.get(i) 
				- subtractInt.digits.get(i) - borrow;
				borrow = 0;
				System.out.println(difference);
			}
			else
			{
				difference = subtractedInt.digits.get(i) 
				+ 10 - subtractInt.digits.get(i) - borrow;
				borrow = 1;
				System.out.println(difference);
			}
			result.digits.set(i, new Integer(difference));
		}
		// Core codes process subtract.
		for(int i = result.digits.size() - 1; i > 0; i--)
		{
			if(result.digits.get(i) != 0)
			{
				successive = false;
			}
			if(result.digits.get(i) == 0 && successive == true)
			{
				result.digits.remove(i);
			}
		}
		// Removes any leading zeros.
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * This method returns a String which is a combination of all elements
	 * stored in the HugeInteger object.
	 */
	public String toString()
	{
		String display = "";
		String minusSign;
		if(isNegative == true)
		{
			minusSign = "-";
		}
		else
		{
			minusSign = "";
		}
		for(int i = this.digits.size() - 1; i >= 0; i--)
		{
			display = display + this.digits.get(i);
		}

		return minusSign + display;
	}
	/**
	 * I want to point out that the isNegative is very tricky here. One has to set
	 * it static so it only relates to the class rather than the object. If not, the
	 * boolean will never return right value because when we change the value of
	 * the boolean, it change the value of which is a wrong object.
	 */
	
	// setter for GUI
	/**
	 * @param S 
	 * The parameter passed by GUI calculator.
	 */
	public void set(String S)
	{
		this.digits.clear();
		/**
		 * This just makes sure when user does the calculation again the calculator
		 * can still return the right result.
		 */
		this.digits.add(0);
		/**
		 * Initialize it. Just incase bad things happen.
		 */
		int k = 0;// an independent version of "i".
		for(int i = S.length() - 1; i >= 0; i--) // the equal sign is very 
												 // important
		{
			char c = S.charAt(i);
			int num = c - '0';
			this.digits.add(k, new Integer(num));
			k++;
		}
	}
}
