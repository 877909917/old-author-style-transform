import java.io.*;
 import java.util.*;
 
 public class PancakesRevenge {
 
     public void solve() {
         String s = in.nextToken();
         int ans = 0;
         boolean flip = false;
         for (int i = s.length() - 1; i >= 0; i--) {
             if ((s.charAt(i) == '+') == (flip)) {
                 ans++;
                 flip ^= true;
             }
         }
         out.println(ans);
     }
 
     public void run() {
         try {
             in = new FastScanner("input.txt");
             out = new PrintWriter("output.txt");
             int tests = in.nextInt();
             for (int i = 1; i <= tests; i++) {
                 out.printf("Case #%d: ", i);
                 solve();
             }
             out.close();
         } catch (FileNotFoundException e) {
         }
     }
 
     FastScanner in;
     PrintWriter out;
 
     class FastScanner {
         BufferedReader br;
         StringTokenizer st;
 
         public FastScanner(String fileName) {
             try {
                 br = new BufferedReader(new FileReader(fileName));
             } catch (FileNotFoundException e) {
             }
         }
 
         String nextToken() {
             while (st == null || !st.hasMoreElements()) {
                 try {
                     st = new StringTokenizer(br.readLine());
                 } catch (IOException e) {
                 }
             }
             return st.nextToken();
         }
 
         int nextInt() {
             return Integer.parseInt(nextToken());
         }
 
         long nextLong() {
             return Long.parseLong(nextToken());
         }
 
         double nextDouble() {
             return Double.parseDouble(nextToken());
         }
     }
 
     public static void main(String[] args) {
         new PancakesRevenge().run();
     }
 }
