package codeJam2017;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class RQP3 {
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
 		new RQP3(argv[0]);
 	}
 	
 	public RQP3(String inputFile)
 	{
 		openInput(inputFile);
 		readNextLine();
 
 		int TC = NextInt();
 
 		
 		for(int tt=0; tt<TC; tt++)
 		{	
 			String output=solve();
 			System.out.println("Case #"+(tt+1)+": "+output);
 		}
 		closeInput();
 	}
 	
 	private String solve() {
 		readNextLine();
 		long N = NextLong();
 		long K = NextLong() - 1;
 		
 		PriorityQueue<Long>q = new PriorityQueue<Long>(Collections.reverseOrder());
 		q.add(N);
 		
 		while(K-->0)
 		{
 			long x = q.poll();
 			
 			if(x%2==1)
 			{
 				q.add(x/2);
 				q.add(x/2);
 			}else
 			{
 				q.add(x/2);
 				q.add(x/2-1);
 			}
 		}
 		long x = q.peek();
 		if(x%2==1)
 			return x/2 + " " + x/2;
 		return x/2 + " " + (x/2 - 1);
 	}
 
 
 }
 
 
