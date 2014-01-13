//Name: Qingxiang Jia (Lee)
//Assignment: 03
//Title: Media Library
//Course: CSCE 270
//Lab Section: 01
//Semester: Spring 2011
//Instructor: David Wolff
//Date: 3/7/2011 2:28AM
//Sources consulted: Java Doc, textbook and exampledepot.com.
//Known Bugs: Have not found yet.
//Creativity: I was so sleepy so I didn't do anything extra. What a pity!
//Comments: I did this with Yi Zhou. But we coded separately.

import java.text.DecimalFormat;


public class DVD extends MediaItem
{
	// instance filed
	private String studio = null;
	private int acting;
	private int directing;
	private int soundtrack;
	private String ID = "DVD";
	
	// methods
	@Override
	public int compareTo(MediaItem o)
	{
		if(this.overallRating() < o.overallRating())
			return -1;
		if(this.overallRating() > o.overallRating())
			return 1;
		else
			return 0;// why ><= don't cover all?
	}

	@Override
	public double overallRating() 
	{
		double overallRating = ((double)(acting + directing + soundtrack))/3;
		return overallRating;
	}
	
	public DVD(String t, double p, String stu, int a, int d, int s)
	{
		super(t, p);
		studio = stu;
		acting = a;
		directing = d;
		soundtrack = s;
	}

	public String toString()
	{
		DecimalFormat showTwo = new DecimalFormat("0.00");
		String objectInfo = ID + " [" + showTwo.format(this.overallRating()) + "] " + "$" + price + " " + "\"" + title + "\"" +" " 
		+ " (" + "acting=" + acting + ", directing=" + directing + ", soundtrack=" + soundtrack + ")";
		return objectInfo;
	}
	
	// methods - getters | some getters are in super class
	public String getStudio()
	{
		return studio;
	}
	
	public int getActing()
	{
		return acting;
	}
	
	public int getDirecting()
	{
		return directing;
	}
	
	public int getSoundtrack()
	{
		return soundtrack;
	}
	
	public String getID()
	{
		return "DVD";
	}
	
	// methods - setters | some setters are in super class
	public void setStudio(String stu)
	{
		studio = stu;
	}
	
	public void setActing(int a)
	{
		acting = a;
	}
	
	public void setDirecting(int d)
	{
		directing = d;
	}
	
	public void setSoundtrack(int s)
	{
		soundtrack = s;
	}

}
