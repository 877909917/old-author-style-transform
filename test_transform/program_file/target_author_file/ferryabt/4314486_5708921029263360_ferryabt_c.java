package abtric.round1c;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.List;
 
 import abtric.utility.Writer;
 
 public class C {
 
 	private static int done = 0;
 
 	public static void main(String[] args) {
 		
 		String stringPath = "C-small-attempt0.in";
 		
 		try {
 			final List<String> inputFile = Files
 					.readAllLines(FileSystems.getDefault().getPath("in\\2016\\1C\\C", stringPath));
 			
 			final int T = Integer.parseInt(inputFile.get(0));
 			done = 0;
 			Object lock = new Object();
 			String[] results = new String[T];
 			for (int i = 0; i < T; i++) {
 				final int I = i;
 				Runnable runner = new Runnable() {
 
 					@Override
 					public void run() {
 						final int J = Integer.parseInt(inputFile.get(I + 1).split(" ")[0]);
 						final int P = Integer.parseInt(inputFile.get(I + 1).split(" ")[1]);
 						final int S = Integer.parseInt(inputFile.get(I + 1).split(" ")[2]);
 						final int K = Integer.parseInt(inputFile.get(I + 1).split(" ")[3]);
 
 						ArrayList<String> solutions = new ArrayList<>();
 						int[][] JP = new int[J][P];
 						int[][] JS = new int[J][S];
 						int[][] PS = new int[P][S];
 						for (int j = 1; j <= J; j++) {
 							for (int p = 1; p <= P; p++) {
 								if (JP[j - 1][p - 1] == K) {
 									continue;
 								}
 								for (int s = 1; s <= S; s++) {
 									if (JP[j - 1][p - 1] == K) {
 										continue;
 									}
 									if (JS[j - 1][s - 1] == K) {
 										continue;
 									}
 									if (PS[p - 1][s - 1] == K) {
 										continue;
 									}
 									solutions.add(Integer.toString(j) + " " + Integer.toString(p) + " "
 											+ Integer.toString(s));
 									JP[j - 1][p - 1]++;
 									JS[j - 1][s - 1]++;
 									PS[p - 1][s - 1]++;
 								}
 							}
 						}
 
 						String solution = Integer.toString(solutions.size());
 						for (String s : solutions) {
 							solution += "\n" + s;
 						}
 
 						results[I] = solution;
 						synchronized (lock) {
 							done++;
 						}
 					}
 				};
 				runner.run();
 			}
 			while (done < T) {
 				try {
 					Thread.sleep(500);
 				} catch (InterruptedException e) {
 					e.printStackTrace();
 				}
 			}
 			
 			Writer.write("out\\C-small.out", results);
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}
 }
