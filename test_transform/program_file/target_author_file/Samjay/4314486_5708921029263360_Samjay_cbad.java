package con2016.con2016R1C;
 
 
 import java.io.*;
 import java.util.*;
 
 public class Cbad {
 	
 
 	private static final String fileLoc = "src/con2016/con2016R1C/files/";
 	private static final String fileName = fileLoc+C.class.getSimpleName().toLowerCase();
 	private static final String inputFileName = fileName + ".in";
 	private static final String outputFileName = fileName + ".out";
 	private static InputReader in;
 	private static OutputWriter out;
 	
 	private void solve() {
 		int A = in.readInt(),
 			B = in.readInt(),
 			C = in.readInt(),
 			K = in.readInt();
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		LinkedList<Comb> res = generate(A,B,C,K);
 		
 		out.printLine(res.size());
 		for(Comb comb : res){
 			out.printLine(comb);
 		}
 	}
 	
 	static LinkedList<Comb> generate(int A, int B, int C, int K){
 		LinkedList<Comb> res = new LinkedList<Comb>();
 		
 		LinkedList<Comb> all = new LinkedList<Comb>();
 		for(int a=1;a<=A;a++){
 			for(int b=1;b<=B;b++){
 				for(int c=1;c<=C;c++){
 					all.add(new Comb(a,b,c));
 				}
 			}
 		}
 		att=0;bestlist=null;
 		dfs(all,new int[150], new int[150], new int[150], K, res);
 		return bestlist;
 	}
 	
 	static void solve(LinkedList<Comb> todo, int[] ab, int[] ac, int[] bc, int K, LinkedList<Comb> cur){
 		while(!todo.isEmpty()){
 			Comb c = todo.pollFirst();
 			if(ab[c.ab()] < K
 				&& ac[c.ac()] < K
 				&& bc[c.bc()] < K){
 				ab[c.ab()]++;
 				ac[c.ac()]++;
 				bc[c.bc()]++;
 				cur.addLast(c);
 			}
 		}
 	}
 	
 	static int att = 0;
 	static LinkedList<Comb> bestlist;
 	static void dfs(LinkedList<Comb> todo, int[] ab, int[] ac, int[] bc, int K, LinkedList<Comb> cur){
 		if(todo.isEmpty()){
 			if(bestlist == null
 				|| bestlist.size() < cur.size()){
 				bestlist = new LinkedList<Comb>();
 				bestlist.addAll(cur);
 			}
 			return;
 		}
 		Comb c = todo.pollFirst();
 		if(ab[c.ab()] < K
 			&& ac[c.ac()] < K
 			&& bc[c.bc()] < K){
 			cur.addLast(c);
 			ab[c.ab()]++;
 			ac[c.ac()]++;
 			bc[c.bc()]++;
 			dfs(todo,ab,ac,bc,K,cur);
 			ab[c.ab()]--;
 			ac[c.ac()]--;
 			bc[c.bc()]--;
 			cur.pollLast();
 		}
 		dfs(todo,ab,ac,bc,K,cur);
 		todo.addFirst(c);
 	}
 	
 	static class Comb{
 		final int a, b, c;
 		public Comb(int a, int b, int c) {
 			this.a=a;this.b=b;this.c=c;
 		}
 		@Override
 		public String toString() {
 			return a+" "+b+" "+c;
 		}
 		int ab(){
 			return hash(a,b);
 		}
 		int ac(){
 			return hash(a,c);
 		}
 		int bc(){
 			return hash(b,c);
 		}
 		static int hash(int x, int y){
 			return x*11 + y;
 		}
 	}
 	
 	public static void main(String[] args) throws IOException {
 		long start = System.currentTimeMillis();
 		in = new InputReader(new FileInputStream(inputFileName));
 		out = new OutputWriter(new FileOutputStream(outputFileName));
 		int tests = in.readInt();
 		for (int t = 1; t <= tests; t++) {
 			out.print("Case #" + t + ": ");
 			new Cbad().solve();
 			System.out.println("Case #" + t + ": solved");
 		}
 		out.close();
 		long stop = System.currentTimeMillis();
 		System.out.println(stop-start+" ms");
 	}
 	
 
 	static class InputReader {
 		private InputStream stream;
 		private byte[] buf = new byte[1024];
 		private int curChar;
 		private int numChars;
 
 		public InputReader(InputStream stream) {
 			this.stream = stream;
 		}
 
 		public int read() {
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
 
 		public String readLine() {
 			int c = read();
 			while (isSpaceChar(c))
 				c = read();
 			StringBuilder res = new StringBuilder();
 			do {
 				res.appendCodePoint(c);
 				c = read();
 			} while (!isEndOfLine(c));
 			return res.toString();
 		}
 
 		public String readString() {
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
 
 		public long readLong() {
 			int c = read();
 			while (isSpaceChar(c))
 				c = read();
 			int sgn = 1;
 			if (c == '-') {
 				sgn = -1;
 				c = read();
 			}
 			long res = 0;
 			do {
 				if (c < '0' || c > '9')
 					throw new InputMismatchException();
 				res *= 10;
 				res += c - '0';
 				c = read();
 			} while (!isSpaceChar(c));
 			return res * sgn;
 		}
 
 		public int readInt() {
 			int c = read();
 			while (isSpaceChar(c))
 				c = read();
 			int sgn = 1;
 			if (c == '-') {
 				sgn = -1;
 				c = read();
 			}
 			int res = 0;
 			do {
 				if (c < '0' || c > '9')
 					throw new InputMismatchException();
 				res *= 10;
 				res += c - '0';
 				c = read();
 			} while (!isSpaceChar(c));
 			return res * sgn;
 		}
 
 		public boolean isSpaceChar(int c) {
 			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
 		}
 
 		public boolean isEndOfLine(int c) {
 			return c == '\n' || c == '\r' || c == -1;
 		}
 	}
 
 	static class OutputWriter {
 		private final PrintWriter writer;
 
 		public OutputWriter(OutputStream outputStream) {
 			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
 					outputStream)));
 		}
 
 		public OutputWriter(Writer writer) {
 			this.writer = new PrintWriter(writer);
 		}
 
 		public void print(Object... objects) {
 			for (int i = 0; i < objects.length; i++) {
 				if (i != 0)
 					writer.print(' ');
 				writer.print(objects[i]);
 			}
 		}
 
 		public void printLine(Object... objects) {
 			print(objects);
 			writer.println();
 		}
 
 		public void close() {
 			writer.close();
 		}
 	}
 }
