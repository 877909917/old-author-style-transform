import java.util.HashMap;
 import java.util.Scanner;
 
 public class Main {
 
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int T = sc.nextInt();
 		for (int i = 1; i <= T; i++) {
 			HashMap<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
 			boolean[] flag = new boolean[sc.nextInt()];
 			String[] topic1 = new String[flag.length], topic2 = new String[flag.length];
 			for (int j = 0; j < flag.length; j++) {
 				topic1[j] = sc.next();
 				topic2[j] = sc.next();
 				map1.put(topic1[j], map1.containsKey(topic1[j]) ? map1.get(topic1[j]) + 1 : 1);
 				map2.put(topic2[j], map2.containsKey(topic2[j]) ? map2.get(topic2[j]) + 1 : 1);
 			}
 			
 			int count = 0;
 			while (true) {
 				boolean s = false;
 				for (int j = 0; j < flag.length; j++) {
 					if (flag[j]) {
 						continue;
 					}
 					if (map1.get(topic1[j]) > 1 && map2.get(topic2[j]) > 1) {
 						count++;
 						flag[j] = s = true;
 						map1.put(topic1[j], map1.get(topic1[j]) - 1);
 						map2.put(topic2[j], map2.get(topic2[j]) - 1);
 						break;
 					}
 				}
 				if (s) {
 					continue;
 				} else {
 					break;
 				}
 			}
 			System.out.println("Case #" + i + ": " + count);
 		}
 		sc.close();
 	}
 }