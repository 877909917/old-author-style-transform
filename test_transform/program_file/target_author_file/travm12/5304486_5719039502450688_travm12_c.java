import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class c {
 	public static int[] des;
 	public static int c;
 	public static int r;
 	public static PrintWriter out;
 
 	public static void main(String[] Args) throws Exception {
 		FS sc = new FS(new File("C-small-attempt3.in"));
 		
 		
 		
 		out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
 				"c.out"))));
 		int cc = 0;
 		int t = sc.nextInt();
 		while (t-- > 0) {
 			out.printf("Case #%d: ", ++cc);
 			long hdm = sc.nextInt();
 			long ad = sc.nextInt();
 			long hk = sc.nextInt();
 			long ak = sc.nextInt();
 			long b = sc.nextInt();
 			long d = sc.nextInt();
 			long oo = 1l << 51;
 			long ans = oo;
 
 			
 			long ba = 0;
 			long a1 = oo;
 			for (long i = 0; i < 1000000; i++) {
 				long amt = i * b + ad;
 				if (i + ((hk + amt - 1) / (amt)) < a1) {
 					a1 = i + ((hk + amt - 1) / (amt));
 					ba = i;
 				}
 			}
 
 			if (a1 == 1 || (a1 == 2 && hdm > ak))
 				out.println(a1);
 			else {
 				long wig = (hdm - 1 - ak) / ak;
 				
 				if (wig >= 1) {
 					long tans = (a1 + wig - 3) / wig - 1;
 					if (tans < 0)
 						tans = 0;
 					tans += a1;
 					if (ans > tans)
 						ans = tans;
 				}
 				if (d > 0) {
 					long hd = hdm;
 					long wasted = 0;
 					if (wig <= 0) {
 						wasted++;
 						ak -= d;
 						hd -= ak;
 					}
 					for (long i = 0; hd > 0 && i < 100000; i++) {
 						if (ak <= 0) {
 							if (ans > wasted + a1) {
 								ans = wasted + a1;
 							}
 							break;
 						} else {
 							wig = (hdm - 1 - ak) / ak;
 							if (wig > 1) {
 								long ex = (hd - 1) / ak;
 								long tans = (a1 - ex - 2 + wig) / wig;
 								if (0 > tans)
 									tans = 0;
 								tans += a1 + wasted;
 								if (tans < ans)
 									tans++;
 							}
 							if (i < 100) {
 								if (hd - ak + d <= 0) {
 									hd = hdm - ak;
 									wasted++;
 								}
 								wasted++;
 								ak -= d;
 								hd -= ak;
 
 							} else if (wig == 0) {
 								break;
 							} else {
 								long min = 0;
 								long max = 1000000000;
 								while (max - min != 1) {
 									long mid = (min + max) / 2;
 									long a3 = ak - (mid * d);
 									if (a3 <= 0)
 										max = mid;
 									else {
 										long wig3 = (hdm - 1 - a3) / a3;
 										if (wig == wig3)
 											min = mid;
 										else
 											max = mid;
 									}
 								}
 								long nhd = update(hd, ak, d, max);
 								if (nhd > 0) {
 									wasted += max;
 									hd = nhd;
 									ak -= d * max;
 								} else {
 									long ex = (hd - 1 - ak) / ak;
 									ak -= d * ex;
 									wasted += max;
 									max -= ex;
 									long last = max % wig;
 									hd = update(hdm, ak - (max - last) * d, d,
 											last);
 									ak -= max * d;
 									wasted += max / wig;
 
 								}
 							}
 
 						}
 
 					}
 					if (ans != oo)
 						out.println(ans);
 					else
 						out.println("IMPOSSIBLE");
 				} else {
 					if (ans == oo)
 						out.println("IMPOSSIBLE");
 					else
 						out.println(ans);
 				}
 			}
 
 		}
 		out.close();
 	}
 
 	private static long update(long hd, long ak, long d, long amt) {
 		return hd - (ak * amt) + (d * ((amt * (amt + 1)) / 2));
 	}
 
 	public static class FS {
 		BufferedReader br;
 		StringTokenizer st;
 
 		FS(InputStream in) throws Exception {
 			br = new BufferedReader(new InputStreamReader(in));
 			st = new StringTokenizer(br.readLine());
 		}
 
 		FS(File in) throws Exception {
 			br = new BufferedReader(new FileReader(in));
 			st = new StringTokenizer(br.readLine());
 		}
 
 		String next() throws Exception {
 			if (st.hasMoreTokens())
 				return st.nextToken();
 			st = new StringTokenizer(br.readLine());
 			return next();
 		}
 
 		int nextInt() throws Exception {
 			return Integer.parseInt(next());
 		}
 	}
 }
