import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.*;
 
 public class A {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
     public static double EPS = 0.000001;
 
     public static void print(Object x) {
         System.out.println(x + "");
     }
     public static String join(Collection<?> x, String space) {
         if (x.size() == 0) return "";
         StringBuilder sb = new StringBuilder();
         boolean first = true;
         for (Object elt : x) {
             if (first) first = false;
             else sb.append(space);
             sb.append(elt);
         }
         return sb.toString();
     }
 
     public static String nextToken() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             String line = br.readLine();
             st = new StringTokenizer(line.trim());
         }
         return st.nextToken();
     }
     public static int nextInt() throws IOException {
         return Integer.parseInt(nextToken());
     }
     public static long nextLong() throws IOException {
         return Long.parseLong(nextToken());
     }
     public static double nextDouble() throws IOException {
         return Double.parseDouble(nextToken());
     }
 
     public static int key(int one, int two, int three, int mod) {
         return one + two * 105 + three * 105 * 105;
     }
 
     public static int solve(int one, int two, int three, int mod) {
         
         int k = key(one, two, three, mod);
         if (dp[k] == -1) {
             int best = -10;
             if (one > 0) {
                 best = Math.max(best, solve(one - 1, two, three, (mod + P - 1) % P));
             }
             if (two > 0) {
                 best = Math.max(best, solve(one, two - 1, three, (mod + P - 2) % P));
             }
             if (three > 0) {
                 best = Math.max(best, solve(one, two, three - 1, (mod + P - 3) % P));
             }
             if (mod == 0) {
                 best += 1; 
             }
             dp[k] = best;
         }
         return dp[k];
     }
 
     public static int[] dp;
     public static int P;
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             int N = nextInt();
             P = nextInt();
             int[] count = new int[4];
             for (int i = 0; i < N; i++) {
                 int G = nextInt();
                 count[G % P] += 1;
             }
 
             int fresh = count[0]; 
 
             dp = new int[105*105*105]; 
             for (int i = 0; i < dp.length; i++) {
                 dp[i] = -1;
             }
             dp[key(0, 0, 0, 0)] = 0;
             dp[key(0, 0, 0, 1)] = 0;
             dp[key(0, 0, 0, 2)] = 0;
             dp[key(0, 0, 0, 3)] = 0;
 
             fresh += solve(count[1], count[2], count[3], 0);
 
             System.out.printf("Case #%d: %d%n", t, fresh);
         }
     }
 }
