import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Main {
 	public static void main(String[] args) throws FileNotFoundException {
 		new Main();
 	}
 	
 	public Main() throws FileNotFoundException {
 		
 		Scanner sc = new Scanner(new File("D-small-attempt3.in"));
 		PrintWriter out = new PrintWriter(new File("D_small.out"));
 		
 		int T = sc.nextInt();
 		for (int t = 1; t <= T; t++) {
 			boolean richard = true;
 			int x = sc.nextInt();
 			int r = sc.nextInt();
 			int c = sc.nextInt();
 			if (x == 1) {
 				richard = false;
 			}
 			if (x == 2) {
 				if ((r*c)%2 != 0) richard = true;
 				else richard = false;
 			}
 			if (x == 3) {
 				if ((r*c)%3 != 0) richard = true;
 				else if ( r < 3 && c < 3) richard = true;
 				else if ( r >= 3 && c >= 3) richard = false;
 				else if ( r == 1 || c == 1) richard = true;
 				else richard = false;
 			}
 			if (x == 4) {
 				if ((r*c)%4 != 0) richard = true;
 				else if ( r < 4 && c < 4) richard = true;
 				else if ( r >= 4 && c >= 4) richard = false;
 				else if ( r == 4 && c == 1) richard = true;
 				else if ( r == 4 && c == 2) richard = true;
 				else if ( r == 1 && c == 4) richard = true;
 				else if ( r == 2 && c == 4) richard = true;
 				else richard = false;
 			}
 			if (richard) out.println("Case #" + t + ": RICHARD");
 			else out.println("Case #" + t + ": GABRIEL");
 			if (richard) System.out.println("Case #" + t + ": RICHARD");
 			else System.out.println("Case #" + t + ": GABRIEL");
 		}
 		
 		out.close();
 		sc.close();
 	}
 }
