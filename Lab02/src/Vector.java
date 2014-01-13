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

public class Vector {
	// variable declaration
	private double x, y, z;

	// constructor s
	public Vector(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector(double initVal){
		x = initVal;
		y = initVal;
		z = initVal;
	}
	
	public Vector(double initX, double initY, double initZ)
	{
		x = initX;
		y = initY;
		z = initZ;
	}

	public Vector( Vector other)
	{
		if(other!=null)
		{
			this.x = other.x;
			this.y = other.y;
			this.z = other.z;
		}
	}
	
	// methods
	public double getX()
	{
		return x;
	}
	
	public void setX(double X)
	{
		x = X;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setY(double Y)
	{
		y = Y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public void setZ(double Z)
	{
		z = Z;
	}
	
	public void setValue(double xVal, double yVal, double zVal)
	{
		x = xVal;
		y = yVal;
		z = zVal;
	}
	
	public String toString()
	{
		return String.format("%.6f, %.6f, %.6f", x,y,z);
	}
	
	public Vector minus(Vector other)
	{
		if( other != null )
		{
		Vector difference = new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
        return difference;	
		}
		else
			throw new IllegalArgumentException("The vector is null!");
	}
	
	public Vector plus(Vector other)
	{
		if( other != null )
		{
			Vector sum = new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
			return sum;
		}
		else
			throw new IllegalArgumentException("The vector is null!");
	}
	
	
	public boolean isZero()
	{
		if(x == 0 && y == 0 && z== 0)
		{
			return true;
		}
		else
			return false;
	}
	
	public double length()
	{
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public void normalize()
	{
		if(x != 0 || y != 0 || z != 0)
		{
		double temp = this.length();
		this.x = this.x / temp;
		this.y = this.y / temp;
		this.z = this.z / temp;
		}
	}
	
	public Vector normalized()
	{
		Vector normalizedNum = new Vector(0,0,0);
		if (this.x ==0 && this.y == 0 && this.z == 0)
			return normalizedNum;
		else
			{
			normalizedNum.setValue(this.x / this.length(), this.y / this.length(), this.z / this.length());
			return normalizedNum;
			}
	}
	
	public Vector cross(Vector other)
	{
		if(other!=null)
			{
			Vector crossedNum = new Vector(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x);
			return crossedNum;
			}
		else 
			throw new IllegalArgumentException("You are crossing with a null vector!");
			
	}
	
	public double dot(Vector other)
	{
		if(other!=null)
			return this.x * other.x + this.y * other.y + this.z * other.z;
		else
			throw new IllegalArgumentException("The vector is null!");
	}
	
	public double angle(Vector other)
	{
		if(other == null)
			throw new IllegalArgumentException("The vector is null!");
		if(this.length() * other.length() == 0)
		{
			return 0;
		}
		else
		{
		double upper = this.dot(other);
		double lower = this.length()*other.length();
		double cosine = upper/lower;
		double degrees = Math.acos(cosine)*(180/(Math.PI));
		return degrees;
		}
	}
	
	public Vector reflect( Vector n )
	{
		n.normalize();
		//r = a - 2 (a · n) n
		double x = 2*(this.dot(n))*(n.getX());
		double y = 2*(this.dot(n))*(n.getY());
		double z = 2*(this.dot(n))*(n.getZ());
		
		n.setValue(x, y, z);

		return this.minus(n);
	}
}