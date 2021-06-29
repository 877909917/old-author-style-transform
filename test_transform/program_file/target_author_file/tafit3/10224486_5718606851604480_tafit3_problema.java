import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.HashMap;
 import java.util.Map;
 
 public class ProblemA {
     BufferedReader rd;
     Map<String, Boolean> m = new HashMap<>();
 
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
         int[] q = intarr();
         int n = q[0];
         int r = q[1];
         int p = q[2];
         int s = q[3];
         int len = pow(2,n);
         int all = pow(3,len);
         for(int i=0;i<all;i++) {
             String z = lz(Integer.toString(i,3), len);
             if(check(z) && count(z,'0')==p && count(z,'1')==r && count(z,'2')==s) {
                 return translate(z);
             }
         }
         return "IMPOSSIBLE";
     }
 
     private int count(String z, char c) {
         int s = 0;
         for(int i=0;i<z.length();i++) {
             if(z.charAt(i) == c) {
                 s++;
             }
         }
         return s;
     }
 
     private boolean check(String z) {
         Boolean res = m.get(z);
         if(res == null) {
             if(z.length() == 1) {
                 res = true;
             } else {
                 StringBuilder buf = new StringBuilder();
                 int s = z.length();
                 int i = 0;
                 while (i < s) {
                     int c = z.charAt(i) - '0';
                     int d = z.charAt(i + 1) - '0';
                     if (c == d) {
                         break;
                     } else {
                         if ((c + 1) % 3 == d) {
                             buf.append(z.charAt(i));
                         } else {
                             buf.append(z.charAt(i + 1));
                         }
                     }
                     i += 2;
                 }
                 if (i < s) {
                     res = false;
                 } else {
                     res = check(buf.toString());
                 }
             }
             m.put(z, res);
         }
         return res;
     }
 
     private String translate(String x) {
         StringBuilder buf = new StringBuilder();
         for(int i=0;i<x.length();i++) {
             switch (x.charAt(i)) {
                 case '0':
                     buf.append('P');
                     break;
                 case '1':
                     buf.append('R');
                     break;
                 default:
                     buf.append('S');
             }
         }
         return buf.toString();
     }
 
     private String lz(String x, int k) {
         if(x.length() >= k) {
             return x;
         }
         StringBuilder buf = new StringBuilder();
         for(int i=0;i<k-x.length();i++) {
             buf.append('0');
         }
         buf.append(x);
         return buf.toString();
     }
 
     private int pow(int x, int y) {
         int z = 1;
         for(int i=0;i<y;i++) {
             z *= x;
         }
         return z;
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
