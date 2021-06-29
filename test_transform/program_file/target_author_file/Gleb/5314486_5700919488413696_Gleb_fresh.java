import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 
 public class Fresh implements Runnable {
   private static final String NAME = "fresh";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   @Override
   public void run() {
     try {
       
       
       in = new StreamTokenizer(new BufferedReader(new FileReader(new File(NAME + ".in"))));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int n = nextInt();
         int p = nextInt();
         int[] d = new int[p];
         for (int i = 0; i < n; i++) {
           d[nextInt() % p]++;
         }
 
         int res = -1;
 
         if (p == 2) {
           res = d[0] + (d[1] + 1) / 2;
         }
 
         if (p == 3) {
           res = d[0] + Math.min(d[1], d[2]) + (Math.max(d[1], d[2]) - Math.min(d[1], d[2]) + 2) / 3;
         }
 
         out.println("Case #" + test + ": " + res);
       }
 
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Fresh()).start();
   }
 }