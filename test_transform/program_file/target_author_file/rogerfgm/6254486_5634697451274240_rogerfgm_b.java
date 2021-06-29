
 
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
 
 	
 
 	
 	public void solve() throws Exception{
 		String s = br.readLine();
 		boolean f = false;
 		char[] cs = s.toCharArray();
 		char P = '+';
 		char M = '-';
 		int idx = cs.length-1;
 		int ans = 0;
 		while(idx >= 0){
 			if( (f && cs[idx] == P) || (!f && cs[idx] == M)){
 				f = !f;
 				ans++;
 			}
 			else{
 				while(idx > 0 && cs[idx] == cs[idx-1]){
 					idx--;
 				}
 				idx--;
 			}
 		}
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
