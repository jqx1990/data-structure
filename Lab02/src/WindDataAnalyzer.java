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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class WindDataAnalyzer {

	//declare variables
	
	private String filename = "";
	private int LineNum = 0;
	private double avgSpeed = 0;
	private double totalSpeed = 0;
	private double SD = 0;
	private double mean = 0;
	private String avgDirection = "";
	private double maxGust = 0;
	private String maxTime = "";
	private String maxDirection = "";
	private int invalidReadings = 0;
	private int outliers = 0;
	private ArrayList<WindReading> readings;
	Scanner keyboard = new Scanner(System.in);
	
	//Main method
	public static void main(String[] args)
	{
		WindDataAnalyzer Start = new WindDataAnalyzer();
		Start.Analyzer();
	}
	
	//WindDataAnalyzer methods
	public void Analyzer()
	{
		
	System.out.println("Hi, what is the file name (.txt included)?");
	filename = keyboard.nextLine();
	File wind = new File(filename);
	
	try{
	Scanner input = new Scanner(wind);
	LineNum = input.nextInt();
	readings = new ArrayList<WindReading>(LineNum);
	
	//Create the elements
	for (int j = 0; j < LineNum; j++)
	{
		readings.add(new WindReading());
	}
	
	while (input.hasNext())
	{
			for(int k = 0; k < LineNum; k++)
			{
			try
			{
				readings.get(k).setTime(input.next());
				readings.get(k).setX(input.nextDouble());
				readings.get(k).setY(input.nextDouble());
				readings.get(k).setZ(input.nextDouble());
			}
			catch(InputMismatchException e)
			{
				readings.get(k).ignored(true);
				readings.get(k).setTime("");
				readings.get(k).setX(0);// Make the data neat.
				readings.get(k).setY(0);
				readings.get(k).setZ(0);
				invalidReadings++;
				input.nextLine();// Because I don't know where the invalid input would be,
								 // this would skip the whole current line, so the scanner
								 // would be in the right position.
			}
			}
	}
	}
	
	catch(FileNotFoundException e)
	{
		System.out.println("Oh, file not found!");
		System.out.println("Do you want to retype the filename? Y/N");
		if(keyboard.nextLine().equals("Y") || keyboard.equals("y"))
		{
			System.out.println("Type: ");
			filename = keyboard.nextLine();
		}
		else
		{
			System.out.println("The program is closed, bye bye");
			System.exit(0);
		}
	}
	//clean the arrayList
	for (int i = 0; i < readings.size(); i++)
	{
		if(readings.get(i).isIgnored() == true)// One of the great thing about arrayList.
		{
			readings.remove(i);
			i--;
		}
		
	}
	
	//calculations
	//Average speed
	for (int i = 0; i < readings.size(); i++)
	{
		totalSpeed += readings.get(i).getSpeed();
	}
	avgSpeed = totalSpeed/(LineNum - invalidReadings);
	
	
	//Std.Dev
	int n = readings.size();
	double mess = 0;
	mean = avgSpeed;
	for (int i = 0; i < n; i++)
	{
		mess += (readings.get(i).getSpeed() - mean)
				* (readings.get(i).getSpeed() - mean);
		if(readings.get(i).getSpeed() > 300)
			outliers ++;
	}
	SD = Math.sqrt(1/(double)n * mess);// You have to cast it! Or you will get 0!
	
	//Average direction
	Vector[] angles = new Vector[n];
	Vector north = new Vector(0,1,0);
	Vector accumulator = new Vector(0,0,0);

	//Initialize the angles array of vectors
	for(int i = 0; i < n; i++)
	{
		angles[i] = new Vector(0,0,0);// You must initialize vectors in the angles array
									  // or you will encounter null pointer exception at
									  // the following for loop(setX part).
	}
	
	for(int i = 0; i < n; i++)
	{
		angles[i].setX(readings.get(i).getX());
		angles[i].setY(readings.get(i).getY());
		angles[i].setZ(0);
		accumulator.plus(angles[i]);
	}
	
	int direction = Math.round((float)(accumulator.angle(north)/22.5));
	String[] arrows  = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
	avgDirection = arrows[direction];
	
	//Maximum gust
	maxGust = mean;
	int whichOne = 0;
	Vector theOne = new Vector(0,0,0);
	for (int i = 0; i < n; i++)
	{
		if(readings.get(i).getSpeed() > maxGust)
		{
			maxGust = readings.get(i).getSpeed();
			whichOne = i;
		}
	}
	theOne.setValue(readings.get(whichOne).getX(), readings.get(whichOne).getY(), 0);
	maxDirection = arrows[Math.round((float)(theOne.angle(north)/22.5))];
	maxTime = readings.get(whichOne).getTime();
	
	//Show results
	System.out.println("Wind speed data file: " + filename);
	System.out.println("=====================================");
	System.out.printf("Average speed:      %.3f\n", avgSpeed);
	System.out.printf("Std. Dev:           %.3f\n", SD);
	System.out.println("Average direction:  " + avgDirection);
	System.out.printf("Maximum gust:       " + maxTime + " " + "%.3f" + " MPH " + maxDirection + "\n", maxGust);
	System.out.println("Invalid readings:   " + invalidReadings);
	System.out.println("Outliers:           " + outliers);
	
	//Export CSV file
	File outputFile;
	PrintWriter pw;
	
	try
	{
		outputFile = new File("result.csv");
		pw = new PrintWriter(outputFile);
		for(int i = 0; i < n; i++)
		{
			pw.printf("%.2f,%s\n",readings.get(i).getSpeed(), readings.get(i).getTime());
		}
		pw.close();
		// Don't open result.csv by notepad.exe, it wouldn't show data with new lines.
	}
	catch(FileNotFoundException e)
	{
		System.out.println("File not found, program exited.");
		System.exit(-1);
	}

}
}// This curly braces doesn't make any sense but just shut eclipse up. I think it's a bug!