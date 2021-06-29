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
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         out.println("Case #" + testNumber + ":");
         int N = in.nextInt();
         Point[] points = new Point[N];
         int left = 0;
         for (int i = 0; i < N; i++) {
             points[i] = new Point(in.nextInt(), in.nextInt(), i);
             if (i > 0 && points[i].x < points[left].x) {
                 left = i;
             }
         }
         
         int[] res = new int[N];
         for (int i = 0; i < N; i++) res[i] = N;
         for (int i = 0; i < N; i++) {
             for (int j = i+1; j < N; j++) {
                 int l = 0;
                 int r = 0;
                 for (int k = 0; k < N; k++) {
                     if (k == i || k == j) {
                         continue;
                     }
                     int o = Orientation(points[i], points[j], points[k]);
                     if (o == 1) {
                         r++;
                     } else if (o == -1) {
                         l++;
                     }
                 }
                 int rm = Math.min(l,r);
                 res[i] = Math.min(res[i], rm);
                 res[j] = Math.min(res[j], rm);
             }
         }
         for (int i = 0; i < N; i++) {
             out.println(res[i]);
         }
     }
 
     int Orientation(Point p1, Point p2, Point p)
     {
         
         int Orin = (p2.x - p1.x) * (p.y - p1.y) - (p.x - p1.x) * (p2.y - p1.y);
 
         if (Orin > 0)
             return -1; 
         if (Orin < 0)
             return 1; 
 
         return 0; 
     }
     class Point {
         int x;
         int y;
         int idx;
 
         Point(int x, int y, int idx) {
             this.x = x;
             this.y = y;
             this.idx = idx;
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
 
