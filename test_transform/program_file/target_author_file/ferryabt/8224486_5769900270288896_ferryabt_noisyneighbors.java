package Round1B;
 
 import java.util.ArrayList;
 
 import helpers.AbtRicReader;
 import helpers.AbtRicWriter;
 
 public class NoisyNeighbors {
 	public static void main(String[] args) {
 		String file = "C:/Users/Ferry/eclipse/CodeJam/in/B-small-attempt1.in";
 		AbtRicReader reader = new AbtRicReader();
 		int[] cases = reader.getNumberOfCases(file, 1);
 		System.out.println(cases[0]);
 		ArrayList<Integer[]> listOfCases = reader.readIntFile(file);
 		NoisyNeighbors solver = new NoisyNeighbors();
 		String[] solution = new String[cases[0]];
 		for (int i = 0; i < cases[0]; i++) {
 			solution[i] = Integer.toString(solver.solve(listOfCases.get(i)));
 			System.out.println("Case #" + (i + 1) + ": " + solution[i]);
 		}
 		AbtRicWriter writer = new AbtRicWriter();
 		writer.write(
 				"C:/Users/Ferry/eclipse/CodeJam/in/NoisyNeighborsSmall.out",
 				solution);
 	}
 
 	public int solve(Integer[] input) {
 		int R = input[0];
 		int C = input[1];
 		int N = input[2];
 		boolean[][] apps = new boolean[R][C];
 		return solve2(apps, N, 0, -1);
 
 	}
 
 	private int solve2(boolean[][] apps, int n, int first1, int first2) {
 		if (n == 0) {
 			return unhappiness(apps);
 		}
 		int min = Integer.MAX_VALUE;
 		for (int i = first1; i < apps.length; i++) {
 			for (int j = 0; j < apps[0].length; j++) {
 				if (i == first1 && j <= first2) {
 
 				} else {
 					if (!apps[i][j]) {
 						apps[i][j] = true;
 						int tmp = solve2(apps, n - 1, i, j);
 						if (tmp < min) {
 							min = tmp;
 						}
 						apps[i][j] = false;
 					}
 				}
 			}
 		}
 		return min;
 	}
 
 	private static int unhappiness(boolean[][] apps) {
 		int count = 0;
 		for (int i = 0; i < apps.length; i++) {
 			for (int j = 0; j < apps[0].length - 1; j++) {
 				if (apps[i][j] && apps[i][j + 1]) {
 					count++;
 				}
 			}
 		}
 		for (int i = 0; i < apps[0].length; i++) {
 			for (int j = 0; j < apps.length - 1; j++) {
 				if (apps[j][i] && apps[j + 1][i]) {
 					count++;
 				}
 			}
 		}
 		return count;
 	}
 }
