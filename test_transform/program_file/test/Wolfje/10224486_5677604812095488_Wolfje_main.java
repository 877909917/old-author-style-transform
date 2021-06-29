import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "B-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified())
                     toRun = candidate;
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("b.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskB solver = new TaskB();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             int n = in.nextInt();
             int k = in.nextInt();
             double[] p = new double[n];
             double[] q = new double[k];
             double[][] dp = new double[k][k + 1];
             for (int i = 0; i < n; i++) p[i] = in.nextDouble();
             double answer = 0.0;
             for (int mask = 0; mask < (1 << n); mask++) {
                 if (Integer.bitCount(mask) != k) continue;
 
                 int index = 0;
                 for (int i = 0; i < n; i++) {
                     if (((1 << i) & mask) == 0) continue;
                     q[index++] = p[i];
                 }
 
 
                 dp[0][0] = 1 - q[0];
                 dp[0][1] = q[0];
                 for (int i = 1; i < k; i++) {
                     dp[i][0] = dp[i - 1][0] * (1 - q[i]);
                     for (int votes = 1; votes <= k; votes++) {
                         dp[i][votes] = dp[i - 1][votes] * (1 - q[i]) + dp[i - 1][votes - 1] * q[i];
                     }
                 }
 
                 answer = Math.max(answer, dp[k - 1][k / 2]);
             }
             out.printf("Case #%d: %f\n", testNumber, answer);
         }
 
     }
 }
 
