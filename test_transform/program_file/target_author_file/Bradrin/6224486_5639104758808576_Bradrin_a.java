import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
 	
 	public void solve(Scanner scan, PrintWriter out) {
 		int length = scan.nextInt() + 1;
 		String audience = scan.next();
 		int[] array = new int[length];
 		for (int i = 0; i < length; i++) {
 			array[i] = Integer.parseInt(audience.charAt(i) + "");
 		}
 		int result = 0;
 		int total = 0;
 		for (int i = 0; i < length; i++) {
 			if (total < i) {
 				int extra = i - total;
 				result += extra;
 				total += extra;
 			}
 			total += array[i];
 		}
 		System.out.println(result);
 		out.println(result);
 	}
 	
 	public static void main(String[] args) throws Exception {
         Scanner scan = new Scanner(new FileReader("inputa.in"));
         PrintWriter out = new PrintWriter("outputa.txt");
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