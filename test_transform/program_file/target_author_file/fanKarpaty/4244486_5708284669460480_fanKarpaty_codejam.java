
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 
 class Codejam {
 	
 	public static int ma = 0;
 	public static int wcount(String keys, String word, int s,  String cur) {
 		int l = word.length();
 		int ans = 0;
 		if(cur.length() == s) {
 			for (int i = 0; i + l <= s; i++) {
 				if (word.equals(cur.substring(i, i + l))) ans++;
 			}
 			ma = Math.max(ma, ans);
 		} else {
 			for (int i = keys.length() - 1; i >=0 ;i--) {
 				ans += wcount(keys, word, s, cur + keys.charAt(i));
 			}
 			
 		}
 		return ans;
 	}
 	public static int maxwords(String abc, String word, int s) {
 		int l = word.length();
 		for (int i = 0; i < l; i++) {
 			if (!abc.contains("" + word.charAt(i))) {
 				return 0;
 			}
 		}
 		
 		String str = "";
 		int j = 0;
 		int ret = 0;
 		for (int i = 0; i < l; i++) {
 			if (j < l) {
 				str += word.charAt(j);
 				j++;
 				
 			} else {
 				ret++;
 				
 				int k  = i - l + 1;
 				for (; k < i; k++) {
 					String tmp = str.substring(k);
 					if (tmp.equals(word.substring(0, k - i + l))) {
 						break;
 					}
 				}
 				if (k == i) {
 					str += word.charAt(0);
 					j = 1;
 				} else {
 					str += word.charAt(i - k);
 					j = i - k + 1;
 				}
 			}
 		}
 		if (j == l)ret ++;
 		return ret;
 		
 	}
 	
 	
     public static void main(String args[]) {
         Scanner in = null;
 		try {
 			in = new Scanner(new FileInputStream("input.txt"));
 			System.setOut(new PrintStream(new File("output.txt")));
 		} catch (FileNotFoundException e) {
 			
 			e.printStackTrace();
 		}
 		
 		int T = in.nextInt();
 		for (int t = 1; t <= T; t++) {
 			
 			int K = in.nextInt();
 			int L = in.nextInt();
 			int S = in.nextInt();
 			in.nextLine();
 			String keys = in.nextLine();
 			String word = in.nextLine();
 			
 			
 			ma = 0;
 			int ans = wcount(keys, word, S, "");
 			double ret = ans;
 			for (int i = 0; i < S; i++) {
 				ret /= (double) K;
 			}
 			System.out.println("Case #" + t + ": " + (ma - ret));
 		}
     }
  
 }