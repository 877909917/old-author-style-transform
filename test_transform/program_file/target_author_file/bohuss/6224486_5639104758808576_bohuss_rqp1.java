package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class RQP1 {
 	String line;
 	StringTokenizer inputParser;
 	BufferedReader is;
 	FileInputStream fstream;
 	DataInputStream in;
 	
 	void openInput(String file)
 	{
 
 		
 		try{
 			fstream = new FileInputStream(file);
 			in = new DataInputStream(fstream);
 			is = new BufferedReader(new InputStreamReader(in));
 		}catch(Exception e)
 		{
 			System.err.println(e);
 		}
 
 	}
 	
 	void readNextLine()
 	{
 		try {
 			line = is.readLine();
 			inputParser = new StringTokenizer(line, " ");
 			
 		} catch (IOException e) {
 			System.err.println("Unexpected IO ERROR: " + e);
 		}	
 		
 	}
 	
 	int NextInt()
 	{
 		String n = inputParser.nextToken();
 		int val = Integer.parseInt(n);
 		
 		
 		return val;
 	}
 	
 	long NextLong()
 	{
 		String n = inputParser.nextToken();
 		long val = Long.parseLong(n);
 		
 		
 		return val;
 	}
 	
 	String NextString()
 	{
 		String n = inputParser.nextToken();
 		return n;
 	}
 	
 	void closeInput()
 	{
 		try {
 			is.close();
 		} catch (IOException e) {
 			System.err.println("Unexpected IO ERROR: " + e);
 		}
 			
 	}
 	
 	public static void main(String [] argv)
 	{
 		new RQP1(argv[0]);
 	}
 	
 	public RQP1(String inputFile)
 	{
 		openInput(inputFile);
 		readNextLine();
 
 		int TC = NextInt();
 
 		
 		for(int tt=0; tt<TC; tt++)
 		{	
 			readNextLine();
 			int SMAX = NextInt();
 			String s = NextString();
 			String output=solve(SMAX, s);
 			System.out.println("Case #"+(tt+1)+": "+output);
 		}
 		closeInput();
 	}
 	
 	private String solve(int MAX, String s) {
 		long ret = 0;
 		long standing = 0;
 		
 		for(int i=0; i<=MAX; i++)
 		{
 			int x = s.charAt(i)-'0';
 			
 			if(standing < i && x > 0)
 			{
 				ret += i - standing;
 				standing = i;
 			}
 			
 			standing += x;
 		}
 		
 		return ""+ret;
 	}
 
 }
 
 
