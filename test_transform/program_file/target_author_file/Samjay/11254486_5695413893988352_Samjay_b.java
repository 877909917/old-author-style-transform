package con2016.con2016R1B;
 
 
 import java.io.*;
 import java.math.BigInteger;
 import java.util.*;
 
 public class B {
 	
 
 	private static final String fileLoc = "src/con2016/con2016R1B/files/";
 	private static final String fileName = fileLoc+B.class.getSimpleName().toLowerCase();
 	private static final String inputFileName = fileName + ".in";
 	private static final String outputFileName = fileName + ".out";
 	private static InputReader in;
 	private static OutputWriter out;
 	
 	private void solve() {
 		
 		String C = in.readString(),
 			J = in.readString();
 		int[] code = new int[C.length()],
 			jam = new int[J.length()];			
 		for(int i=0;i<C.length();i++){
 			code[i] = (C.charAt(i)=='?' ? -1 : C.charAt(i)-'0');
 			jam[i] = (J.charAt(i)=='?' ? -1 : J.charAt(i)-'0');
 		}
 		
 		int start = code.length;
 		for(int i=0;i<code.length;i++){
 			if(code[i]>=0 && jam[i]>=0 && code[i] != jam[i]){
 				start = i;
 				break;
 			}
 		}
 		if(start < code.length){
 			boolean codeWins = code[start] > jam[start];
 			if(start > 0){
 				if(code[start-1]==-1){
 					if(codeWins){
 						if(jam[start-1]==-1){
 							calcBest(code, jam, start, 0, 0, 0, 1, codeWins);
 						}else{
 							calcBest(code, jam, start, jam[start-1]-1, jam[start-1], jam[start-1], jam[start-1], codeWins);
 						}
 					}else{
 						if(jam[start-1]==-1){
 							calcBest(code, jam, start, 0, 1, 0, 0, codeWins);
 						}else{
 							calcBest(code, jam, start, jam[start-1], jam[start-1]+1, jam[start-1], jam[start-1], codeWins);
 						}
 					}
 				}else if(jam[start-1]==-1){
 					if(codeWins){
 						calcBest(code, jam, start, code[start-1], code[start-1], code[start-1], code[start-1]+1, codeWins);
 					}else{
 						calcBest(code, jam, start, code[start-1], code[start-1], code[start-1]-1, code[start-1], codeWins);
 					}
 				}
 			}else{
 				for(int i=start;i<code.length;i++){
 					if(code[i]>=0 && jam[i]>=0)continue;
 					if(codeWins){
 						if(code[i]==-1){
 							code[i]=0;
 						}
 						if(jam[i]==-1){
 							jam[i]=9;
 						}
 					}else{
 						if(code[i]==-1){
 							code[i]=9;
 						}
 						if(jam[i]==-1){
 							jam[i]=0;
 						}
 					}
 				}
 			}
 		}
 		
 		for(int i=start-1;i>=0;i--){
 			if(code[i]==-1 && jam[i]==-1){
 				code[i] = jam[i] = 0;					
 			}else if(code[i]==-1){
 				code[i] = jam[i];
 			}else if(jam[i]==-1){
 				jam[i]= code[i];
 			}
 		}
 		
 		for(int n : code){
 			out.print(n);
 		}
 		out.print(" ");
 		for(int n : jam){
 			out.print(n);
 		}
 		out.printLine();
 	}
 	
 	static void calcBest(int[]code, int[]jam, int start, int cmin, int cmax, int jmin, int jmax, boolean codeWins){
 		int bestC = -1,
 			bestJ = -1;
 		BigInteger bestDiff = null,
 				bestCi = null,
 				bestJi = null;
 		for(int c=cmin;c<=cmax;c++){
 			for(int j=jmin;j<=jmax;j++){
 				if(c==10 && !pos(code,jam,start-2))continue;
 				if(j==10 && !pos(jam,code,start-2))continue;
 				if(c==-1 && !posNeg(code,jam,start-2))continue;
 				if(j==-1 && !posNeg(jam,code,start-2))continue;
 				code[start-1] = c;
 				jam[start-1] = j;
 				BigInteger ci = BigInteger.valueOf(c),
 						ji = BigInteger.valueOf(j);
 				for(int i=start;i<code.length;i++){
 					ci = ci.multiply(BigInteger.TEN);
 					ji = ji.multiply(BigInteger.TEN);
 					if(codeWins ? c >= j : j < c){
 						ci = ci.add(BigInteger.valueOf(code[i]==-1 ? 0 : code[i]));
 						ji = ji.add(BigInteger.valueOf(jam[i]==-1 ? 9 : jam[i]));						
 					}else{
 						ci = ci.add(BigInteger.valueOf(code[i]==-1 ? 9 : code[i]));
 						ji = ji.add(BigInteger.valueOf(jam[i]==-1 ? 0 : jam[i]));						
 					}
 				}
 
 
 				BigInteger diff = ji.subtract(ci).abs();
 				if(isBetter(bestDiff, bestCi, bestJi, diff, ci, ji)){
 					bestDiff = diff;
 					bestCi = ci;
 					bestJi = ji;
 					bestC = c;
 					bestJ = j;
 				}
 			}
 		}
 		if(bestC == 10){
 			for(int i=start-2;i>=0;i--){
 				if(code[i]==-1){
 					if(jam[i]==9){
 						code[i]=0;
 					}else{
 						if(jam[i]==-1){
 							jam[i]=0;
 						}
 						code[i]=jam[i]+1;
 						break;						
 					}
 				}else if(jam[i]==-1){
 					if(code[i]==0){
 						jam[i]=0;
 					}else{
 						jam[i]=code[i]-1;
 						break;						
 					}
 				}
 			}
 			bestC = 0;
 		}else if(bestC == -1){
 			for(int i=start-2;i>=0;i--){
 				if(code[i]==-1){
 					if(jam[i]==0){
 						code[i]=9;
 					}else{
 						if(jam[i]==-1){
 							jam[i]=1;
 						}
 						code[i]=jam[i]-1;
 						break;			
 					}
 				}else if(jam[i]==-1){
 					if(code[i]==9){
 						jam[i]=9;
 					}else{
 						jam[i]=code[i]+1;
 						break;			
 					}
 				}
 			}
 			bestC = 9;			
 		}
 		code[start-1] = bestC;
 		if(bestJ == 10){
 			for(int i=start-2;i>=0;i--){
 				if(jam[i]==-1){
 					if(code[i]==9){
 						jam[i]=9;
 					}else{
 						if(code[i]==-1){
 							code[i]=0;
 						}
 						jam[i]=code[i]+1;
 						break;						
 					}
 				}else if(code[i]==-1){
 					if(jam[i]!=0)throw new RuntimeException("impossibru");
 					if(jam[i]==0){
 						code[i]=0;
 					}else{
 						code[i]=jam[i]-1;
 						break;						
 					}
 				}
 			}
 			bestJ = 0;
 		}else if(bestJ == -1){
 			for(int i=start-2;i>=0;i--){
 				if(jam[i]==-1){
 					if(code[i]==0){
 						jam[i]=9;
 					}else{
 						if(code[i]==-1){
 							code[i]=1;
 						}
 						jam[i]=code[i]-1;
 						break;			
 					}
 				}else if(code[i]==-1){
 					if(jam[i]==9){
 						code[i]=9;
 					}else{
 						code[i]=jam[i]+1;
 						break;			
 					}
 				}
 			}
 			bestJ = 9;			
 		}
 		jam[start-1]=bestJ;
 		for(int i=start;i<code.length;i++){
 			if(code[i]>=0 && jam[i]>=0)continue;
 			if(codeWins ? bestC >= bestJ : bestJ < bestC){
 				if(code[i]==-1){
 					code[i]=0;
 				}
 				if(jam[i]==-1){
 					jam[i]=9;
 				}
 			}else{
 				if(code[i]==-1){
 					code[i]=9;
 				}
 				if(jam[i]==-1){
 					jam[i]=0;
 				}
 			}
 		}		
 	}
 	static boolean pos(int[]arr, int[]oth, int start){
 		while(start>=0){
 			if(arr[start]==-1 && oth[start]!=9){ 
 				return true;
 			}
 			if(arr[start]>0 && oth[start]==-1){ 
 				return true;
 			}
 			if(arr[start] < 9
 				&& arr[start] >= 0
 				&& oth[start] < 9
 				&& oth[start] >=0
 			){
 				return false; 
 			}
 			start--;
 		}
 		return false;
 	}
 	static boolean posNeg(int[]arr, int[]oth, int start){
 		while(start>=0){
 			if(arr[start]==-1 && oth[start]!=0){ 
 				return true;
 			}
 			if(arr[start] < 9 && oth[start]==-1){ 
 				return true;
 			}
 			if(arr[start] > 0
 				&& oth[start] > 0
 			){
 				return false; 
 			}
 			start--;
 		}
 		return false;
 	}
 	public static boolean isBetter(BigInteger bestDiff, BigInteger bestC, BigInteger bestJ, BigInteger diff, BigInteger c, BigInteger j){
 		if(bestDiff == null)return true;
 		System.err.println(bestDiff+" vs "+diff);
 		System.err.println(bestC+" vs "+c);
 		System.err.println(bestJ+" vs "+j);
 		int cdiff = bestDiff.compareTo(diff);
 		if(cdiff != 0){
 			return cdiff > 0;
 		}
 		cdiff = bestC.compareTo(c);
 		if(cdiff!=0){
 			return cdiff > 0;
 		}
 		cdiff = bestJ.compareTo(j);
 		if(cdiff!=0){
 			return cdiff > 0;
 		}
 		System.err.println(bestDiff+" vs "+diff);
 		System.err.println(bestC+" vs "+c);
 		System.err.println(bestJ+" vs "+j);
 		throw new RuntimeException("impossible");
 	}
 	
 	public static void main(String[] args) throws IOException {
 		long start = System.currentTimeMillis();
 		in = new InputReader(new FileInputStream(inputFileName));
 		out = new OutputWriter(new FileOutputStream(outputFileName));
 		int tests = in.readInt();
 		for (int t = 1; t <= tests; t++) {
 			out.print("Case #" + t + ": ");
 			new B().solve();
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
