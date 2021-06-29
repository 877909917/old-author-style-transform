package contest.codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class B {
 
 	static BufferedReader br;
 	static PrintWriter out;
 	static StringTokenizer st;
 
 	static int T;
 	static char first;
 	
 	public static void main (String[] args) throws IOException {
 		br = new BufferedReader(new InputStreamReader(System.in));
 		out = new PrintWriter(new OutputStreamWriter(System.out));
 		br = new BufferedReader(new FileReader("B-small-attempt1.in"));
 		out = new PrintWriter(new FileWriter("out.txt"));
 
 		T = readInt();
 		
 		main : for (int t = 1; t <= T; t++) {
 			int N = readInt();
 			int R = readInt();
 			int O = readInt();
 			int Y = readInt();
 			int G = readInt();
 			int B = readInt();
 			int V = readInt();
 			
 			
 			
 			char[] res = new char[N];
 			State[] states = {new State('R', R), new State('Y', Y), new State('B', B)};
 			char prev = 'a';
 			first = 'R';
 			for (int i = 0; i < N; i++) {
 				Arrays.sort(states);
 				boolean valid = false;
 				for (int j = 0; j < 3; j++) {
 					if (states[j].c != prev && states[j].occ > 0) {
 						res[i] = states[j].c;
 						states[j].occ--;
 						valid = true;
 						prev = states[j].c;
 						if (i == 0)
 							first = prev;
 						break;
 					}
 				}
 				if (!valid) {
 					out.printf("Case #%d: IMPOSSIBLE\n", t);
 					continue main;
 				}
 			}
 			if (res[0] != res[N - 1])
 				out.printf("Case #%d: %s\n", t, new String(res));
 			else
 				out.printf("Case #%d: IMPOSSIBLE\n", t);
 		}
 		
 		out.close();
 	}
 
 	static class State implements Comparable<State> {
 		char c;
 		int occ;
 		State (char c, int occ) {
 			this.c = c;
 			this.occ = occ;
 		}
 		@Override
 		public int compareTo (State o) {
 			if (occ == o.occ) {
 				if (c == o.c)
 					return 0;
 				if (first == c)
 					return -1;
 				if (first == o.c)
 					return 1;
 				return c - o.c;
 			}
 			return o.occ - occ;
 		}
 		@Override
 		public String toString () {
 			return String.format("%c %d", c, occ);
 		}
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
 
