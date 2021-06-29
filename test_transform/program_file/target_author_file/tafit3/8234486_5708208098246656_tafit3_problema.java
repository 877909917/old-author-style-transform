import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemA {
     BufferedReader rd;
 
     ProblemA() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         int n = pint();
         for(int i=0;i<n;i++) {
             out("Case #" + (i + 1) + ": " + solve());
         }
     }
 
     private String solve() throws IOException {
         int[] a = intarr();
         int r = a[0];
         int c = a[1];
         char[][] d = new char[r][];
         for(int i=0;i<r;i++) {
             d[i] = rd.readLine().trim().toCharArray();
         }
         int[][] e = new int[][] { { 1,0 }, {-1,0}, {0,-1},{0,1}};
         int res = 0;
         for(int i=0;i<r;i++) {
             for(int j=0;j<c;j++) {
                 if(d[i][j] != '.') {
                     boolean[] ok = new boolean[4];
                     boolean one = false;
                     for(int k=0;k<4;k++) {
                         int sr = i+e[k][0];
                         int sc = j+e[k][1];
                         while(sr >= 0 && sr < r && sc >= 0 && sc < c) {
                             if(d[sr][sc]!='.') {
                                 ok[k] = true;
                                 one = true;
                                 break;
                             }
                             sr += e[k][0];
                             sc += e[k][1];
                         }
                     }
                     if(!one) {
                         return "IMPOSSIBLE";
                     }
                     switch(d[i][j]) {
                         case '^':
                             if(!ok[1])res++;
                             break;
                         case 'v':
                             if(!ok[0])res++;
                             break;
                         case '>':
                             if(!ok[3])res++;
                             break;
                         case '<':
                             if(!ok[2])res++;
                     }
                 }
             }
         }
         return Integer.toString(res);
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private int[] intarr() throws IOException {
         return intarr(rd.readLine());
     }
 
     private int[] intarr(String s) {
         String[] q = split(s);
         int n = q.length;
         int[] a = new int[n];
         for(int i=0;i<n;i++) {
             a[i] = Integer.parseInt(q[i]);
         }
         return a;
     }
 
     public String[] split(String s) {
         if(s == null) {
             return new String[0];
         }
         int n = s.length();
         int start = -1;
         int end = 0;
         int sp = 0;
         boolean lastWhitespace = true;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(isWhitespace(c)) {
                 lastWhitespace = true;
             } else {
                 if(lastWhitespace) {
                     sp++;
                 }
                 if(start == -1) {
                     start = i;
                 }
                 end = i;
                 lastWhitespace = false;
             }
         }
         if(start == -1) {
             return new String[0];
         }
         String[] res = new String[sp];
         int last = start;
         int x = 0;
         lastWhitespace = true;
         for(int i=start;i<=end;i++) {
             char c = s.charAt(i);
             boolean w = isWhitespace(c);
             if(w && !lastWhitespace) {
                 res[x++] = s.substring(last,i);
             } else if(!w && lastWhitespace) {
                 last = i;
             }
             lastWhitespace = w;
         }
         res[x] = s.substring(last,end+1);
         return res;
     }
 
     private boolean isWhitespace(char c) {
         return c==' ' || c=='\t';
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemA();
     }
 }
