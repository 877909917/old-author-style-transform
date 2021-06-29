
 
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class B {
 	static Scanner sc = null;
 	static BufferedReader br = null;
 	static PrintWriter out = null;
 	static PrintStream sysout = System.out;
 	static Random rnd = new Random();
 	
 	int INF = Integer.MAX_VALUE / 10;
 	double DF = 0.0000000001;
 	
 	long b = 1;
 	int N = 0;
 	int M = 0;
 	
 
 	
 	public void solve() throws Exception{
 		String s = br.readLine();
 		
 		String[] sp = s.split(" ");
 		int B = Integer.parseInt(sp[0]);
 		int N = Integer.parseInt(sp[1]);
 		s = br.readLine();
 		sp = s.split(" ");
 		int[] d = new int[B];
 		for(int i = 0; i < B; i++){
 			d[i] = Integer.parseInt(sp[i]);
 		}
 		
 		int INT = 1;
 		for(int i = 0; i < B; i++){
 			INT = lcm(INT, d[i]);
 		}
 		int onecy = 0;
 		for(int i = 0; i < B; i++){
 			onecy += INT / d[i];
 		}
 		if(N % onecy > 0){
 			N %= onecy;
 		}
 		else{
 			N = onecy;
 		}
 		int[] st = new int[B];
 		while(N > 1){
 			int midx = 0;
 		
 			for(int i = 1; i < B; i++){
 				if(st[i] < st[midx]){
 					midx = i;
 				}
 			}
 			st[midx] += d[midx];
 			N--;
 		}
 		int midx = 0;
 		
 		for(int i = 1; i < B; i++){
 			if(st[i] < st[midx]){
 				midx = i;
 			}
 		}
 		int ans = midx +1;
 		println(ans);
 	}
 	
 	
 	public static void main(String[] args) throws Exception{
 		File file = new File("B-small-attempt0.in");
 		if(file.exists()){
 			System.setIn(new BufferedInputStream(new FileInputStream(file)));
 		}
 		else{
 			throw new Exception("can't find a input file : " + file.getAbsolutePath());
 		}
 		
 		br = new BufferedReader(new InputStreamReader(System.in));
 		FileWriter fw = new FileWriter(new File("output.txt"));
 		out = new PrintWriter(fw);
 		
 		B b = new B();
 		int T = 0;
 		if(sc != null){
 			T = sc.nextInt();
 		}
 		else{
 			T = parseInt(br.readLine());
 		}
 		int t = 1;
 		while(t <= T){
 			out.print("Case #" + t + ": ");
 			System.out.print("Case #" + t + ": ");
 			b.solve();
 			t++;
 		}
 		out.close();
 		fw.close();
 	}
 	
 		int gcd(int a, int b){
 			if(a < b){
 				int tmp = a;
 				a = b;
 				b=tmp;
 			}
 			if(b == 0) return a;
 			return gcd(b, a%b);
 		}
 		
 		
 		int lcm(int a, int b){
 			if(a == 0 || b == 0){
 				return 0;
 			}
 			if(a < b){
 				int tmp = a;
 				a = b;
 				b=tmp;
 			}
 			return a / gcd(a, b) * b;
 		}
 	void print(int i){
 		out.print(i + "");
 		System.out.print(i);
 	}
 	void println(int i){
 		out.println(i + "");
 		System.out.println(i);
 	}
 	void print(String s){
 		out.print(s);
 		System.out.print(s);
 	}
 	void println(String s){
 		out.println(s);
 		System.out.println(s);
 	}
 	void print(long i){
 		out.print(i + "");
 		System.out.print(i);
 	}
 	void println(long i){
 		out.println(i + "");
 		System.out.println(i);
 	}
 }
