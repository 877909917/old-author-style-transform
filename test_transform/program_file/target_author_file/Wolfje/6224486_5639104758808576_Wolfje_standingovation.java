import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 import static java.lang.Math.*;
 
 public class StandingOvation {
 	String PROBLEM_ID = "problemA";
 
 	enum TestType {
 		EXAMPLE, SMALL, LARGE
 	}
 
 
 	 TestType TYPE = TestType.SMALL;
 
 
 	public String getFileName() {
 		String result = PROBLEM_ID + "_";
 		switch (TYPE) {
 		case EXAMPLE:
 			result += "example";
 			break;
 		case SMALL:
 			result += "small";
 			break;
 		case LARGE:
 			result += "large";
 			break;
 		}
 		return result;
 	}
 
 	public String getInFileName() {
 		return getFileName() + ".in";
 	}
 
 	public String getOutFileName() {
 		return getFileName() + ".out";
 	}
 
 	public static void main(String[] args) throws Exception {
 		new StandingOvation();
 	}
 
 	public StandingOvation() throws Exception {
 		BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
 		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
 				getOutFileName())));
 		Scanner scan = new Scanner(in);
 		int tests = scan.nextInt();
 		for (int test = 0; test < tests; test++) {
 			scan.nextInt();
 			String input = scan.next();
 			int result = 0;
 			int total = 0;
 			for ( int shyness = 0; shyness < input.length(); shyness++) {
 				if ( shyness > total ) result = max(result, shyness - total);
 				total += input.charAt(shyness) - '0';
 			}
 			String resultStr = String.format("Case #%d: %d", test + 1, result);
 			
 
 			System.out.println(resultStr);
 			out.println(resultStr);
 		}
 		out.close();
 		System.out.println("*** in file =  " + getInFileName());
 		System.out.println("*** out file = " + getOutFileName());
 	}
 }
