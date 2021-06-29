import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class Main {
 
 	public static void main(String[] args) throws FileNotFoundException {
 		System.setIn(new FileInputStream("B-small-attempt4.in"));
 		System.setOut(new PrintStream("B-small-attempt4.out"));
 		Scanner sc = new Scanner(System.in);
 		int T = sc.nextInt();
 		for (int i = 1; i <= T; i++) {
 			int N = sc.nextInt();
 			double X = sc.nextDouble(), C = sc.nextDouble();
 			if (N == 1) {
 				double V = sc.nextDouble(), R = sc.nextDouble();
 				System.out.println("Case #" + i + ": " + (R == C ? X / V : "IMPOSSIBLE"));
 			} else {
 				double V1 = sc.nextDouble(), R1 = sc.nextDouble(), V2 = sc.nextDouble(), R2 = sc.nextDouble();
 				if (R1 < C && R2 < C || R1 > C && R2 > C) {
 					System.out.println("Case #" + i + ": IMPOSSIBLE");
 				} else if (R1 == R2) {
 					System.out.printf("Case #" + i + ": %.9f\n", Math.min(X / V1, X / V2));
 				} else {
 					if (R1 < R2) {
 						double result = binarySearch(0, X, R1, R2, X, C);
 						System.out.printf("Case #" + i + ": %.9f\n", Math.max(result / V1, (X - result) / V2));
 					} else {
 						double result = binarySearch(0, X, R2, R1, X, C);
 						System.out.printf("Case #" + i + ": %.9f\n", Math.max(result / V2, (X - result) / V1));
 					}
 				}
 			}
 		}
 		sc.close();
 	}
 
 	private static double binarySearch(double left, double right, double R1, double R2, double X, double C) {
 		return (X * C - R2 * X) / (R1 - R2);
 
 
 
 
 
 
 
 
 
 
 	}
 }