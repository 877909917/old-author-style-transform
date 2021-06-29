import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class D {
 	Scanner scan;
 
 	public D(Scanner s) {
 		scan = s;
 	}
 	
 	int intLine(){
 		return Integer.parseInt(scan.nextLine());
 	}
 	
 	int res =0;
 
 	int n;
 
 	
 
 	
 	
 	boolean doit(boolean[][]M, boolean[]work, boolean[]mach, int left){
 		if(left==0)return true;
 		for(int w=0;w<n;w++){
 			if(work[w])continue;
 			boolean found = false;
 			for(int m=0;m<n;m++){
 				if(mach[m])continue;
 				if(!M[w][m])continue;
 				boolean good;
 				work[w]=true;
 				mach[m]=true;
 				good =doit(M,work,mach,left-1);
 				work[w]=false;
 				mach[m]=false;
 				if(!good)return false;
 				
 				found = true;
 			}
 			if(!found)return false;
 		}
 		return true;
 	}
 	
 	public String solve() {
 		n = intLine();
 		boolean[][]M = new boolean[n][n];
 		for(int i=0;i<n;i++){
 			String s = scan.nextLine();
 			for(int j=0;j<s.length();j++)M[i][j]=s.charAt(j)=='1';
 		}
 		res = 1000;
 		for(int m =0;m<(1<<(n*n));m++){
 			boolean[][]NM = new boolean[n][n];
 			int idx=0;
 			int r =0;
 			boolean ok = true;
 			for(int i=0;i<n;i++)for(int j=0;j<n;j++){
 				if((m&(1<<idx))==0){
 					if(M[i][j]){ok = false;break;}
 				} else{
 					if(!M[i][j])r++;
 					NM[i][j]=true;
 				}
 			
 				idx++;
 			}
 			if(ok && r<res && doit(NM,new boolean[n], new boolean[n], n)) {
 				res=r;
 			}
 		}
 
 		
 		return ""+res;
 	}
 	
 	
 	public static void main(String[] args) throws Exception{
 		Locale.setDefault(Locale.US);
 		String cn = "src/"+D.class.getName();
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
 			String res = new D(scan).solve();
 			out.printf("Case #%d: %s\n",c, res);
 			if(out!=System.out)System.err.println(c + " done");
 		}
 		if(out!=System.out)System.err.println("All done");
 		
 	}
 }
