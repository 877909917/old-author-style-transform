import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.StringTokenizer;
 
 public class Pancakes {
 	public static void main(String[] args) throws Exception {
 		InputReader r = new InputReader(new FileReader("B-small-attempt1.in"));
 		PrintWriter out = new PrintWriter(new FileWriter("b_small_submit.txt"));
 		int T = r.nextInt();
 		int test = 1;
 		while (T-- > 0) {
 			int d = r.nextInt();
 			int[] arr = new int[d];
 			for (int i = 0; i < d; i++) {
 				arr[i] = r.nextInt();
 			}
 			int res = 1 << 28;
 			for (int x = 1; x < 100; x++) {
 				int temp = 0;
 				for (int p : arr) {
 					temp += Math.ceil(1.0 * p / x) - 1;
 				}
 				res = Math.min(res, x + temp);
 			}
 			out.printf("Case #%d: %d\n", test++, res);
 		}
 		out.close();
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
