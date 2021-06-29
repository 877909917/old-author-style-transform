
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class ProblemA {
 	public static void main(String[] args) throws Exception {
 		String fileName = args[0];
 		ProblemA obj = new ProblemA();
 		obj.solve(fileName);
 	}
 
 	public void solve(String fileName) throws Exception {
 		BufferedReader br = new BufferedReader(new InputStreamReader(
 				this.getClass().getResourceAsStream(fileName)));
 		int sfx = fileName.indexOf('.');
 		BufferedWriter bw = new BufferedWriter(
 				new FileWriter("./src/" + fileName.substring(0, sfx) + ".out"));
 
 		int T = Integer.parseInt(br.readLine());
 		for (int i = 0; i < T; i++) {
 			String str = br.readLine();
 			StringTokenizer token = new StringTokenizer(str, " ");
 
 			int[] num = new int[3];
 			int N = Integer.parseInt(token.nextToken());
 			num[1] = Integer.parseInt(token.nextToken());
 			num[0] = Integer.parseInt(token.nextToken());
 			num[2] = Integer.parseInt(token.nextToken());
 			int total = num[0] + num[1] + num[2];
 
 			String ans = "IMPOSSIBLE";
 			LOOP_J: for (int j = 0; j < Math.pow(3, total); j++) {
 				String tmp = Integer.toString(j, 3);
 				char[] hand = new char[total];
 				int[] player = new int[total];
 				for (int k = 0; k < total; k++) {
 					if (k < total - tmp.length()) {
 						hand[k] = '0';
 					} else {
 						hand[k] = tmp.charAt(
 								k - total + tmp.length());
 					}
 					player[k] = hand[k] - '0';
 				}
 
 				int[] cnt = new int[3];
 				for (int n = 0; n < N; n++) {
 					for (int k = 0; k < Math.pow(2, N - n); k += 2) {
 						if (n == 0) {
 							cnt[player[k]] += 1;
 							cnt[player[k + 1]] += 1;
 						}
 						if (player[k] == player[k + 1]) {
 							continue LOOP_J;
 						} else if ((player[k] - player[k + 1] + 3) % 3 == 2) {
 							player[k / 2] = player[k];
 						} else {
 							player[k / 2] = player[k + 1];
 						}
 					}
 					if (n == 0) {
 						if (num[0] != cnt[0] || num[1] != cnt[1]
 								|| num[2] != cnt[2]) {
 							continue LOOP_J;
 						}
 					}
 				}
 				for (int k = 0; k < total; k++) {
 					switch (hand[k]) {
 					case '0':
 						hand[k] = 'P';
 						break;
 					case '1':
 						hand[k] = 'R';
 						break;
 					case '2':
 						hand[k] = 'S';
 						break;
 					default:
 						break;
 					}
 				}
 				ans = new String(hand);
 				break;
 			}
 
 			bw.write("Case #" + (i + 1) + ": " + ans);
 			bw.write("\r\n");
 		}
 		bw.close();
 		br.close();
 	}
 }
