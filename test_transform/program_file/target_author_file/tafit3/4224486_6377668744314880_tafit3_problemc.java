import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemC {
     BufferedReader rd;
 
     private ProblemC() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         int n = pint();
         for(int i=0;i<n;i++) {
             out("Case #" + (i + 1) + ":");
             solve();
         }
     }
 
     private void solve() throws IOException {
         int n = pint();
         long [][] p = new long [n][];
         for(int i=0;i<n;i++) {
             p[i] = longarr();
         }
         for(int i=0;i<n;i++) {
             int min = Integer.MAX_VALUE;
             for(int j=0;j<n;j++) {
                 if(i!=j) {
                     int over = 0;
                     int under = 0;
                     for(int k=0;k<n;k++) {
                         if(i != k && j != k) {
                             long c = (p[i][0] - p[j][0])*(p[k][1] - p[j][1]) - (p[i][1] - p[j][1])*(p[k][0] - p[j][0]);
                             if(c > 0) {
                                 over++;
                             } else if(c < 0) {
                                 under++;
                             }
                         }
                     }
                     min = Math.min(min, over);
                     min = Math.min(min, under);
                 }
             }
             out(min==Integer.MAX_VALUE?0:min);
         }
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private long[] longarr() throws IOException {
         return longarr(rd.readLine());
     }
 
     private long[] longarr(String s) {
         String[] q = split(s);
         int n = q.length;
         long[] a = new long[n];
         for(int i=0;i<n;i++) {
             a[i] = Long.parseLong(q[i]);
         }
         return a;
     }
 
     private String[] split(String s) {
         int n = s.length();
         int sp = 0;
         for(int i=0;i<n;i++) {
             if(s.charAt(i)==' ') {
                 sp++;
             }
         }
         String[] res = new String[sp+1];
         int last = 0;
         int x = 0;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(c == ' ') {
                 res[x++] = s.substring(last,i);
                 last = i+1;
             }
         }
         res[x] = s.substring(last,n);
         return res;
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemC();
     }
 }
