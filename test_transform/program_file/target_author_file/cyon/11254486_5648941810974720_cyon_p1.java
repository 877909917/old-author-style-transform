package rb;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class p1 {
 
     static final String[] L = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         String in = br.readLine();
         int[] f = new int[26];
         for (int i=0;i<in.length();i++) {
             f[in.charAt(i)-'A']++;
         }
         int c[] = new int[10];
 
         while(f['Z'-'A'] > 0) {
             dec(L[0], f);
             c[0]++;
         }
 
 
         while(f['X'-'A'] > 0) {
             dec(L[6], f);
             c[6]++;
         }
 
 
         while(f['G'-'A'] > 0) {
             dec(L[8], f);
             c[8]++;
         }
 
         while(f['H'-'A'] > 0) {
             dec(L[3], f);
             c[3]++;
         }
 
 
         while(f['W'-'A'] > 0) {
             dec(L[2], f);
             c[2]++;
         }
 
 
         while(f['U'-'A'] > 0) {
             dec(L[4], f);
             c[4]++;
         }
 
 
         while(f['F'-'A'] > 0) {
             dec(L[5], f);
             c[5]++;
         }
 
 
         while(f['O'-'A'] > 0) {
             dec(L[1], f);
             c[1]++;
         }
 
 
         while(f['V'-'A'] > 0) {
             dec(L[7], f);
             c[7]++;
         }
 
 
         while(f['N'-'A'] > 0) {
             dec(L[9], f);
             c[9]++;
         }
 
         StringBuilder res = new StringBuilder();
         for (int i=0;i<10;i++) {
             while (c[i]>0) {
                 res.append(i);
                 c[i]--;
             }
         }
 
         return res.toString();
     }
 
     private static void dec(String s, int[] f) {
         for (int i=0;i<s.length();i++) {
             f[s.charAt(i)-'A']--;
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
