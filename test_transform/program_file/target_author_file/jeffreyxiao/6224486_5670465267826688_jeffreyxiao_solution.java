
 import java.util.*;
 import java.io.*;
 
 public class Dijkstra {
 
 	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
 	static StringTokenizer st;
 
 	static int[][] table = {{0, 0, 0, 0, 0},
 		{0, 1, 2, 3, 4},
 		{0, 2,-1, 4,-3},
 		{0, 3,-4,-1, 2},
 		{0, 4, 3,-2,-1}};
 	static int l;
 	static long x;
 	static char[] in;
 	static int[] prefix;
 	static int[] suffix;
 	static int[] total = new int[5];
 	static final int SHIFT = 'i'-2;
 	static boolean res;
 	static boolean[][][] v;
 	public static void main (String[] args) throws IOException {
 		int t = readInt();
 		for (int q = 1; q <= t; q++) {
 			l = readInt();
 			x = readLong();
 			in = (" "+next()).toCharArray();
 			prefix = new int[l+2];
 			suffix = new int[l+2];
 			prefix[0] = prefix[l+1] = suffix[0] = suffix[l+1] = 1;
 
 			for (int i = 1; i <= l; i++)
 				prefix[i] = multi(prefix[i-1], in[i]-SHIFT);
 			for (int i = l; i >= 1; i--)
 				suffix[i] = multi(in[i]-SHIFT, suffix[i+1]);
 
 			total[0] = 1;
 			v = new boolean[l+1][6][20];
 			for (int i = 1; i <= 4; i++)
 				total[i] = multi(total[i-1], prefix[l]);
 			res = false;
 			solve(1, 2, 0);
 			System.out.printf("Case #%d: %s\n", q, res ? "YES" : "NO");
 		}
 	}
 	
 	private static void solve (int i, int j, int min) {
 		if (min > x || res || v[i][j][min])
 			return;
 		v[i][j][min] = true;
 
 		if (j == 5) {
 			if (i == 1) {
 
 				if (x >= min && (x - min)%4 == 0)
 					res = true;
 			}
 			return;
 		}
 		for (int k = 1; k <= l; k++) {
 			for (int z = 0; z <= 3; z++) {
 				int curr1 = 0, curr2 = 0;
 				int min1 = 0, min2 = 0;
 				if (k >= i) {
 					curr1 = get(i, k);
 
 					curr2 = multi(multi(suffix[i], total[z]), prefix[k]);
 					min1 = 0;
 					min2 = z+1;
 				} else {
 					curr1 = multi(suffix[i], prefix[k]);
 					curr2 = multi(multi(suffix[i], total[z]), prefix[k]);
 					min1 = 1;
 					min2 = z+1;
 				}
 				if (curr1 == j) {
 					if (k == l)
 						solve(1, j+1, min+min1+1);
 					else
 						solve(k+1, j+1, min+min1);
 				}
 				if (curr2 == j) {
 					if (k == l)
 						solve(1, j+1, min+min2+1);
 					else
 						solve(k+1, j+1, min+min2);
 				}
 			}
 		}
 	}
 	private static int get (int i, int j) {
 		int x = prefix[i-1];
 		int y = suffix[j+1];
 		for (int z = -4; z <= 4; z++) {
 			if (z == 0)
 				continue;
 			if (multi(multi(x, z), y) == prefix[l])
 				return z;
 		}
 		return 0;
 	}
 	static int multi (int a, int b) {
 		int res = table[abs(a)][abs(b)];
 		res *= a*b < 0 ? -1 : 1;
 		return res;
 	}
 	static int abs (int a) {
 		if (a < 0)
 			return -a;
 		return a;
 	}
 	static String next () throws IOException {
 		while (st == null || !st.hasMoreTokens())
 			st = new StringTokenizer(br.readLine().trim());
 		return st.nextToken();
 	}
 
 	static long readLong () throws IOException {
 		return Long.parseLong(next());
 	}
 
 	static int readInt () throws IOException {
 		return Integer.parseInt(next());
 	}
 
 	static double readDouble () throws IOException {
 		return Double.parseDouble(next());
 	}
 
 	static char readCharacter () throws IOException {
 		return next().charAt(0);
 	}
 
 	static String readLine () throws IOException {
 		return br.readLine().trim();
 	}
 }
