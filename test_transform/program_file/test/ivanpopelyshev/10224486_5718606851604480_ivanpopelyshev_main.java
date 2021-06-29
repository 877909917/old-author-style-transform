import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
 	final String problem = "A";
 
 
 	 final String filename= problem+"-small-attempt1";
 	
 
 	int MAXN = 5;
 	int MAX = 1 << 12;
 
 	public void solve() throws Exception {
 		int N = iread();
 
 		int a = iread(), b = iread(), c = iread();
 		int pn = 1 << N;
 		int pn2 = pn / 2;
 		res = new StringBuilder();
 		cycle: for (int k = 0; k < 3; k++) {
 			int k1 = (k + 2) % 3;
 			for (int a1 = 0; a1 <= a / 2; a1++) {
 				for (int b1 = 0; b1 <= b && b1 <= pn2; b1++) {
 					int c1 = pn2 - a1 - b1;
 					if (c1 < 0 || c1 > c)
 						continue;
 
 					if (best[k][N - 1][a1][b1] > 0
 							&& best[k1][N - 1][a - a1][b - b1] > 0) {
 						fill(k, N - 1, a1, b1);
 						fill(k1, N - 1, a - a1, b - b1);
 						break cycle;
 					}
 					if (best[k1][N - 1][a1][b1] > 0
 							&& best[k][N - 1][a - a1][b - b1] > 0) {
 						fill(k1, N - 1, a1, b1);
 						fill(k, N - 1, a - a1, b - b1);
 						break cycle;
 					}
 				}
 			}
 		}
 		if (res.length() == 0) {
 			out.write("IMPOSSIBLE");
 			return;
 		}
 		out.write(res.toString());
 	}
 
 	StringBuilder res;
 
 	public void fill(int k, int n, int a, int b) {
 		if (n == 0) {
 			if (a == 1) {
 				res.append('R');
 			} else if (b == 1) {
 				res.append('P');
 			} else {
 				res.append('S');
 			}
 			return;
 		}
 
 		int tt = best[k][n][a][b];
 		int pn = 1 << n;
 		int kk = tt / (pn * pn);
 		int k1 = (kk / 3);
 		int k2 = kk % 3;
 		int a1 = tt / pn % pn;
 		int b1 = tt % pn;
 		fill(k1, n - 1, a1, b1);
 		fill(k2, n - 1, a - a1, b - b1);
 	}
 
 	public void solve_gcj() throws Exception {
 		int tests = iread();
 		for (int test = 1; test <= tests; test++) {
 			out.write("Case #" + test + ": ");
 			solve();
 			out.write("\n");
 		}
 	}
 
 	int[][][][] best = new int[3][MAXN + 1][][];
 
 	public void init() {
 		long t = System.currentTimeMillis();
 		best[0][0] = new int[2][2];
 		best[1][0] = new int[2][2];
 		best[2][0] = new int[2][2];
 		best[0][0][1][0] = 1;
 		best[1][0][0][1] = 1;
 		best[2][0][0][0] = 1;
 		for (int n = 1; n <= MAXN; n++) {
 			int pn = 1 << n;
 			int pn2 = 1 << (n - 1);
 			best[0][n] = new int[pn + 1][pn + 1];
 			best[1][n] = new int[pn + 1][pn + 1];
 			best[2][n] = new int[pn + 1][pn + 1];
 
 			for (int a = 0; a <= pn; a++) {
 				for (int b = 0; a + b <= pn; b++) {
 					int c = pn - a - b;
 
 					if (a > b + c || b > a + c || c > a + b)
 						continue;
 
 					cycle: for (int k = 0; k < 3; k++) {
 
 						if (k == 0 && (a == 0 || c == 0))
 							continue;
 						if (k == 1 && (b == 0 || a == 0))
 							continue;
 						if (k == 2 && (c == 0 || b == 0))
 							continue;
 
 						int k1 = (k + 2) % 3;
 						for (int a1 = 0; a1 <= a / 2; a1++) {
 							if (a1 > pn2 - a1 && a1 > 1)
 								continue;
 							for (int b1 = 0; b1 <= b; b1++) {
 								int c1 = pn2 - a1 - b1;
 								if (c1 > c)
 									continue;
 								if (c1 < 0)
 									break;
 								if (best[k][n - 1][a1][b1] > 0
 										&& best[k1][n - 1][a - a1][b - b1] > 0) {
 									best[k][n][a][b] = (k * 3 + k1) * pn * pn
 											+ pn * a1 + b1;
 									break cycle;
 								}
 								if (best[k1][n - 1][a1][b1] > 0
 										&& best[k][n - 1][a - a1][b - b1] > 0) {
 									best[k][n][a][b] = (k1 * 3 + k) * pn * pn
 											+ pn * a1 + b1;
 									break cycle;
 								}
 							}
 						}
 						
 						
 					}
 				}
 			}
 			System.out.println("n=" + n + " s="
 					+ (System.currentTimeMillis() - t) / 1000);
 		}
 	}
 
 	public void run() {
 		try {
 			
 			
 			in = new BufferedReader(new FileReader(filename + ".in"));
 			out = new BufferedWriter(new FileWriter(filename + ".out"));
 			init();
 			solve_gcj();
 			out.flush();
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.exit(1);
 		}
 	}
 
 	public int iread() throws Exception {
 		return Integer.parseInt(readword());
 	}
 
 	public double dread() throws Exception {
 		return Double.parseDouble(readword());
 	}
 
 	public long lread() throws Exception {
 		return Long.parseLong(readword());
 	}
 
 	BufferedReader in;
 
 	BufferedWriter out;
 
 	public String readword() throws IOException {
 		StringBuilder b = new StringBuilder();
 		int c;
 		c = in.read();
 		while (c >= 0 && c <= ' ')
 			c = in.read();
 		if (c < 0)
 			return "";
 		while (c > ' ') {
 			b.append((char) c);
 			c = in.read();
 		}
 		return b.toString();
 	}
 
 	public static void main(String[] args) {
 		try {
 			Locale.setDefault(Locale.US);
 		} catch (Exception e) {
 
 		}
 		new Thread(new Main()).start();
 		
 	}
 }