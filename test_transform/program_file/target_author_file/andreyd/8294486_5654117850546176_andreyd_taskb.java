import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 public class TaskB {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int r = sc.nextInt();
             int o = sc.nextInt();
             int y = sc.nextInt();
             int g = sc.nextInt();
             int b = sc.nextInt();
             int v = sc.nextInt();
             if (r + y < b || y + b < r || b + r < y) {
                 print(i, "IMPOSSIBLE");
                 continue;
             }
             char[] c = new char[] { 'R', 'Y', 'B'};
             int[] f = new int[] {r, y, b};
             StringBuilder sb = new StringBuilder(n);
             while (n > 1) {
                 int max = maxInd(f, -1);
                 int second = maxInd(f, max);
                 sb.append(c[max]).append(c[second]);
                 n -= 2;
                 f[max]--;
                 f[second]--;
             }
             if (n == 1) {
                 sb.append(c[maxInd(f, -1)]);
             }
             if (sb.charAt(0) == sb.charAt(sb.length() - 1)) {
                 n = sb.length();
                 char l = sb.charAt(n - 1);
                 sb.setCharAt(n - 1, sb.charAt(n-2));
                 sb.setCharAt(n-2, l);
             }
             print(i, sb.toString());
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static int maxInd(int[] f, int exc) {
         int maxInd = exc == 0 ? 1 : 0;
         for (int i = 1; i < f.length; i++) {
             if (i == exc) {
                 continue;
             }
             if (f[i] > f[maxInd]) {
                 maxInd = i;
             }
         }
         return maxInd;
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
 
 }
