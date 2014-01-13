//Name: Qingxiang Jia
//Assignment: 02
//Title: Processing Weather Data
//Course: CSCE 270
//Lab Section: 01
//Semester: Spring 2011
//Instructor: David Wolff
//Date: 2/27/2011
//Sources consulted: Java DOC; Our lab tutors. 
//Creativity: #1	I use ArrayList instead of Array to handle the whole program.
//			  #2	I added a function to export .cvf files.

public class WindReading {

	//declare variables
	
	private String time = "";
	Vector a = new Vector(0,0,0);
	boolean ignored = false;
	
	//constructors
	
	public WindReading(String HHMM, double x, double y, double z)
	{
		time = HHMM;
		a.setValue(x, y, z);
	}
	
	public WindReading()
	{
		time = "";
		a.setValue(0,0,0);
	}
	
	//methods
	
	public String getTime()//a getter
	{
		return time;
	}
	
	public double getX()//a getter
	{
		return a.getX();
	}
	
	public double getY()//a getter
	{
		return a.getY();
	}
	
	public double getZ()//a getter
	{
		return a.getZ();
	}
	
	public double getSpeed()//a getter
	{
		return Math.sqrt(a.getX()*a.getX() + a.getY()*a.getY() + a.getZ()*a.getZ());
	}
	public void setX(double x)// a setter
	{
		a.setX(x);
	}
	
	public void setY(double y)// a setter
	{
		a.setY(y);
	}
	
	public void setZ(double z)// a setter
	{
		a.setZ(z);
	}
	
	public void setTime(String HHMM)// a setter
	{
		time = HHMM;
	}
	
	public void ignored(boolean a)// to label the element if it contains invalid data
	                              // (i.e. noise)
	{
		ignored = a;
	}
	
	public boolean isIgnored()// to identify the element if it contains invalid data
	{
		return ignored;
	}
}
