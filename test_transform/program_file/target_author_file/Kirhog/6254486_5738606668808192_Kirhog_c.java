package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.math.BigInteger;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class C {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     private static final int PRIMES_COUNT = 50;
     private static final long[] PRIMES = new long[PRIMES_COUNT];
 
 
     void solve() {
 
         PRIMES[0] = 2;
         for (int i = 1, n = 3; i < 50; ) {
             boolean prime = true;
             for (int k = 0; k < i; ++k) {
                 if (n % PRIMES[k] == 0) {
                     prime = false;
                     break;
                 }
             }
 
             if (prime) {
                 PRIMES[i++] = n;
             }
 
             n += 2;
         }
 
         int n = sc.nextInt();
         int j = sc.nextInt();
 
         out.println();
 
         String zeroes = n > 2 ? String.format("%0" + (n - 2) + "d", 0) : "";
         BigInteger two = BigInteger.valueOf(2);
         long[] tests = new long[11];
         long[] divisors = new long[11];
 
         BigInteger current = new BigInteger("1" + zeroes + "1", 2);
         long test;
         long divisor;
         for (int i = 0; i < j; ) {
             String binary = current.toString(2);
 
             boolean composite = true;
             for (int radix = 2; radix <= 10; ++radix) {
                 test = new BigInteger(binary, radix).longValue();
                 divisor = nontrivialDivisor(test);
                 if (divisor == 0) {
                     composite = false;
                     break;
                 }
 
                 tests[radix] = test;
                 divisors[radix] = divisor;
             }
 
             if (composite) {
                 out.printf(binary);
                 for (int radix = 2; radix <= 10; ++radix) {
                     
                     divisor = divisors[radix];
                     
                     out.printf(" %d", divisor);
                 }
                 out.println();
                 ++i;
             }
 
             current = current.add(two);
         }
     }
 
     private long nontrivialDivisor(long val) {
         long prime;
         for (int i = 0; i < PRIMES_COUNT; ++i) {
             prime = PRIMES[i];
             if (prime >= val) {
                 return 0;
             }
 
             if (val % prime == 0) {
                 return prime;
             }
         }
 
         return 0;
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "C-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             C template = new C();
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
