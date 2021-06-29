import java.io.*;
 import java.util.*;
 
 public class bilingual2 {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "C-small-attempt0";
 
   private static void main2() throws IOException {
     int N = in.nextInt();
     String[][] s = new String[N][];
     int idx = 0;
     HashMap<String, Integer> hs = new HashMap<>();
     for (int i = 0; i < N; i++) {
       s[i] = in.nextLine().split("\\s+");
       for (String p : s[i]) {
         if (!hs.containsKey(p))
           hs.put(p, idx++);
       }
     }
     int min = 1 << 29;
     for (int mask = 0; mask < 1 << N; mask++) {
       if ((mask&3) != 2) continue;
       int[] q = new int[hs.size()];
       for (int i = 0; i < N; i++) {
         for (String p : s[i]) {
           q[hs.get(p)] |= ((mask >> i) & 1)+1;
         }
       }
       int count = 0;
       for (int x : q) if (x == 3) count++;
       min = Math.min(min, count);
     }
     out.println(min);
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
       System.out.println(test);
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
     
     public String nextLine() throws IOException {
       return reader.readLine();
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
