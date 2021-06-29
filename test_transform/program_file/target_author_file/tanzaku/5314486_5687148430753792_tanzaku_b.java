package r2;
 
 
 import java.io.*;
 import java.math.*;
 import java.util.*;
 import java.util.concurrent.atomic.AtomicInteger;
 
 import static java.util.Arrays.*;
 
 public class B {
 	
 	static
 	class Config {
 		public static final boolean DISP_PROGRESS = true;
 		public static final boolean DISP_ELASPED_TIME = true;
 		public static final boolean STDIO = false;
 
 		public static final String BASE_DIR = "data\\r2" ;
 		public static final String IN_FILE = "B-small-attempt0.in";
 		public static final String OUT_FILE = IN_FILE + "_out.txt";
 		public static final int MAX_THREAD = 3;
 	}
 
 	
 	static
 	class ParallelSolver implements Runnable {
 		private static final int mod = (int)1e9+7;
 
 		int n, m, c;
 		int[] p, b;
 		public void readInput(final IOFast io) throws IOException {
 			n = io.nextInt();
 			c = io.nextInt();
 			m = io.nextInt();
 			p = new int[m];
 			b = new int[m];
 			for (int i = 0; i < m; i++) {
 				p[i] = io.nextInt() - 1;
 				b[i] = io.nextInt() - 1;
 			}
 		}
 
 		@Override
 		public void run() {
 			int prom = 0;
 			
 			int[] cnt = new int[c];
 			for (int i = 0; i < m; i++) {
 				cnt[b[i]]++;
 			}
 			int low = 0;
 			for (int i = 0; i < c; i++) {
 				low = Math.max(low, cnt[i]);
 			}
 			
 			low--;
 			int high = m + 1;
 			while (high - low > 1) {
 				int mid = (low + high) / 2;
 				
 				final int S = 2*n + c, T = S + 1;
 				MinimumCostFlow flow = new MinimumCostFlow(T + 1);
 				for (int i = 0; i < c; i++) flow.addEdge(S, i, cnt[i], 0);
 				for (int i = 0; i < n; i++) {
 					flow.addEdge(c+n+i, T, mid, 0);
 					flow.addEdge(c+n+i, c+i, 1<<29, 1);
 					flow.addEdge(c+i, c+n+i, 1<<29, 0);
 					if (i > 0) flow.addEdge(c+i, c+i-1, 1<<29, 0);
 				}
 				for (int i = 0; i < m; i++) {
 					int cc = b[i], ss = p[i];
 					flow.addEdge(cc, c+n+ss, 1, 0);
 				}
 				
 				int pr = flow.MinCostFlow(S, T, m);
 				if (pr >= 0) {
 					high = mid;
 					prom = pr;
 				} else {
 					low = mid;
 				}
 			}
 			out.println(high + " " + prom);
 		}
 		
 		
 		void printList(int[] res) {
 			for(int i = 0; i < res.length; i++) {
 				out.print(res[i] + (i==res.length-1?"\n":" "));
 			}
 		}
 		void printList(List<Integer> res) {
 			for(int i = 0; i < res.size(); i++) {
 				out.print(res.get(i) + (i==res.size()-1?"\n":" "));
 			}
 		}
 		
 
 		final Random random = new Random(System.currentTimeMillis());
 		final StringWriter sw = new StringWriter();
 		final PrintWriter out = new PrintWriter(sw);
 		
 		@Override
 		public String toString() {
 			out.flush();
 			return sw.toString();
 		}
 		
 		public ParallelSolver init(final IOFast io) throws IOException {
 			readInput(io);
 			return this;
 		}
 	}
 
 	static void dump(Object... o) { System.err.println(Arrays.deepToString(o)); } 
 	
 	static class MinimumCostFlow {
 		class Edge {
 			public int to, cap, cost, rev;
 			public Edge(int to_, int cap_, int cost_, int rev_) { to = to_; cap = cap_; cost = cost_; rev = rev_; }
 		}
 		
 		static class KeyValuePair<T1 extends Comparable<T1>, T2> implements Comparable<KeyValuePair<T1, T2>> {
 			T1 key;
 			T2 value;
 			
 			public KeyValuePair(T1 key, T2 value) { this.key = key; this.value = value; }
 
 			@Override
 			public int compareTo(KeyValuePair<T1, T2> o) {
 				return key.compareTo(o.key);
 			}
 		}
 
 
 		static final int INF = 1 << 29;
 		final int V;
 		List<List<Edge>> G;
 		int[] h, dist, prevv, preve;
 		
 		public void addEdge(int from, int to, int cap, int cost) {
 			G.get(from).add(new Edge(to, cap, cost, G.get(to).size()));
 			G.get(to).add(new Edge(from, 0, -cost, G.get(from).size() - 1));
 		}
 
 		public void addEdgeWithMinFlowConstraint(int from, int to, int cap, int cost, int minFlowB) {
 			addEdge(from, to, cap - minFlowB, cost);
 			addEdge(from, to, minFlowB, cost);
 		}
 
 		public int MinCostFlow(int s, int t, int f) {
 			int res = 0;
 			for (int i = 0; i < V; i++) h[i] = 0;
 			while (f > 0) {
 				for (int i = 0; i < V; i++) dist[i] = INF;
 				dist[s] = 0;
 				PriorityQueue<KeyValuePair<Integer, Integer>> q = new PriorityQueue<KeyValuePair<Integer, Integer>>();
 				q.add(new KeyValuePair<Integer, Integer>(0, s));
 				while (!q.isEmpty()) {
 					final KeyValuePair<Integer, Integer> pair = q.poll();
 					int p = pair.key,
 						v = pair.value;
 					if (dist[v] < p) continue;
 					for (int i = 0; i < G.get(v).size(); i++) {
 						Edge e = G.get(v).get(i);
 						int dis = dist[v] + e.cost + h[v] - h[e.to];
 						if (e.cap > 0 && dist[e.to] > dis) {
 							dist[e.to] = dis;
 							prevv[e.to] = v;
 							preve[e.to] = i;
 							q.add(new KeyValuePair<Integer, Integer>(dist[e.to], e.to));
 						}
 					}
 				}
 				if (dist[t] == INF) return -1;
 				for (int i = 0; i < V; i++) h[i] += dist[i];
 
 				int d = f;
 				for (int i = t; i != s; i = prevv[i])
 					d = Math.min(d, G.get(prevv[i]).get(preve[i]).cap);
 
 				f -= d;
 				res += d * h[t];
 				for (int i = t; i != s; i = prevv[i]) {
 					Edge e = G.get(prevv[i]).get(preve[i]);
 					e.cap -= d;
 					G.get(i).get(e.rev).cap += d;
 				}
 			}
 			return res;
 		}
 
 		public MinimumCostFlow(int size) {
 			V = size;
 			h = new int[size];
 			dist = new int[size];
 			prevv = new int[size];
 			preve = new int[size];
 			G = new ArrayList<List<Edge>>(size);
 			for (int i = 0; i < size; i++)
 				G.add(new ArrayList<Edge>());
 		}
 	}
 
 
 	
 	final IOFast io = new IOFast();
 	
 	
 	public long elaspedTimeMilli;
 	
 	
 	
 	static class Par implements Runnable {
 		private static AtomicInteger cur = new AtomicInteger(1);
 		
 		IOFast io;
 		int T;
 		int caseIndex;
 		String[] answer;
 		ParallelSolver ps;
 		
 		public Par(int T, String[] answer, IOFast io) {
 			this.T = T;
 			this.answer = answer;
 			this.io = io;
 		}
 		
 		public boolean init() throws IOException {
 			synchronized (answer) {
 				caseIndex = cur.getAndIncrement();
 				if(caseIndex > T) return false;
 				System.gc();
 				ps = new ParallelSolver();
 				ps.init(io);
 				return true;
 			}
 		}
 		
 		public void debugProgress() {
 			synchronized (answer) {
 				if(Config.DISP_PROGRESS) {
 					System.err.println("Case #" + caseIndex + ": DONE");
 				}
 			}
 		}
 		
 		@Override
 		public void run() {
 			try {
 				while(init()) {
 					ps.run();
 					answer[caseIndex - 1] = "Case #" + caseIndex + ": " + ps.toString();
 					debugProgress();
 				}
 			} catch (IOException e) {
 				
 				e.printStackTrace();
 			}
 		}
 	}
 	
 	public void run() throws IOException, InterruptedException {
 		if(!Config.STDIO) {
 			io.setFileDir(Config.BASE_DIR);
 			io.setFileIO(Config.IN_FILE, Config.OUT_FILE);
 		}
 		
 		int T = io.nextInt();
 
 		Runnable[] inst = new Runnable[Config.MAX_THREAD];
 		Thread[] thread = new Thread[Config.MAX_THREAD];
 		String[] answer = new String[T];
 		
 		final long start = System.currentTimeMillis();
 		for(int i = 0; i < Config.MAX_THREAD; i++) {
 			inst[i] = new Par(T, answer, io);
 
 			thread[i] = new Thread(null, inst[i], ""+(i+1), 1<<25);
 			thread[i].start();
 		}
 		for(int i = 0; i < Config.MAX_THREAD; i++) {
 			thread[i].join();
 		}
 		for(String s : answer) {
 			io.out.print(s);
 		}
 		final long end = System.currentTimeMillis();
 		elaspedTimeMilli = end - start;
 		System.err.println(elaspedTimeMilli + " [ms]");
 	}
 
 
 	
 	static int gcd(int n, int r) { return r == 0 ? n : gcd(r, n%r); }
 	static long gcd(long n, long r) { return r == 0 ? n : gcd(r, n%r); }
 	
 	static <T> void swap(T[] x, int i, int j) {
 		T t = x[i];
 		x[i] = x[j];
 		x[j] = t;
 	}
 	
 	static void swap(int[] x, int i, int j) {
 		int t = x[i];
 		x[i] = x[j];
 		x[j] = t;
 	}
 	
 
 	static void radixSort(int[] xs) {
 		int[] cnt = new int[(1<<16)+1];
 		int[] ys = new int[xs.length];
 		
 		for(int j = 0; j <= 16; j += 16) {
 			Arrays.fill(cnt, 0);
 			for(int x : xs) { cnt[(x>>j&0xFFFF)+1]++; }
 			for(int i = 1; i < cnt.length; i++) { cnt[i] += cnt[i-1]; }
 			for(int x : xs) { ys[cnt[x>>j&0xFFFF]++] = x; }
 			{ final int[] t = xs; xs = ys; ys = t; }
 		}
 	}
 	
 	static void radixSort(long[] xs) {
 		int[] cnt = new int[(1<<16)+1];
 		long[] ys = new long[xs.length];
 		
 		for(int j = 0; j <= 48; j += 16) {
 			Arrays.fill(cnt, 0);
 			for(long x : xs) { cnt[(int)(x>>j&0xFFFF)+1]++; }
 			for(int i = 1; i < cnt.length; i++) { cnt[i] += cnt[i-1]; }
 			for(long x : xs) { ys[cnt[(int)(x>>j&0xFFFF)]++] = x; }
 			{ final long[] t = xs; xs = ys; ys = t; }
 		}
 	}
 	
 
 	static void arrayIntSort(int[][] x, int... keys) {
 		Arrays.sort(x, new ArrayIntsComparator(keys));
 	}
 	
 	static class ArrayIntsComparator implements Comparator<int[]> {
 		final int[] KEY;
 		
 		public ArrayIntsComparator(int... key) {
 			KEY = key;
 		}
 		
 		@Override
 		public int compare(int[] o1, int[] o2) {
 			for(int k : KEY) if(o1[k] != o2[k]) return o1[k] - o2[k];
 			return 0;
 		}
 	}
 	
 	static class ArrayIntComparator implements Comparator<int[]> {
 		final int KEY;
 		
 		public ArrayIntComparator(int key) {
 			KEY = key;
 		}
 		
 		@Override
 		public int compare(int[] o1, int[] o2) {
 			return o1[KEY] - o2[KEY];
 		}
 	}
 	
 	
 	void main() throws IOException, InterruptedException {
 		
 		try {
 			run();
 		}
 		catch (EndOfFileRuntimeException e) { }
 		io.out.flush();
 	}
 
 	public static void main(String[] args) throws IOException, InterruptedException {
 		new B().main();
 	}
 	
 	static class EndOfFileRuntimeException extends RuntimeException {
 		private static final long serialVersionUID = -8565341110209207657L; }
 
 	static
 	public class IOFast {
 		private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 		private PrintWriter out = new PrintWriter(System.out);
 		private String _dir;
 		
 		void setFileDir(String dir) {
 			this._dir = dir;
 		}
 
 		void setFileIn(String ins) throws IOException {
 			String pf = _dir == null ? "" : _dir + "\\";
 			in = new BufferedReader(new FileReader(pf + ins));
 		}
 
 		void setFileOut(String outs) throws IOException {
 			String pf = _dir == null ? "" : _dir + "\\";
 			out = new PrintWriter(new FileWriter(pf + outs));
 		}
 
 		void setFileIO(String ins, String outs) throws IOException {
 			out.flush();
 			out.close();
 			in.close();
 
 
 			setFileIn(ins);
 			setFileOut(outs);
 			System.err.println("reading from " + ins);
 		}
 
 		
 		private static int pos, readLen;
 		private static final char[] buffer = new char[1024 * 8];
 		private static char[] str = new char[500*8*2];
 		private static boolean[] isDigit = new boolean[256];
 		private static boolean[] isSpace = new boolean[256];
 		private static boolean[] isLineSep = new boolean[256];
 
 		static {
 			for(int i = 0; i < 10; i++) { isDigit['0' + i] = true; }
 			isDigit['-'] = true;
 			isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
 			isLineSep['\r'] = isLineSep['\n'] = true;
 		}
 
 		public int read() throws IOException {
 			if(pos >= readLen) {
 				pos = 0;
 				readLen = in.read(buffer);
 				if(readLen <= 0) { throw new EndOfFileRuntimeException(); }
 			}
 			return buffer[pos++];
 		}
 
 		public int nextInt() throws IOException {
 			int len = 0;
 			str[len++] = nextChar();
 			len = reads(len, isSpace);
 			
 			int i = 0;
 			int ret = 0;
 			if(str[0] == '-') { i = 1; }
 			for(; i < len; i++) ret = ret * 10 + str[i] - '0';
 			if(str[0] == '-') { ret = -ret; }
 			return ret;
 
 		}
 
 		public long nextLong() throws IOException {
 			int len = 0;
 			str[len++] = nextChar();
 			len = reads(len, isSpace);
 			
 			int i = 0;
 			long ret = 0;
 			if(str[0] == '-') { i = 1; }
 			for(; i < len; i++) ret = ret * 10 + str[i] - '0';
 			if(str[0] == '-') { ret = -ret; }
 			return ret;
 
 		}
 
 		public char nextChar() throws IOException {
 			while(true) {
 				final int c = read();
 				if(!isSpace[c]) { return (char)c; }
 			}
 		}
 		
 		int reads(int len, boolean[] accept) throws IOException {
 			try {
 				while(true) {
 					final int c = read();
 					if(accept[c]) { break; }
 					
 					if(str.length == len) {
 						char[] rep = new char[str.length * 3 / 2];
 						System.arraycopy(str, 0, rep, 0, str.length);
 						str = rep;
 					}
 					
 					str[len++] = (char)c;
 				}
 			}
 			catch(EndOfFileRuntimeException e) { ; }
 			
 			return len;
 		}
 		
 		int reads(char[] cs, int len, boolean[] accept) throws IOException {
 			try {
 				while(true) {
 					final int c = read();
 					if(accept[c]) { break; }
 					cs[len++] = (char)c;
 				}
 			}
 			catch(EndOfFileRuntimeException e) { ; }
 			
 			return len;
 		}
 
 		public char[] nextLine() throws IOException {
 			int len = 0;
 
 			str[len++] = (char)read();
 			len = reads(len, isLineSep);
 			
 			try {
 				if(str[len-1] == '\r') { len--; read(); }
 			}
 			catch(EndOfFileRuntimeException e) { ; }
 			
 			return Arrays.copyOf(str, len);
 		}
 
 		public String nextString() throws IOException {
 			return new String(next());
 		}
 
 		public char[] next() throws IOException {
 			int len = 0;
 			str[len++] = nextChar();
 			len = reads(len, isSpace);
 			return Arrays.copyOf(str, len);
 		}
 		
 		public double nextDouble() throws IOException {
 			return Double.parseDouble(nextString());
 		}
 
 		public long[] nextLongArray(final int n) throws IOException {
 			final long[] res = new long[n];
 			for(int i = 0; i < n; i++) {
 				res[i] = nextLong();
 			}
 			return res;
 		}
 
 		public int[] nextIntArray(final int n) throws IOException {
 			final int[] res = new int[n];
 			for(int i = 0; i < n; i++) {
 				res[i] = nextInt();
 			}
 			return res;
 		}
 
 		public int[][] nextIntArray2D(final int n, final int k) throws IOException {
 			final int[][] res = new int[n][];
 			for(int i = 0; i < n; i++) {
 				res[i] = nextIntArray(k);
 			}
 			return res;
 		}
 
 		public int[][] nextIntArray2DWithIndex(final int n, final int k) throws IOException {
 			final int[][] res = new int[n][k+1];
 			for(int i = 0; i < n; i++) {
 				for(int j = 0; j < k; j++) {
 					res[i][j] = nextInt();
 				}
 				res[i][k] = i;
 			}
 			return res;
 		}
 
 		public double[] nextDoubleArray(final int n) throws IOException {
 			final double[] res = new double[n];
 			for(int i = 0; i < n; i++) {
 				res[i] = nextDouble();
 			}
 			return res;
 		}
 
 	}
 
 }