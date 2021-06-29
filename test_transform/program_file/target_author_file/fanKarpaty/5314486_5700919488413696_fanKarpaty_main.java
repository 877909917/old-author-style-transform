import java.io.*;
 import java.util.Scanner;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         System.setOut(new PrintStream(new File("output.txt")));
         Scanner in = new Scanner(new FileInputStream(new File("input.txt")));
 
         int T = in.nextInt();
 
         for (int t = 1; t <= T; t++) {
             int n = in.nextInt();
             int p = in.nextInt();
 
             int[] count = new int[p];
             for (int i = 0; i < n; i++) {
                 int x = in.nextInt();
                 count[x % p]++;
             }
 
             int ret = count[0];
             if (p == 2) {
                 ret += count[1] / 2 + (count[1] % 2);
             } else if (p == 3) {
                 ret += Math.min(count[1], count[2]);
                 int tmp = Math.max(count[1], count[2]) - Math.min(count[1], count[2]);
                 ret += tmp / 3;
                 if (tmp % 3 != 0)ret++;
             } else {
                 ret += Math.min(count[1], count[3]);
                 int tmp = Math.max(count[1], count[3]) - Math.min(count[1], count[3]);
                 ret += count[2] / 2;
                 if (count[2] % 2 == 1) {
                     ret++;
                     tmp-=2;
                 }
                 if (tmp > 0) {
                     ret += tmp / 4;
                     if (tmp % 4 != 0)ret++;
                 }
             }
 
             System.out.println("Case #" + t + ": " + ret);
 
         }
     }
 
 }
