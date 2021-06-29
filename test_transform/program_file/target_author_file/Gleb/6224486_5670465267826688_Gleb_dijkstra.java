import java.io.*;
 import java.util.Scanner;
 
 public class Dijkstra implements Runnable {
   private static final String NAME = "dijkstra";
 
   int mul(int a, int b) {
     if (a >= 4) {
       return inv(mul(inv(a), b));
     }
     if (b >= 4) {
       return inv(mul(a, inv(b)));
     }
     if (a == 0) {
       return b;
     }
     if (b == 0) {
       return a;
     }
 
     if (a == 1) {
       if (b == 1) return inv(0);
       if (b == 2) return 3;
       if (b == 3) return inv(2);
     }
     if (a == 2) {
       if (b == 1) return inv(3);
       if (b == 2) return inv(0);
       if (b == 3) return 1;
     }
     if (a == 3) {
       if (b == 1) return 2;
       if (b == 2) return inv(1);
       if (b == 3) return inv(0);
     }
 
     throw new IllegalStateException();
   }
 
   int inv(int a) {
     if (a >= 4) {
       return a - 4;
     } else {
       return a + 4;
     }
   }
 
   @Override
   public void run() {
     try {
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String[] ss = in.readLine().split(" ");
         int l = Integer.parseInt(ss[0]);
         int x = Integer.parseInt(ss[1]);
         String s = in.readLine();
         StringBuilder b = new StringBuilder();
         for (int i = 0; i < x; i++) {
           b.append(s);
         }
         s = b.toString();
         int[] p = new int[s.length()];
         for (int i = 0; i < p.length; i++) {
           if (s.charAt(i) == 'i') p[i] = 1;
           if (s.charAt(i) == 'j') p[i] = 2;
           if (s.charAt(i) == 'k') p[i] = 3;
         }
         int r = 0;
         int cur = 0;
         boolean found = true;
         while (cur < p.length && r != 1) {
           r = mul(r, p[cur]);
           cur++;
         }
         if (r != 1) {
           found = false;
         }
         r = 0;
         while (cur < p.length && r != 2) {
           r = mul(r, p[cur]);
           cur++;
         }
         if (r != 2) {
           found = false;
         }
         r = 0;
         for (int i = cur; i < p.length; i++) {
           r = mul(r, p[i]);
         }
         if (r != 3) {
           found = false;
         }
         String res = found ? "YES" : "NO";
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Dijkstra()).start();
   }
 }
