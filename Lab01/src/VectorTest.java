//Name: Qingxiang Jia
//Assignment: Lab 01
//Title: Vector
//Course: CSCE 270
//Lab Section: 01
//Semester: Spring 2011
//Instructor: David Wolff
//Date: 2/22/2011
//Sources consulted: Data Structure (very little)
//Known Bugs: N/A
//Creativity: Angle calculation; I also use a large amount of exceptions to handle the
// unexpected situation such as a null vector; I also tried to do reflect part but somehow
// the code doesn't work(I think the code is correct so does the test part). 
//Comments: I worked with Yi Zhou for the last part, but none of us uses other's codes.

import org.junit.Assert;
import org.junit.Test;


public class VectorTest {

	@Test
	public void testMinus01() {
		Vector a = new Vector(3,1,1);
		Vector b = new Vector(1,1,0);
		Vector c = new Vector(0,0,0);
		
		c = a.minus(b);
		
		Assert.assertEquals(2.0, c.getX(), .0000000000001);
		Assert.assertEquals(0.0, c.getY(), .0000000000001);
		Assert.assertEquals(1.0, c.getZ(), .0000000000001);
		
		Assert.assertEquals(3.0, a.getX(), .0000000000001);
		Assert.assertEquals(1.0, a.getY(), .0000000000001);
		Assert.assertEquals(1.0, a.getZ(), .0000000000001);
		
		Assert.assertEquals(1.0, b.getX(), .0000000000001);
		Assert.assertEquals(1.0, b.getY(), .0000000000001);
		Assert.assertEquals(0.0, b.getZ(), .0000000000001);
	}

	@Test
	public void testMinus02() {
		Vector a = new Vector(1.0,2.0,1.0);
		Vector b = new Vector(3.0,2.0,2.0);
		Vector c = new Vector(0.0,0.0,0.0);
		
		c = a.minus(b);
		
		Assert.assertEquals(-2.0, c.getX(), .0000000000001);
		Assert.assertEquals(0.0, c.getY(), .0000000000001);
		Assert.assertEquals(-1.0, c.getZ(), .0000000000001);
		
		Assert.assertEquals(1.0, a.getX(), .0000000000001);
		Assert.assertEquals(2.0, a.getY(), .0000000000001);
		Assert.assertEquals(1.0, a.getZ(), .0000000000001);
		
		Assert.assertEquals(3.0, b.getX(), .0000000000001);
		Assert.assertEquals(2.0, b.getY(), .0000000000001);
		Assert.assertEquals(2.0, b.getZ(), .0000000000001);
	}
	
	@Test
	public void testPlus01() {
		Vector a = new Vector(1.0,2.0,3.0);
		Vector b = new Vector(4.0,5.0,6.0);
		Vector c = new Vector(0.0,0.0,0.0);
		
		c = a.plus(b);
		
		Assert.assertEquals(5.0, c.getX(), .0000000000001);
		Assert.assertEquals(7.0, c.getY(), .0000000000001);
		Assert.assertEquals(9.0, c.getZ(), .0000000000001);
		
		Assert.assertEquals(1.0, a.getX(), .0000000000001);
		Assert.assertEquals(2.0, a.getY(), .0000000000001);
		Assert.assertEquals(3.0, a.getZ(), .0000000000001);
		
		Assert.assertEquals(4.0, b.getX(), .0000000000001);
		Assert.assertEquals(5.0, b.getY(), .0000000000001);
		Assert.assertEquals(6.0, b.getZ(), .0000000000001);
	}
	
	@Test
	public void testPlus02() {
		Vector a = new Vector(1.0,0.0,1.0);
		Vector b = new Vector(2.0,1.0,0.0);
		Vector c = new Vector(0.0,0.0,0.0);
		
		c = a.plus(b);
		
		Assert.assertEquals(3.0, c.getX(), .0000000000001);
		Assert.assertEquals(1.0, c.getY(), .0000000000001);
		Assert.assertEquals(1.0, c.getZ(), .0000000000001);
		
		Assert.assertEquals(1.0, a.getX(), .0000000000001);
		Assert.assertEquals(0.0, a.getY(), .0000000000001);
		Assert.assertEquals(1.0, a.getZ(), .0000000000001);
		
		Assert.assertEquals(2.0, b.getX(), .0000000000001);
		Assert.assertEquals(1.0, b.getY(), .0000000000001);
		Assert.assertEquals(0.0, b.getZ(), .0000000000001);
	}

	@Test
	public void testIsZero01() {
		Vector a = new Vector(0,0,0);
		Assert.assertEquals(true, a.isZero());
	}
	
	@Test
	public void testIsZero02() {
		Vector a = new Vector(1,2,0);
		Assert.assertEquals(false, a.isZero());
	}

	@Test
	public void testLength01() {
		Vector a = new Vector (1,1,1);
		Assert.assertEquals(1.73, a.length(), .01);
	}

	@Test
	public void testLength02() {
		Vector a = new Vector (1,-3,0);
		Assert.assertEquals(3.162278, a.length(), .000001);
	}

