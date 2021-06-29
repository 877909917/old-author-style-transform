import java.util.*;
 
 public class QDS {
 
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 
 		int t = sc.nextInt();
 		for(int test=1;test<=t;test++){
 			int x = sc.nextInt();
 			int r = sc.nextInt();
 			int c = sc.nextInt();
 			
 			if(r<c){
 				int tmp = c;
 				c = r;
 				r = tmp;
 			}
 			
 			int dif = r*c-x;
 			
 			String ans = "RICHARD";
 			if(dif>=0 && dif%x==0){
 				if(x==1 || x==2){
 					ans = "GABRIEL";
 				}else if(x==3){
 					if(c>1) ans = "GABRIEL";
 				}else if(x==4){
 					if(r==4 && c>2) ans = "GABRIEL";
 				}
 			}
 			
 			System.out.println("case #" + test + ": " + ans);
 		}
 		sc.close();
 	}
 
 }
