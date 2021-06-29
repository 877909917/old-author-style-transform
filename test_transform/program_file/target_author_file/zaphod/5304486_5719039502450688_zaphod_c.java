import java.io.*;
 import java.util.Scanner;
 
 public class C
 {
 	static int healthDS, attackDS, healthKS, attackKS;
 	static int B, D;
 	static long minTurns;
 	static int[][][][] noOfTurns;
 	static int MAX_SIZE = 100;
 
 	
 	
 	static void nextTurn(int healthD, int attackD, int healthK, int attackK,
 			int turns)
 	{
 
 
 
 
 		if (healthK <= 0)
 		{
 			
 			if (turns < minTurns)
 				minTurns = turns;
 			return;
 		}
 		if (healthD <= 0)
 			return;
 		
 		if (turns >= minTurns)
 			return;
 
 		if (healthD <= MAX_SIZE && attackD <= MAX_SIZE && healthK <= MAX_SIZE
 		     && healthK <= MAX_SIZE)
 		{
 		if (turns >= noOfTurns[healthD][attackD][healthK][attackK])
 			return;
 		noOfTurns[healthD][attackD][healthK][attackK] = turns;
 		}
 		
 		
 		nextTurn(healthD - attackK, attackD, healthK - attackD, attackK,
 				turns + 1);
 
 		
 		nextTurn(healthD - attackK, attackD + B, healthK, attackK, turns + 1);
 
 		
 		nextTurn(healthDS - attackK, attackD, healthK,
 				attackK, turns + 1);
 
 		
 		nextTurn(healthD - Math.max(0, attackK - D), attackD, healthK, Math.max(0, attackK - D),
 				turns + 1);
 	}
 
 	public static void main(String[] args) throws IOException
 	{
 		String fileName = "C-small1";
 		Scanner in = new Scanner(new File(fileName + ".in"));
 		PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 
 		int noOfCases = in.nextInt();
 		
 		for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
 		{
 			healthDS = in.nextInt();
 			attackDS = in.nextInt();
 			healthKS = in.nextInt();
 			attackKS = in.nextInt();
 			B = in.nextInt();
 			D = in.nextInt();
 
 			noOfTurns = new int[MAX_SIZE+1][MAX_SIZE+1][MAX_SIZE+1][MAX_SIZE+1];
 			for (int i = 0; i <= MAX_SIZE; i++)
 				for (int j = 0; j <= MAX_SIZE; j++)
 					for (int k = 0; k <= MAX_SIZE; k++)
 						for (int l = 0; l <= MAX_SIZE; l++)
 							noOfTurns[i][j][k][l] = Integer.MAX_VALUE;
 			minTurns = Integer.MAX_VALUE;
 
 			nextTurn(healthDS, attackDS, healthKS, attackKS, 0);
 
 			if (minTurns < Integer.MAX_VALUE)
 			{
 				System.out.printf("Case #%d: %d%n", caseNo, minTurns);
 				out.printf("Case #%d: %d%n", caseNo, minTurns);
 			}
 			else
 			{
 				System.out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
 				out.printf("Case #%d: IMPOSSIBLE%n", caseNo);
 			}
 
 		}
 		in.close();
 		out.close();
 	}
 }