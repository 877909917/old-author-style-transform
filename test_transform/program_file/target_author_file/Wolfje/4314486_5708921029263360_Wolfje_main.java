import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 import java.util.HashMap;
 import java.util.BitSet;
 
 
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
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         static HashMap<Integer, Integer> memo = new HashMap<>();
 
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             int jackets = in.nextInt();
             int pants = in.nextInt();
             int shirts = in.nextInt();
             int k = in.nextInt();
             int n = jackets * pants * shirts;
             int[][] pick = new int[n][3];
             int index = 0;
             for (int j = 0; j < jackets; j++)
                 for (int p = 0; p < pants; p++)
                     for (int s = 0; s < shirts; s++) {
                         pick[index++] = new int[]{j, p, s};
                     }
             int bestMask = lookup(jackets, pants, shirts, k);
             int bestValue = Integer.bitCount(bestMask);
             out.printf("Case #%d: %d\n", testNumber, bestValue);
             for (int i = 0; i < n; i++) {
                 if (((1 << i) & bestMask) != 0) {
                     out.printf("%d %d %d\n", pick[i][0] + 1, pick[i][1] + 1, pick[i][2] + 1);
                 }
             }
 
         }
 
         public int lookup(int jackets, int pants, int shirts, int k) {
             k = Math.min(shirts, k);
             int id = 1000 * jackets + 100 * pants + 10 * shirts + k;
             if (memo.containsKey(id)) return memo.get(id);
             int n = jackets * pants * shirts;
             int[][] pick = new int[n][3];
             int index = 0;
             for (int j = 0; j < jackets; j++)
                 for (int p = 0; p < pants; p++)
                     for (int s = 0; s < shirts; s++) {
                         pick[index++] = new int[]{j, p, s};
                     }
             int[][] comboJP = new int[jackets][pants];
             index = 0;
             for (int j = 0; j < jackets; j++)
                 for (int p = 0; p < pants; p++) comboJP[j][p] = index++;
 
             int[][] comboJS = new int[jackets][shirts];
             index = 0;
             for (int j = 0; j < jackets; j++)
                 for (int s = 0; s < shirts; s++) comboJS[j][s] = index++;
             int[][] comboPS = new int[pants][shirts];
             index = 0;
             for (int p = 0; p < pants; p++)
                 for (int s = 0; s < shirts; s++) comboPS[p][s] = index++;
             BitSet impossible = new BitSet(1 << n);
             int[] countJP = new int[jackets * pants];
             int[] countJS = new int[jackets * shirts];
             int[] countPS = new int[pants * shirts];
             int bestMask = 0;
             int bestValue = 0;
             for (int mask = 1; mask < 1 << n; mask++) {
                 if (!impossible.get(mask)) {
                     Arrays.fill(countJP, 0);
                     Arrays.fill(countJS, 0);
                     Arrays.fill(countPS, 0);
                     for (int i = 0; i < n; i++) {
                         if (((1 << i) & mask) != 0) {
                             countJP[comboJP[pick[i][0]][pick[i][1]]]++;
                             countJS[comboJS[pick[i][0]][pick[i][2]]]++;
                             countPS[comboPS[pick[i][1]][pick[i][2]]]++;
                         }
                     }
                     boolean ok = true;
                     for (int c : countJP) ok &= c <= k;
                     for (int c : countJS) ok &= c <= k;
                     for (int c : countPS) ok &= c <= k;
                     if (ok) {
                         if (Integer.bitCount(mask) > bestValue) {
                             bestValue = Integer.bitCount(mask);
                             bestMask = mask;
                         }
                     } else impossible.set(mask);
                 }
                 if (impossible.get(mask)) {
                     for (int i = 0; i < n; i++) impossible.set(mask | (1 << i));
                 }
             }
             memo.put(id, bestMask);
             return bestMask;
         }
 
     }
 }
 
