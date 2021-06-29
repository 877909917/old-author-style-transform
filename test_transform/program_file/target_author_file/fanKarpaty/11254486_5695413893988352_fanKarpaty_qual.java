package facebook;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 
 public class Qual {
 
 	private static BufferedReader br = null;
 	private static int readInt() {
 		try {
 			return Integer.parseInt(br.readLine());
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return 0;
 	}
 	
 	private static double readDouble() {
 		try {
 			return Double.parseDouble(br.readLine());
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return 0;
 	}
 	
 	private static int[] readIntArr() {
 		int[] ret = null;
 		String[] tmp;
 		try {
 			String str = br.readLine();
 			tmp = str.split(" ");
 			ret = new int[tmp.length];
 			for (int i = 0; i < tmp.length; i++)
 				ret[i] = Integer.parseInt(tmp[i]);
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return ret;
 	}
 	
 	public static boolean check(String a, String b) {
 		for (int i = 0; i < a.length(); i++) {
 			char ch = a.charAt(i);
 			if (ch == '?')continue;
 			if (ch != b.charAt(i))return false;
 		}
 		return true;
 	}
 	public static void main(String[] args) throws IOException {
 		
 		br = new BufferedReader(new FileReader(new File("input.txt")));
 		System.setOut(new PrintStream(new File("output.txt")));
 		int T = readInt();
 		
 		for (int ind = 1; ind<=T; ind++) {
 			System.out.print("Case #" + ind + ": ");
 			
 			String s = br.readLine();
 			String[] ar = s.split(" ");
 			String c = ar[0];
 			String j = ar[1];
 			
 			int l = c.length();
 			int large = 10;
 			if (l == 2)large = 100;
 			else if (l == 3)large = 1000;
 			
 			int max = 1000;
 			String bi = "";
 			String bk = "";
 			for (int i = 0; i < large; i++)
 				for (int k = 0; k < large; k++) {
 					int dif = Math.abs(i - k);
 					if (dif >= max)continue;
 					
 					String C = "" + i;
 					while(C.length() < l)C = "0" + C;
 					String J = "" + k;
 					while(J.length() < l)J = "0" + J;
 					if (check(c, C) && check(j, J)) {
 						bi = C;
 						bk  = J;
 						max = dif;
 					}
 				}
 			System.out.println(bi + " " + bk);
 			
 		}
 		
 
 	}
 
 }
