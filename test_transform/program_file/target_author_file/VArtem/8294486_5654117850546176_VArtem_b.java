import java.util.*;
 import java.io.*;
 
 public class B {
 
 	void solve() {
 		int n = in.nextInt();
 		int r = in.nextInt();
 		int o = in.nextInt();
 		int y = in.nextInt();
 		int g = in.nextInt();
 		int b = in.nextInt();
 		int v = in.nextInt();
 
 		List<String> reds = getList(r, g, 'R', 'G');
 		List<String> blues = getList(b, o, 'B', 'O');
 		List<String> yellows = getList(y, v, 'Y', 'V');
 		if (reds == null || blues == null || yellows == null) {
 			out.println("IMPOSSIBLE");
 			return;
 		}
 
 		List<String>[] all = new List[] { reds, blues, yellows };
 		for (int init = 0; init < 3; init++) {
 			boolean[][][][] dp = new boolean[3][reds.size() + 1][blues.size() + 1][yellows.size() + 1];
 			byte[][][][] prevLast = new byte[3][reds.size() + 1][blues.size() + 1][yellows.size() + 1];
 
 			if (all[init].size() > 0) {
 				dp[init][init == 0 ? 1 : 0][init == 1 ? 1 : 0][init == 2 ? 1 : 0] = true;
 			}
 
 			
 			for (int i = 0; i <= reds.size(); i++) {
 				for (int j = 0; j <= blues.size(); j++) {
 					for (int k = 0; k <= yellows.size(); k++) {
 						for (byte last = 0; last < 3; last++) {
 							if (!dp[last][i][j][k]) {
 								continue;
 							}
 							
 							
 							
 							if (i + 1 <= reds.size() && last != 0) {
 								dp[0][i + 1][j][k] = true;
 								prevLast[0][i + 1][j][k] = last;
 							}
 							if (j + 1 <= blues.size() && last != 1) {
 								dp[1][i][j + 1][k] = true;
 								prevLast[1][i][j + 1][k] = last;
 
 							}
 							if (k + 1 <= yellows.size() && last != 2) {
 								dp[2][i][j][k + 1] = true;
 								prevLast[2][i][j][k + 1] = last;
 							}
 						}
 					}
 				}
 			}
 
 			String result = null;
 			for (byte last = 0; last < 3; last++) {
 				if (init != last) {
 					if (dp[last][reds.size()][blues.size()][yellows.size()] && result == null) {
 						StringBuilder sb = new StringBuilder();
 
 						int curRed = reds.size(), curBlue = blues.size(), curYellow = yellows.size();
 						byte curLast = last;
 
 						while (curRed + curBlue + curYellow > 0) {
 							byte plast = prevLast[curLast][curRed][curBlue][curYellow];
 							if (curLast == 0) {
 								sb.append(reds.get(--curRed));
 							} else if (curLast == 1) {
 								sb.append(blues.get(--curBlue));
 							} else {
 								sb.append(yellows.get(--curYellow));
 							}
 							curLast = plast;
 						}
 
 						out.println(sb.toString());
 						return;
 					}
 				}
 			}
 		}
 		out.println("IMPOSSIBLE");
 	}
 
 	private List<String> getList(int single, int pair, char singleChar, char pairChar) {
 		if (single == 0 && pair == 0) {
 			return new ArrayList<>();
 		}
 		if (single + 1 < pair) {
 			return null;
 		}
 		List<String> result = new ArrayList<>();
 		for (int i = 0; i < single - (pair + 1); i++) {
 			result.add("" + singleChar);
 		}
 		String last = "" + singleChar;
 		for (int i = 0; i < pair; i++) {
 			last += pairChar + "" + singleChar;
 		}
 		result.add(last);
 		return result;
 	}
 
 	FastScanner in;
 	PrintWriter out;
 
 	void run() {
 		in = new FastScanner("input.txt");
 		try {
 			out = new PrintWriter("output.txt");
 		} catch (FileNotFoundException e) {
 		}
 		int tests = in.nextInt();
 		for (int i = 0; i < tests; i++) {
 			long startTime = System.currentTimeMillis();
 			out.printf("Case #%d: ", i + 1);
 			solve();
 			System.err.printf("Test #%d solved in %d ms\n", i + 1, System.currentTimeMillis() - startTime);
 		}
 		out.close();
 	}
 
 	class FastScanner {
 		BufferedReader br;
 		StringTokenizer st;
 
 		public FastScanner() {
 			br = new BufferedReader(new InputStreamReader(System.in));
 		}
 
 		public FastScanner(String s) {
 			try {
 				br = new BufferedReader(new FileReader(s));
 			} catch (FileNotFoundException e) {
 				
 				e.printStackTrace();
 			}
 		}
 
 		public String nextToken() {
 			while (st == null || !st.hasMoreTokens()) {
 				try {
 					st = new StringTokenizer(br.readLine());
 				} catch (IOException e) {
 				}
 			}
 			return st.nextToken();
 		}
 
 		public int nextInt() {
 			return Integer.parseInt(nextToken());
 		}
 
 		public long nextLong() {
 			return Long.parseLong(nextToken());
 		}
 
 		public double nextDouble() {
 			return Double.parseDouble(nextToken());
 		}
 	}
 
 	public static void main(String[] args) {
 		new B().run();
 	}
 }
