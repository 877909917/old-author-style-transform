package ra;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 import java.io.PrintWriter;
 
 public class p3 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         int N = Integer.parseInt(br.readLine());
 
         boolean[][]bff = new boolean[N][N];
         StringTokenizer st = new StringTokenizer(br.readLine());
         for (int i=0;i<N;i++) {
             int ni = Integer.parseInt(st.nextToken())-1;
             bff[i][ni] = true;
         }
         List<List<Integer>> perms = perms(N);
         
         int maxi = 0;
         for (List<Integer> p : perms) {
             
             for (int i=0;i<p.size();i++) {
                 int v2 = p.get(i);
                 boolean hasBff = false;
                 if (i > 0) {
                     int v1 = p.get(i-1);
                     if(bff[v2][v1]) {
                         maxi = Math.max(maxi, i+1);
                         hasBff = true;
                     }
                 }
                 int v1 = p.get(0);
                 if(bff[v2][v1]) {
                     maxi = Math.max(maxi, i+1);
                 }
 
                 if (i < p.size()-1) {
                     int vz = p.get(i+1);
                     hasBff |= bff[v2][vz];
                 }
                 if (!hasBff) {
                     break;
                 }
 
 
 
 
 
             }
         }
 
         return "" + maxi;
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 
     static Map<Integer, List<List<Integer>>> permC = new HashMap<>();
 
     private static List<List<Integer>> perms(int N) {
         if (permC.get(N) != null) {
             return permC.get(N);
         }
         List<Integer> z = new ArrayList<>();
         for (int i=0;i<N;i++) {
             z.add(i);
         }
         List<List<Integer>> perms = perms(z);
         permC.put(N, perms);
         return perms;
     }
 
     private static List<List<Integer>> perms(List<Integer> ai) {
 
         List<List<Integer>> res = new ArrayList<>();
 
         if (ai.size()==1) {
             List<Integer> f = new ArrayList<>();
             f.add(ai.get(0));
             res.add(f);
             return res;
         }
 
         for (int i=0;i<ai.size();i++) {
             List<Integer> a2 = new ArrayList<>(ai);
             a2.remove(ai.get(i));
             List<List<Integer>> perms = perms(a2);
             for (List<Integer> p : perms) {
                 p.add(ai.get(i));
                 res.add(p);
             }
         }
 
         return res;
     }
 
 }