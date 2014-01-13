/**
 *
  Name: Qingxiang Jia (Lee)
  Assignment: 09
  Title: Boggle Board
  Course: CSCE 270
  Lab Section: 01
  Semester: Spring 2011
  Instructor: David Wolff
  Date: the 5/2/2011
  Sources consulted: Dr.Wolff, Matt, Java Doc and 
  http://www.java2s.com/Tutorial/Java/0240__Swing/MessagePopUps.htm
  Program description: This program will read a text file to generate the
  boggle board and the user can play with it. This program has three main
  part: Boggle Board (main program), GUI, and Dictionary. User can play both
  GUI and Boggle Board. Two parts use recursion: binary search in Dictionary and
  boggleSolver in BoggleBoard. GUI uses buttons to represent grids.
  Known Bugs: [SOLVED]When input more than one word, the program will not return
  correct answer BUT this bug has been avoided by creating a new boggleBoard. 
  And it works well. So, no bugs left.
  Creativity: A GUI for the program. It uses dialog windows to interact with the 
  user. It provides the same quality as the one provided by non-GUI program.
  In creativity part, the UI will change according to the "board.txt". This is a 
  major progress in my programming career. I also use a function to make sure 
  in most cases the fonts will be clearly displayed on the button. It's a GUI, but
  more than a GUI.
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/************************** CAUTION ****************************
 * All the test probably will fail because I used this test only
 * when I built the recursion part(boggleSolver). So, if you want
 * to check them you have to modify the BoggleBoard.java. But all
 * these tests passed when I was doing recursion.
 */

/**
 * I didn't do much Java Docs for this part 'cause I think it's 
 * not necessary.
 */

public class BoggleBoardTest {

	BoggleBoard bgBoard;
	@Before
	public void preparations()
	{
		bgBoard = new BoggleBoard();
		bgBoard.boardLoader();
	}
	@Test
	public void testBoggleSolver01() 
	{
		assertEquals( "y", bgBoard.boggleSolver("hit") );
	}
	
	@Test
	public void testBoggleSolver02() 
	{
		assertEquals( "n", bgBoard.boggleSolver("water") );
	}
	
	@Test
	public void testBoggleSolver03() 
	{
		assertEquals( "n", bgBoard.boggleSolver("int") );
	}
	
	@Test
	public void testBoggleSolver04() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aasa") );
	}
	
	@Test
	public void testBoggleSolver05() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aasawtbete") );
	}
	
	@Test
	public void testBoggleSolver06() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aicb") );
	}
	
	@Test
	public void testBoggleSolver07() 
	{
		assertEquals( "n", bgBoard.boggleSolver("wat") );
	}
	
	@Test
	public void testBoggleSolver08() 
	{
		assertEquals( "n", bgBoard.boggleSolver("wax") );
	}
	
	@Test
	public void testBoggleSolver09() 
	{
		assertEquals( "n", bgBoard.boggleSolver("tbx") );
	}
	
	@Test
	public void testBoggleSolver10() 
	{
		assertEquals( "y", bgBoard.boggleSolver("eie") );
	}
	
	@Test
	public void testBoggleSolver11() 
	{
		assertEquals( "y", bgBoard.boggleSolver("ete") );
	}
	
	@Test
	public void testBoggleSolver12() 
	{
		assertEquals( "y", bgBoard.boggleSolver("awetea") );
	}
	
	@Test
	public void testBoggleSolver13() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aasa") );
	}
	
	@Test
	public void testBoggleSolver14() 
	{
		assertEquals( "y", bgBoard.boggleSolver("eiew") );
	}
	
	@Test
	public void testBoggleSolver15() 
	{
		assertEquals( "y", bgBoard.boggleSolver("htct") );
	}
	
	@Test
	public void testBoggleSolver16() 
	{
		assertEquals( "y", bgBoard.boggleSolver("xneb") );
	}
	
	@Test
	public void testBoggleSolver17() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aehx") );
	}
	
	@Test
	public void testBoggleSolver18() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aitn") );
	}
	
	@Test
	public void testBoggleSolver19() 
	{
		assertEquals( "y", bgBoard.boggleSolver("sece") );
	}
	
	@Test
	public void testBoggleSolver20() 
	{
		assertEquals( "y", bgBoard.boggleSolver("awtb") );
	}
	
	@Test
	public void testBoggleSolver21() 
	{
		assertEquals( "y", bgBoard.boggleSolver("sw") );
	}
	
	@Test
	public void testBoggleSolver22() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aet") );
	}
	
	@Test
	public void testBoggleSolver23() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aicb") );
	}
	
	@Test
	public void testBoggleSolver24() 
	{
		assertEquals( "y", bgBoard.boggleSolver("ete") );
	}
	
	@Test
	public void testBoggleSolver25() 
	{
		assertEquals( "y", bgBoard.boggleSolver("hn") );
	}
	
	@Test
	public void testBoggleSolver26() 
	{
		assertEquals( "y", bgBoard.boggleSolver("ae") );
	}
	
	@Test
	public void testBoggleSolver27() 
	{
		assertEquals( "y", bgBoard.boggleSolver("sih") );
	}
	
	@Test
	public void testBoggleSolver28() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aetx") );
	}
	
	@Test
	public void testBoggleSolver29() 
	{
		assertEquals( "y", bgBoard.boggleSolver("wcn") );
	}
	
	@Test
	public void testBoggleSolver30() 
	{
		assertEquals( "y", bgBoard.boggleSolver("te") );
	}
	
	@Test
	public void testBoggleSolver31() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aasawtbeceiehtnx") );
	}
	
	@Test
	public void testBoggleSolver32() 
	{
		assertEquals( "n", bgBoard.boggleSolver("xnebtawsectheaai") );
	}
	
	@Test
	public void testBoggleSolver33() 
	{
		assertEquals( "y", bgBoard.boggleSolver("xnhetebciaaetwas") );
	}
	
	@Test
	public void testBoggleSolver34() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aasaweiehtctbenx") );
	}
	
	@Test
	public void testBoggleSolver35()
	{
		assertEquals( "y", bgBoard.boggleSolver("ntiasece") );
	}
	
	@Test
	public void testBoggleSolver36() 
	{
		assertEquals( "y", bgBoard.boggleSolver("weiehtct") );
	}
	
	@Test
	public void testBoggleSolver37() 
	{
		assertEquals( "y", bgBoard.boggleSolver("aeietcneb") );
	}
	
	@Test
	public void testBoggleSolver38() 
	{
		assertEquals( "y", bgBoard.boggleSolver("ecesaitn") );
	}
}
