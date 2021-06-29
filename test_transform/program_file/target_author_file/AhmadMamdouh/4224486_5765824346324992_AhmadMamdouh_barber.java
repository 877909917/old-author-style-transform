import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Barber {
 	public static void main(String[] args) throws Exception {
 		
 		InputReader r = new InputReader(new FileReader("B-small-attempt0.in"));
 		PrintWriter out = new PrintWriter(new FileWriter("B_submit.txt"));
 		int T = r.nextInt();
 		int test = 1;
 		while (T-- > 0) {
 			int b = r.nextInt();
 			int n = r.nextInt() - 1;
 			int[] arr = new int[b];
 			for (int i = 0; i < arr.length; i++) {
 				arr[i] = r.nextInt();
 			}
 			int lcm = arr[0];
 			for (int i = 0; i < arr.length; i++) {
 				lcm = lcm(lcm, arr[i]);
 			}
 			System.out.println(lcm);
 			ArrayList<Integer>[] c = new ArrayList[lcm];
 			for (int i = 0; i < c.length; i++) {
 				c[i] = new ArrayList<Integer>();
 			}
 			int k = 0;
 			for (int i = 0; i < arr.length; i++) {
 				int x = 0;
 				while (x < c.length) {
 					c[x].add(i + 1);
 					x += arr[i];
 					k++;
 				}
 			}
 			int[] all = new int[k];
 			k = 0;
 			for (int i = 0; i < c.length; i++) {
 				for (int x : c[i]) {
 					all[k++] = x;
 				}
 			}
 			out.printf("Case #%d: %d\n", test++, all[n % all.length]);
 		}
 		out.close();
 	}
 
 	public static int lcm(int a, int b) {
 		return (a * b) / gcd(a, b);
 	}
 
 	public static int gcd(int a, int b) {
 		return b == 0 ? a : gcd(b, a % b);
 	}
 
 	static class InputReader {
 		private BufferedReader reader;
 		private StringTokenizer tokenizer;
 
 		public InputReader(InputStream stream) {
 			reader = new BufferedReader(new InputStreamReader(stream));
 			tokenizer = null;
 		}
 
 		public InputReader(FileReader stream) {
 			reader = new BufferedReader(stream);
 			tokenizer = null;
 		}
 
 		public String nextLine() {
 			try {
 				return reader.readLine();
 			} catch (IOException e) {
 				
 				e.printStackTrace();
 				return null;
 			}
 		}
 
 		public String next() {
 			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
 				try {
 					tokenizer = new StringTokenizer(reader.readLine());
 				} catch (IOException e) {
 					throw new RuntimeException(e);
 				}
 			}
 			return tokenizer.nextToken();
 		}
 
 		public int nextInt() {
 			return Integer.parseInt(next());
 		}
 
 		public long nextLong() {
 			return Long.parseLong(next());
 		}
 	}
 }
