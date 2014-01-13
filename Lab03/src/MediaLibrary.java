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

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;


public class MediaLibrary implements MediaLibraryModel
{
	int lineAmount; //move them out of the method so another methods can share.
	MediaItem[] items;
	@Override
	public void loadLibrary( String fileName )
	{
		// create a file
		File inputFile = new File(fileName);
		
		// create a scanner
		Scanner scan;
		
		// variables needed
		//int lineAmount; move it out of the method so another methods can share.
		//MediaItem[] items; Move it so other methods can share.
		
		try
		{
			scan = new Scanner(inputFile);
			lineAmount = scan.nextInt();
			items = new MediaItem[lineAmount];
			
			// scan every single line into a new element of the 
			// MediaItem array
			
			for(int i = -1; i < lineAmount; i++) // why the next doesn't go!
			{
				String line = scan.nextLine();
				String[] tokens = line.split("\\s*\\|\\s*");
				if(tokens[0].equals("CD"))
				{
					items[i] = new CD(tokens[1], Double.parseDouble(tokens[2]), tokens[3], Integer.parseInt(tokens[4]), 
							Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));	
				}
				if(tokens[0].equals("DVD"))
				{
					items[i] = new DVD(tokens[1], Double.parseDouble(tokens[2]), tokens[3], Integer.parseInt(tokens[4]), 
							Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
				}
			}
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, program exited");
			System.exit(-1); // tell OS the program quit unexpectedly.
		}
	}
	
	@Override
	public String getTopTenList()
	{
		StringBuilder topTen = new StringBuilder();
		Arrays.sort(items);
		NumberFormat formatter2 = new DecimalFormat(" #"); // for k < 10
		
		topTen.append("Top Ten by Overall Rating \n-------------------------\n");
		int k = 0;
		for(int i = lineAmount - 1; i > lineAmount - 11; i--)
		{
			k++;
			String s1 = formatter2.format(k);
			if(k < 10)
			{
				topTen.append(s1 + "." + items[i].toString() + "\n");
			}
			else // sorry I did this, I was just too sleepy to come up with a 
				 // good idea.
			{
				topTen.append("10" + "." + items[i].toString() + "\n");
			}
		}
		return topTen.toString();
	}
	
	@Override
	public String getFullList() //can one string add another string?
	{
		StringBuilder fullList = new StringBuilder();
		fullList.append("");
		for(int i = 0; i < lineAmount; i++)
		{
			fullList.append(items[i].toString() + "\n");
			//fullList = fullList + items[i].toString() + "\n"; // why can't do this?
			//System.out.println(i); // comment it so I can ask questions later.
		}
		return fullList.toString();
	}
	
	@Override
	public String getCDsByMusicRating(int rating)
	{
		StringBuilder CDListByMusicRating = new StringBuilder();
		for(int i = 0; i < lineAmount ; i++)
		{
			if(items[i].overallRating()>= rating && items[i] instanceof CD)
			{
				CDListByMusicRating.append(items[i].toString() + "\n");
			}
		}
		return CDListByMusicRating.toString();
	}
	
	@Override
	public String getDVDsByDirectingRating(int rating)
	{
		StringBuilder DVDListByDirectingRating = new StringBuilder();
		for(int i = 0; i < lineAmount ; i++)
		{
			if(items[i].overallRating()>= rating && items[i] instanceof DVD)
			{
				DVDListByDirectingRating.append(items[i].toString() + "\n");
			}
		}
		return DVDListByDirectingRating.toString();
	}
	
	@Override
	public String getItemsWithinBudget(double low, double high)
	{
		StringBuilder ItemsWithinBudget = new StringBuilder();
		for(int i = 0; i < lineAmount ; i++)
		{
			if(items[i].getPrice() >= low && items[i].getPrice() <= high)
			{
				ItemsWithinBudget.append(items[i].toString() + "\n");
			}
		}
		return ItemsWithinBudget.toString();
	}
}
