package gcj2015.r2;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.concurrent.CompletionService;
 import java.util.concurrent.ExecutorCompletionService;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class C implements Runnable {
 	static final boolean LARGE = false;
 	static final boolean PROD = true;
 	static final int NTHREAD = 7;
 	static String BASEPATH = "c:\\temp\\gcj2015\\r2\\";
 
 	
 	static String INPATH = BASEPATH + C.class.getSimpleName().charAt(0) + (LARGE ? "-large.in" : "-small-attempt0.in");
 
 	static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
 	
 	static String INPUT = "";
 	
 	List<Set<String>> bags;
 	
 	public void read() 
 	{
 		int n = ni();
 		in.nextLine();
 		bags = new ArrayList<Set<String>>();
 		for(int i = 0;i < n;i++){
 			Set<String> bag = new HashSet<String>();
 			for(String u : in.nextLine().split("\\s+")){
 				bag.add(u);
 			}
 			bags.add(bag);
 		}
 	}
 	
 	public void process() 
 	{
 		int n = bags.size();
 		int min = 999999999;
 		for(int i = 0;i < 1<<n-2;i++){
 			Set<String> eng = new HashSet<String>(bags.get(0));
 			Set<String> fre = new HashSet<String>(bags.get(1));
 			for(int j = 0;j < n-2;j++){
 				if(i<<~j<0){
 					eng.addAll(bags.get(j+2));
 				}else{
 					fre.addAll(bags.get(j+2));
 				}
 			}
 			eng.retainAll(fre);
 			min = Math.min(min, eng.size());
 		}
 		out.println(min);
 	}
 	
 	public static void preprocess()
 	{
 	}
 	
 	Scanner in;
 	PrintWriter out;
 	StringWriter sw;
 	int cas;
 	static List<Status> running = new ArrayList<Status>();
 	
 	@Override
 	public void run()
 	{
 		long S = System.nanoTime();
 		
 		synchronized(running){
 			Status st = new Status();
 			st.id = cas;
 			st.S = S;
 			running.add(st);
 		}
 		process();
 		
 		synchronized(running){
 			for(Status st : running){
 				if(st.id == cas){
 					running.remove(st);
 					break;
 				}
 			}
 		}
 		long G = System.nanoTime();
 		
 		if(PROD){
 			System.err.println("case " + cas + " solved. [" + (G-S)/1000000 + "ms]");
 			synchronized(running){
 				StringBuilder sb = new StringBuilder("running : ");
 				for(Status st : running){
 					sb.append(st.id + ":" + (G-st.S)/1000000 + "ms, ");
 				}
 				System.err.println(sb);
 			}
 		}
 	}
 	
 	private static class Status {
 		public int id;
 		public long S;
 	}
 	
 	public C(int cas, Scanner in)
 	{
 		this.cas = cas;
 		this.in = in;
 		this.sw = new StringWriter();
 		this.out = new PrintWriter(this.sw);
 	}
 	
 	private int ni() { return Integer.parseInt(in.next()); }
 	private long nl() { return Long.parseLong(in.next()); }
 	private int[] na(int n) { int[] a = new int[n]; for(int i = 0;i < n;i++)a[i] = ni(); return a; }
 	private double nd() { return Double.parseDouble(in.next()); }
 	private void tr(Object... o) { if(!PROD)System.out.println(Arrays.deepToString(o)); }
 	
 	public static void main(String[] args) throws Exception
 	{
 		long start = System.nanoTime();
 		
 		ExecutorService es = Executors.newFixedThreadPool(NTHREAD);
 		CompletionService<C> cs = new ExecutorCompletionService<C>(es);
 		
 		if(PROD){
 			System.out.println("INPATH : " + INPATH);
 			System.out.println("OUTPATH : " + OUTPATH);
 		}
 		Scanner in = PROD ? new Scanner(new File(INPATH)) : new Scanner(INPUT);
 		PrintWriter out = PROD ? new PrintWriter(new File(OUTPATH)) : new PrintWriter(System.out);
 		int n = in.nextInt();
 		in.nextLine();
 		
 		preprocess();
 		for(int i = 0;i < n;i++){
 			C runner = new C(i+1, in);
 			runner.read();
 			cs.submit(runner, runner);
 		}
 		es.shutdown();
 		String[] outs = new String[n];
 		for(int i = 0;i < n;i++){
 			C runner = cs.take().get(); 
 			runner.out.flush();
 			runner.out.close();
 			outs[runner.cas-1] = runner.sw.toString();
 		}
 		for(int i = 0;i < n;i++){
 			out.printf("Case #%d: ", i+1);
 			out.append(outs[i]);
 			out.flush();
 		}
 		
 		long end = System.nanoTime();
 		System.out.println((end - start)/1000000 + "ms");
 		if(PROD){
 			System.out.println("INPATH : " + INPATH);
 			System.out.println("OUTPATH : " + OUTPATH);
 		}
 	}
 }
