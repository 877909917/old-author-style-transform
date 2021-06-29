package round1a;
 
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class C {
 	static List<Integer>[] inv;
 	static int maxLevel(int node, int ignore) {
 		List<Integer> cc = inv[node];
 		int ans = 0;
 		for (Integer c : cc) {
 			if (c != ignore) ans = Math.max(ans, 1 + maxLevel(c, ignore));
 		}
 		return ans;
 	}
 	
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int cases = sc.nextInt();
 		for (int caze = 1; caze <= cases; caze++) {
 			int N = sc.nextInt();
 			int[] bff = new int[N];
 			inv = new ArrayList[N];
 			for (int i = 0; i < inv.length; i++) {
 				inv[i] = new ArrayList<Integer>();
 			}
 			for (int i = 0; i < bff.length; i++) {
 				bff[i] = sc.nextInt() - 1;
 				inv[bff[i]].add(i);
 			}
 			int[] vis = new int[N];
 			int ans = 0;
 			int totvis = 0;
 			while (totvis < N) {
 				int first = -1;
 				int cant = 1;
 				for (int i = 0; i < vis.length; i++) {
 					if (vis[i] == 0) {
 						vis[i] = cant;
 						first = i;
 						totvis++;
 						break;
 					}
 				}
 				int next = first;
 				while (vis[(next = bff[next])] == 0) {
 					cant++;
 					totvis++;
 					vis[next] = cant;
 				}
 				if (vis[next] > 0) ans = Math.max(ans, 1 + cant - vis[next]);
 				vis[first] = -1;
 				next = first;
 				while (vis[(next = bff[next])] != -1) {
 					vis[next] = -1;
 				}
 			}
 			int ans2 = 0;
 			for (int i = 0; i < N; i++) {
 				if (bff[bff[i]] == i ) {
 					ans2 += 1 + maxLevel(i, bff[i]);
 				}
 			}
 			ans = Math.max(ans, ans2);
 			System.out.println("Case #" + caze + ": " + ans);
 		}
 	}
 
 }
