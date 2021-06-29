import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int n = in.nextInt();
             int p = in.nextInt();
 
             int[] count = new int[p];
 
             for (int i = 0; i < n; i++) {
                 int g = in.nextInt();
                 count[g % p]++;
             }
 
             int result = count[0];
             if (p == 2) {
                 int t = count[1] / 2;
                 result += t;
                 if (count[1] - t * 2 > 0) {
                     result++;
                 }
             } else if (p == 3) {
                 int t = Math.min(count[1], count[2]);
                 result += t;
 
                 int t1 = (count[1] - t) / 3;
                 result += t1;
 
                 int t2 = (count[2] - t) / 3;
                 result += t2;
 
                 if (count[1] - t  - t1 * 3 > 0) {
                     result++;
                 }
 
                 if (count[2] - t  - t2 * 3 > 0) {
                     result++;
                 }
             }
 
             out.printf("Case #%d: %d\n", test, result);
         }
     }
 }
