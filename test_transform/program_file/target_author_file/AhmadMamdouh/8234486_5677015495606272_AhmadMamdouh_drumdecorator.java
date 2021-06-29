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
 
 public class DrumDecorator {
 	static ArrayList<int[][]> list;
 	static int[][] solution = new int[7][7];
 
 	static void go(int[][] arr, int i, int j) {
 		if (i == arr.length) {
 			int[][] a = new int[arr.length][arr[0].length];
 			for (int k = 0; k < a.length; k++) {
 				for (int k2 = 0; k2 < a[k].length; k2++) {
 					a[k][k2] = arr[k][k2];
 				}
 				list.add(a);
 			}
 			return;
 		}
 		if (j == arr[0].length) {
 			go(arr, i + 1, 0);
 			return;
 		}
 		for (int x = 1; x <= 4; x++) {
 			arr[i][j] = x;
 			if (valid(arr))
 				go(arr, i, j + 1);
 			arr[i][j] = -1;
 		}
 	}
 
 	static int[] dx = { -1, 1, 0, 0 };
 	static int[] dy = { 0, 0, 1, -1 };
 
 	private static boolean valid(int[][] arr) {
 		for (int i = 0; i < arr.length; i++) {
 			for (int j = 0; j < arr[0].length; j++) {
 				int c = 0, empty = 0;
 				if (arr[i][j] == -1)
 					continue;
 				for (int k = 0; k < 4; k++) {
 					int ni = i + dx[k];
 					int nj = j + dy[k];
 					if (nj < 0)
 						nj = arr[0].length - 1;
 					if (nj == arr[0].length) {
 						nj = 0;
 					}
 					if (ni >= 0 && ni < arr.length) {
 						if (arr[ni][nj] != -1 && arr[ni][nj] == arr[i][j])
 							c++;
 						if (arr[ni][nj] == -1)
 							empty++;
 					}
 				}
 
 
 				if (empty == 0) {
 					if (c != arr[i][j])
 						return false;
 				} else {
 
 
 				}
 			}
 		}
 		return true;
 	}
 
 	public static void main(String[] args) throws IOException {
 
 		InputReader r = new InputReader(new FileReader("D-small-attempt1.in"));
 		PrintWriter out = new PrintWriter(new FileWriter("D_small_isA.txt"));
 		int T = r.nextInt();
 		int test = 1;
 		long t = System.currentTimeMillis();
 		precompute();
 
 		while (T-- > 0) {
 			int n = r.nextInt();
 			int m = r.nextInt();
 
 			System.out.println(test + " " + solution[n][m]);
 			out.printf("Case #%d: %d\n", test++, solution[n][m]);
 		}
 		out.close();
 	}
 
 	private static void precompute() {
 		for (int n = 2; n <= 6; n++) {
 			for (int m = 3; m <= 6; m++) {
 				list = new ArrayList<int[][]>();
 				int[][] arr = new int[n][m];
 				for (int i = 0; i < arr.length; i++) {
 					Arrays.fill(arr[i], -1);
 				}
 				go(arr, 0, 0);
 				boolean[] vis = new boolean[list.size()];
 
 				int res = 0;
 				for (int i = 0; i < list.size(); i++) {
 					if (vis[i])
 						continue;
 					res++;
 					vis[i] = true;
 					for (int j = i + 1; j < list.size(); j++) {
 						if (!vis[j]) {
 							if (isSame(list.get(i), list.get(j))) {
 								vis[j] = true;
 							}
 						}
 					}
 				}
 
 				solution[n][m] = res;
 			}
 		}
 	}
 
 	private static void print(int[][] a) {
 		for(int[] x:a){
 			for(int y:x){
 				System.out.print(y+" ");
 			}
 			System.out.println();
 		}
 	}
 
 	private static boolean isSame(int[][] a, int[][] b) {
 		int n = a.length;
 		int m = a[0].length;
 		for (int x = 0; x < m; x++) {
 			
 			boolean can = true;
 			for (int j = 0; j < m; j++) {
 				for (int i = 0; i < n; i++) {
 					if (a[i][j] != b[i][(j + x) % m])
 						can = false;
 				}
 			}
 			if(can)
 				return true;
 		}
 		return false;
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
