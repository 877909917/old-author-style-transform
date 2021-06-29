import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 public class BFFs {
 
     static int n;
     static boolean[][] g;
     static boolean[] visited;
     static int[] a;
     static int max;
     static boolean ok;
 
     static void dfs(int t, int m) {
         if (ok) return;
         if (t >= m) {
             boolean good = true;
             for (int i = 0; i < m; i++) {
                 if (!g[a[i]][a[(i + 1) % m]] && !g[a[i]][a[(i + m - 1) % m]]) {
                     good = false;
                     break;
                 }
             }
             if (good) {
                 ok = true;
             }
         } else {
             for (int i = 0; i < n; i++)
                 if (!visited[i]) {
                     visited[i] = true;
                     a[t] = i;
                     dfs(t + 1, m);
                     visited[i] = false;
                 }
         }
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("C-small-attempt0.in"));
         PrintStream cout = new PrintStream("C-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             StringBuilder ans = new StringBuilder();
 
             n = cin.nextInt();
             g = new boolean[n][n];
             visited = new boolean[n];
             a = new int[n];
             for (int i = 0; i < n; i++) {
                 int u = i;
                 int v = cin.nextInt() - 1;
                 g[u][v] = true;
             }
 
             for (max = n; max >= 1; max--) {
                 Arrays.fill(visited, false);
                 ok = false;
                 dfs(0, max);
                 if (ok) break;
             }
             ans.append(max);
 
             cout.printf("Case #%d: %s%n", _case, ans.toString());
         }
 
         cin.close();
         cout.close();
     }
 }
