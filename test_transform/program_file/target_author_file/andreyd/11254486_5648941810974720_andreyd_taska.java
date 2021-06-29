import java.io.BufferedInputStream;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 
 public class TaskA {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             String s = sc.nextLine();
             TreeMap<Character, Integer> map = new TreeMap<>();
             for (char c : s.toCharArray()) {
                 Integer num = get(map, c);
                 map.put(c, num + 1);
             }
             int[] arr = new int[10];
             arr[0] = get(map, 'Z');
             arr[2] = get(map, 'W');
             arr[4] = get(map, 'U');
             arr[6] = get(map, 'X');
             arr[8] = get(map, 'G');
             arr[1] = get(map, 'O') - arr[0] - arr[2] - arr[4];
             arr[3] = get(map, 'R') - arr[0] - arr[4];
             arr[5] = get(map, 'F') - arr[4];
             arr[7] = get(map, 'S') - arr[6];
             arr[9] = (get(map, 'N') - arr[1] - arr[7])/2;
             
             print(i, arr);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static int get(Map<Character, Integer> map, char c) {
         Integer i = map.get(c);
         return i == null ? 0 : i;
     }
     
     private static void print(int caseNum, int[] answer) {
         StringBuilder sb = new StringBuilder();
         sb.append("Case #").append(caseNum).append(": ");
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < answer[i]; j++) {
                 sb.append(i);
             }
         }
         System.out.println(sb.toString());
     }
     
 }
