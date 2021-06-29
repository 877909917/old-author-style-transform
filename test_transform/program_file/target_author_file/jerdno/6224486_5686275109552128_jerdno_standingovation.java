import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Collection;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.util.Queue;
 import java.util.SortedSet;
 import java.util.TreeSet;
 
 import com.sun.javafx.collections.transformation.SortedList;
 
 public class StandingOvation {
 	public static void main(String[] args) throws Exception{
 		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
 		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
 		List<Integer> list = new LinkedList<Integer>();
 		Queue<Integer> priorityQueue = new PriorityQueue<Integer>(1000001, Collections.reverseOrder());
 		try {
 			String line = br.readLine();
 			int max, act, result, count = Integer.parseInt(line), end, del;
 			for(int i = 0; i < count; i++) {
 				line = br.readLine();
 				max = Integer.parseInt(line);
 				line = br.readLine();
 				priorityQueue.clear();
 				del = 0;
 				end = 0;
 				for (int ii = 0; ii < max; ii++) {
 					priorityQueue.add(parse2(line, ii));
 				}
 				result = priorityQueue.peek();
 				while (priorityQueue.peek() > 1) {
 					priorityQueue.add(priorityQueue.peek() / 2);
 					priorityQueue.add(priorityQueue.peek() % 2 == 0 ? priorityQueue.peek() / 2 : priorityQueue.peek() / 2 + 1);
 					priorityQueue.poll();
 					del++;
 					if (del + priorityQueue.peek() < result)
 						result = del + priorityQueue.peek();
 				}
 				writer.println("Case #" + (i + 1) + ": " + result);
 			}
 		} finally {
 			br.close();
 		}
 		writer.close();
 	}
 
 	private static int parse(String s) {
 		return Integer.parseInt(s.substring(0, s.indexOf(' ')));
 	}
 
 	private static int parse2(String s, int i) {
 		return Integer.parseInt(s.substring(i * 2, i * 2 + 1));
 	}
 }
