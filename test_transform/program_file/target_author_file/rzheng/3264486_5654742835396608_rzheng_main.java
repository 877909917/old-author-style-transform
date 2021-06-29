import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class Main {
 
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int T = sc.nextInt();
 		for (int i = 1; i <= T; i++) {
 			int N = sc.nextInt(), K = sc.nextInt(), y = 0, z = 0;
 			PriorityQueue<Stall> queue = new PriorityQueue<Stall>();
 			queue.add(new Stall(0, N + 1));
 			for (int j = 0; j < K; j++) {
 				Stall stall = queue.poll();
 				y = (stall.right - stall.left - 1) / 2;
 				z = (stall.right - stall.left - 2) / 2;
 				queue.add(new Stall(stall.left, stall.left + z + 1));
 				queue.add(new Stall(stall.right - y - 1, stall.right));
 			}
 			System.out.println("Case #" + i + ": " + y + " " + z);
 		}
 		sc.close();
 	}
 
 	private static class Stall implements Comparable<Stall> {
 		private Integer left, right;
 
 		private Stall(int left, int right) {
 			this.left = left;
 			this.right = right;
 		}
 
 		public int compareTo(Stall o) {
 			return right - left == o.right - left ? left.compareTo(o.left)
 					: Integer.valueOf(o.right - o.left).compareTo(right - left);
 		}
 	}
 }