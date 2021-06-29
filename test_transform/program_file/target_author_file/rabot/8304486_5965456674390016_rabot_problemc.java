
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProblemC {
 	public static void main(String[] args) throws Exception {
 		Scanner sc = new Scanner(System.in);
 		String fileName = sc.next();
 		sc.close();
 		int fileNamePos = fileName.indexOf('.');
 		String outputFileName = null;
 		if (fileNamePos >= 0) {
 			outputFileName = fileName.substring(0, fileNamePos) + ".out.txt";
 		} else {
 			outputFileName = fileName + ".out.txt";
 		}
 
 		String path = ProblemC.class.getResource("").getPath();
 		path = path.substring(0, path.lastIndexOf('/'));
 		path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
 		sc = new Scanner(new File(path + fileName));
 		BufferedWriter bw = new BufferedWriter(
 				new FileWriter(path + outputFileName));
 
 		int T = sc.nextInt();
 		for (int i = 1; i <= T; i++) {
 			int C = sc.nextInt();
 			int[][] E = new int[C][2];
 			int[][] L = new int[C][2];
 			int[][] D = new int[C][2];
 			boolean[][] flg = new boolean[C][2];
 			for (int c = 0; c < C; c++) {
 				E[c][0] = sc.nextInt() - 1;
 				L[c][0] = sc.nextInt();
 				D[c][0] = sc.nextInt();
 				E[c][1] = sc.nextInt() - 1;
 				L[c][1] = sc.nextInt();
 				D[c][1] = sc.nextInt();
 				flg[c][0] = false;
 				flg[c][1] = false;
 			}
 
 			int ans = rec(E, L, D, flg, 0, 0, 0);
 			bw.write("Case #" + i + ": " + ans);
 			bw.write("\r\n");
 		}
 		bw.close();
 		sc.close();
 	}
 
 	private static int rec(int[][] E, int[][] L, int[][] D, boolean[][] flg,
 			int cur, int t, int step) {
 		if (flg[cur][0] && flg[cur][1]) {
 			if (step >= 2 * E.length) {
 				return t;
 			} else {
 				return Integer.MAX_VALUE;
 			}
 		}
 		int time = Integer.MAX_VALUE;
 		for (int i = 0; i <= 1; i++) {
 			if (flg[cur][i]) {
 				continue;
 			}
 			flg[cur][i] = true;
 			time = Math.min(time,
 					rec(E, L, D, flg, E[cur][i],
 							L[cur][i] - (t % 24) + t + D[cur][i]
 									+ ((t % 24) <= L[cur][i] ? 0 : 24),
 							step + 1));
 			flg[cur][i] = false;
 		}
 		return time;
 	}
 }
