import java.util.Scanner;
 
 public class Main {
 
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int T = sc.nextInt();
 		for (int i = 1; i <= T; i++) {
 			Point[] N = new Point[sc.nextInt()];
 			for (int j = 0; j < N.length; j++) {
 				N[j] = new Point(sc.nextInt(), sc.nextInt());
 			}
 			System.out.println("Case #" + i + ":");
 			for (Point a : N) {
 				int min = Integer.MAX_VALUE;
 				for (Point b : N) {
 					int count1 = 0, count2 = 0;
 					if (a != b) {
 						for (Point c : N) {
 							if ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) < 0) {
 								count1++;
 							} else if ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) > 0) {
 								count2++;
 							}
 						}
 						min = Math.min(min, Math.min(count1, count2));
 					}
 				}
 				System.out.println(min == Integer.MAX_VALUE ? 0 : min);
 			}
 		}
 		sc.close();
 	}
 
 	private static class Point {
 		private long x, y;
 
 		private Point(long x, long y) {
 			this.x = x;
 			this.y = y;
 		}
 	}
 }