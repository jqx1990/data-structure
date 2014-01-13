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

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class HugeIntegerTest {
	@Test
	public void testAdd1() {
		HugeInteger a = new HugeInteger(1234567890);
		HugeInteger b = new HugeInteger(987654321);
		Assert.assertEquals("2222222211", a.add(b).toString());
	}

	@Test
	public void testAdd2() {
		HugeInteger a = new HugeInteger(1234567890);
		HugeInteger b = new HugeInteger("987654321");
		Assert.assertEquals("2222222211", b.add(a).toString());
	}
	
	@Test
	public void testAdd3() {
		HugeInteger a = new HugeInteger("243723478057839349568236389589088489523");
		HugeInteger b = new HugeInteger("583429853326475434376453424678235688432");
		Assert.assertEquals("827153331384314783944689814267324177955", a.add(b).toString());
	}
	
	@Test
	public void testAdd4() {
		HugeInteger a = new HugeInteger("243723478057839349568236389589088489523");
		HugeInteger b = new HugeInteger("583429853326475434376453424678235688432");
		Assert.assertEquals("827153331384314783944689814267324177955", b.add(a).toString());
	}
	
	@Test
	public void testSubtract1() {
		HugeInteger a = new HugeInteger("213");
		HugeInteger b = new HugeInteger("5112");
		Assert.assertEquals("-4899", a.subtract(b).toString());
	}
}
