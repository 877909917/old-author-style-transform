import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 
 
 import static java.lang.Math.*; 
 import static java.util.Arrays.*;
 import static java.lang.Integer.*;
 import static java.lang.Character.*;  
 
 
 public class B {
 	
 	Scanner scan;
 	public B(Scanner s) {
 		this.scan = s;
 	}
 
 	public String solve() {
 		int D = scan.nextInt();
 		int[]a = new int[D];
 		for(int i=0;i<D;i++)a[i]= scan.nextInt();
 		int res = 1000;
 		for(int p = 1;p<=1000;p++) {
 			int r = p;
 			for(int x:a){
 				if(x>p)r += (x+p-1)/p - 1;
 			}
 			res = Math.min(res,r);
 		}
 		return ""+res;
 	}
 	int intLine(){
 		return parseInt(scan.nextLine());
 	}
 	public static void main(String[] args) throws Exception{
 		Locale.setDefault(Locale.US);
 		String cn = B.class.getName();
 		String sampleName = cn+"-sample.in";
 		String smallName = cn+"-small-attempt0.in";
 		String largeName = cn+"-large.in";
 		String name = smallName;
 		String outName = name.substring(0, name.indexOf('.'))+".out";
 		InputStream in = new BufferedInputStream(new FileInputStream(name));
 		PrintStream out = new PrintStream(new File(outName));
 		
 		Scanner scan = new Scanner(in);
 		
 		int N = parseInt(scan.nextLine());
 		for(int c=1;c<=N;c++) {
 			String res = new B(scan).solve();
 			out.printf("Case #%d: %s\n",c, res);
 			System.err.println(c + " done");
 		}
 		
 		System.err.println("All done");
 		
 	}
 
 }
 
 
