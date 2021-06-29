import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class B {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static class Horse implements Comparable<Horse> {
     public int cnt;
     public String rep;
     public int cnt() { return cnt; }
     public String rep() { return rep; }
     Horse(int cnt_, String rep_) { cnt=cnt_; rep=rep_; }
     public Horse clone() { return new Horse(cnt,rep); }
     public static Comparator<Horse> cmp = Comparator.comparing(Horse::cnt).thenComparing(Horse::rep);
     public int compareTo(Horse o) { return cmp.compare(this, o); }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       Horse o = (Horse)oo;
       return cnt==o.cnt&&rep.equals(o.rep);
     }
     public int hashCode() { return Objects.hash(cnt,rep); }
     public String toString() { return ""+cnt+","+rep; }
   }
   public static void add(StringBuilder out, Horse tmp) {
     out.append(tmp.rep);
     tmp.cnt--;
   }
   public static void add2(StringBuilder out, Horse a, Horse b, Horse c) {
     Horse[] h = new Horse[]{a,b,c};
     Arrays.sort(h);
     a = h[0]; b = h[1]; c = h[2];
     while (c.cnt > b.cnt && a.cnt > 0) {
       add(out, c);
       add(out, b);
       if (c.cnt > 0 && a.cnt > 0) {
         add(out, c);
         add(out, a);
       }
     }
     if (c.cnt > b.cnt) { throw new Asdf(); }
     while (c.cnt > 0) {
       add(out, c);
       add(out, b);
       if (a.cnt > 0) {
         add(out, a);
       }
     }
   }
   public static class Asdf extends RuntimeException {
   }
   public static Object solve() {
     int n = nextInt(), r = nextInt(), o = nextInt(), y = nextInt(), g = nextInt(), b = nextInt(), v = nextInt();
     StringBuilder out = new StringBuilder();
     try {
       add2(out, new Horse(r, "R"), new Horse(y, "Y"), new Horse(b, "B"));
     } catch (Asdf a) {
       return "IMPOSSIBLE";
     }
     return out;
   }
   public static void main(String[] args) {
     int T = nextInt();
     for (int i = 0; i < T; i++) {
       outWriter.print("Case #"+(i+1)+": ");
       Object tmp = solve();
       if (tmp != null) { outWriter.println(tmp); }
     }
     outWriter.flush();
   }
   public static StringTokenizer tokenizer = null;
   public static String nextLine() {
     try { return buffer.readLine(); } catch (IOException e) { throw new UncheckedIOException(e); }
   }
   public static String next() {
     while (tokenizer == null || !tokenizer.hasMoreElements()) { tokenizer = new StringTokenizer(nextLine()); }
     return tokenizer.nextToken();
   }
   public static int nextInt() { return Integer.parseInt(next()); }
   public static long nextLong() { return Long.parseLong(next()); }
   public static double nextDouble() { return Double.parseDouble(next()); }
 }
