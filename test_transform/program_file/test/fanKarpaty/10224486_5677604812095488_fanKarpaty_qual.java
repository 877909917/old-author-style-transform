package facebook;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.util.Arrays;
 
 public class Qual {
 
 	private static BufferedReader br = null;
 	private static int readInt() {
 		try {
 			return Integer.parseInt(br.readLine());
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return 0;
 	}
 	
 	private static double readDouble() {
 		try {
 			return Double.parseDouble(br.readLine());
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return 0;
 	}
 	
 	private static int[] readIntArr() {
 		int[] ret = null;
 		String[] tmp;
 		try {
 			String str = br.readLine();
 			tmp = str.split(" ");
 			ret = new int[tmp.length];
 			for (int i = 0; i < tmp.length; i++)
 				ret[i] = Integer.parseInt(tmp[i]);
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return ret;
 	}
 	
 	private static double[] readDouArr() {
 		double[] ret = null;
 		String[] tmp;
 		try {
 			String str = br.readLine();
 			tmp = str.split(" ");
 			ret = new double[tmp.length];
 			for (int i = 0; i < tmp.length; i++)
 				ret[i] = Double.parseDouble(tmp[i]);
 		} catch (NumberFormatException e) {
 			
 			e.printStackTrace();
 		} catch (IOException e) {
 			
 			e.printStackTrace();
 		}
 		return ret;
 	}
 	
 
 	public static void main(String[] args) throws IOException {
 		
 		br = new BufferedReader(new FileReader(new File("input.txt")));
 		System.setOut(new PrintStream(new File("output.txt")));
 		int T = readInt();
 		
 		
 		for (int ind = 1; ind<=T; ind++) {
 			System.out.print("Case #" + ind + ": ");
 			int[] ar = readIntArr();
 			int n = ar[0];
 			int k = ar[1];
 			
 			double[] p = readDouArr();
 			double best = 0.0;
 			
 			for (int m = (1<<n) -1; m > 0; m--) {
 				int count = 0;
 				for (int b = 0; b < n; b++) {
 					if ((m & (1 << b)) != 0)count++;
 				}
 				if (count != k)continue;
 				
 				double pot = 0.0;
 				for (int mk = (1<<k) - 1; mk > 0; mk--) {
 					int c = 0;
 					for (int b = 0; b < k; b++) {
 						if ((mk & (1 << b)) != 0)c++;
 					}
 					
 					if (c != k / 2) continue;
 					
 					double tmp = 1.0;
 					int b = 0;
 					for (int i = 0; i < k; i++) {
 						while ((m & (1 << b)) == 0)b++;
 						
 						if ((mk & (1 << i)) != 0) {
 							tmp *= p[b];
 						} else {
 							tmp *= 1.0 - p[b];
 						}
 						
 						b++;
 					}
 					
 					pot += tmp;
 				}
 				
 				if (pot > best) best = pot;
 			}
 			
 			System.out.println(best);
 			
 		}
 	}
 
 }
