import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class RedTape implements Runnable {
   private static final String NAME = "tape";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   double p(double[] x) {
     int n = x.length;
     double[][] r = new double[n + 1][n + 1];
     r[0][0] = 1;
     for (int nn = 1; nn <= n; nn++) {
       for (int k = 0; k <= nn; k++) {
         r[nn][k] = (1 - x[nn - 1]) * r[nn - 1][k];
         if (k > 0) r[nn][k] += x[nn - 1] * r[nn - 1][k - 1];
       }
     }
     return r[n][n / 2];
   }
 
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int n = in.nextInt();
         int k = in.nextInt();
         double[] p = new double[n];
         for (int i = 0; i < n; i++) {
           p[i] = in.nextDouble();
         }
         Arrays.sort(p);
         double res = 0;
         for (int i = 0; i <= k; i++) {
           double[] x = new double[k];
           int xc = 0;
           for (int j = 0; j < i; j++) x[xc++] = p[j];
           for (int j = 0; j < k - i; j++) x[xc++] = p[n - j - 1];
           res = Math.max(res, p(x));
         }
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new RedTape()).start();
   }
 }
