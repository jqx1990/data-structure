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


public class CD extends MediaItem 
{
	// instance filed
	private String artist = null;
	private int vocals;
	private int music;
	private int lyrics;
	private String ID = " CD";
	
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
		double overallRating = ((double)(vocals + music + lyrics))/3;
		return overallRating;
	}
	
	public CD(String t, double p, String art, int v, int m, int l)
	{
		super(t, p);
		artist = art;
		vocals = v;
		music = m;
		lyrics = l;
	}

	public String toString()
	{
		DecimalFormat showTwo = new DecimalFormat("0.00");
		String objectInfo = ID + " [" + showTwo.format(this.overallRating()) + "]" + " $" + price + " " + "\"" + title + "\""
							+ " (" + "music=" + music + ", lyrics=" + lyrics + ", vocals=" + vocals + ")";
		return objectInfo;
	}
	
	// methods - getters | some getters are in super class
	public String getArtist()
	{
		return artist;
	}
	
	public int getVocals()
	{
		return vocals;
	}
	
	public int getMusic()
	{
		return music;
	}
	
	public int getLyrics()
	{
		return lyrics;
	}
	
	public String getID()
	{
		return "CD";
	}
	
	// methods - setters | some setters are in super class
	public void setArtist(String art)
	{
		artist = art;
	}
	
	public void setVocals(int v)
	{
		vocals = v;
	}
	
	public void setMusic(int m)
	{
		music = m;
	}
	
	public void setLyrics(int l)
	{
		lyrics = l;
	}
}
