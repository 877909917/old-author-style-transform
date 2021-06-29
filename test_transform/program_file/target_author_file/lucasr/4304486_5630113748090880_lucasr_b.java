package round1a;
 
 import java.util.Scanner;
 
 public class B {
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int cases = sc.nextInt();
 		for (int caze = 1; caze <= cases; caze++) {
 			int N = sc.nextInt();
 			int[] freq = new int[2501];
 			for (int i = 0; i < 2*N-1; i++) {
 				for (int j = 0; j < N; j++) {
 					freq[sc.nextInt()]++;
 				}
 			}
 			System.out.print("Case #" + caze + ":");
 			for (int i = 0; i < freq.length; i++) {
 				if (freq[i] % 2 == 1) System.out.print(" " + i);
 			}
 			System.out.println();
 		}
 	}
 
 }
