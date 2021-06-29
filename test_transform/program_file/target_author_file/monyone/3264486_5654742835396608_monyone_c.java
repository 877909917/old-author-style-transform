import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.PriorityQueue;
 import java.util.StringTokenizer;
 
 public class C {
 	
 	public static void main(String[] args){
 		Scanner sc = new Scanner(System.in);
 		
 		final int T = sc.nextInt();
 		for(int tt = 1; tt <= T; tt++){
 			final long N = sc.nextLong();
 			final long K = sc.nextLong();
 			
 			HashMap<Long, Long> count = new HashMap<Long, Long>();
 			PriorityQueue<Long> queue = new PriorityQueue<Long>(Collections.reverseOrder());
 			
 			count.put(N, 1l);
 			queue.add(N);
 			
 			long rest = K;
 			while(!queue.isEmpty()){
 				final long range = queue.poll();
 				final long range_count = count.get(range);
 				
 				final long l_range = Math.max(0, (range / 2) - (range % 2 == 0 ? 1 : 0));
 				final long r_range = Math.max(0, (range / 2));
 				
 				
 				if(rest <= range_count){
 					System.out.printf("Case #%d: %d %d\n", tt, Math.max(r_range, l_range), Math.min(r_range, l_range));
 					break;
 				}else{
 					rest -= range_count;
 				}
 				
 				if(l_range > 0){
 					if(!count.containsKey(l_range)){
 						queue.add(l_range);
 						count.put(l_range, range_count);
 					}else{
 						count.put(l_range, count.get(l_range) + range_count);
 					}
 				}
 				
 				if(r_range > 0){
 					if(!count.containsKey(r_range)){
 						queue.add(r_range);
 						count.put(r_range, range_count);
 					}else{
 						count.put(r_range, count.get(r_range) + range_count);
 					}
 				}
 			}
 			
 			
 		}
 	}
 	
 	public static class Scanner implements Closeable {
 		private BufferedReader br;
 		private StringTokenizer tok;
  
 		public Scanner(InputStream is) {
 			br = new BufferedReader(new InputStreamReader(is));
 		}
  
 		private void getLine() {
 			try {
 				while (!hasNext()) {
 					tok = new StringTokenizer(br.readLine());
 				}
 			} catch (IOException e) { 
 			}
 		}
  
 		private boolean hasNext() {
 			return tok != null && tok.hasMoreTokens();
 		}
  
 		public String next() {
 			getLine();
 			return tok.nextToken();
 		}
  
 		public int nextInt() {
 			return Integer.parseInt(next());
 		}
  
 		public long nextLong() {
 			return Long.parseLong(next());
 		}
  
 		public double nextDouble() {
 			return Double.parseDouble(next());
 		}
  
 		public void close() {
 			try {
 				br.close();
 			} catch (IOException e) { 
 			}
 		}
 	}
 }
