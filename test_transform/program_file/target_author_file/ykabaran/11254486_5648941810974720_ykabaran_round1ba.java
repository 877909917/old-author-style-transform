package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 
 public class Round1BA implements Problem {
 	
 	private static final String[] NUMBERS = {"ZERO","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
 	private static final char[] KEY_CHARS = {'Z','W','X','G','H','S','V','F','O','I'};
 	private static final int[] KEY_NUMBERS = {0, 2, 6, 8, 3, 7, 5, 4, 1, 9};
 	
 	private char[] letters;
 	private int numLetters;
 	private boolean[] used;
 	private List<Integer> allNumbers;
 	private String phoneNumber;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
 		letters = input.readLine().toCharArray();
   }
 
 	private void generatePhoneNumber(){
 		Collections.sort(allNumbers);
 		int numNumbers = allNumbers.size();
 		StringBuilder builder = new StringBuilder();
 		for(int i=0; i<numNumbers; ++i){
 			builder.append(allNumbers.get(i));
 		}
 		phoneNumber = builder.toString();
 	}
 	
 	private void eliminate(String s, int count){
 		if(count == 0){
 			return;
 		}
 		int numSChars = s.length();
 		for(int i=0; i<numSChars; ++i){
 			char currChar = s.charAt(i);
 			int numLeft = count;
 			for(int j=0; j<numLetters; ++j){
 				if(used[j]){
 					continue;
 				}
 				if(letters[j] == currChar){
 					used[j] = true;
 					--numLeft;
 					if(numLeft == 0){
 						break;
 					}
 				}
 			}
 		}
 	}
 	
 	private int countAndEliminate(int n, char keyChar){
 		int count = 0;
 		for(int i=0; i<numLetters; ++i){
 			if(used[i]){
 				continue;
 			}
 			if(letters[i] == keyChar){
 				++count;
 			}
 		}
 		
 		String keyNumber = NUMBERS[n];
 		eliminate(keyNumber, count);
 		return count;
 	}
 	
   @Override
   public void solve() {
 		numLetters = letters.length;
 		used = new boolean[numLetters];
 		for(int i=0; i<numLetters; ++i){
 			used[i] = false;
 		}
 		
 		allNumbers = new ArrayList<>();
 		for(int i=0; i<10; ++i){
 			int num = KEY_NUMBERS[i];
 			char keyChar = KEY_CHARS[i];
 			int count = countAndEliminate(num, keyChar);
 			for(int j=0; j<count; ++j){
 				allNumbers.add(num);
 			}
 		}
 		
 		generatePhoneNumber();
   }
 
   @Override
   public String getSolution() {
     return phoneNumber;
   }
 
 }
