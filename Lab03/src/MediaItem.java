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

public abstract class MediaItem implements Comparable<MediaItem>
{
	// instance field
	protected double price;
	protected String title;
	
	// methods
	public int compareTo(MediaItem o)
	{
		if(this.price < o.price)
			return -1;
		if(this.price > o.price)
			return 1;
		else
			return 0;// why ><= don't cover all?
	}
	
	public MediaItem(String t, double p)
	{
		title = t;
		price = p;
	}
	
	public abstract double overallRating();
	
	public String toString()
	{
		String objectInfo = null;
		return objectInfo;
	}
	
	// methods - getters | some getters are in sub class
	public double getPrice()
	{
		return price;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	// methods - setters | some setters are in sub class
	public void setPrice(double p)
	{
		price = p;
	}
	
	public void setTitle(String t)
	{
		title = t;
	}
}
