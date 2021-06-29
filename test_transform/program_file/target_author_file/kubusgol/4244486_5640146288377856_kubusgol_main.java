import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.BufferedReader;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 
 
 public class Main {
 	public static void main(String[] args) {
 		Locale.setDefault(Locale.US);
 		InputStream inputStream;
 		try {
 			final String regex = "A-(small|large).*[.]in";
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
 			outputStream = new FileOutputStream("a.out");
 		} catch (IOException e) {
 			throw new RuntimeException(e);
 		}
 		InputReader in = new InputReader(inputStream);
 		PrintWriter out = new PrintWriter(outputStream);
 		TaskA solver = new TaskA();
 		int testCount = Integer.parseInt(in.next());
 		for (int i = 1; i <= testCount; i++)
 			solver.solve(i, in, out);
 		out.close();
 	}
 }
 
 class TaskA {
     boolean grid[][];
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         int R = in.nextInt();
         int C = in.nextInt();
         int W = in.nextInt();
         grid = new boolean[R + 2][C + 2];
         for (int c = 0; c < W; c++) {
             grid[R][C - c] = true;
         }
         int ans = 0;
         for (int r = 1; r < R; r++) {
             ans += (C / W);
         }
         int r = R;
         int c = W;
         int hits = 0;
         ans++;
         while (!grid[r][c]) {
             ans++;
             c += W;
         }
         hits = 1;
         int p = c + 1;
         if (hits != W) {
             c = c - 1;
             if (c > 0 && !grid[r][c]) {
                 ans++;
             } else if (c > 0) {
                 while (hits < W && c > 0 && grid[r][c]) {
                     ans++;
                     hits++;
                     c--;
                 }
                 if (hits < W && c > 0) ans++;
             }
             while (p <= C && grid[r][p]) {
                 ans++;
                 p++;
             }
         }
         out.println("Case #" + testNumber + ": " + ans);
     }
 }
 
 class InputReader {
     public BufferedReader reader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream stream) {
         reader = new BufferedReader(new InputStreamReader(stream), 32768);
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
 
