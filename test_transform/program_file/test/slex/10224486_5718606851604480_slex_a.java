import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class A {
 	Scanner scan;
 
 	public A(Scanner s) {
 		scan = s;
 	}
 	
 	int intLine(){
 		return Integer.parseInt(scan.nextLine());
 	}
 	
 	char get(char a, char b){
 		char aa = (char)min(a,b);
 		char bb = (char)max(b,a);
 		if(aa=='P'&&bb=='S')return 'S';
 		if(aa=='P'&&bb=='R')return 'P';
 		return 'R';
 	}
 	
 	boolean test(char[]A) {
 		if(A.length==1)return true;
 		char[]B = new char[A.length/2];
 		for(int i=0;i<A.length;i+=2){
 			if(A[i]==A[i+1])return false;
 			B[i/2] = get(A[i],A[i+1]);
 		}
 		return test(B);
 	}
 	
 	String res="Z";
 	void doit(int r, int p, int s, int idx, char[]A){
 		if(idx==A.length){
 			if(test(A)){
 				String a = new String(A);
 				if(a.compareTo(res)<0)res=a;
 			}
 		}else {
 			if(r>0){
 				A[idx]='R';
 				doit(r-1,p,s,idx+1,A);
 			}
 			if(p>0){
 				A[idx]='P';
 				doit(r,p-1,s,idx+1,A);
 			}
 			if(s>0){
 				A[idx]='S';
 				doit(r,p,s-1,idx+1,A);
 			}
 		}
 	}
 	
 	public String solve() {
 		int n = scan.nextInt();
 		int R = scan.nextInt();
 		int P = scan.nextInt();
 		int S = scan.nextInt();
 		doit(R,P,S,0, new char[R+P+S]);
 		if(res.equals("Z"))return "IMPOSSIBLE";
 		return res;
 	}
 	
 	
 	public static void main(String[] args) throws Exception{
 		Locale.setDefault(Locale.US);
 		String cn = "src/"+A.class.getName();
 		String sampleName = cn+"-sample.in";
 		String smallName = cn+"-small-attempt0.in";
 		String largeName = cn+"-large.in";
 		
 		String name = smallName;
 		
 		String outName = name.substring(0, name.indexOf('.'))+".out";
 		InputStream in = new BufferedInputStream(new FileInputStream(name));
 		PrintStream out =null;
 		out = new PrintStream(new File(outName));
 		if(out==null) out = System.out;
 		Scanner scan = new Scanner(in);
 		
 		int N = Integer.parseInt(scan.nextLine());
 		for(int c=1;c<=N;c++) {
 			String res = new A(scan).solve();
 			out.printf("Case #%d: %s\n",c, res);
 			if(out!=System.out)System.err.println(c + " done");
 		}
 		if(out!=System.out)System.err.println("All done");
 		
 	}
 }
