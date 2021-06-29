import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.AbstractCollection;
 import java.util.StringTokenizer;
 import java.io.BufferedReader;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "C-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified())
                     toRun = candidate;
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("c.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             int N = in.nextInt();
             int Q = in.nextInt();
             int[] horseE = new int[N];
             int[] horseS = new int[N];
             for (int i = 0; i < N; i += 1) {
                 horseE[i] = in.nextInt();
                 horseS[i] = in.nextInt();
             }
             int[][] graph = new int[N][N];
             for (int i = 0; i < N; i += 1) {
                 for (int j = 0; j < N; j += 1) {
                     graph[i][j] = in.nextInt();
                 }
             }
             for (int q = 0; q < Q; q += 1) {
                 int u = in.nextInt() - 1;
                 int v = in.nextInt() - 1;
                 PriorityQueue<TaskC.Horse> queue = new PriorityQueue<>();
                 queue.add(new TaskC.Horse(0.0, horseE[u], horseS[u], u));
                 while (!queue.isEmpty()) {
                     TaskC.Horse cur = queue.poll();
                     if (cur.city == v) {
                         out.println(cur.t);
                         return;
                     }
                     for (int i = 0; i < N; i += 1) {
                         int distance = graph[cur.city][i];
                         if (distance > -1) {
                             if (cur.e >= distance) {
                                 queue.add(new TaskC.Horse(cur.t + distance / cur.s, cur.e - distance, cur.s, i));
                             }
                             if (horseE[cur.city] >= distance) {
                                 queue.add(new TaskC.Horse(cur.t + distance / (double) horseS[cur.city], horseE[cur.city] - distance, horseS[cur.city], i));
                             }
                         }
                     }
                 }
             }
         }
 
         static class Horse implements Comparable<TaskC.Horse> {
             double t;
             double e;
             double s;
             int city;
 
             public Horse(double t, double e, double s, int city) {
                 this.t = t;
                 this.e = e;
                 this.s = s;
                 this.city = city;
             }
 
 
             public int compareTo(TaskC.Horse o) {
                 if (this.t < o.t) return -1;
                 else if (this.t > o.t) return 1;
                 return 0;
             }
 
         }
 
     }
 
     static class InputReader {
         public BufferedReader reader;
         public StringTokenizer tokenizer;
 
         public InputReader(InputStream stream) {
             reader = new BufferedReader(new InputStreamReader(stream));
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
 
