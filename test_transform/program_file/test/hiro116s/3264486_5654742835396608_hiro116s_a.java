import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.InputMismatchException;
  
 public class A {
 	InputStream is;
  
 	int __t__ = 1;
 	int __f__ = 0;
 	int __FILE_DEBUG_FLAG__ = __f__;
 	String __DEBUG_FILE_NAME__ = "src/C1";
  
 	FastScanner in;
 	PrintWriter out;
 
 	public void solve() {
 		int T = in.nextInt();
 		for (int __times = 1; __times <= T; __times++) {
 			long N = in.nextInt(), K = in.nextInt();
 			long v = N;
 			long cntBig = 1, cntSmall = 0;
 			long cur = 1;
 			
 			while (cur * 2 <= K) {
 				long nextV = v / 2;
 				long nextCntBig, nextCntSmall;
 				if (v % 2 == 0) {
 					nextCntBig = cntBig;
 					nextCntSmall = cntBig + cntSmall * 2;
 				} else {
 					nextCntBig = cntBig * 2 + cntSmall;
 					nextCntSmall = cntSmall;
 				}
 				cntSmall = nextCntSmall;
 				cntBig = nextCntBig;
 				v = nextV;
 				cur <<= 1L;
 			}
 			
 			if (cur + cntBig <= K) v--;
 			
 			System.out.println("Case #" + __times + ": " + (v / 2) + " " + ((v - 1) / 2));
 		}
 	}
 	
 	public void run() {
 		if (__FILE_DEBUG_FLAG__ == __t__) {
 			try {
 				is = new FileInputStream(__DEBUG_FILE_NAME__);
 			} catch (FileNotFoundException e) {
 				
 				e.printStackTrace();
 			}
 			System.out.println("FILE_INPUT!");
 		} else {
 			is = System.in;
 		}
 		in = new FastScanner(is);
 		out = new PrintWriter(System.out);
  
 		solve();
 	}
  
 	public static void main(String[] args) {
 		new A().run();
 	}
  
 	public void mapDebug(int[][] a) {
 		System.out.println("--------map display---------");
  
 		for (int i = 0; i < a.length; i++) {
 			for (int j = 0; j < a[i].length; j++) {
 				System.out.printf("%3d ", a[i][j]);
 			}
 			System.out.println();
 		}
  
 		System.out.println("----------------------------");
 		System.out.println();
 	}
  
 	public void debug(Object... obj) {
 		System.out.println(Arrays.deepToString(obj));
 	}
  
 	class FastScanner {
 		private InputStream stream;
 		private byte[] buf = new byte[1024];
 		private int curChar;
 		private int numChars;
  
 		public FastScanner(InputStream stream) {
 			this.stream = stream;
 			
  
 		}
  
 		int read() {
 			if (numChars == -1)
 				throw new InputMismatchException();
 			if (curChar >= numChars) {
 				curChar = 0;
 				try {
 					numChars = stream.read(buf);
 				} catch (IOException e) {
 					throw new InputMismatchException();
 				}
 				if (numChars <= 0)
 					return -1;
 			}
 			return buf[curChar++];
 		}
  
 		boolean isSpaceChar(int c) {
 			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
 		}
  
 		boolean isEndline(int c) {
 			return c == '\n' || c == '\r' || c == -1;
 		}
  
 		int nextInt() {
 			return Integer.parseInt(next());
 		}
  
 		int[] nextIntArray(int n) {
 			int[] array = new int[n];
 			for (int i = 0; i < n; i++)
 				array[i] = nextInt();
  
 			return array;
 		}
  
 		int[][] nextIntMap(int n, int m) {
 			int[][] map = new int[n][m];
 			for (int i = 0; i < n; i++) {
 				map[i] = in.nextIntArray(m);
 			}
 			return map;
 		}
  
 		long nextLong() {
 			return Long.parseLong(next());
 		}
  
 		long[] nextLongArray(int n) {
 			long[] array = new long[n];
 			for (int i = 0; i < n; i++)
 				array[i] = nextLong();
  
 			return array;
 		}
  
 		long[][] nextLongMap(int n, int m) {
 			long[][] map = new long[n][m];
 			for (int i = 0; i < n; i++) {
 				map[i] = in.nextLongArray(m);
 			}
 			return map;
 		}
  
 		double nextDouble() {
 			return Double.parseDouble(next());
 		}
  
 		double[] nextDoubleArray(int n) {
 			double[] array = new double[n];
 			for (int i = 0; i < n; i++)
 				array[i] = nextDouble();
  
 			return array;
 		}
  
 		double[][] nextDoubleMap(int n, int m) {
 			double[][] map = new double[n][m];
 			for (int i = 0; i < n; i++) {
 				map[i] = in.nextDoubleArray(m);
 			}
 			return map;
 		}
  
 		String next() {
 			int c = read();
 			while (isSpaceChar(c))
 				c = read();
 			StringBuilder res = new StringBuilder();
 			do {
 				res.appendCodePoint(c);
 				c = read();
 			} while (!isSpaceChar(c));
 			return res.toString();
 		}
  
 		String[] nextStringArray(int n) {
 			String[] array = new String[n];
 			for (int i = 0; i < n; i++)
 				array[i] = next();
  
 			return array;
 		}
  
 		String nextLine() {
 			int c = read();
 			while (isEndline(c))
 				c = read();
 			StringBuilder res = new StringBuilder();
 			do {
 				res.appendCodePoint(c);
 				c = read();
 			} while (!isEndline(c));
 			return res.toString();
 		}
 	}
 }
  