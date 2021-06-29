
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class Bsmall {
 
     private String solveTest() throws IOException {
         int n = nextInt();
         int k = nextInt();
         double[] p = new double[n];
         for (int i = 0; i < n; i++) p[i] = Double.parseDouble(next());
         double res = 0;
         for (int m = 0; m < (1 << n); m++) {
             if (Integer.bitCount(m) == k) {
                 double[] d = new double[k + 1];
                 d[0] = 1;
                 for (int j = 0; j < n; j++) if ((m & (1 << j)) > 0) {
                     for (int q = k; q >= 0; q--) {
                         d[q] = d[q] * (1 - p[j]) + (q == 0 ? 0 : d[q - 1] * p[j]);
                     }
                 }
                 res = Math.max(res, d[k / 2]);
             }
         }
         return "" + res;
     }
 
     private void solve() throws IOException {
         int n = nextInt();
         for (int i = 0; i < n; i++) {
             String res = solveTest();
             System.out.println("Case #" + (i + 1) + ": " + res);
             out.println("Case #" + (i + 1) + ": " + res);
         }
     }
 
 
     BufferedReader br;
     StringTokenizer st;
     PrintWriter out;
 
     String next() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             st = new StringTokenizer(br.readLine());
         }
         return st.nextToken();
     }
 
     int nextInt() throws IOException {
         return Integer.parseInt(next());
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         new Bsmall().run();
     }
 
     private void run() throws FileNotFoundException {
         br = new BufferedReader(new FileReader(this.getClass().getSimpleName().substring(0, 1) + ".in"));
         out = new PrintWriter(this.getClass().getSimpleName().substring(0, 1) + ".out");
         try {
             solve();
         } catch (IOException e) {
             e.printStackTrace();
         }
         out.close();
     }
 
 }