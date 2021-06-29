package r2015.round1b;
 
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Scanner;
 
 import common.OfflineSolution;
 
 public class R2015_1B_A extends OfflineSolution {
 	
 	private int n;
 	
 	private int number;
 	
 	private int[] sol;
 
 	@Override
 	public void readCase(Scanner in) {
 			n = in.nextInt();
 			sol = new int[n + 1];
 	}
 	
 	private int reverseAndTrim(int n) {
 		String s = "" + n;
 		StringBuilder b = new StringBuilder();
 		boolean leadingZeros = true;
 		
 		for (int i = s.length() - 1; i >= 0; i--) {
 			if (s.charAt(i) != '0' || !leadingZeros) {
 				leadingZeros = false;
 				b.append(s.charAt(i));
 			}
 		}
 		
 		if (b.length() == 0) {
 			b.append("0");
 		}
 		
 		return Integer.parseInt(b.toString());
 	}
 
 	@Override
 	public void solveCase() {
 		sol[n] = 0;
 		for (int i = n - 1; i >= 0; i--) {
 			int rev = reverseAndTrim(i);
 			
 			if (rev > i && rev <= n) {
 				sol[i] = Math.min(sol[i + 1] + 1, sol[rev] + 1);
 			} else {
 				sol[i] = sol[i+1] + 1;
 			}
 		}
 		number = sol[0];
 		
 	}
 
 	@Override
 	public void printOutput(PrintStream out) {
 			out.println(number);
 	}
 
 }
