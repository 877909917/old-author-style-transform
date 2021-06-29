package round1b;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class p2 {
 
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
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int Q = Integer.parseInt(st.nextToken());
         long ei[] = new long[N];
         double si[] = new double[N];
         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             ei[i] = Integer.parseInt(st.nextToken());
             si[i] = Integer.parseInt(st.nextToken());
         }
         long[][] adj = new long[N][N];
         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             for (int j = 0; j < N; j++) {
                 adj[i][j] = Integer.parseInt(st.nextToken());
             }
         }
         for (int i = 0; i < Q; i++) {
             br.readLine();
         }
 
         double[] dp = new double[N];
         Arrays.fill(dp, Long.MAX_VALUE);
         dp[0] = 0;
         for (int i = 0; i < N; i++) {
             
             long cum = 0;
             for (int j = i+1; j < N; j++) {
                 cum += adj[j-1][j];
                 if (cum > ei[i]) {
                     break;
                 }
                 dp[j] = Math.min(dp[j], cum / si[i] + dp[i]);
             }
         }
 
 
         return "" + dp[N-1];
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
 