	@Test
	public void testNormalize01() {
		Vector a = new Vector (2,-1,0);
		a.normalize();
		Assert.assertEquals(0.894, a.getX(), .001);
		Assert.assertEquals(-0.447, a.getY(), .001);
		Assert.assertEquals(0, a.getZ(), .001);
	}

	@Test
	public void testNormalize02() {
		Vector a = new Vector (0,1,0);
		a.normalize();
		Assert.assertEquals(0, a.getX(), .001);
		Assert.assertEquals(1, a.getY(), .001);
		Assert.assertEquals(0, a.getZ(), .001);
	}

	@Test
	public void testNormalized01() {
		Vector a = new Vector (2,2,1);
	
		Assert.assertEquals(0.667, a.normalized().getX(), .001);
		Assert.assertEquals(0.667, a.normalized().getY(), .001);
		Assert.assertEquals(0.333, a.normalized().getZ(), .001);
	}

	@Test
	public void testNormalized02() {
		Vector a = new Vector (2,1,2);
		
		Assert.assertEquals(0.667, a.normalized().getX(), .001);
		Assert.assertEquals(0.333, a.normalized().getY(), .001);
		Assert.assertEquals(0.667, a.normalized().getZ(), .001);
	}
	
	@Test
	public void testNormalized03() {
		Vector a = new Vector (0,0,0);
		
		Assert.assertEquals(0, a.normalized().getX(), .001);
		Assert.assertEquals(0, a.normalized().getY(), .001);
		Assert.assertEquals(0, a.normalized().getZ(), .001);
	}

	@Test
	public void testCross01() {
		Vector a = new Vector(1,2,3);
		Vector b = new Vector(2,3,0);
		
		Assert.assertEquals(-9, a.cross(b).getX(), .0000000000001);
		Assert.assertEquals(6, a.cross(b).getY(), .0000000000001);
		Assert.assertEquals(-1, a.cross(b).getZ(), .0000000000001);
		
		Assert.assertEquals(1, a.getX(), .00000000000001);
		Assert.assertEquals(2, a.getY(), .00000000000001);
		Assert.assertEquals(3, a.getZ(), .00000000000001);
		
		Assert.assertEquals(2, b.getX(), .00000000000001);
		Assert.assertEquals(3, b.getY(), .00000000000001);
		Assert.assertEquals(0, b.getZ(), .00000000000001);
	}
	
	@Test
	public void testCross02() {
		Vector a = new Vector(1,1,1);
		Vector b = new Vector(-1,0,1);
		
		Assert.assertEquals(1, a.cross(b).getX(), .0000000000001);
		Assert.assertEquals(-2, a.cross(b).getY(), .0000000000001);
		Assert.assertEquals(1, a.cross(b).getZ(), .0000000000001);
		
		Assert.assertEquals(1, a.getX(), .00000000000001);
		Assert.assertEquals(1, a.getY(), .00000000000001);
		Assert.assertEquals(1, a.getZ(), .00000000000001);
		
		Assert.assertEquals(-1, b.getX(), .00000000000001);
		Assert.assertEquals(0, b.getY(), .00000000000001);
		Assert.assertEquals(1, b.getZ(), .00000000000001);
	}


	@Test
	public void testDot01() {
		Vector a = new Vector(1,0,-3);
		Vector b = new Vector(0,5,1);
		
		Assert.assertEquals(-3, a.dot(b), .0000000000001);
		
		Assert.assertEquals(1, a.getX(), .00000000000001);
		Assert.assertEquals(0, a.getY(), .00000000000001);
		Assert.assertEquals(-3, a.getZ(), .00000000000001);
		
		Assert.assertEquals(0, b.getX(), .00000000000001);
		Assert.assertEquals(5, b.getY(), .00000000000001);
		Assert.assertEquals(1, b.getZ(), .00000000000001);
	}
	
	@Test
	public void testDot02() {
		Vector a = new Vector(1,1,1);
		Vector b = new Vector(3,1,0);
		
		Assert.assertEquals(4, a.dot(b), .0000000000001);
		
		Assert.assertEquals(1, a.getX(), .00000000000001);
		Assert.assertEquals(1, a.getY(), .00000000000001);
		Assert.assertEquals(1, a.getZ(), .00000000000001);
		
		Assert.assertEquals(3, b.getX(), .00000000000001);
		Assert.assertEquals(1, b.getY(), .00000000000001);
		Assert.assertEquals(0, b.getZ(), .00000000000001);
	}

	@Test
	public void testAngle01() {
		Vector a = new Vector(1,2,3);
		Vector b = new Vector(3,2,1);
		
		Assert.assertEquals(44.415, a.angle(b), .001);
	}
	
	//@Test
	//public void testReflect01()
	//{
	//	Vector a = new Vector(1,2,3);
	//	Vector n = new Vector(3,2,1);
	//	
	//	Assert.assertEquals(4.44444756, a.reflect(n).getX(), .000001);
	//	Assert.assertEquals(2.22222044, a.reflect(n).getY(), .000001);
	//	Assert.assertEquals(4.44444756, a.reflect(n).getZ(), .000001);
	//}

}
