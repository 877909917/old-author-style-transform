import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.*;
 
 public class Main {
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
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
         for (int t = 1; t <= T; t++) {
             String S = nextToken();
             int N = S.length();
             int K = nextInt();
             boolean[] happy = new boolean[N];
             for (int i = 0; i < N; i++) {
                 if (S.charAt(i) == '+') {
                     happy[i] = true;
                 }
             }
 
             
             int flips = 0;
             for (int i = 0; i + K <= N; i++) {
                 if (happy[i]) continue;
                 flips += 1;
                 for (int j = i; j < i + K; j++) {
                     happy[j] = !happy[j];
                 }
             }
 
             boolean done = true;
             for (int i = 0; i < N; i++) {
                 if (!happy[i]) {
                     done = false;
                 }
             }
 
             if (done) {
                 System.out.printf("Case #%d: %d%n", t, flips);
             } else {
                 System.out.printf("Case #%d: IMPOSSIBLE%n", t);
             }
         }
     }
 }
