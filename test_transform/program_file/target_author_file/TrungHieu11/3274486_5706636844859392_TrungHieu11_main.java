import java.io.OutputStream;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             inputStream = new FileInputStream("input.txt");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("output.txt");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskB solver = new TaskB();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.print("Case #" + testNumber + ": ");
             int countC = in.readInt();
             int countJ = in.readInt();
             IntPair[] C = new IntPair[countC];
             IntPair[] J = new IntPair[countJ];
 
             for (int i = 0; i < countC; i++) {
                 C[i] = new IntPair(in.readInt(), in.readInt());
             }
 
             for (int i = 0; i < countJ; i++) {
                 J[i] = new IntPair(in.readInt(), in.readInt());
             }
 
             if (C.length == 2) {
                 if (inRange(C)) {
                     out.printLine(2);
                 } else {
                     out.printLine(4);
                 }
             } else if (J.length == 2) {
                 if (inRange(J)) {
                     out.printLine(2);
                 } else {
                     out.printLine(4);
                 }
             } else if (C.length == 1 && J.length == 1) {
                 int next = C[0].second + 720;
                 if (next > 1440) {
                     next -= 1440;
                 }
                 if (next < J[0].first) {
                     out.printLine(2);
                     return;
                 }
 
                 next = J[0].second + 720;
                 if (next > 1440) {
                     next -= 1440;
                 }
                 if (next < C[0].first) {
                     out.printLine(2);
                     return;
                 }
 
                 out.printLine(4);
             } else {
                 out.printLine(4);
             }
         }
 
         private boolean inRange(IntPair[] A) {
             int next = A[0].second + 720;
             if (next > 1440) {
                 next -= 1440;
             }
             if (next < A[1].first) {
                 return true;
             }
 
             next = A[1].second + 720;
             if (next > 1440) {
                 next -= 1440;
             }
             if (next < A[0].first) {
                 return true;
             }
 
             return false;
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
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(objects[i]);
             }
         }
 
         public void close() {
             writer.close();
         }
 
         public void printLine(int i) {
             writer.println(i);
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
             if (numChars == -1) {
                 throw new InputMismatchException();
             }
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0) {
                     return -1;
                 }
             }
             return buf[curChar++];
         }
 
         public int readInt() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c)) {
                     res.appendCodePoint(c);
                 }
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null) {
                 return filter.isSpaceChar(c);
             }
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
 
     static class IntPair implements Comparable<IntPair> {
         public final int first;
         public final int second;
 
         public IntPair(int first, int second) {
             this.first = first;
             this.second = second;
         }
 
 
         public String toString() {
             return "(" + first + "," + second + ")";
         }
 
 
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
 
             IntPair intPair = (IntPair) o;
 
             return first == intPair.first && second == intPair.second;
 
         }
 
 
         public int hashCode() {
             int result = first;
             result = 31 * result + second;
             return result;
         }
 
         public int compareTo(IntPair o) {
             if (first < o.first)
                 return -1;
             if (first > o.first)
                 return 1;
             if (second < o.second)
                 return -1;
             if (second > o.second)
                 return 1;
             return 0;
         }
 
     }
 }
 
