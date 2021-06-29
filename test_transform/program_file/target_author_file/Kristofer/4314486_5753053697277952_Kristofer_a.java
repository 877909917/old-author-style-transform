
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class A {
     private static final String INPUT = null; 
 
     public static void main(String[] args) throws Exception {
         new A().run();
     }
 
     private final PrintStream out;
     private final BufferedReader reader;
     private StringTokenizer tokenizer = new StringTokenizer("");
 
     public A() throws Exception {
         String problem = getClass().getSimpleName();
         if (INPUT == null) {
             File input = findInput(problem);
             if (input == null) {
                 throw new IOException("No input file found");
             }
             File output = new File(input.getParent(), input.getName().replace(".in", ".out"));
             System.err.println("input:  " + input.getPath());
             System.err.println("output: " + output.getPath());
             out = new PrintStream(new FileOutputStream(output));
             reader = new BufferedReader(new FileReader(input));
         } else if (INPUT.equals("stdin")) {
             System.err.println("input:  stdin");
             System.err.println("output: stdout");
             out = System.out;
             reader = new BufferedReader(new InputStreamReader(System.in));
         } else {
             System.err.println("input:  " + problem + "-" + INPUT + ".in");
             System.err.println("output: " + problem + "-" + INPUT + ".out");
             out = new PrintStream(new FileOutputStream("source/" + problem + "-" + INPUT + ".out"));
             reader = new BufferedReader(new FileReader("source/" + problem + "-" + INPUT + ".in"));
         }
     }
 
     public static File findInput(String problem) throws Exception {
         File dir = new File("source");
         long bestTimestamp = -1;
         File bestFile = null;
         for (File file : dir.listFiles()) {
            if (file.getName().startsWith(problem + "-") && file.getName().endsWith(".in")) {
                long timestamp = file.lastModified();
                if (timestamp > bestTimestamp) {
                    bestTimestamp = timestamp;
                    bestFile = file;
                }
            }
         }
         return bestFile;
     }
 
     public void run() {
         try {
             runCases();
         } finally {
             out.close();
         }
     }
 
     public void debug(String s, Object... args) {
         System.err.printf("DEBUG: " + s + "\n", args);
     }
 
     private void runCases() {
         try {
             int cases = getInt();
             for (int c = 1; c <= cases; c++) {
                 try {
                     String answer = new Solver(c).solve();
                     String s = "Case #" + c + ": " + answer;
                     out.println(s);
                     if (out != System.out) {
                         System.out.println(s);
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         } finally {
             debug("done with all!");
         }
     }
 
     public String readLine() {
         try {
             return reader.readLine();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
 
     public String getToken() {
         while (true) {
             if (tokenizer.hasMoreTokens()) {
                 return tokenizer.nextToken();
             }
             String s = readLine();
             if (s == null) {
                 return null;
             }
             tokenizer = new StringTokenizer(s, " \t\n\r");
         }
     }
 
     public double getDouble() {
         return Double.parseDouble(getToken());
     }
 
     public int getInt() {
         return Integer.parseInt(getToken());
     }
 
     public long getLong() {
         return Long.parseLong(getToken());
     }
 
     public BigInteger getBigInt() {
         return new BigInteger(getToken());
     }
 
     public BigDecimal getBigDec() {
         return new BigDecimal(getToken());
     }
 
     public class Solver {
         private final int caseNumber;
 
         public Solver(int caseNumber) {
             this.caseNumber = caseNumber;
         }
 
         public String solve() throws Exception {
             debug("solving case %d", caseNumber);
 
             int N = getInt();
 
             int count[] = new int[N];
             int sum = 0;
             for (int i = 0; i < N; i++) {
                 count[i] = getInt();
                 sum += count[i];
             }
 
             StringBuilder sb = new StringBuilder();
 
             while (sum > 0) {
                 boolean found;
                 do {
                     found = false;
                     for (int i = 0; i < N; i++) {
                         int p = count[i];
                         if (p > 0 && sum > 0) {
                             int nextP = p - 1;
                             int nextSum = sum - 1;
                             boolean ok = true;
                             for (int j = 0; j < N; j++) {
                                 if (j != i && 2*count[j] > nextSum) {
                                     ok = false;
                                     break;
                                 }
                             }
                             if (ok && nextP <= nextSum - nextP) {
                                 count[i]--;
                                 sum--;
                                 sb.append(" ");
                                 sb.append((char) ('A' + i));
                                 found = true;
                             }
                         }
                     }
                 } while (found && sum > 0);
 
                 if (!found) {
                     int max1 = 0;
                     for (int i = 1; i < N; i++) {
                         if (count[i] > count[max1]) {
                             max1 = i;
                             break;
                         }
                     }
                     int max2 = -1;
                     for (int i = 0; i < N; i++) {
                         if (i != max1 && count[i] == count[max1]) {
                             max2 = i;
                             break;
                         }
                     }
                     if (max2 == -1) {
                         throw new RuntimeException("Unsolvable");
                     }
                     if (count[max1] > 0 && count[max2] > 0) {
                         count[max1]--;
                         count[max2]--;
 
                         sum -= 2;
 
                         sb.append(" ");
                         sb.append((char) ('A' + (max1)));
                         sb.append((char) ('A' + (max2)));
                     }
                 }
             }
 
             return sb.substring(1);
         }
     }
 }
