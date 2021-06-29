
 
 
 import java.util.*;
 import java.io.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
 	
     }
 
     
     private int input() {
 	int ret=0;
 	String com1;
 	int T=0;
 
 	if (stdin.hasNextInt()) {
 	    T = stdin.nextInt();
 	} else {
 	    return ret;
 	}
 
 	for (int t1=0; t1<T; t1++) { 
 	    long N=0;
 	    long K=0;
 	    if (stdin.hasNextLong()) {
 		N = stdin.nextLong();
 	    } else {
 		return ret;
 	    }
 	    if (stdin.hasNextLong()) {
 		K = stdin.nextLong();
 	    } else {
 		return ret;
 	    }
 
 	    solve1(N,K);
 	}
 	ret=0;
     	return ret;
     }
 
     
     private void solve1(long N, long K) {
 	long[] Ls = new long[(int)N];
 	long[] Rs = new long[(int)N];
 	boolean[] occupied = new boolean[(int)N];
 
 	
 	Arrays.fill(occupied, false);
 	for (int i=0; i<N; i++) {
 	    Ls[i]=i;
 	    Rs[i]=N-i-1;
 	}
 	
 	int targeti = -1;
 	for (long k1 = 0; k1<K; k1++) {
 	    long maxMin = Long.MIN_VALUE;
 	    long maxMax = Long.MIN_VALUE;
 	    
 	    for (int i=0; i<N; i++) {
 		if (occupied[i]) {
 		    continue;
 		}
 		if (maxMin < Math.min(Ls[i], Rs[i])) {
 		    maxMin = Math.min(Ls[i], Rs[i]);
 		    maxMax = Math.max(Ls[i], Rs[i]);
 		    targeti = i;
 		} else if (maxMin == Math.min(Ls[i], Rs[i])
 			   && maxMax < Math.max(Ls[i], Rs[i])) {
 		    maxMax = Math.max(Ls[i], Rs[i]);
 		    targeti = i;
 		}
 	    }
 	    if (targeti != -1) {
 		occupied[targeti]=true;
 		int j=0;
 		for (int i=targeti-1; i>=0 ; i--) {
 		    if (occupied[i]) {
 			break;
 		    }
 		    Rs[i] = j;
 		    j++;
 		}
 		j=0;
 		for (int i=targeti+1; i<N ; i++) {
 		    if (occupied[i]) {
 			break;
 		    }
 		    Ls[i] = j;
 		    j++;
 		}
 	    }
 	}
 	
 	output(Math.max(Ls[targeti], Rs[targeti]), Math.min(Ls[targeti], Rs[targeti]));
     }
 
     int output_N=0;
     
     private void output(long ls, long rs) {
 	output_N++;
 	System.out.format("Case #%1$d: %2$d %3$d%n", output_N, ls, rs);
     }
 
 
     
     public void logInfo(String a, Object... args) {
 	if (logger != null) {
 	    logger.info(String.format(a,args));
 	}
     }
 
     public void begin() {
 	this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
 	if (this.logger.getLevel() != java.util.logging.Level.INFO) {
 	    this.logger = null;
 	}
 	init();
 	while (input()==1) {
 	}
     }
 
     public void unittest() {
 	this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
     }
 
     public static void main (String args[]) {
 	Main myMain = new Main();
 	if (args.length >= 1 && args[0].equals("unittest")) {
 	    myMain.unittest();
 	    return;
 	}
 	java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.SEVERE);
 	for (int i=0; args!=null && i<args.length; i++) {
 	    if (args[i].equals("debug")) {
 		java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.INFO);
 	    }
 	}
 	myMain.begin();
     }
 }
