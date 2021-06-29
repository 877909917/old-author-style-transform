import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class Fractiles {
 
 	public static void main(String[] args) throws Exception {
 		Locale.setDefault(Locale.US);
 
 		Scanner in = new Scanner(new File("problem.in"));
 		PrintStream out = new PrintStream(new File("problem.out"));
 
 		int T = in.nextInt();
 
 		for (int test = 1; test <= T; test++) {
 			long k = in.nextLong();
 			long c = in.nextLong();
 			int s = in.nextInt();
 
 			if (s < k) {
 				out.printf("Case #%d: %s\n", test, "IMPOSSIBLE");
 			} else {
 				long p = 1;
 				for (int i=1; i<c; i++) {
 					p *= k;
 				}
 
 				out.printf("Case #%d:", test);
 				for (int i=0; i<s; i++) {
 					out.printf(" %d", p * i + 1);
 				}
 				out.println();
 			}
 		}
 
 	}
 }
