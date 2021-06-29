import java.util.*;
 
 public class A {
 	
 	public static void main(String[] args){
 		new A().run();
 	}
 	
 	void run(){
 		Scanner sc = new Scanner(System.in);
 		
 		int testNum = sc.nextInt();
 		for(int t=1;t<=testNum;t++){
 			int n = sc.nextInt();
 			String ans = "INSOMNIA";
 			if(n!=0){
 				ans = fnc(n);
 			}
 			System.out.println("Case #" + t + ": " + ans);
 		}
 	}
 	
 	String fnc(int n){
 		String ans = "";
 		
 		boolean[] f = new boolean[10];
 		for(int i=1;;i++){
 			int tmp = n*i;
 			while(true){
 				int a = tmp % 10;
 				f[a] = true;
 				tmp /= 10;
 				if(tmp==0){
 					break;
 				}
 			}
 			
 			if(check(f)){
 				ans = Integer.toString(n*i);
 				break;
 			}
 		}
 		
 		return ans;
 	}
 	
 	boolean check(boolean[] f){
 		for(int i=0;i<10;i++){
 			if(!f[i]){
 				return false;
 			}
 		}
 		return true;
 	}
 	
 }
