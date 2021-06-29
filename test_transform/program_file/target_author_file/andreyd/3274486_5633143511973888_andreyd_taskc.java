import java.io.BufferedInputStream;
 import java.text.NumberFormat;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class TaskC {
     
     private static double EPS = 1e-7;
 
     public static void main(String[] args) {
         NumberFormat nf = NumberFormat.getInstance(Locale.US);
         nf.setMinimumFractionDigits(7);
         nf.setMaximumFractionDigits(7);
         nf.setGroupingUsed(false);
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int k = sc.nextInt();
             double u = sc.nextDouble();
             double[] p = new double[n];
             double sum = 0;
             for (int j = 0; j < n; j++) {
                 p[j] = sc.nextDouble();
                 sum += p[j];
             }
             if (n == 1) {
                 print(i, nf.format(p[0] + u));
                 continue;
             }
             if (sum + u >= n - EPS) {
                 print(i, nf.format(1));
                 continue;
             }
             if (u <= EPS) {
                 double prod = 1.;
                 for (int j = 0; j < n; j++) {
                     prod *= p[j];
                 }
                 print(i, nf.format(prod));
                 continue;
             }
             Arrays.sort(p);
             while (u > EPS) {
                 if (p[0] < p[1] - EPS) {
                     double q = Math.min(u, p[1]-p[0]);
                     p[0] += q;
                     u -= q;
                     continue;
                 }
                 int ind = 1;
                 while (ind < n && Math.abs(p[0] - p[ind]) < EPS) {
                     ind++;
                 }
                 double q = Math.min(u / ind, ind < n ? p[ind] - p[0] : Double.MAX_VALUE);
                 for (int j = 0; j < ind; j++) {
                     p[j] += q;
                 }
                 u -= ind * q;
             }
             double prod = 1.;
             for (int j = 0; j < n; j++) {
                 prod *= p[j];
             }
             print(i, nf.format(prod));
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
 }
