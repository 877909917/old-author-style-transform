import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 import java.util.Map;
 import java.util.HashMap;
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.InputStream;
 
 
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
 
     static class TaskA {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             String S = in.next();
             int cnt[] = new int[26];
             for (int i = 0; i < S.length(); i++) {
                 cnt[S.charAt(i) - 'A']++;
             }
 
             String digits[] = new String[]{"ZERO", "SIX", "EIGHT", "FOUR", "FIVE", "TWO", "THREE", "SEVEN", "ONE", "NINE"};
 
             Map<String, Integer> map = new HashMap<>();
 
             for (String digit : digits) {
                 while (possible(digit, cnt)) {
                     map.compute(digit, (k, v) -> v == null ? 1 : v + 1);
                     decrease(cnt, digit);
                 }
             }
             String sorted[] = new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
             StringBuilder sb = new StringBuilder();
             for (int k = 0; k < sorted.length; k++) {
                 String s = sorted[k];
                 if (!map.containsKey(s)) continue;
                 for (int i = 0; i < map.get(s); i++) {
                     sb.append(k);
                 }
             }
             out.println("Case #" + testNumber + ": " + sb.toString());
         }
 
         private void decrease(int[] cnt, String digit) {
             int c[] = new int[26];
             for (int i = 0; i < digit.length(); i++) {
                 c[digit.charAt(i) - 'A']++;
             }
             for (int i = 0; i < 26; i++) {
                 cnt[i] -= c[i];
             }
         }
 
         private boolean possible(String digit, int[] cnt) {
             int c[] = new int[26];
             for (int i = 0; i < digit.length(); i++) {
                 c[digit.charAt(i) - 'A']++;
             }
             for (int i = 0; i < 26; i++) {
                 if (c[i] > cnt[i]) return false;
             }
             return true;
         }
 
     }
 
     static class InputReader {
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
 
     }
 }
 
