import java.io.*;
 import java.util.Scanner;
 
 public class D
 {
 	public static int K, C, S;
 
 	
 	
 	public static long nextPos(int level)
 	{
 		int step = C;
 		long start = 0;
 		while (step > 1 && level <= K)
 		{
 			start += (level - 1) * ((long) Math.pow(K, step - 1));
 			step--;
 			level++;
 		}
 		if (level <= K)
 			start += level;
 		if (start == 0)
 			return 1;
 		return start;
 	}
 
 	public static void main(String[] args) throws IOException
 	{
 		String fileName = "D-small1";
 		Scanner in = new Scanner(new File(fileName + ".in"));
 		PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
 		int noOfCases = in.nextInt();
 		for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
 		{
 			K = in.nextInt();
 			C = in.nextInt();
 			S = in.nextInt();
 			System.out.println(K + " "+ C + " "+S);
 
 			int noOfGradStudents = (K + C - 1) / C;
 			if (noOfGradStudents > S)
 			{
 				System.out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
 				out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
 			}
 			else
 			{
 				long[] positions = new long[noOfGradStudents];
 
 				
 				int level = 1;
 				for (int i = 0; i < noOfGradStudents; i++)
 				{
 					positions[i] = nextPos(level);
 					level += C;
 				}
 
 				System.out.printf("Case #%d:", caseNo);
 				out.printf("Case #%d:", caseNo);
 				for (int i = 0; i < noOfGradStudents; i++)
 				{
 					System.out.printf(" %d", positions[i]);
 					out.printf(" %d", positions[i]);
 				}
 				System.out.println();
 				out.println();
 			}
 
 		}
 		in.close();
 		out.close();
 
 	}
 
 }
