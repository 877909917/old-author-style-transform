
 
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
 	
     }
 
     
     private int input() {
 	int ret=0;
 	String com1;
 	int T;
 
 	if (stdin.hasNextInt()) {
 	    T = stdin.nextInt();
 	} else {
 	    return ret;
 	}
 
 	for (int t1=0; t1<T; t1++) {
 	    int N;
 	    if (stdin.hasNextInt()) {
 		N = stdin.nextInt();
 	    } else {
 		return ret;
 	    }
 	    ArrayList < ArrayList < String > > li = new ArrayList < ArrayList < String > > ();
 	    for (int i=0; i<N; i++) {
 		
 		String S1;
 		if (stdin.hasNext()) {
 		    S1 = stdin.next();
 		} else {
 		    return ret;
 		}
 		String S2;
 		if (stdin.hasNext()) {
 		    S2 = stdin.next();
 		} else {
 		    return ret;
 		}
 		ArrayList<String> t = new ArrayList<String>();
 		t.add(S1);
 		t.add(S2);
 		li.add(t);
 	    }
 	    solve(li);
 	}
 
 	ret=0;
     	return ret;
     }
 
     int solveR_maxFake = 0;
     private boolean solveR(ArrayList <ArrayList < String > > list, ArrayList < ArrayList < String > > original, ArrayList < ArrayList < String > > fake, int index) {
 	if (index >= list.size()) {
 	    HashSet<String> t1 = new HashSet<String>();
 	    HashSet<String> t2 = new HashSet<String>();
 	
 	    for (ArrayList < String > item : original) {
 		t1.add(item.get(0));
 		t2.add(item.get(1));
 	    }
 	    
 	    
 	    boolean checkFlag=true;
 	    for (ArrayList<String> item : fake) {
 		String s1 = item.get(0);
 		String s2 = item.get(1);
 		if (t1.contains(s1) && t2.contains(s2)) {
 		} else {
 		    checkFlag=false;
 		    break;
 		}
 	    }
 	    if (checkFlag) {
 		if (fake.size() > solveR_maxFake) {
 		    solveR_maxFake = fake.size();
 		}
 	    }
 	    return checkFlag;
 	}
 	boolean checkCanOriginal = true;
 	HashSet<String> t1 = new HashSet<String>();
 	HashSet<String> t2 = new HashSet<String>();
 	
 	for (ArrayList < String > item : original) {
 	    t1.add(item.get(0));
 	    t2.add(item.get(1));
 	}
 	if (t1.contains(list.get(index).get(0)) && t2.contains(list.get(index).get(1))) {
 	    
 	} else {
 	    original.add(list.get(index));
 	    solveR(list, original, fake, index+1);
 	    original.remove(list.get(index));
 	}
 	
 	fake.add(list.get(index));
 	solveR(list, original, fake, index+1);
 	fake.remove(list.get(index));
 
 	return false;
     }
     
     
     private void solve(ArrayList < ArrayList < String > > list) {
 	int N = list.size();
 
 	solveR_maxFake = Integer.MIN_VALUE;
 	solveR(list, new ArrayList< ArrayList < String> > (),
 	       new ArrayList < ArrayList < String> > (),
 	       0);
 	
 	
 	output(Integer.toString(solveR_maxFake));
     }
 
     private int output_N=0;
     
     private void output(String X) {
 	output_N++;
 	if (X != null) {
 	    System.out.format("Case #%1$d: %2$s%n",output_N,X);
 	} else {
 	    System.out.format("Case #%1$d: %2$s%n",output_N,X);
 	}	    
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
 
 class Permutation {
     private static <T> void reverse(java.util.List<T> list, int i, int j) {
 	while (i<j) {
 	    java.util.Collections.swap(list,i,j);
 	    i++;
 	    j--;
 	}
     }
     public static <T extends Comparable<? super T> > boolean next_permutation(java.util.List<T> list) {
 	if (list.size()<=1) {
 	    return false;
 	}
 	int i=list.size()-1;
 	while (true) {
 	    int j = i;
 	    i--;
 
 	    if (list.get(i).compareTo(list.get(j))<0) {
 		int k = list.size()-1;
 		while ( !(list.get(i).compareTo(list.get(k))<0) ) {
 		    k--;
 		}
 		java.util.Collections.swap(list,i,k);
 		reverse(list,j,list.size()-1);
 		return true;
 	    }
 
 	    if (i==0) {
 		java.util.Collections.reverse(list);
 		return false;
 	    }
 	}
     }
 
     public static boolean next_ksubset(int n,int[] set) {
 	int i;
 	for (i=set.length-1; i>=0; i--) {
 	    if (set[i]+1 < n-(set.length-1-i)) {
 		break;
 	    }
 	}
 	if (i<0) {
 	    return false;
 	}
 	set[i]++;
 	i++;
 	for (; i<set.length; i++) {
 	    set[i] = set[i-1]+1;
 	}
 	return true;
     }
 
     public static void unittest_permutation() {
 	java.util.List<Integer> list = new java.util.ArrayList<Integer>();
 	for (int i=0; i<4; i++) {
 	    list.add(new Integer(i));
 	}
 	while (true) {
 	    boolean spaceFlag=false;
 	    for (Integer l : list) {
 		if (spaceFlag) {
 		    System.out.print(" ");
 		} else {
 		    spaceFlag=true;
 		}
 		System.out.print(l);
 	    }
 	    System.out.println();
 	    boolean result1 = next_permutation(list);
 	    if (!result1) {
 		break;
 	    }
 	}
     }
 
     public static void unittest_ksubset() {
 	int n=5;
 	int[] set = new int[3];
 	for (int i=0; i<set.length; i++) {
 	    set[i]=i;
 	}
 	while (true) {
 	    boolean spaceFlag=false;
 	    for (int i=0; i<set.length; i++) {
 		if (spaceFlag) {
 		    System.out.print(" ");
 		} else {
 		    spaceFlag=true;
 		}
 		System.out.print(set[i]);
 	    }
 	    System.out.println();
 	    boolean result1 = next_ksubset(n,set);
 	    if (!result1) {
 		break;
 	    }
 	}
 	
     }
 
 
     public static void unittest() {
 	unittest_permutation();
 	unittest_ksubset();
     }
 
 }
