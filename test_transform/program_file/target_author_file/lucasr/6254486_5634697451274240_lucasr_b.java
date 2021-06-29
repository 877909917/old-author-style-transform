package qualy;
 
 import java.util.Scanner;
 
 public class B {
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int cases = sc.nextInt();
 		for (int caze = 1; caze <= cases; caze++) {
 			char[] s = sc.next().toCharArray();
 			int ans = 0;
 			for (int i = 1; i < s.length; i++) {
 				if (s[i] != s[i-1]) ans++;
 			}
 			if (s[s.length-1] == '-') ans++;
 			System.out.println("Case #" + caze + ": " + ans);
 		}
 	}
 
 }
