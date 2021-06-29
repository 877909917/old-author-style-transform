import java.io.InputStreamReader;
 import java.io.IOException;
 import java.util.Locale;
 import java.io.BufferedReader;
 import java.io.OutputStream;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.io.FileInputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 import java.io.FilenameFilter;
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
 }
 
 class TaskC {
     String ijk = "ijk";
     int[][] map = new int[][]{{1, 2, 3, 4}, {2, -1, 4, -3}, {3, -4, -1, 2}, {4, 3, -2, -1}};
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         out.print("Case #" + testNumber + ": ");
         int L = in.nextInt();
         int X = in.nextInt();
         String s = in.next();
         int p = 0;
         int v = 1;
         int sign = 1;
         
         for (; p < L*X; p++) {
             int c = ijk.indexOf(s.charAt(p%L)) + 2;
             v = map[v-1][c-1];
             if (v < 0) {
                 v *= -1;
                 sign *= -1;
             }
             if (v == 2 && sign == 1) {
                 p++;
                 break;
             }
         }
         if (p == L*X) {
             out.println("NO");
             return;
         }
         
         v = 1;
         sign = 1;
         for (; p < L*X; p++) {
             int c = ijk.indexOf(s.charAt(p%L)) + 2;
             v = map[v-1][c-1];
             if (v < 0) {
                 v *= -1;
                 sign *= -1;
             }
             if (v == 3 && sign == 1) {
                 p++;
                 break;
             }
         }
         if (p == L*X) {
             out.println("NO");
             return;
         }
         v = 1;
         sign = 1;
         
         for (; p < L*X; p++) {
             int c = ijk.indexOf(s.charAt(p % L)) + 2;
             v = map[v - 1][c - 1];
             if (v < 0) {
                 v *= -1;
                 sign *= -1;
             }
         }
         if (v == 4 && sign == 1) {
             out.println("YES");
         } else {
             out.println("NO");
         }
     }
 }
 
 class InputReader {
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
 
