package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class B {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     void solve() {
         int n = sc.nextInt();
 
         Horse red = new Horse('R', sc.nextInt());
         int orange = sc.nextInt();
         Horse yellow = new Horse('Y', sc.nextInt());
         int green = sc.nextInt();
         Horse blue = new Horse('B', sc.nextInt());
         int violet = sc.nextInt();
 
         if (orange != 0 || green != 0 || violet != 0) {
             out.printf("NOT SUPPORTED\n");
             return;
         }
 
         Horse[] horses = new Horse[]{red, yellow, blue};
         Arrays.sort(horses, (o1, o2) -> Integer.compare(o1.count, o2.count));
         if (horses[2].count > horses[0].count + horses[1].count) {
             out.printf("IMPOSSIBLE\n");
             return;
         }
 
         char[] stales = new char[n];
         for (int i = 0; i < n; ) {
             Horse h1 = horses[2];
             Horse h2 = horses[1];
             Horse h3 = horses[0];
             if (h1.count > 0) {
                 stales[i++] = h1.color;
                 h1.count--;
             }
             if (h2.count > 0) {
                 stales[i++] = h2.color;
                 h2.count--;
             }
             if (h1.count < h3.count) {
                 stales[i++] = h3.color;
                 h3.count--;
             }
         }
 
         out.printf("%s\n", new String(stales));
     }
 
     static class Horse {
         char color;
         int count;
 
         public Horse(char color, int count) {
             this.color = color;
             this.count = count;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "B-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             B template = new B();
             template.caseNumber = caseNumber;
             template.solve();
             out.flush();
         }
 
         sc.close();
     }
 
     static class CJPrintStream extends PrintStream {
         public CJPrintStream(String fileName) throws FileNotFoundException {
             super(fileName);
         }
 
         @SuppressWarnings("NullableProblems")
         @Override
         public PrintStream printf(String format, Object... args) {
             System.out.printf(format, args);
             return super.printf(format, args);
         }
 
         @Override
         public void println() {
             System.out.println();
             super.println();
         }
 
         @Override
         public void flush() {
             System.out.flush();
             super.flush();
         }
     }
 }
