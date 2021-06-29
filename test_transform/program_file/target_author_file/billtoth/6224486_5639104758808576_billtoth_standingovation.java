import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class StandingOvation {
 
 	int totalCases;
 	Scanner input;
 	TestCase[] caseArray;
 	int result;
 	PrintWriter outputFile;
 	
 	public StandingOvation(File inputFile) throws FileNotFoundException {
 		input = new Scanner(inputFile);
 		totalCases = input.nextInt();
 		input.nextLine();
 		caseArray = new TestCase[totalCases]; 
 		buildCaseArray();
 		
 	}
 	
 	private void buildCaseArray() {
 		for(int i = 0; i < totalCases; i++){
 			String line = input.nextLine();
 			parseCase(i, line);
 		}
 		
 	}
 
 	private void parseCase(int i, String line) {
 		TestCase c = new TestCase(line);
 		caseArray[i] = c;
 	}
 
 	public void evaluate() throws FileNotFoundException {
 		outputFile = new PrintWriter("StandingOvation.out");
 		for (int i = 0; i < caseArray.length; i++){
 			
 					
 			outputFile.println("Case #" + (i+1) + ": " + 
 					caseArray[i].evaluateCase());
 		}
 		outputFile.close();
 		
 	}
 
 }
