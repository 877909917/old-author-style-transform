import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class A {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static class Pancake implements Comparable<Pancake> {
     public int r;
     public int h;
     public int r() { return r; }
     public int h() { return h; }
     Pancake(int r_, int h_) { r=r_; h=h_; }
     public Pancake clone() { return new Pancake(r,h); }
     public static Comparator<Pancake> cmp = Comparator.comparing(Pancake::r).thenComparing(Pancake::h);
     public int compareTo(Pancake o) { return cmp.compare(this, o); }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       Pancake o = (Pancake)oo;
       return r==o.r&&h==o.h;
     }
     public int hashCode() { return Objects.hash(r,h); }
     public String toString() { return ""+r+","+h; }
     double hArea() { return 2 * Math.PI * r * h; }
     double tArea() { return Math.PI * r * r; }
   }
   public static double maxWithBase(int base, Pancake[] pancakes, int k) {
     double out = pancakes[base].tArea() + pancakes[base].hArea();
     PriorityQueue<Pancake> stack = new PriorityQueue<Pancake>(k, Comparator.comparing(Pancake::hArea));
     for (int i = 1; i < k; i++) {
       stack.add(pancakes[base+i]);
     }
     for (int i = base+k; i < pancakes.length; i++) {
       stack.add(pancakes[i]);
       stack.poll();
     }
     for (Pancake p : stack) {
       out += p.hArea();
     }
     return out;
   }
   public static Object solve() {
     int n = nextInt(), k = nextInt();
     Pancake[] pancakes = new Pancake[n];
     for (int i = 0; i < pancakes.length; i++) { pancakes[i] = new Pancake(nextInt(), nextInt()); }
     Arrays.sort(pancakes, Collections.reverseOrder());
     double max = Double.MIN_VALUE;
     for (int i = 0; i < n-k+1; i++) {
       double tmp = maxWithBase(i, pancakes, k);
       if (tmp > max) {
         max = tmp;
         System.err.println("i:"+(i)+"  ats(pancakes):"+(Arrays.toString(pancakes)));
       }
     }
     return max;
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
