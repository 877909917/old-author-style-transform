import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
 	
 	public void solve(Scanner scan, PrintWriter out) {
 		int resultA = 0;
 		int resultB = 0;
 		int n = scan.nextInt();
 		int[] array = new int[n];
 		
 		for (int i = 0; i < n; i++) {
 			array[i] = scan.nextInt();
 		}
 		
 		int maxDiff = 0;
 		for (int i = 1; i < n; i++) {
 			int diff = array[i-1] - array[i];
 			maxDiff = Math.max(maxDiff, diff);
 			if (array[i] < array[i-1]) {
 				resultA += diff;
 			}
 		}
 		
 		for (int i = 0; i < n-1; i++) {
 			resultB += Math.min(array[i], maxDiff);
 		}
 		
 		System.out.println(resultA + " " + resultB);
 		out.println(resultA + " " + resultB);
 		
 		resultA = 0;
 	}
 	
 	public static void main(String[] args) throws Exception {
 		String filename = "A-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
 	
 }