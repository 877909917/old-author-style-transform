
 import java.io.*;
 import java.util.*;
 
 public class B {
 	FastScanner in = new FastScanner(System.in);
 	PrintWriter out = new PrintWriter(System.out);
 
 	double EPS = 1e-9;
 	boolean isZero(double x) {
 		return (x + EPS >= 0 && x - EPS <= 0);
 	}
 	
 	public void run() {
 		int T = in.nextInt();
 		for (int caseN = 1; caseN <= T; caseN++) {
 			int N = in.nextInt();
 			double V = in.nextDouble(), X = in.nextDouble();
 			double[] R = new double[N];
 			double[] C = new double[N];
 			for (int i = 0; i < N; i++) {
 				R[i] = in.nextDouble();
 				C[i] = in.nextDouble();
 			}
 			
 			double res = -1;
 			if (N == 2) {
 				if (C[0] != C[1]) {
 					double v0 = (V * (X - C[1])) / (C[0] - C[1]);
 					double v1 = V - v0;
 						
 					if (v0 + EPS < 0 || v1 + EPS < 0) {
 						
 					} else {
 						res = Math.max(v0 / R[0], v1 / R[1]);
 					}
 					v1 = (V * (X - C[0])) / (C[1] - C[0]);
 					v0 = V - v1;
 					
 					if (v0 + EPS < 0 || v1 + EPS < 0) {
 						
 					} else {
 						res = Math.max(v0 / R[0], v1 / R[1]);
 					}
 				} else {
 					if (C[0] == X) {
 						res = Math.max(V / R[0], V / R[1]);
 					}
 				}
 			} else {
 				if (C[0] == X) {
 					res = V / R[0];
 				}
 			}
 			out.println("Case #" + caseN + ": " + (res <= 0 ? "IMPOSSIBLE" : res));
 		}
 		out.close();
 	}
 
 	public static void main(String[] args) {
 		new B().run();
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
 
