import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 import java.io.PrintWriter;
 
 public class Counted {
 
 	int totalCases;
 	Scanner input;
 	String[] caseArray;
 	char[] charDigitsArray;
 	int[] digitsArray;
 	int priorDigit;
 	int presentDigit;
 	PrintWriter writer = new PrintWriter("TidyNumber.out");
 
 	public Counted(File inputFile) throws FileNotFoundException {
 		input = new Scanner(inputFile);
 		totalCases = input.nextInt();
 		input.nextLine();
 		buildCaseArray();
 		
 	}
 	
 	private void buildCaseArray() {
 		caseArray = new String[totalCases];
 		for(int i = 0; i < totalCases; i++){
 			caseArray[i] = input.nextLine();
 		}
 	}
 	
 	public void evaluate() {
 		
 		for(int i = 0; i < totalCases; i++){
 			writer.println("Case #" + (i+1) + ": " + 
 					findHighestTidy(caseArray[i]));
 		}
 		writer.close();
 	}
 	
 	private long findHighestTidy(String t){
 		
 		
 		for(long i = Long.parseLong(t); i > 0; i--){
 			if(checkIfTidy(i)){
 				return i;
 			}
 		}
 		return 1;
 	}
 	
 	private boolean checkIfTidy (long t){
 		
 		
 		makeDigitsArray(Long.toString(t));
 		for (int i = 0; i < digitsArray.length-1; i++){
 			if(digitsArray[i] > digitsArray [i+1]){
 				return false;
 			}
 		}
 		
 		
 		
 		return true;
 	}
 	
 	private void makeDigitsArray (String t){
 		charDigitsArray = new char[t.length()];
 		digitsArray = new int[t.length()];
 		charDigitsArray = t.toCharArray();
 		for(int i = 0; i < t.length(); i++){
 			digitsArray[i] = Character.digit(charDigitsArray[i],10);
 		}
 	}
 }
