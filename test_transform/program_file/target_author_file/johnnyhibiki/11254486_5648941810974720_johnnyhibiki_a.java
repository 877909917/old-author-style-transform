import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class A {
 	
 	public static void main(String[] args){
 		new A().run();
 	}
 	
 	String org;
 	
 	void run(){
 		Scanner sc = new Scanner(System.in);
 		
 		int testNum = sc.nextInt();
 		for(int t=1;t<=testNum;t++){
 			org = sc.next();
 			String ans = fnc();
 			System.out.println("Case #" + t + ": " + ans);
 		}
 	}
 	
 	String fnc(){
 		int[] ans = new int[10];
 		
 		int[] cnt = new int[26];
 		for(int i=0;i<org.length();i++){
 			int p = org.codePointAt(i)-65;
 			cnt[p]++;
 		}
 		
 		
 		ans[0] = cnt[25]; 
 		cnt[4] -= cnt[25]; 
 		cnt[17] -= cnt[25]; 
 		cnt[14] -= cnt[25]; 
 		cnt[25] = 0;
 		
 		
 		ans[2] = cnt[22]; 
 		cnt[19] -= cnt[22]; 
 		cnt[14] -= cnt[22]; 
 		cnt[22] = 0;
 		
 		
 		ans[4] = cnt[20]; 
 		cnt[5] -= cnt[20]; 
 		cnt[14] -= cnt[20]; 
 		cnt[17] -= cnt[20]; 
 		cnt[20] = 0;
 				
 		
 		ans[6] = cnt[23]; 
 		cnt[18] -= cnt[23]; 
 		cnt[8] -= cnt[23]; 
 		cnt[23] = 0;
 		
 		
 		ans[5] = cnt[5];
 		cnt[8] -= cnt[5];
 		cnt[21] -= cnt[5];
 		cnt[4] -= cnt[5];
 		cnt[5] = 0;
 		
 		
 		ans[7] = cnt[21]; 
 		cnt[18] -= cnt[21]; 
 		cnt[4] -= cnt[21]*2;
 		cnt[13] -= cnt[21];
 		cnt[21] = 0;
 		
 		
 		ans[3] = cnt[17];
 		cnt[4] -= cnt[17]*2;
 		cnt[19] -= cnt[17];
 		cnt[7] -= cnt[17];
 		cnt[17] = 0;
 		
 		
 		ans[1] = cnt[14];
 		cnt[13] -= cnt[14];
 		cnt[4] -= cnt[14];
 		cnt[14] = 0;
 		
 		
 		ans[8] = cnt[7];
 		cnt[4] -= cnt[7];
 		cnt[8] -= cnt[7];
 		cnt[6] -= cnt[7];
 		cnt[19] -= cnt[7];
 		cnt[7] = 0;
 		
 		
 		ans[9] = cnt[8];
 		
 		StringBuilder sb = new StringBuilder();
 		for(int i=0;i<10;i++){
 			for(int j=0;j<ans[i];j++){
 				sb.append(i);
 			}
 		}
 		
 		return sb.toString();
 	}
 
 
 	
 }
