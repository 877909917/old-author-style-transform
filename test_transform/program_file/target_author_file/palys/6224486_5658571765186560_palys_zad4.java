package roundq;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class Zad4 {
 	
 	int t;
 	
 	int[] X;
 	int[] R;
 	int[] C;
 	
 	private static final String forAllPossible = "GABRIEL";
 	
 	private static final String existsImpossible = "RICHARD";
 	
 	public void readInput() throws FileNotFoundException {
 		Scanner s = new Scanner(new File("zad4.in"));
 		
 		t = s.nextInt();
 		X = new int[t];
 		R = new int[t];
 		C = new int[t];
 		
 		for (int i = 0; i < t; i++) {
 			X[i] = s.nextInt();
 			R[i] = s.nextInt();
 			C[i] = s.nextInt();
 		}
 		
 		s.close();
 	}
 	
 	private String solve4(int r, int c) {
 		
 		if ((r * c) % 4 != 0) {
 			return existsImpossible;
 		}
 		
 		if ((r * c) > 8) {
 			return forAllPossible;
 		} else {
 			return existsImpossible;
 		}
 		
 	}
 	
 	private String solve3(int r, int c) {
 
 		if ((r * c) % 3 != 0) {
 			return existsImpossible;
 		}
 		
 		if ((r * c) > 3) {
 			return forAllPossible;
 		} else {
 			return existsImpossible;
 		}
 	}
 	
 	private String solve(int x, int r, int c) {
 		
 		if (x == 1) {
 			return forAllPossible;
 		}
 		
 		if (x == 2) {
 			if ((r * c) % 2 == 1) {
 				return existsImpossible;
 			} else {
 				return forAllPossible;
 			}
 		}
 		
 		if (x == 3) {
 			return solve3(r, c);
 		}
 		
 		if (x == 4) {
 			return solve4(r, c);
 		}
 		
 		return "";
 	}
 	
 	private String solveCase(int caseNum) {
 		return solve(X[caseNum], R[caseNum], C[caseNum]);
 	}
 	
 	public void solveAndPrintOutput() {
 		
 		for (int i = 0; i < t; i++) {
 			System.out.println("Case #" + (i+1) + ": " + solveCase(i));
 		}
 	}
 
 	public static void main(String[] args) {
 
 		Zad4 z = new Zad4();
 		try {
 			z.readInput();
 			z.solveAndPrintOutput();
 			
 		} catch (FileNotFoundException e) {
 			
 			e.printStackTrace();
 		}
 		
 		
 
 
 	}
 
 }
