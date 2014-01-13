/**
 * Name: Qingxiang Jia
 * Assignment: Lab #7
 * Title: Expression Evaluator
 * Course: CSCE 270
 * Lab Section: 01
 * Semester: Spring 2011
 * Instructor: David Wolff
 * Date: 4/10/2011
 * Sources consulted: Java Docs, Tutor Matt,
 * http://www.codeguru.com/forum/showthread.php?t=277281
 * and http://junit.sourceforge.net/javadoc/org/junit/BeforeClass.html.
 * Program description: It implements all the required functionalities and it also
 * does a lot of creativity. It has three files. They are: InfixEvaluator.java,
 * InfixEvaluatorGUI.java and InfixEvaluatorTest.java. The first file does all the
 * required works and InfixEvaluatorGUI.java does creativity. The last file does
 * both required works and creativity. 
 * Known Bugs: Not found yet.
 * 
 * Creativity: This program can evaluate % and it also has a GUI to display 
 * the graphic representation of the stack in the test program. Every time you 
 * use InfixEvaluator it will generate a file and record all the stack state 
 * information into the file report.txt. If you use it at the second time,
 * the previous record will be kept so even though you test multiple @test
 * in JUnit, the report will be kept. This function actually create a problem.
 * When you test the program several times the report.txt will get really big.
 * So in order to solve this problem I use @BeforeClass in JUnit class to clean
 * previous records so the report you need can be kept and the thing you don't
 * want will be eliminated. I think this should be another creativity. Another 
 * creativity is in the GUI, I use % to determine where should the program print
 * a bar to separate the pair of operand stack and operator stack. There is a small
 * creativity is that when you click "Display report" button, the program will not
 * generate new information because the .setText(""). The last creativity is my
 * program can detect whether it is caused by operand stack or operator stack when
 * an exception happens and it will point out which exception(an prematurely empty
 * operand stack or a prematurely empty operand stack).
 * 
 * Quick questions:
 * Why don't you create a InfixEvaluator object when test?
 * Why does output file lose all formating?
 */

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Required tests.
 *
 */
public class InfixEvaluatorTest {

	/**
	 * @throws FileNotFoundException Because I use file as part of creativity.
	 * I think the use of @BeforeClass can be a creativity. Because it avoids 
	 * the situation where the report.txt gets bigger and bigger.
	 */
	@BeforeClass
	public static void cleaningJob() throws FileNotFoundException
	{
		File needToBeCleaned = new File("report.txt");
		PrintWriter pw = new PrintWriter(needToBeCleaned);
		pw.print("");
	}
	
	/**
	 * @throws SyntaxErrorException
	 * @throws IOException Because the creativity is involved with file 
	 * writing. The Java Doc below are same below so I will not do the
	 * copy and paste.
	 */
	@Test
	public void testExpression01() throws SyntaxErrorException, IOException 
	{
	    assertEquals( 1, InfixEvaluator.evaluate("7 - 3 * 2") );
	}
	
	@Test
	public void testExpression02() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
	    assertEquals( 8, InfixEvaluator.evaluate("( 7 - 3 ) * 2") );
	}
	
	@Test
	public void testExpression03() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 19, InfixEvaluator.evaluate("( 4 * 3 ) + 4 + ( 2 + 1 )"));
	}
	
	@Test
	public void testExpression04() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 66, InfixEvaluator.evaluate("3 * 2 * ( 1 + 10 )"));
	}

	@Test
	public void testExpression05() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 3, InfixEvaluator.evaluate("27 / 9"));
	}
	
	@Test
	public void testExpression06() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 11, InfixEvaluator.evaluate("1 + ( 180 / 6 ) / 3"));
	}
	
	@Test
	public void testExpression07() throws SyntaxErrorException,
	FileNotFoundException, IOException 
	{
		assertEquals( -57, InfixEvaluator.evaluate("32 / 8 + ( 3 + 3 ) / 2 * ( 2 + 1 ) - ( 99 / 9 - 1 ) * 7"));
	}
	
	@Test
	public void testExpression08() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 64, InfixEvaluator.evaluate("8 ^ 2"));
	}
	
	@Test
	public void testExpression09() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 64, InfixEvaluator.evaluate("( 4 + 4 ) ^ ( ( 45 - 30 ) / 5 - 1 )"));
	}
	
	@Test
	public void testExpression10() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 20, InfixEvaluator.evaluate("( ( ( 2 * ( 5 + 2 ) - 2 ) * 2 - 4 ) / 5 ) - ( ( 5 - 1 ) / 2 ) + ( 8 + 3 ) + 7"));
	}
	
	@Test
	public void testExpression11() throws SyntaxErrorException,
	FileNotFoundException, IOException 
	{
		assertEquals( 3, InfixEvaluator.evaluate("3 % 9"));
	}
	
	@Test
	public void testExpression12() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 0, InfixEvaluator.evaluate("3 % 9 % 3"));
	}
	
	@Test
	public void testExpression13() throws SyntaxErrorException,
	FileNotFoundException, IOException 
	{
		assertEquals( 0, InfixEvaluator.evaluate("3 % 9 % 3"));
	}
	
	@Test
	public void testExpression14() throws SyntaxErrorException,
	FileNotFoundException, IOException 
	{
		assertEquals( 0, InfixEvaluator.evaluate("( ( 3 % 9 % 3 ) - 5 ) % 1"));
	}
	
	@Test
	public void testExpression15() throws SyntaxErrorException, 
	FileNotFoundException, IOException 
	{
		assertEquals( 1000, InfixEvaluator.evaluate("( ( 3 % 9 ) * 7 - 11 ) ^ 3 ^ 2 ^ 0.5"));
	}
	
	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SyntaxErrorException
	 */
	@Test(expected=SyntaxErrorException.class)
	public void testExpression16() throws FileNotFoundException,
	IOException, SyntaxErrorException
	{
		InfixEvaluator.evaluate("( ( 1 + 2");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void testExpression17() throws FileNotFoundException,
	IOException, SyntaxErrorException
	{
		InfixEvaluator.evaluate("87 + + + * 2");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void testExpression18() throws FileNotFoundException,
	IOException, SyntaxErrorException
	{
		InfixEvaluator.evaluate("/// / 2 * (2( 2");
	}
}
