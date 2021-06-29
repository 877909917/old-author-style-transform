import java.io.*;
 import java.util.Scanner;
 
 public class A
 {
 
 	
 	
 	static int N;
     String [] DIGITS = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
 	static int [] digits ;
 
     static boolean eliminate(StringBuilder sb, char letter)
     {
     	int indexOfLetter = sb.toString().indexOf(letter);
     	if (indexOfLetter == -1)
     		return false;
     	
     	sb.deleteCharAt(indexOfLetter);
     	
     	return true;
     }
 	public static void main(String[] args) throws IOException
 	{
 		String fileName = "A-small0";
 		Scanner in = new Scanner(new File(fileName + ".in"));
 		PrintWriter out = new PrintWriter(new FileWriter(fileName + ".out"));
 		
 		int noOfCases = in.nextInt();
 		for (int caseNo = 1; caseNo <= noOfCases; caseNo++)
 		{
 			StringBuilder phone = new StringBuilder(in.next());
 		
 			
 			digits = new int[10];
 			
 			while (eliminate(phone, 'Z')) 
 			{
 				eliminate(phone, 'E');
 				eliminate(phone, 'R');
 				eliminate(phone, 'O');
 				digits[0]++;
 			}
 			while (eliminate(phone, 'X')) 
 			{
 				eliminate(phone, 'S');
 				eliminate(phone, 'I');
 				digits[6]++;
 			}
 			
 			while (eliminate(phone, 'W')) 
 			{
 				eliminate(phone, 'T');
 				eliminate(phone, 'O');
 				digits[2]++;
 			}
 			
 			while (eliminate(phone, 'G')) 
 			{
 				eliminate(phone, 'E');
 				eliminate(phone, 'I');
 				eliminate(phone, 'H');
 				eliminate(phone, 'T');
 				digits[8]++;
 			}
 			
 			while (eliminate(phone, 'H')) 
 			{
 				eliminate(phone, 'T');
 				eliminate(phone, 'R');
 				eliminate(phone, 'E');
 				eliminate(phone, 'E');
 				digits[3]++;
 			}
 			
 			while (eliminate(phone, 'U')) 
 			{
 				eliminate(phone, 'F');
 				eliminate(phone, 'O');
 				eliminate(phone, 'R');
 				digits[4]++;
 			}
 			
 			while (eliminate(phone, 'F')) 
 			{
 				eliminate(phone, 'I');
 				eliminate(phone, 'V');
 				eliminate(phone, 'E');
 				digits[5]++;
 			}
 			
 			while (eliminate(phone, 'V')) 
 			{
 				eliminate(phone, 'S');
 				eliminate(phone, 'E');
 				eliminate(phone, 'E');
 				eliminate(phone, 'N');
 				digits[7]++;
 			}
 			
 			while (eliminate(phone, 'O')) 
 			{
 				eliminate(phone, 'E');
 				eliminate(phone, 'N');
 				digits[1]++;
 			}
 			
 			while (eliminate(phone, 'N')) 
 			{
 				eliminate(phone, 'E');
 				eliminate(phone, 'N');
 				eliminate(phone, 'I');
 				digits[9]++;
 			}
 	
 
 
 
 
 
 
 
 
 
 
 
 			out.printf("Case #%d: ", caseNo);
 			for (int i = 0; i < 10; i++)
 			{
 				while(digits[i]> 0)
 				{
 					out.print(i);
 					digits[i]--;
 				}
 			}
 			out.println();
 		}
 		in.close();
 		out.close();
 
 	}
 
 }
