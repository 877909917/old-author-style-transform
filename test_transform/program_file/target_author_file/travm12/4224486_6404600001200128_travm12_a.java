import java.io.*;
 import java.util.*;
 
 public class a {
 
 	public static final String FILE_NAME = "a.in";
 	public static final String OUTPUT_FILE_NAME = "a.out";
 	public static PrintWriter out;
 
 	public static void main(String[] Args) throws Exception {
 		FastScanner sc = new FastScanner(new File(FILE_NAME));
 		out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
 				OUTPUT_FILE_NAME))));
 
 		int cc = 0, t = sc.nextInt();
 
 		while (t-- > 0) {
 			int n = sc.nextInt();
 			int[] vals = new int[n];
 			for (int k = 0; k < n; k++) {
 				vals[k] = sc.nextInt();
 			}
 			long ans = 0;
 			int max = 0;
 			for (int k = 1; k < n; k++) {
 				if (vals[k] < vals[k - 1]) {
 					if (vals[k - 1] - vals[k] > max){
 						max = vals[k - 1] - vals[k];
 					}
 					ans += vals[k - 1] - vals[k];
 				}
 			}
 			out.printf("Case #%d: %d", ++cc, ans);
 			ans = 0;
 			for (int k = 1; k < n; k++){
 				ans += Math.min(max, vals[k-1]);
 			}
 			out.printf(" %d%n", ans);
 		}
 
 		out.close();
 	}
 
 	public static class FastScanner {
 		private StringTokenizer st;
 		private BufferedReader br;
 
 		FastScanner(File in) throws Exception {
 			br = new BufferedReader(new FileReader(in));
 			st = new StringTokenizer(br.readLine());
 		}
 
 		String next() throws Exception {
 			if (st.hasMoreTokens()) {
 				return st.nextToken();
 			}
 
 			st = new StringTokenizer(br.readLine());
 			return next();
 		}
 
 		int nextInt() throws Exception {
 			return Integer.parseInt(next());
 		}
 
 		long nextLong() throws Exception {
 			return Long.parseLong(next());
 		}
 	}
 }
