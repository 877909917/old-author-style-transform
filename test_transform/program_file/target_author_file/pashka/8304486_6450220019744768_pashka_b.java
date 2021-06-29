
 import java.io.*;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B {
 
     private String solveTest() throws IOException {
         int n = nextInt();
         int m = nextInt();
         init(n, m);
         for (int i = 0; i < m; i++) {
             int x = nextInt() - 1;
             int y = nextInt() - 1;
             addEdge(x, y);
         }
         bad = false;
         for (int i = 0; i < n; i++) {
             dfs(i, -1);
         }
 
         for (int x = 0; x < n; x++) {
             int j = head[x];
             int d = 0;
             while (j >= 0) {
                 d += fl[j] - fl[j ^ 1];
                 j = nx[j];
             }
             if (d != 0) {
                 throw new RuntimeException();
             }
         }
 
         for (int i = 0; i < m; i++) {
             if (fl[2 * i] > 0 && fl[2 * i + 1] > 0) {
                 throw new RuntimeException();
             }
         }
 
         StringBuilder res = new StringBuilder();
         for (int i = 0; i < m; i++) {
             res.append(fl[2 * i] - fl[2 * i + 1]);
             if (fl[2 * i] - fl[2 * i + 1] == 0) {
                 if (!bad)
                     throw new RuntimeException();
                 return "IMPOSSIBLE";
             }
             if (Math.abs(fl[2 * i] - fl[2 * i + 1]) > n * n ) {
                 throw new RuntimeException();
             }
             if (i < m - 1) res.append(" ");
         }
         if (bad) throw new RuntimeException();
         return res.toString();
     }
 
     void init(int n, int m) {
         m *= 2;
         this.n = n;
         this.m = m;
         last = 0;
         head = new int[n];
         nx = new int[m];
         dst = new int[m];
         src = new int[m];
         fl = new int[m];
         inTime = new int[n];
         d = new int[n];
         dd = new int[n];
         Arrays.fill(head, -1);
         z = new boolean[n];
     }
 
     void addEdge(int x, int y) {
         nx[last] = head[x];
         src[last] = x;
         dst[last] = y;
         head[x] = last;
         last++;
         nx[last] = head[y];
         src[last] = y;
         dst[last] = x;
         head[y] = last;
         last++;
     }
 
     int time = 0;
 
     boolean bad;
 
     private void dfs(int x, int ee) {
 
         if (z[x]) return;
         z[x] = true;
         inTime[x] = time++;
         dd[x] = inTime[x];
         int j = head[x];
         while (j >= 0) {
             int y = dst[j];
             if (z[y]) {
                 if (ee != (j ^ 1) && inTime[y] < inTime[x]) {
 
                     dd[x] = Math.min(dd[x], inTime[y]);
                     fl[j] = 1;
                     d[x]++;
                     d[y]--;
                 }
             } else {
                 dfs(y, j);
                 if (dd[y] > inTime[x]) {
                     bad = true;
                 }
                 dd[x] = Math.min(dd[x], dd[y]);
                 fl[j] = d[y];
                 d[x] += d[y];
             }
             j = nx[j];
         }
     }
 
     int n, m;
     int[] head;
     int[] nx;
     int[] src;
     int[] dd;
     int[] fl;
     int[] dst;
     boolean[] z;
     int[] inTime;
     int[] d;
     int last;
 
 
     private void solve() throws IOException {
         int n = nextInt();
         for (int i = 0; i < n; i++) {
             String res = solveTest();
             System.out.println("Case #" + (i + 1) + ": " + res);
             out.println("Case #" + (i + 1) + ": " + res);
         }
     }
 
 
     BufferedReader br;
     StringTokenizer st;
     PrintWriter out;
 
     String next() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             st = new StringTokenizer(br.readLine());
         }
         return st.nextToken();
     }
 
     int nextInt() throws IOException {
         return Integer.parseInt(next());
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         new B().run();
     }
 
     private void run() throws FileNotFoundException {
         br = new BufferedReader(new FileReader(this.getClass().getSimpleName().substring(0, 1) + ".in"));
         out = new PrintWriter(this.getClass().getSimpleName().substring(0, 1) + ".out");
         try {
             solve();
         } catch (IOException e) {
             e.printStackTrace();
         }
         out.close();
     }
 
 }