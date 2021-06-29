import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 
 public class C_small {
 	
 	public static void main(String[] args){
 		Scanner sc = new Scanner(System.in);
 		
 		final int T = sc.nextInt();
 		for(int tt = 1; tt <= T; tt++){
 			final int N = sc.nextInt();
 			
 			String[] fst_topic = new String[N];
 			String[] snd_topic = new String[N];
 			for(int i = 0; i < N; i++){
 				fst_topic[i] = sc.next();
 				snd_topic[i] = sc.next();
 			}
 			
 			int answer = 0;
 			LOOP:
 			for(int faker_bit = 0; faker_bit < (1 << N); faker_bit++){
 				HashSet<String> not_fake_fst_set = new HashSet<String>();
 				HashSet<String> not_fake_snd_set = new HashSet<String>();
 				
 				for(int i = 0; i < N; i++){
 					if((faker_bit & (1 << i)) == 0){
 						not_fake_fst_set.add(fst_topic[i]);
 						not_fake_snd_set.add(snd_topic[i]);
 					}
 				}
 				
 				for(int i = 0; i < N; i++){
 					if((faker_bit & (1 << i)) != 0){
 						if(!not_fake_fst_set.contains(fst_topic[i]) || !not_fake_snd_set.contains(snd_topic[i])){
 							continue LOOP;
 						}
 					}
 				}
 				
 				
 				
 				answer = Math.max(answer, Integer.bitCount(faker_bit));
 			}
 			
 			System.out.printf("Case #%d: %d\n", tt, answer);
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
