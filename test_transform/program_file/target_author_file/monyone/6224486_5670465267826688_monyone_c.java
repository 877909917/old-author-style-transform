import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 
 public class C {
 	
 	public static void main(String[] args) throws IOException {
 		Scanner sc = new Scanner(System.in);
 		
 		final int T = sc.nextInt();
 		
 		int[][] trans = {
 				{0,  0,  0,  0,  0}, 
 				{0,  1,  2,  3,  4}, 
 				{0,  2, -1,  4, -3}, 
 				{0,  3, -4, -1,  2}, 
 				{0,  4,  3, -2, -1}, 
 		};
 		String[] strs = {"0", "1", "i", "j", "k"};
 		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
 		map.put('1', 1);
 		map.put('i', 2);
 		map.put('j', 3);
 		map.put('k', 4);		
 		
 		
 		for(int tt = 1; tt <= T; tt++){
 			final int L = sc.nextInt();
 			final long X = sc.nextLong();
 			
 			final char[] inputs = sc.next().toCharArray();
 			
 			int[] converted = new int[L];
 			for(int i = 0; i < L; i++){
 				converted[i] = map.get(inputs[i]);
 			}
 			
 			int value = 1;
 			for(int i = 0; i < L; i++){
 				final int next_value = trans[Math.abs(value)][converted[i]] * (value < 0 ? -1 : 1);
 				
 				System.out.printf("%dth: %s%s\n", i, next_value < 0 ? "-" : "", strs[Math.abs(next_value)]);
 				value = next_value;
 			}
 			
 			
 			
 			System.out.printf("Case #%d: \n", tt);
 		}
 		
 		sc.close();
 	}
 	
 	public static class Scanner {
 	    private BufferedReader br;
 	    private StringTokenizer tok;
 
 	    public Scanner(InputStream is) throws IOException {
 	        br = new BufferedReader(new InputStreamReader(is));
 	    }
 
 	    private void getLine() throws IOException {
 	        while (!hasNext()) { tok = new StringTokenizer(br.readLine()); }
 	    }
 
 	    private boolean hasNext() {
 	        return tok != null && tok.hasMoreTokens();
 	    }
 
 	    public String next() throws IOException {
 	        getLine(); return tok.nextToken();
 	    }
 
 	    public int nextInt() throws IOException {
 	        return Integer.parseInt(next());
 	    }
 	    
 	    public long nextLong() throws IOException {
 	    	return Long.parseLong(next());
 	    }
 	    
 	    public void close() throws IOException {
 	        br.close();
 	    }
 	}
 }
