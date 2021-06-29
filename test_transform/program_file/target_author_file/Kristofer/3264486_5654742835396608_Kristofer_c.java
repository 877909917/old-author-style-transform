
 import com.google.common.collect.Maps;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Set;
 import java.util.SortedSet;
 import java.util.StringTokenizer;
 import java.util.TreeMap;
 import java.util.TreeSet;
 import java.util.concurrent.atomic.AtomicLong;
 
 public class C {
     private static final String INPUT = null; 
 
     public static void main(String[] args) throws Exception {
         new C().run();
     }
 
     private final PrintStream out;
     private final BufferedReader reader;
     private StringTokenizer tokenizer = new StringTokenizer("");
 
     public C() throws Exception {
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
             long N = getLong();
             long K = getLong();
 
             TreeMap<Long, AtomicLong> partitions = new TreeMap<>();
             partitions.put(N, new AtomicLong(1L));
 
             long leftSide = -1;
             long rightSide = -1;
             for (int i = 0; i < K; i++) {
                 Map.Entry<Long, AtomicLong> entry = partitions.lastEntry();
                 long size = entry.getKey();
                 AtomicLong count = entry.getValue();
 
                 long newSize = size - 1;
                 leftSide = newSize / 2;
                 rightSide = newSize - leftSide;
                 addPartition(partitions, leftSide);
                 addPartition(partitions, rightSide);
                 if (count.decrementAndGet() == 0) {
                     partitions.remove(size);
                 }
             }
             return rightSide + " " + leftSide;
         }
 
         private void addPartition(TreeMap<Long, AtomicLong> partitions, long size) {
             AtomicLong atomicLong = partitions.get(size);
             if (atomicLong == null) {
                 atomicLong = new AtomicLong(0);
                 partitions.put(size, atomicLong);
             }
             atomicLong.incrementAndGet();
         }
     }
 
     private static class Pair implements Comparable<Pair> {
         private final long N;
         private final long K;
 
         public Pair(long n, long k) {
             N = n;
             K = k;
         }
 
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             Pair pair = (Pair) o;
             return N == pair.N && K == pair.K;
 
         }
 
         @Override
         public int hashCode() {
             int result = (int) (N ^ (N >>> 32));
             result = 31 * result + (int) (K ^ (K >>> 32));
             return result;
         }
 
         @Override
         public int compareTo(Pair o) {
             int compare = Long.compare(N, o.N);
             if (compare != 0) {
                 return compare;
             }
             return Long.compare(K, o.K);
         }
     }
 }
