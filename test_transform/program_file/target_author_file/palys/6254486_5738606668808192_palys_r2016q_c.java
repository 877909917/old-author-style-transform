package r2016.roundq;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class R2016Q_C {
 	
 	private final static String CASE = "Case #%d: ";
 	
 	private int t;
 	
 	public static void main(final String[] args) {
 		new R2016Q_C().solve();
 	}
 	
 	public void solve(final Scanner in, final PrintStream out) throws IOException {
 		readNumberCases(in);
 		for (int i = 0; i < numberCases(); i++) {
 			printCaseLabel(i, out);
 			readCase(in);
 			solveCase();
 			printOutput(out);
 			out.println();
 		}
 	}
 
 	private String folder() {
 		return this.getClass().getSimpleName() + "_cases";
 	}
 
 	private String inFolder() {
 		return folder() + "/in";
 	}
 	
 	private String outFolder() {
 		return folder() + "/out";
 	}
 	
 	private void ensureDirectoryExists(final File f) {
 		if (!f.isDirectory()) {
 			f.mkdir();
 		}
 	}
 	
 	private String filePath(final String file, final String dir) {
 		return dir + "/" + file;
 	}
 	
 	private String caseLabel(final int n) {
 		return String.format(CASE, n + 1);
 	}
 	
 	private void printCaseLabel(final int n, final PrintStream out) {
 		out.print(caseLabel(n));
 	}
 	
 	private void solve() {
 		
 		final File directory = new File(folder());
 		final File inDirectory = new File(inFolder());
 		final File outDirectory = new File(outFolder());
 		
 		ensureDirectoryExists(directory);
 		ensureDirectoryExists(outDirectory);
 		ensureDirectoryExists(inDirectory);
 		
 		for (final String in : inDirectory.list()) {
 			final String fileNameCore = in.replace(".in", "");
 			final String outFileName = fileNameCore + ".out";
 			
 			final File inFile = new File(filePath(in, inFolder()));
 			final File outFile = new File(filePath(outFileName, outFolder()));
 			try (final PrintStream ps =  new PrintStream(outFile);
 					final Scanner s = new Scanner(inFile)){
 				solve(s, ps);
 			} catch (final IOException e) {
 				e.printStackTrace();
 			} 
 		}
 	}
 	
 	private int numberCases() {
 		return t;
 	}
 	
 	private void readNumberCases(final Scanner in) {
 		t = in.nextInt();
 	}
 	
 	private int N;
 	
 	private int J;
 	
 	private String[] numbers;
 	
 	private int[][] divisors;
 	
 	private long curent;
 	
 	private void readCase(final Scanner in) {
 		N = in.nextInt();
 		J = in.nextInt();
 	}
 	
 	private void solveCase() {
 		curent = convert("1000000000000001", 2);
 		numbers = new String[J];
 		divisors = new int[J][];
 		int i = 0;
 		while(i < J) {
 			String n = nextNumber();
 			if (isJamcoin(n)) {
 				numbers[i] = n;
 				divisors[i] = divisors(n);
 				i++;
 			}
 		}
 	}
 	
 	private String nextNumber() {
 		long l = curent++;
 		return Long.toBinaryString(l);
 	}
 	
 	private boolean isJamcoin(String n) {
 		if (n.charAt(n.length() - 1) == '0') {
 			return false;
 		}
 		for (int base = 2; base <= 10; base++) {
 			long number = convert(n, base);
 			if (isPrime(number)) {
 				return false;
 			}
 		}
 		return true;
 	}
 	
 	private long convert(String n, int base) {
 		long number = 0;
 		for (int i = 0; i < n.length(); i++) {
 			number *= base;
 			number += n.charAt(i) - '0';
 		}
 		return number;
 	}
 	
 	private boolean isPrime(long number) {
 		if (number == 1) {
 			return false;
 		}
 		if (number == 2) {
 			return true;
 		}
 		if (number % 2 == 0) {
 			return false;
 		}
 		for (long i = 3l; i * i <= number; i+=2) {
 			if (number % i == 0) {
 				return false;
 			}
 		}
 		return true;
 	}
 	
 	private int divisor(long number) {
 		if (number % 2 == 0) {
 			return 2;
 		}
 		for (int i = 3;; i+=2) {
 			if (number % i == 0) {
 				return i;
 			}
 		}
 	}
 	
 	private int[] divisors(String n) {
 		int[] div = new int[9];
 		for (int i = 2; i <= 10; i++) {
 			long number = convert(n, i);
 			div[i - 2] = divisor(number);
 		}
 		return div;
 	}
 	
 	private void printOutput(final PrintStream out) {
 		out.println();
 		for (int i = 0; i < J; i++) {
 			out.print(numbers[i]);
 			for (int j = 0; j < divisors[i].length; j++) {
 				out.print(" " + divisors[i][j]);
 			}
 			out.println();
 		}
 	}
 }
