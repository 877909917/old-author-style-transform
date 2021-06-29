package abtric.round1b;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.List;
 
 import abtric.utility.Writer;
 
 public class CloseMatch {
 	public static void main(String[] args) {
 		
 		String stringPath = "B-small-attempt0.in";
 		
 		try {
 			final List<String> inputFile = Files
 					.readAllLines(FileSystems.getDefault().getPath("in\\CloseMatch", stringPath));
 			
 			final int T = Integer.parseInt(inputFile.get(0));
 			String[] results = new String[T];
 			for (int i = 0; i < T; i++) {
 				
 				
 				final String C = inputFile.get(i + 1).split(" ")[0];
 				final String J = inputFile.get(i + 1).split(" ")[1];
 				ArrayList<String> solutions = new ArrayList<>();
 				solve(C, J, solutions);
 				if (solutions.size() > 1) {
 					int min = Integer.parseInt(solutions.get(0).split(" ")[0]);
 					for (int j = 1; j < solutions.size(); j++) {
 						if (Integer.parseInt(solutions.get(j).split(" ")[0]) > min) {
 							solutions.remove(j);
 							j--;
 						} else if (Integer.parseInt(solutions.get(j).split(" ")[0]) < min) {
 							min = Integer.parseInt(solutions.get(j).split(" ")[0]);
 							for (int k = 0; k < j; k++) {
 								solutions.remove(0);
 							}
 							j = 0;
 						}
 					}
 				}
 
 				if (solutions.size() > 1) {
 					int min = Integer.parseInt(solutions.get(0).split(" ")[1]);
 					for (int j = 1; j < solutions.size(); j++) {
 						if (Integer.parseInt(solutions.get(j).split(" ")[1]) > min) {
 							solutions.remove(j);
 							j--;
 						} else if (Integer.parseInt(solutions.get(j).split(" ")[1]) < min) {
 							min = Integer.parseInt(solutions.get(j).split(" ")[1]);
 							for (int k = 0; k < j; k++) {
 								solutions.remove(0);
 							}
 							j = 0;
 						}
 					}
 				}
 				if (solutions.size() != 1) {
 					System.err.println("fail at case " + i);
 				}
 				results[i] = solutions.get(0);
 				
 			}
 			
 			Writer.write("out\\CloseMatch-small.out", results);
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}
 
 	private static void solve(String left, String right, ArrayList<String> solutions) {
 		if (left.contains("?")) {
 			for (int i = 0; i < 10; i++) {
 				solve(left.replaceFirst("\\?", "" + (char) (48 + i)), right, solutions);
 			}
 		} else if (right.contains("?")) {
 			for (int i = 0; i < 10; i++) {
 				solve(left, right.replaceFirst("\\?", "" + (char) (48 + i)), solutions);
 			}
 		} else {
 			int diff = Math.abs(Integer.parseInt(left) - Integer.parseInt(right));
 			if (solutions.isEmpty()) {
 				solutions.add(left + " " + right);
 			} else {
 				String[] otherSolution = solutions.get(0).split(" ");
 				int otherDiff = Math.abs(Integer.parseInt(otherSolution[0]) - Integer.parseInt(otherSolution[1]));
 				if (diff < otherDiff) {
 					solutions.clear();
 					solutions.add(left + " " + right);
 				} else if (diff == otherDiff) {
 					solutions.add(left + " " + right);
 				}
 			}
 		}
 	}
 }
