import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
 
 	public static void main(String[] args) throws Exception {
 		Locale.setDefault(Locale.US);
 
 		Scanner in = new Scanner(new File("problem.in"));
 		PrintStream out = new PrintStream(new File("problem.out"));
 
 		int T = in.nextInt();
 
 		for (int test = 1; test <= T; test++) {
 			char[] s = in.next().toCharArray();
 			
 			
 			boolean correct = false;
 			while (!correct) {
 				correct = true;
 				for (int i=0; i<s.length-1; i++) {
 					if (s[i] > s[i+1]) {
 						correct = false;
 						s[i]--;
 						for (int j=i+1; j<s.length; j++) {
 							s[j] = '9';
 						}
 						break;
 					}
 				}
 			}
 			StringBuilder result = new StringBuilder();
 			int i=0;
 			while (s[i] == '0') {
 				i++;
 			}
 			for (int j=i; j<s.length; j++) {
 				result.append(s[j]);
 			}
 	
 			out.printf("Case #%d: %s\n", test, result);
 		}
 	}
 }
