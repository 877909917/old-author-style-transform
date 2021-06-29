import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 import static java.lang.Math.*;
 
 public class ProblemA {
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
 		new ProblemA();
 	}
 
 	public ProblemA() throws Exception {
 		BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
 		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
 				getOutFileName())));
 		Scanner scan = new Scanner(in);
 		int maxN = 2000000;
 		int[] dist = new int[maxN + 1];
 		Arrays.fill(dist, Integer.MAX_VALUE);
 		dist[1] = 1;
 		LinkedList<Integer> todo = new LinkedList<Integer>();
 		todo.add(1);
 		while ( todo.size() > 0 ) {
 			int z = todo.pollFirst();
 			if (  z < maxN && dist[z] + 1 < dist[z+1]  ) {
 				dist[z+1] = dist[z] + 1;
 				todo.add(z+1);
 			}
 			int rev = reverse(z);
 			if ( rev <= maxN &&dist[z] + 1 < dist[rev] ) {
 				dist[rev] = dist[z] + 1;
 				todo.add(rev);
 			}
 		}
 		
 		
 		int tests = scan.nextInt();
 		for (int test = 0; test < tests; test++) {
 			int n = scan.nextInt();
 			
 			
 
 			String resultStr = String.format("Case #%d: %d", test + 1, dist[n]);
 			
 
 			System.out.println(resultStr);
 			out.println(resultStr);
 		}
 		out.close();
 		System.out.println("*** in file =  " + getInFileName());
 		System.out.println("*** out file = " + getOutFileName());
 	}
 	
 	public int reverse(int x) {
 		int result = 0;
 		while ( x > 0 ) {
 			int z = x % 10;
 			result = 10*result + z;
 			x /= 10;
 		}
 		return result;
 	}
 }
