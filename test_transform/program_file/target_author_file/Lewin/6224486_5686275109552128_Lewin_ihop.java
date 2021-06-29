import java.io.*;
 import java.util.*;
 
 public class ihop {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "B-small-attempt0";
 
   private static void main2() throws IOException {
     int N = in.nextInt();
     int[] arr = new int[N];
     int max = 0;
     for (int i = 0; i < N; i++) {
       max = Math.max(max, arr[i] = in.nextInt());
     }
     int res = 1 << 29;
     for (int numSpecial = 0; numSpecial < 1000; numSpecial++) {
       int lo = 1, hi = max;
       while (lo < hi) {
         int mid = (lo + hi) >> 1;
         if (possible(arr, numSpecial, mid)) hi = mid;
         else lo = mid + 1;
       }
       res = Math.min(res, numSpecial + lo);
     }
     out.println(res);
   }
   
   public static boolean possible (int[] arr, int n, int t) {
     int left = n;
     for (int i = 0; i < arr.length; i++)
       left -= (arr[i] - 1) / t;
     return left >= 0;
   }
 
   public static void main(String[] args) throws IOException {
     if (SUBMIT) {
       in = new InputReader(new FileInputStream(new File(NAME + ".in")));
       out = new PrintWriter(new BufferedWriter(new FileWriter(NAME + ".out")));
     } else {
       in = new InputReader(System.in);
       out = new PrintWriter(System.out, true);
     }
 
     int numCases = in.nextInt();
     for (int test = 1; test <= numCases; test++) {
       out.print("Case #" + test + ": ");
       main2();
     }
 
     out.close();
     System.exit(0);
   }
 
   static class InputReader {
     public BufferedReader reader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream stream) {
       reader = new BufferedReader(new InputStreamReader(stream), 32768);
       tokenizer = null;
     }
 
     public String next() {
       while (tokenizer == null || !tokenizer.hasMoreTokens()) {
         try {
           tokenizer = new StringTokenizer(reader.readLine());
         } catch (IOException e) {
           throw new RuntimeException(e);
         }
       }
       return tokenizer.nextToken();
     }
 
     public int nextInt() {
       return Integer.parseInt(next());
     }
   }
 }
