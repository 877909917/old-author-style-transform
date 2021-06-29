import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.BufferedWriter;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
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
         OutputWriter out = new OutputWriter(outputStream);
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.print("Case #" + testNumber + ": ");
             int count = in.readInt();
             int[] A = IOUtils.readIntArray(in, count);
             long sum = ArrayUtils.sumArray(A);
 
             while (sum > 0) {
                 int maxIdx = ArrayUtils.maxPosition(A);
                 A[maxIdx]--;
                 out.print(LETTERS.charAt(maxIdx));
 
                 boolean haveOther = false;
                 maxIdx = ArrayUtils.maxPosition(A);
 
                 sum = ArrayUtils.sumArray(A);
 
                 if (sum > 0 && A[maxIdx] * 2 > sum) {
                     A[maxIdx]--;
                     out.print(LETTERS.charAt(maxIdx) + " ");
                     sum = ArrayUtils.sumArray(A);
                 } else {
                     out.print(" ");
                 }
             }
 
             out.printLine();
         }
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
         private InputReader.SpaceCharFilter filter;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         public int read() {
             if (numChars == -1)
                 throw new InputMismatchException();
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0)
                     return -1;
             }
             return buf[curChar++];
         }
 
         public int readInt() {
             int c = read();
             while (isSpaceChar(c))
                 c = read();
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9')
                     throw new InputMismatchException();
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c))
                 c = read();
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c))
                     res.appendCodePoint(c);
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null)
                 return filter.isSpaceChar(c);
             return isWhitespace(c);
         }
 
         public static boolean isWhitespace(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public String next() {
             return readString();
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
         }
 
     }
 
     static class IOUtils {
         public static int[] readIntArray(InputReader in, int size) {
             int[] array = new int[size];
             for (int i = 0; i < size; i++)
                 array[i] = in.readInt();
             return array;
         }
 
     }
 
     static class OutputWriter {
         private final PrintWriter writer;
 
         public OutputWriter(OutputStream outputStream) {
             writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
         }
 
         public OutputWriter(Writer writer) {
             this.writer = new PrintWriter(writer);
         }
 
         public void print(Object... objects) {
             for (int i = 0; i < objects.length; i++) {
                 if (i != 0)
                     writer.print(' ');
                 writer.print(objects[i]);
             }
         }
 
         public void printLine() {
             writer.println();
         }
 
         public void print(char i) {
             writer.print(i);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 
     static class ArrayUtils {
         public static long sumArray(int[] array) {
             long result = 0;
             for (int element : array)
                 result += element;
             return result;
         }
 
         public static int maxPosition(int[] array) {
             return maxPosition(array, 0, array.length);
         }
 
         public static int maxPosition(int[] array, int from, int to) {
             if (from >= to)
                 return -1;
             int max = array[from];
             int result = from;
             for (int i = from + 1; i < to; i++) {
                 if (array[i] > max) {
                     max = array[i];
                     result = i;
                 }
             }
             return result;
         }
 
     }
 }
 
